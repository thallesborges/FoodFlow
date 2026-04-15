package com.foodflow.FoodFlow.controller;

import com.foodflow.FoodFlow.dto.RegisterUserRequest;
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

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        return userService.registerUser(registerUserRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

}
