package br.com.lukas.pisos_api.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Value("${api.security.admin.login}")
    private String adminLogin;
    @Value("${api.security.admin.senha}")
    private String adminSenha;

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO dados){
        if(adminLogin.equals(dados.login()) && adminSenha.equals(dados.senha())){
            return tokenService.gerarToken(dados.login());
        }else {
            throw new RuntimeException("Usuário ou senha incorretos");
        }
    }
}
