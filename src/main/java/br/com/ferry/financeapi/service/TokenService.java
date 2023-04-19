package br.com.ferry.financeapi.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class TokenService {

    public String getToken(UserDetails userDetails) {
        return JWT
                .create()
                .withIssuer("Finance API")
                .withSubject(userDetails.getUsername())
                .withExpiresAt(getExpireTime())
                .sign(Algorithm.HMAC256("123456"));
    }

    private Instant getExpireTime() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
