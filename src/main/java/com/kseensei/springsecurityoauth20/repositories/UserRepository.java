package com.kseensei.springsecurityoauth20.repositories;

import java.util.Optional;

import com.kseensei.springsecurityoauth20.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
    
}
