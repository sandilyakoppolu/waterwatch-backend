package com.waterwatch.backend.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String role;   // USER or ADMIN

    public User() {
        this.id = "USR-" + UUID.randomUUID().toString().substring(0,5).toUpperCase();
        this.role = "ROLE_USER";   // default role
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    // IMPORTANT: allow setting role from register request
    public void setRole(String role) {
        if (role != null) {
            if (role.equals("ADMIN")) {
                this.role = "ROLE_ADMIN";
            } else {
                this.role = "ROLE_USER";
            }
        }
    }

}
