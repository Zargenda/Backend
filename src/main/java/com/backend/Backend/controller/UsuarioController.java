package com.backend.Backend.controller;

import com.backend.Backend.Service.UsuarioService;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public int login(@RequestBody String file) throws ParseException, JSONException {
        JSONObject jsonObj = new JSONObject(file);
        JSONObject jsonInner = jsonObj.getJSONObject("loginInfo");
        return usuarioService.loginUsuario(jsonInner.getString("email"),jsonInner.getString("pass"));
    }

    @PostMapping("/registro")
    public boolean registro(@RequestBody String file) throws ParseException, JSONException {
        JSONObject jsonObj = new JSONObject(file);
        JSONObject jsonInner = jsonObj.getJSONObject("registerInfo");
        return usuarioService.registrarUsuario(jsonInner.getString("email"),jsonInner.getString("pass"));
    }
}
