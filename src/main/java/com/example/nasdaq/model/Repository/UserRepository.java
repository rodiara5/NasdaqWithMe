package com.example.nasdaq.model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.nasdaq.model.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{
    
    @Query(value = "select * from user where user_id = :userId", nativeQuery = true)
    public UserEntity getUserByName(@Param(value = "userId") String userId);
}

