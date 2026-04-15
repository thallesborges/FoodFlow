package com.foodflow.FoodFlow.database.repository;

import com.foodflow.FoodFlow.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
}
