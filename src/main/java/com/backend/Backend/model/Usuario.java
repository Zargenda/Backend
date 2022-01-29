package com.backend.Backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String contrasena;
    private String email;
    private Boolean admin;

    public Usuario() {}

    public Usuario(String email, String contrasena) {
        this.contrasena = contrasena;
        this.email = email;
        admin = false;
    }

    public Long getId() {
        return id;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "contrasena='" + contrasena + '\'' +
                ", email='" + email + '\'' +
                ", admin=" + admin +
                '}';
    }
}
