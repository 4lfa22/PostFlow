package com.example.user_service.dto;

import com.example.user_service.model.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String name;
    private String lastname;
    private String email;
    private String telephone;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.telephone = user.getTelephone();
    }


    // Constructor, Getters y Setters
}

