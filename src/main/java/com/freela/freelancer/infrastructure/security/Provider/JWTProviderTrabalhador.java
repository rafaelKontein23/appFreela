package com.freela.freelancer.infrastructure.security.Provider;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTProviderTrabalhador {

    @Value("${security.token.secret.trabalhador}")
    private String keySecret;

    public DecodedJWT validToken(String token) {
        token = token.replace("Bearer ", "");
        Algorithm algorithm = Algorithm.HMAC256(keySecret);

        try {
            var decodeToken = JWT.require(algorithm).build().verify(token);
            return decodeToken;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
