package com.hms.hms.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;


@Service
public class JWTService {

    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private static String issuer;

    @Value("${jwt.expiry.duration}")
    private static int expiryTime;

    private static Algorithm algorithm;

    @PostConstruct
    public void postConstruct() throws UnsupportedEncodingException {

        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    public static String generateToken(String username){
        return JWT.create()
                .withClaim("name",username)
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryTime))
                .withIssuer(issuer)
                .sign(algorithm);
    }

}