package com.myblog2.myblog2.repository;

import com.myblog2.myblog2.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
    //it will give Role based on name
    //in sql-->Select * From Role Where name=""
}
