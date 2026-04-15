package com.foodflow.FoodFlow.service;

import com.foodflow.FoodFlow.database.entity.UserEntity;
import com.foodflow.FoodFlow.database.repository.UserRepository;
import com.foodflow.FoodFlow.dto.RegisterUserRequest;
import com.foodflow.FoodFlow.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse registerUser(RegisterUserRequest registerUserRequest) {
        if (userRepository.existsByEmail(registerUserRequest.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        String senhaHash = passwordEncoder.encode(registerUserRequest.getSenha());

        UserEntity newUser = UserEntity.builder()
                .nome(registerUserRequest.getNome())
                .email(registerUserRequest.getEmail())
                .senha(senhaHash)
                .build();

        UserEntity savedUser = userRepository.save(newUser);

        return UserResponse.builder()
                .id(savedUser.getId())
                .nome(savedUser.getNome())
                .email(savedUser.getEmail())
                .build();
    }

    public UserResponse findUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return UserResponse.builder()
                .id(userEntity.getId())
                .nome(userEntity.getNome())
                .email(userEntity.getEmail())
                .build();
    }
}
