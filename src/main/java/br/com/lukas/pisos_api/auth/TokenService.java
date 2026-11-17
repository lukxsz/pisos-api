package br.com.lukas.pisos_api.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(String login){
        Algorithm algoritmo = Algorithm.HMAC256(secret);

        return JWT.create()
                .withIssuer("pisos-api")
                .withSubject(login)
                .withExpiresAt(Instant.now().plus(8, ChronoUnit.HOURS))
                .sign(algoritmo);
    }

    public String validarToken(String token){
        try{
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("pisos-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (Exception e){
            return null;
        }
    }

}
