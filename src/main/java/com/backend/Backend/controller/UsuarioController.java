package com.backend.Backend.controller;

import com.backend.Backend.Service.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public int login(@RequestParam(value="email") String email, @RequestParam(value="contrasena") String contrasena) {
        return usuarioService.loginUsuario(email,contrasena);
    }

    @PostMapping("/registro")
    public boolean registro(@RequestParam("email") String email, @RequestParam("contrasena") String contrasena) {
        return usuarioService.registrarUsuario(email,contrasena);
    }
}
