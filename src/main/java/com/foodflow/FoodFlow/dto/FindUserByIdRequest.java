package com.foodflow.FoodFlow.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindUserByIdRequest {

    @NotNull
    @Positive
    private Long id;


}