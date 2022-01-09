package com.backend.Backend.repository;

import com.backend.Backend.model.Usuario;
import org.springframework.data.repository.CrudRepository;


public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndAdmin(String email, Boolean admin);

    boolean existsByEmailAndContrasena(String email, String contrasena);


}

