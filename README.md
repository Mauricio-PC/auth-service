# 🔐 Auth Service – PokeMicroServicios

Este microservicio forma parte del proyecto **PokeMicroServicios**, y se encarga de gestionar **la autenticación y el registro de usuarios** usando **JWT (JSON Web Tokens)**. También expone documentación interactiva mediante **Swagger UI**.

---

## 🚀 Características

✅ Registro de usuarios con roles  
✅ Login con validación y emisión de JWT  
✅ Seguridad con Spring Security + filtros personalizados  
✅ Swagger UI para probar endpoints  
✅ Passwords cifrados con BCrypt  
✅ Listo para usarse con arquitectura de microservicios (incluye Eureka)

---

## 🏗️ Tecnologías usadas

- Java 17  
- Spring Boot 3  
- Spring Security  
- JWT (JJWT)  
- SpringDoc OpenAPI (Swagger 3)  
- Lombok  
- H2 Database (temporal para pruebas)  
- Maven

---

## 📂 Estructura general
```yaml
auth-service
├── controller
│ └── AuthController.java # Endpoints /auth/login y /auth/register
├── dto
│ ├── AuthRequest.java # Payload login
│ ├── RegisterRequest.java # Payload registro
│ └── AuthResponse.java # Token de salida
├── model
│ ├── User.java # Entidad User
│ └── Role.java # Enum de roles (USER, ADMIN)
├── repository
│ └── UserRepository.java
├── service
│ └── AuthService.java # Lógica principal de login/registro
├── config
│ ├── SecurityConfig.java # Filtros, reglas y protección
│ ├── JwtAuthenticationFilter.java
│ └── UserDetailsServiceImpl.java
```

---

## 🔐 Endpoints principales

### `POST /auth/register`
Registra un nuevo usuario.  
**Body JSON:**
```json
{
  "username": "ash",
  "password": "pikachu"
}
```
POST /auth/login
Inicia sesión y devuelve un token JWT.
Respuesta:
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6..."
}
## 🧪 Swagger UI
Puedes acceder a Swagger para probar los endpoints desde tu navegador:
🧭 http://localhost:8083/swagger-ui/index.html
## ⚙️ Cómo correr el proyecto
Requisitos:
Java 17+
Maven
(Opcional) Eureka Server si quieres registrar este microservicio
Instrucciones:
```yaml
git clone https://github.com/Mauricio-PC/auth-service.git
cd auth-service
mvn spring-boot:run
```
Esto levanta el servicio en http://localhost:8083.
##🔄 Registro en Eureka (opcional)
Este servicio está configurado para registrarse automáticamente en Eureka si lo tienes corriendo en http://localhost:8761.

##📌 Notas
Los passwords están encriptados con BCryptPasswordEncoder.
Los tokens JWT incluyen el nombre de usuario y roles, y expiran tras un tiempo configurable.
Los endpoints bajo /auth/** están permitidos sin autenticación; el resto requiere JWT válido.

##🧠 Autor
Mauricio PC – @Mauricio-PC
Desarrollador Java Fullstack | Apasionado por el software limpio, la seguridad y los microservicios.
