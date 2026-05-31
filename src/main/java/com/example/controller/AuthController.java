package com.example.controller;

import com.example.dto.LoginRequest;
import com.example.dto.SignupRequest;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserInfo(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.badRequest().body("No authentication found");
        }

        User user = (User) authentication.getPrincipal();
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("username", user.getUsername());
        response.put("email", user.getEmail());
        response.put("roles", user.getRoles());
        response.put("firstName", user.getFirstName());
        response.put("lastName", user.getLastName());
        response.put("avatarUrl", user.getAvatarUrl());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/check-role")
    public ResponseEntity<?> checkRole(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(401).body("Not authenticated");
        }

        User user = (User) authentication.getPrincipal();
        Map<String, Object> response = new HashMap<>();
        response.put("username", user.getUsername());
        response.put("roles", user.getRoles());
        response.put("isAdmin", user.getRoles().contains("ROLE_ADMIN"));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/add-admin-role")
    public ResponseEntity<?> addAdminRole(@RequestParam String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (!user.getRoles().contains("ROLE_ADMIN")) {
            user.getRoles().add("ROLE_ADMIN");
            userRepository.save(user);
        }

        return ResponseEntity.ok("Admin role added successfully");
    }
}