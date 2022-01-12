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

    @PostMapping("/IniciarC")
    //Todo mirar si los requestybody con el minmo nombre
    public int IniciarC (@RequestBody String file) throws ParseException, JSONException {
        JSONObject jsonObj = new JSONObject(file);
        JSONObject jsonInner = jsonObj.getJSONObject("quarters");
        System.out.println(jsonInner.toString());
        calendarService.iniciarCalendario(jsonInner.getString("startFirstQuarter"), jsonInner.getString("endFirstQuarter"), jsonInner.getString("startSecondQuarter"), jsonInner.getString("endSecondQuarter"),jsonInner.getString("startSecondConvocatory"),jsonInner.getString("endSecondConvocatory"));
        return 200;
    }

    @GetMapping("/ObtenerC")
    public Iterable<Calendario> ObtenerC(){
        return calendarService.getCalendario();
    }

    @PostMapping("/ResetC")
    public int ResetC () {
        calendarService.dropCalendario();
        return 200;
    }

    @PostMapping("/ModificarC")
    public int ModificarC (@RequestBody String file) throws JSONException, ParseException {
        JSONObject jsonObjt = new JSONObject(file);
        JSONArray jsonArr = jsonObjt.getJSONArray("total");
        for (int i = 0; i < jsonArr.length(); i++)
        {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            if (jsonObj.has("endDate")){
                calendarService.modificarPeriodo(jsonObj);
            }else{
                System.out.println("Fecha sola");
                String[] aux;
                if (jsonObj.has("day")){
                    aux=(jsonObj.getString("date")).split("T");
                    calendarService.modFecha(aux[0],jsonObj.getString("comment"),jsonObj.getString("day"),jsonObj.getString("week"));
                }else {
                    aux = (jsonObj.getString("startDate")).split("T");
                    calendarService.modFecha(aux[0], jsonObj.getString("comment"), "", "");
                }
            }
        }
        return 200;


    }
    @PostMapping("/testF")
    public Date testF (@RequestParam("Fecha") String Fecha ) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        sdf.applyPattern("yyyy-MM-dd");
        return sdf.parse(Fecha);
    }



}
