// auth-service/src/main/java/com/mauricio/pc/authservice/domain/UsernameAlreadyExistsException.java
package com.mauricio.pc.authservice.domain;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username) {
        super("Username already exists: " + username);
    }
}
