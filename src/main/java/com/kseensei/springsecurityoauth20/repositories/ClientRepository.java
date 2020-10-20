package com.kseensei.springsecurityoauth20.repositories;

import com.kseensei.springsecurityoauth20.entity.Client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}