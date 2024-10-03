package org.example.kinoxpbackend.service;

import org.example.kinoxpbackend.model.Role;
import org.springframework.stereotype.Service;
import org.example.kinoxpbackend.model.User;

import java.util.Optional;

@Service
public class UserService {
    private static final String EXISTING_USERNAME = "UserTest";
    //TODO skal laves om til database
    public Optional<User> getUserByUsername(String username) {
        if (! EXISTING_USERNAME.equals(username)) return Optional.empty();

        User user = new User();
        user.setId(1);
        user.setUsername(EXISTING_USERNAME);
        user.setPassword("$2a$12$G20tn3gThoAyHKBp6nJyh.SCUivWS7MN5qtc6AU687vkfiOSiSA9C"); // password: "test"
        user.setRole(Role.USER);
        return Optional.of(user);
    }

}
