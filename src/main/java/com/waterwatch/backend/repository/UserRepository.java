package com.waterwatch.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.waterwatch.backend.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);
}
