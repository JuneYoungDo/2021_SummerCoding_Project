package com.example.springProject.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// @Repository -> jpa는 알아서 설정해줌                 // 객체, 식별자
public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByName(String name);

    Optional<User> findByUsername(String username);
}
