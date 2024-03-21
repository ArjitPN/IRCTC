package com.myblog2.myblog2.repository;

import com.myblog2.myblog2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);//it will find user by email
    Optional<User> findByUsernameOrEmail(String username, String email);//it will find user by eather email or name
    Optional<User> findByUsername(String username);//it will find user by username
    Boolean existsByUsername(String username);//it will check username wheather it present or not
    Boolean existsByEmail(String email);//it will check email wheather it present or not
}
