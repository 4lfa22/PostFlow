package com.example.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;   // El JWT generado
    private String message; // Opcional: mensaje adicional

    // Constructor que solo recibe el token
    public LoginResponse(String token) {
        this.token = token;
    }
}
