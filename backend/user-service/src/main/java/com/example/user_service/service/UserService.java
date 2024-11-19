package com.example.user_service.service;

import com.example.user_service.dto.CreateUserDto;
import com.example.user_service.dto.UserDto;
import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(CreateUserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("El email ya está en uso.");
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword()); // Idealmente, hashea la contraseña
        user = userRepository.save(user);
        return new UserDto(user.getId(), user.getUsername(), user.getName(), user.getLastname(), user.getEmail(), user.getTelephone());
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
        return new UserDto(user.getId(), user.getUsername(), user.getName(), user.getLastname(), user.getEmail(), user.getTelephone());
    }
}

