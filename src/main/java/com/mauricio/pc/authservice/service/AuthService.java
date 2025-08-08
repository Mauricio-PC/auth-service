package com.mauricio.pc.authservice.service;

import com.mauricio.pc.authservice.jwt.JwtService;
import com.mauricio.pc.authservice.model.AppUser;
import com.mauricio.pc.authservice.repo.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {
    private final AppUserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public AuthService(AppUserRepository repo, PasswordEncoder encoder, JwtService jwtService) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    public String register(String username, String rawPassword) {
        repo.findByUsername(username).ifPresent(u -> { throw new RuntimeException("username taken"); });
        AppUser u = new AppUser();
        u.setUsername(username);
        u.setPassword(encoder.encode(rawPassword));
        repo.save(u);
        return jwtService.generateToken(username, Map.of("role", u.getRole()));
    }

    public String login(String username, String rawPassword) {
        AppUser u = repo.findByUsername(username).orElseThrow(() -> new RuntimeException("bad credentials"));
        if (!encoder.matches(rawPassword, u.getPassword())) throw new RuntimeException("bad credentials");
        return jwtService.generateToken(username, Map.of("role", u.getRole()));
    }
}