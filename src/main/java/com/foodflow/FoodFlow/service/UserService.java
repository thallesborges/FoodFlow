package com.foodflow.FoodFlow.service;

import com.foodflow.FoodFlow.database.entity.UserEntity;
import com.foodflow.FoodFlow.database.repository.UserRepository;
import com.foodflow.FoodFlow.dto.CreateUserRequest;
import com.foodflow.FoodFlow.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse createUser(CreateUserRequest createUserRequest) {
        if (userRepository.existsByEmail(createUserRequest.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        UserEntity newUser = UserEntity.builder()
                .nome(createUserRequest.getNome())
                .email(createUserRequest.getEmail())
                .senha(createUserRequest.getSenha())
                .build();

        UserEntity savedUser = userRepository.save(newUser);

        return UserResponse.builder()
                .id(savedUser.getId())
                .nome(savedUser.getNome())
                .email(savedUser.getEmail())
                .build();
    }


}
