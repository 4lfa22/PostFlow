package com.example.user_service.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;

import java.util.Date;

public class JWTUtil {

    private static final String SECRET_KEY = "413F4428472B4B6250655368566D5970337336763979244226452948404D6351";  // Clave secreta
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora

    // Método para generar el token JWT
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)  // El "subject" es generalmente el nombre de usuario
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // Establecer tiempo de expiración
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)  // Firma con la clave secreta
                .compact();
    }

    // Método para extraer los claims del token (por ejemplo, el nombre de usuario)
    public static Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Método para extraer el nombre de usuario del token
    public static String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Método para verificar si el token ha expirado
    public static boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // Método para validar el token
    public static boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        return (username.equals(extractedUsername) && !isTokenExpired(token));
    }
}
