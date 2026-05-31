package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.model.User;
import com.example.repository.UserRepository;
@Configuration
public class ApplicationStarup{
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostConstruct
    public void init() {
        createAdminAccount();
    }
    private void createAdminAccount() {
        if(userRepository.existsByUsername("admin")) {
            return;
        }
        List<String> adminRole = new ArrayList<>();
        adminRole.add("USER");
        adminRole.add("ADMIN");
        User admin = new User(
                "admin",
                encoder.encode("Admin001"),
                "admin@example.com",
                adminRole
        );
        userRepository.save(admin);
    }
}
