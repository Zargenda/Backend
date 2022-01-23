package com.backend.Backend.controller;

import com.backend.Backend.Service.CalendarService;
import com.backend.Backend.model.Calendario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@RestController
@CrossOrigin (origins = "*")
public class CalendarioController {

    @Autowired
    private CalendarService calendarService;

    //Endpoint del API rest que inicia el calendario a partir de las 4 fechas pasadas en el JSON
    @PostMapping("/IniciarC")
    //Todo mirar si los requestybody con el minmo nombre
    public int IniciarC (@RequestBody String file) throws ParseException, JSONException {
        JSONObject jsonObj = new JSONObject(file);
        JSONObject jsonInner = jsonObj.getJSONObject("quarters");
        System.out.println(jsonInner.toString());
        calendarService.iniciarCalendario(jsonInner.getString("startFirstQuarter"), jsonInner.getString("endFirstQuarter"), jsonInner.getString("startSecondQuarter"), jsonInner.getString("endSecondQuarter"),jsonInner.getString("startSecondConvocatory"),jsonInner.getString("endSecondConvocatory"));
        return 200;
    }

    //Endpoint del API rest que devuelve el calendario guardado en BD
    @GetMapping("/ObtenerC")
    public Iterable<Calendario> ObtenerC(){
        return calendarService.getCalendario();
    }

    //Endpoint del API rest que elimina el calendario en BD actual
    @PostMapping("/ResetC")
    public int ResetC () {
        calendarService.dropCalendario();
        return 200;
    }

    //Endpoint del API rest que permite la modificacion del calendario guardado en BD a partir de la lista de cambios
    //que se encuentra en el JSONArray
    @PostMapping("/ModificarC")
    public int ModificarC (@RequestBody String file) throws JSONException, ParseException {
        JSONObject jsonObjt = new JSONObject(file);
        JSONArray jsonArr = jsonObjt.getJSONArray("total");
        for (int i = 0; i < jsonArr.length(); i++)
        {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            System.out.println(jsonObj.toString());
            if (!jsonObj.isNull("endDate")) {
                calendarService.modificarPeriodo(jsonObj);
            }else{
                String[] aux;
                aux = (jsonObj.getString("startDate")).split("T");
                calendarService.modFecha(aux[0], jsonObj.getString("comment"), jsonObj.getString("comment"), "");

            }
        }
        return 200;


    }

}
