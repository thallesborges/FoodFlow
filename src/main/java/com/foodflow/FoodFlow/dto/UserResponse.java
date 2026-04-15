package com.foodflow.FoodFlow.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

public record UserResponse (
        @NotNull
        Long id,

        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email
) {}
