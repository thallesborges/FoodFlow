package com.foodflow.FoodFlow.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserPatchRequest(
        @NotBlank
        @Email
        String email,

        @NotBlank
        String senha
) {}
