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

    @GetMapping("/IniciarC")
    public Iterable<Calendario> IniciarC (@RequestParam ("Ini1") String fecha1Ini, @RequestParam ("Fin1") String fecha1Fin, @RequestParam ("Ini2") String fecha2Ini, @RequestParam ("Fin2") String fecha2Fin) throws ParseException {
        calendarService.iniciarCalendario(fecha1Ini,fecha1Fin,fecha2Ini,fecha2Fin);
        return calendarService.getCalendario();
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
    public Iterable<Calendario> ModificarC (@RequestBody String file) throws JSONException, ParseException {
        JSONArray jsonArr = new JSONArray(file);
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
        return calendarService.getCalendario();


    }
    @PostMapping("/testF")
    public Date testF (@RequestParam("Fecha") String Fecha ) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        sdf.applyPattern("yyyy-MM-dd");
        return sdf.parse(Fecha);
    }



}
