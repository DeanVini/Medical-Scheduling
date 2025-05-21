package com.api.medical_scheduling.controller;

import com.api.medical_scheduling.model.User;
import com.api.medical_scheduling.dto.AuthRequestDTO;
import com.api.medical_scheduling.dto.UserRequestDTO;
import com.api.medical_scheduling.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AuthController {
    private final AuthService authService;

    @Autowired
    private AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequestDTO authRequestDTO) {
        return authService.authenticate(authRequestDTO);
    }

    @PostMapping("/register")
    public User register(@RequestBody UserRequestDTO novoUserRequestDTO) {
        return authService.register(novoUserRequestDTO);
    }
}
