package com.foodflow.FoodFlow.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


public record  FindUserByIdRequest (
        @NotNull
        @Positive
        Long id
) {}