
package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dtos.CadastroResponseDTO;
import br.com.alunoonline.api.model.Usuario;
import br.com.alunoonline.api.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/cadastros")
    public ResponseEntity<?> cadastrar(
            @RequestBody Usuario usuario) {

        if (usuario.getLogin() == null || usuario.getLogin().isBlank()
                || usuario.getSenha() == null || usuario.getSenha().isBlank()) {
            return ResponseEntity.badRequest().body("Login e senha são obrigatórios.");
        }

        if (usuarioRepository.existsByLogin(usuario.getLogin())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Login já cadastrado.");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setLogin(usuario.getLogin());
        novoUsuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        usuarioRepository.save(novoUsuario);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CadastroResponseDTO(
                        novoUsuario.getId(),
                        novoUsuario.getLogin()
                ));
    }
}
