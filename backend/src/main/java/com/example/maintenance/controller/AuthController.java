package com.example.maintenance.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> user) {

        if ("admin".equals(user.get("username")) &&
                "1234".equals(user.get("password"))) {
            return ResponseEntity.ok("Login success");
        }

        return ResponseEntity.status(401).body("Invalid credentials");
    }
}