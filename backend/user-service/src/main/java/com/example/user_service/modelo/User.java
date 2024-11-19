package com.example.user_service.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellidos;
    @Column(nullable = false, unique = true)
    private String correo;
    @Column(length = 9, nullable = false)
    private String telefono;
    @Column(nullable = false)
    private String contrasena;
}
