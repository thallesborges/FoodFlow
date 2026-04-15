package com.foodflow.FoodFlow.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
    private Long id;
    private String nome;
    private String email;
}
