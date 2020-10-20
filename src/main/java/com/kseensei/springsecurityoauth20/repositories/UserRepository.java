package com.kseensei.springsecurityoauth20.repositories;

import com.kseensei.springsecurityoauth20.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
