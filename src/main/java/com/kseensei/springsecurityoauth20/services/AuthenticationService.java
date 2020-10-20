package com.kseensei.springsecurityoauth20.services;

import java.util.Optional;

import com.kseensei.springsecurityoauth20.entity.User;
import com.kseensei.springsecurityoauth20.repositories.ClientRepository;
import com.kseensei.springsecurityoauth20.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = usersRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return user.get();
    }

}
