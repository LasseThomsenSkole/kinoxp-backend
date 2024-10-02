package org.example.kinoxpbackend.controller;

import org.example.kinoxpbackend.model.Role;
import org.example.kinoxpbackend.security.AuthenticationRequest;
import org.example.kinoxpbackend.model.User;
import org.example.kinoxpbackend.repository.UserRepository;
import org.example.kinoxpbackend.security.UserPrincipal;
import org.example.kinoxpbackend.service.CustomUserDetailsService;
import org.example.kinoxpbackend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        System.out.println("Registering user: " + user.getUsername()); // Add logging
        // Encode the user's password
        user.setUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        // Save the user to the database
        userRepository.save(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        final UserPrincipal userPrincipal = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        return jwtUtil.generateToken(userPrincipal);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}