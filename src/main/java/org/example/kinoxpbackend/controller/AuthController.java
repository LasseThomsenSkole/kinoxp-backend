package org.example.kinoxpbackend.controller;

import org.example.kinoxpbackend.model.Role;
import org.example.kinoxpbackend.security.AuthenticationRequest;
import org.example.kinoxpbackend.model.User;
import org.example.kinoxpbackend.repository.UserRepository;
import org.example.kinoxpbackend.security.UserPrincipal;
import org.example.kinoxpbackend.service.CustomUserDetailsService;
import org.example.kinoxpbackend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
//todo lav om til /auth/
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;
    //https://www.youtube.com/watch?v=U8D2MPwNARA&list=PLVuqGBBX_tP3KmownF68ifFmgPQt-ujBg&index=5
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) return ResponseEntity.badRequest()
                .body("User already exists");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ADMIN);
        userRepository.save(user);
        return ResponseEntity.ok("User created");
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        final UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        System.out.println("role: " + userPrincipal.getAuthorities()); // Add logging
        return jwtUtil.generateToken(userPrincipal);
    }
    @GetMapping("/test")
    public String test(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return "Test: " + userPrincipal.getUsername();
    }
    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return "Hello, Admin!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}