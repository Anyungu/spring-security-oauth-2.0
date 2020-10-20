package com.kseensei.springsecurityoauth20.services;

import java.util.Optional;

import com.kseensei.springsecurityoauth20.entities.Client;
import com.kseensei.springsecurityoauth20.entities.User;
import com.kseensei.springsecurityoauth20.exceptions.SecurityExceptionHandler;
import com.kseensei.springsecurityoauth20.repositories.ClientRepository;
import com.kseensei.springsecurityoauth20.repositories.UserRepository;
import com.kseensei.springsecurityoauth20.requests.UserRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository usersRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = usersRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return user.get();
    }

    /**
     * 
     * @param user
     * @return
     */
    public User registerUser(String clientId, String clientSecret, UserRequest userRequest)
            throws SecurityExceptionHandler {

        Optional<Client> client = clientRepository.findByClientIdAndClientSecret(clientId, clientSecret);

        if (!client.isPresent()) {
            throw new SecurityExceptionHandler("Client is non existent");
        }

        userRequest.setPassword(this.passwordEncoder.encode(userRequest.getPassword()));
        
        User user = new User();
        user.setAccountNonExpired(userRequest.getAccountNonExpired());
        user.setAccountNonLocked(userRequest.getAccountNonLocked());
        user.setCredentialsNonExpired(userRequest.getCredentialsNonExpired());
        user.setEnabled(userRequest.getEnabled());
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        usersRepository.save(user);
        return user;
    }

}
