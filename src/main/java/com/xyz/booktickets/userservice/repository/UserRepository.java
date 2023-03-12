package com.xyz.booktickets.userservice.repository;

import com.xyz.booktickets.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserNameEqualsIgnoreCase(String userName);
}
