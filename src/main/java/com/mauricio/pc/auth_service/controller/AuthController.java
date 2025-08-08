package com.mauricio.pc.auth_service.controller;

import com.mauricio.pc.auth_service.dto.*;
import com.mauricio.pc.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}

/**
 * ✅ AuthRequest: contiene username y password
 * ✅ RegisterRequest: contiene username, password, roles (puede ser un Set<String>)
 * ✅ AuthResponse: contiene el token JWT
 * **/