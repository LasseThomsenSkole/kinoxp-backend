package org.example.kinoxpbackend.service;

import org.example.kinoxpbackend.repository.UserRepository;
import org.example.kinoxpbackend.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.kinoxpbackend.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user.getUsername(), user.getPassword(), new ArrayList<>()); //DET HER VIRKER IKKE
    }
}