package com.backend.Backend.controller;

import com.backend.Backend.Service.UsuarioService;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    //Endpoint del API rest que permite el login en la aplicaion a traves de dos parametros [email,pass] que se envian a
    //traves de un JSON
    @PostMapping("/login")
    public int login(@RequestBody String file) throws ParseException, JSONException {
        JSONObject jsonObj = new JSONObject(file);
        JSONObject jsonInner = jsonObj.getJSONObject("loginInfo");
        return usuarioService.loginUsuario(jsonInner.getString("email"),jsonInner.getString("pass"));
    }
    //Endpoint del API rest que permite el registro en la aplicaion a traves de dos parametros [email,pass] que se envian a
    //traves de un JSON
    @PostMapping("/registro")
    public boolean registro(@RequestBody String file) throws ParseException, JSONException {
        JSONObject jsonObj = new JSONObject(file);
        JSONObject jsonInner = jsonObj.getJSONObject("registerInfo");
        return usuarioService.registrarUsuario(jsonInner.getString("email"),jsonInner.getString("pass"));
    }
}
