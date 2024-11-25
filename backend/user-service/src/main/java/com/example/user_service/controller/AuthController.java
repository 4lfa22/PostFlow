package com.example.user_service.controller;

import com.example.user_service.dto.LoginRequest;
import com.example.user_service.dto.LoginResponse;
import com.example.user_service.model.User;
import com.example.user_service.service.UserService;
import com.example.user_service.util.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()).isPresent() ||
                userService.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(400).body("Username or email already exists");
        }

        try {
            User savedUser = userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully: " + savedUser.getUsername());
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error during registration: " + e.getMessage());
        }
    }


    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Buscar usuario por username
        Optional<User> optionalUser = userService.findByUsername(loginRequest.getUsername());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Validar la contraseña
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                // Generar JWT si las credenciales son correctas
                String token = JWTUtil.generateToken(user.getUsername());
                return ResponseEntity.ok(new LoginResponse(token));
            }
        }

        // Credenciales inválidas
        return ResponseEntity.status(401).body("Invalid username or password");
    }
}
