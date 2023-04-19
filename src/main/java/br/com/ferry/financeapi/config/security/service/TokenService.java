package br.com.ferry.financeapi.config.security.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String getToken(UserDetails userDetails) {
        return JWT
                .create()
                .withIssuer("Finance API")
                .withSubject(userDetails.getUsername())
                .withExpiresAt(getExpireTime())
                .sign(Algorithm.HMAC256(secret));
    }

    public String getSubject(String tokenJWT) {
        return JWT
                .require(Algorithm.HMAC256(secret))
                .withIssuer("Finance API")
                .build()
                .verify(tokenJWT)
                .getSubject();
    }

    private Instant getExpireTime() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
