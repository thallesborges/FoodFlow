package com.foodflow.FoodFlow.controller;

import com.foodflow.FoodFlow.database.entity.UserEntity;
import com.foodflow.FoodFlow.dto.CreateUserRequest;
import com.foodflow.FoodFlow.dto.UserResponse;
import com.foodflow.FoodFlow.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }

}
