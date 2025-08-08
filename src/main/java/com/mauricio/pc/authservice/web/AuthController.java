package com.mauricio.pc.authservice.web;

import com.mauricio.pc.authservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

record AuthRequest(
        @NotBlank @Size(min = 3, max = 64)
        @Schema(example = "mauricio") String username,

        @NotBlank @Size(min = 6, max = 128)
        @Schema(example = "123456") String password
) {
}

record AuthResponse(
        @Schema(example = "eyJhbGciOiJIUzI1NiJ9...") String token
) {
}

@Validated
@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
public class AuthController {


    private final AuthService authService;
    public AuthController(AuthService authService) { this.authService = authService; }

    @PostMapping(
            path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @SecurityRequirements({}) // ← explícitamente público
    @Operation(
            summary = "Register user and return JWT",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User created",
                            content = @Content(schema = @Schema(implementation = AuthResponse.class))),
                    @ApiResponse(responseCode = "409", description = "Username already exists")
            }
    )
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthRequest req) {
        String token = authService.register(req.username(), req.password());
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse(token));
    }

    @PostMapping(
            path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @SecurityRequirements({}) // ← explícitamente público
    @Operation(
            summary = "Login and return JWT",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(schema = @Schema(implementation = AuthResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Invalid credentials",
                            content = @Content)
            }
    )
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest req) {
        String token = authService.login(req.username(), req.password());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}