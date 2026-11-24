
package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.dtos.AutenticacaoRequestDTO;
import br.com.alunoonline.api.dtos.TokenResponseDTO;
import br.com.alunoonline.api.model.Usuario;
import br.com.alunoonline.api.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AutenticacaoController(
            AuthenticationManager authenticationManager,
            TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody AutenticacaoRequestDTO dados) {

        if (dados.login() == null || dados.login().isBlank()
                || dados.senha() == null || dados.senha().isBlank()) {
            return ResponseEntity.badRequest()
                    .body("Login e senha são obrigatórios.");
        }

        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    dados.login(),
                                    dados.senha()
                            )
                    );

            String token = tokenService.gerarToken(
                    (Usuario) authentication.getPrincipal()
            );

            return ResponseEntity.ok(new TokenResponseDTO(token));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Login ou senha inválidos.");
        }
    }
}
