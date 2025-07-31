package com.mauricio.pc.auth_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

}
/**
 * Te propongo este orden:
 *
 * Orden	Módulo	¿Qué hace?
 * ✅ 1	User + Role	Entidad base, clave para todo
 * ✅ 2	DTOs (AuthRequest, AuthResponse, RegisterRequest)	Payloads de login y registro
 * ✅ 3	UserRepository	Para guardar/buscar usuarios
 * ✅ 4	AuthService	Lógica para registrar usuarios y emitir tokens
 * ✅ 5	AuthController	Endpoints /auth/login, /auth/register
 * ✅ 6	SecurityConfig	Define filtros, reglas, permite /auth/** y protege el resto
 * **/

/***
 * 🧱 Estructura del Paso 6
 * Archivo	                                ¿Qué hace?
 * ✅ SecurityConfig.java	                Configura filtros y reglas de seguridad.
 * ✅ JwtAuthenticationFilter.java	        Filtro que intercepta cada request y valida el JWT.
 * ✅ JwtUtil.java	                        Ya lo tienes, se usa para leer y validar el token.
 * ✅ UserDetailsServiceImpl.java	        Spring necesita esto para saber cómo cargar usuarios desde la DB.
 * ***/