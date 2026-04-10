package edu.icet.service;

import io.jsonwebtoken.Claims;

public interface JwtService {

    String generateToken(String email, String name, String role);
    String extractEmail(String token);
    boolean isTokenValid(String token, String userEmail);
    Claims extractAllClaims(String token);
}
