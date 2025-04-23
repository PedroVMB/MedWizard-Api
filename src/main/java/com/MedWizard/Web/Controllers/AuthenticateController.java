package com.MedWizard.Web.Controllers;


import com.MedWizard.domain.authentication.DataLogin;
import com.MedWizard.domain.authentication.DataRefreshToken;
import com.MedWizard.domain.authentication.DataToken;
import com.MedWizard.domain.authentication.TokenService;
import com.MedWizard.domain.user.User;
import com.MedWizard.domain.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticateController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    public AuthenticateController(AuthenticationManager authenticationManager, TokenService tokenService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }


    @PostMapping("/login")
    public ResponseEntity<DataToken> efetuarLogin(@Valid @RequestBody DataLogin dados){
        var autenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = authenticationManager.authenticate(autenticationToken);

        String tokenAcesso = tokenService.generateToken((User) authentication.getPrincipal());
        String refreshToken = tokenService.generateRefreshToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new DataToken(tokenAcesso, refreshToken));
    }

    @PostMapping("/atualizar-token")
    public ResponseEntity<DataToken> atualizarToken(@Valid @RequestBody DataRefreshToken dados){
        var refreshToken = dados.refreshToken();
        Long idUsuario = Long.valueOf(tokenService.verifyToken(refreshToken));
        var usuario = userRepository.findById(idUsuario).orElseThrow();

        String tokenAcesso = tokenService.generateToken(usuario);
        String tokenAtualizacao = tokenService.generateRefreshToken(usuario);

        return ResponseEntity.ok(new DataToken(tokenAcesso, tokenAtualizacao));
    }
}
