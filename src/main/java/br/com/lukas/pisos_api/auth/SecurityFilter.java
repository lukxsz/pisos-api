package br.com.lukas.pisos_api.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.antlr.v4.runtime.Token;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;

    public SecurityFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    private String recuperarToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null){
            return null;
        }
        return  authHeader.replace("Bearer ","");
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recuperarToken(request);
        if (token != null){
            String login = tokenService.validarToken(token);
            if(login != null){
                var autenticacao = new UsernamePasswordAuthenticationToken(login,null, List.of());
                SecurityContextHolder.getContext().setAuthentication(autenticacao);
            }
        }
        filterChain.doFilter(request, response);
    }
}
