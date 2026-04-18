package com.foodflow.FoodFlow.controller;

import com.foodflow.FoodFlow.database.entity.UserEntity;
import com.foodflow.FoodFlow.dto.RegisterUserRequest;
import com.foodflow.FoodFlow.dto.UserPatchRequest;
import com.foodflow.FoodFlow.dto.UserResponse;
import com.foodflow.FoodFlow.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        return userService.registerUser(registerUserRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateEmail(@Valid @PathVariable Long id,
                                  @RequestBody UserPatchRequest userPatchRequest) {
        return userService.updateUser(id, userPatchRequest);
    }

}
