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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(contrasena, usuario.contrasena) && Objects.equals(email, usuario.email) && Objects.equals(admin, usuario.admin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contrasena, email, admin);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", admin=" + admin +
                '}';
    }
}
