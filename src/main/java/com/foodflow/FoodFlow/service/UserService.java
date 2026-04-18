package com.foodflow.FoodFlow.service;

import com.foodflow.FoodFlow.database.entity.UserEntity;
import com.foodflow.FoodFlow.database.repository.UserRepository;
import com.foodflow.FoodFlow.dto.RegisterUserRequest;
import com.foodflow.FoodFlow.dto.UserPatchRequest;
import com.foodflow.FoodFlow.dto.UserResponse;
import com.foodflow.FoodFlow.exception.EmailAlreadyExistsException;
import com.foodflow.FoodFlow.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse registerUser(RegisterUserRequest registerUserRequest) {
        if (userRepository.existsByEmail(registerUserRequest.email())) {
            throw new EmailAlreadyExistsException("Email já cadastrado");
        }

        String senhaHash = passwordEncoder.encode(registerUserRequest.senha());

        UserEntity newUser = UserEntity.builder()
                .nome(registerUserRequest.nome())
                .email(registerUserRequest.email())
                .senha(senhaHash)
                .build();

        UserEntity savedUser = userRepository.save(newUser);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getNome(),
                savedUser.getEmail()
        );
    }

    public UserResponse findUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        return new UserResponse(
                userEntity.getId(),
                userEntity.getNome(),
                userEntity.getEmail()
        );
    }

    public UserResponse updateUser(Long id, UserPatchRequest userPatchRequest) {
        UserEntity userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        if (userPatchRequest.email() != null) userToUpdate.setEmail(userPatchRequest.email());
        if (userPatchRequest.senha() != null) {
            String senhaHash = passwordEncoder.encode(userPatchRequest.senha());
            userToUpdate.setSenha(senhaHash);
        }

        userRepository.save(userToUpdate);

        return new UserResponse(
                userToUpdate.getId(),
                userToUpdate.getNome(),
                userToUpdate.getEmail()
        );
    }

    public List<UserResponse> findAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(user.getId(), user.getNome(), user.getEmail()))
                .toList();
    }
}
