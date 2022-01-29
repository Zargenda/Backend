package com.backend.Backend.Service;

import com.backend.Backend.model.Usuario;
import com.backend.Backend.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
 @SpringBootTest
class UsuarioServiceTest {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @BeforeEach
    void borrarDB(){
        usuarioRepository.deleteAll();
    }

    @Test
    void registrarUsuario() {
        usuarioService.registrarUsuario("test@unizar.es","test123");
        Iterable<Usuario> DB = usuarioRepository.findAll();
        String DC = "[Usuario{contrasena='test123', email='test@unizar.es', admin=false}]";
        assertEquals(DB.toString(),DC);
    }

    @Test
    void loginUsuario() {
        usuarioService.registrarUsuario("test@unizar.es","test123");
        int permiso = usuarioService.loginUsuario("test@unizar.es","test123");
        assertEquals(permiso,1);
    }
}