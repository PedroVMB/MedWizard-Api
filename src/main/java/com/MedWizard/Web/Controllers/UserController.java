package com.MedWizard.Web.Controllers;

import com.MedWizard.domain.profile.DataProfile;
import com.MedWizard.domain.user.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<DataListUser> register(@RequestBody @Valid DataCreateUser data, UriComponentsBuilder uriBuilder){
        var user = userService.create(data);
        var uri = uriBuilder.path("/{nomeUser}").buildAndExpand(user.getNomeUsuario()).toUri();
        return ResponseEntity.created(uri).body(new DataListUser(user));
    }

    @GetMapping("/verificar-conta")
    public ResponseEntity<String> verificarEmail(@RequestParam String codigo){
        userService.verifyEmail(codigo);
        return ResponseEntity.ok("Conta verificada com sucesso!");
    }

    @GetMapping("/{nomeUser}")
    public ResponseEntity<DataListUser> exibirPerfil(@PathVariable String nomeUser){
        var User = userService.findByUserName(nomeUser);
        return ResponseEntity.ok(new DataListUser(User));
    }

    @PutMapping("/editar-perfil")
    public ResponseEntity<DataListUser> editarPerfil(@RequestBody @Valid DataEditUser dados, @AuthenticationPrincipal User logado){
        var User = userService.editProfile(logado, dados);
        return ResponseEntity.ok(new DataListUser(User));
    }

    @PatchMapping("alterar-senha")
    public ResponseEntity<Void> alterarSenha(@RequestBody @Valid DataChangePassword dados, @AuthenticationPrincipal User logado){
        userService.changePassword(dados, logado);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/desativar")
    public ResponseEntity<Void> desativarUser(@PathVariable Long id, @AuthenticationPrincipal User logado){
        userService.disableUser(id, logado);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/reativar-conta/{id}")
    public ResponseEntity<Void> reactivateUser(@PathVariable Long id){
        userService.reactivateUser(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("adicionar-perfil/{id}")
    public ResponseEntity<DataListUser> addProfile(@PathVariable Long id, @RequestBody @Valid DataProfile dados){
        var User = userService.addProfile(id, dados);
        return ResponseEntity.ok(new DataListUser(User));
    }
}