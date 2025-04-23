package com.MedWizard.domain.user;

import com.MedWizard.domain.profile.DataProfile;
import com.MedWizard.domain.profile.ProfileName;
import com.MedWizard.domain.profile.ProfileRepository;
import com.MedWizard.infrastructure.email.EmailService;
import com.MedWizard.infrastructure.exception.BusinnesRuleException;
import com.MedWizard.infrastructure.security.HierarchyService;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final ProfileRepository profileRepository;
    private final HierarchyService hierarchyServicel;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService, ProfileRepository profileRepository, HierarchyService hierarchyServicel) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.profileRepository = profileRepository;
        this.hierarchyServicel = hierarchyServicel;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmailIgnoreCaseAndVerificadoTrue(username)
                .orElseThrow(() -> new UsernameNotFoundException("O usuário não foi encontrado!"));
    }

    @Transactional
    public User create(DataCreateUser data) {
        var encryptedPassword = passwordEncoder.encode(data.senha());

        var profile = profileRepository.findByNome(ProfileName.ADMINISTRADOR);
        var user = new User(data, encryptedPassword, profile);

        emailService.SendEmailVerification(user);
        return userRepository.save(user);
    }

    @Transactional
    public void verifyEmail(String code) {
        var user = userRepository.findByToken(code).orElseThrow();
        user.verificar();
    }

    public User findByUserName(String userName) {
        return userRepository.findByNomeUsuarioIgnoreCaseAndVerificadoTrueAndAtivoTrue(userName).orElseThrow(
                () -> new BusinnesRuleException("Usuário não encontrado!"));
    }

    @Transactional
    public User editProfile(User user, DataEditUser data) {
        return user.alterarDados(data);
    }

    @Transactional
    public void changePassword(DataChangePassword data, User logged) {
        if(!passwordEncoder.matches(data.currentPassword(), logged.getPassword())){
            throw new BusinnesRuleException("Senha digitada não confere com senha atual!");
        }

        if(!data.newPassword().equals(data.newPasswordConfirmation())){
            throw new BusinnesRuleException("Senha e confirmação não conferem!");
        }

        String encryptedPassword = passwordEncoder.encode(data.newPassword());
        logged.alterarSenha(encryptedPassword);
    }

    @Transactional
    public void disableUser(Long id, User logged) {
        var user = userRepository.findById(id).orElseThrow();

        if(hierarchyServicel.userNotHavePermission(logged, user, "ROLE_ADMIN"))
            throw new AccessDeniedException("Não é possivel realizar essa operação!");

        user.disable();
    }

    @Transactional
    public User addProfile(Long id, DataProfile data) {
        var user = userRepository.findById(id).orElseThrow();
        var perfil = profileRepository.findByNome(data.profileName());
        user.adicionarPerfil(perfil);
        return user;
    }

    @Transactional
    public void reactivateUser(Long id) {
        var user = userRepository.findById(id).orElseThrow();
        user.reactivate();
    }
}
