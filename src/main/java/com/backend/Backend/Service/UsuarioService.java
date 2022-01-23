package com.backend.Backend.Service;

import com.backend.Backend.model.Usuario;
import com.backend.Backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository repo) {
        this.usuarioRepository = repo;
    }

    //Funcion que dado un email y una contraseña comprueba si existe alguien ya con ese email y si no lo registra
    public boolean registrarUsuario(String email,String contrasena) {
        if (usuarioRepository.existsByEmail(email)) {
            return false;
        } else {
            Usuario usuario = new Usuario(email, contrasena);
            usuarioRepository.save(usuario);
            return true;
        }
    }

    //Funcion que dado un email y una contraseña devuelve: 0 no encontrado, 1 usuario normal, 2 usuario con privilegios
    public int loginUsuario(String email, String contrasena) {
        if (usuarioRepository.existsByEmailAndContrasena(email, contrasena)){
            if (usuarioRepository.existsByEmailAndAdmin(email,true)){
                return 2;
            } else{
                return 1;
            }
        }else{
            return 0;
        }

    }

}