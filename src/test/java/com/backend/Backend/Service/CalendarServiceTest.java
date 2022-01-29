package com.backend.Backend.Service;

import com.backend.Backend.model.Calendario;
import com.backend.Backend.repository.CalendarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalendarServiceTest {

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private CalendarRepository calendarRepository;

    @BeforeEach
    void borrarDB(){
        calendarRepository.deleteAll();
    }

    @Test
    void iniciarCalendario() throws ParseException {
        calendarService.iniciarCalendario("2021-09-15T10:26:48.910Z","2021-09-16T10:26:48.910Z","2021-09-17T10:26:48.910Z","2021-09-18T10:26:48.910Z","2021-09-19T10:26:48.910Z","2021-09-20T10:26:48.910Z");
        Iterable<Calendario> DB = calendarService.getCalendario();
        String DC = "[Calendario{date='2021-09-15', type='SCHOOL', day='X', week='0', comment='INICIO_CUATRIMESTRE'}, Calendario{date='2021-09-16', type='SCHOOL', day='J', week='0', comment='null'}, Calendario{date='2021-09-17', type='SCHOOL', day='V', week='0', comment='INICIO_CUATRIMESTRE'}, Calendario{date='2021-09-18', type='FESTIVE', day='S', week='', comment='null'}, Calendario{date='2021-09-19', type='FESTIVE', day='D', week='', comment='INICIO_CUATRIMESTRE'}, Calendario{date='2021-09-20', type='SCHOOL', day='L', week='a1', comment='null'}]";
        assertEquals(DB.toString(),DC);
    }
    @Test
    void dropCalendario() {
        calendarService.dropCalendario();
        Iterable<Calendario> DB = calendarService.getCalendario();
        String DC = "[]";
        assertEquals(DB.toString(),DC);
    }
    @Test
    void modFecha() throws ParseException {
        calendarService.iniciarCalendario("2021-09-15T10:26:48.910Z","2021-09-16T10:26:48.910Z","2021-09-17T10:26:48.910Z","2021-09-18T10:26:48.910Z","2021-09-19T10:26:48.910Z","2021-09-20T10:26:48.910Z");
        calendarService.modFecha("2021-09-16T10:26:48.910Z","Lunes","Lunes","");
        Iterable<Calendario> DB = calendarService.getCalendario();
        String DC = "[Calendario{date='2021-09-15', type='SCHOOL', day='X', week='0', comment='INICIO_CUATRIMESTRE'}, Calendario{date='2021-09-16', type='SCHOOL', day='J', week='0', comment='null'}, Calendario{date='2021-09-17', type='SCHOOL', day='V', week='0', comment='INICIO_CUATRIMESTRE'}, Calendario{date='2021-09-18', type='FESTIVE', day='S', week='', comment='null'}, Calendario{date='2021-09-19', type='FESTIVE', day='D', week='', comment='INICIO_CUATRIMESTRE'}, Calendario{date='2021-09-20', type='SCHOOL', day='L', week='a1', comment='null'}]";
        assertEquals(DB.toString(),DC);

    }
    @Test
    void modificarPeriodo() throws ParseException, JSONException {
        calendarService.iniciarCalendario("2021-09-15T10:26:48.910Z","2021-09-16T10:26:48.910Z","2021-09-17T10:26:48.910Z","2021-09-18T10:26:48.910Z","2021-09-19T10:26:48.910Z","2021-09-25T10:26:48.910Z");
        JSONObject periodo = new JSONObject("{\"startDate\":\"2021-09-20T10:26:48.910Z\",\"endDate\":\"2021-09-23T10:26:48.910Z\",\"comment\":\"Festividad\"}");
        calendarService.modificarPeriodo(periodo);
        Iterable<Calendario> DB = calendarService.getCalendario();
        System.out.println(DB);
        String DC = "[Calendario{date='2021-09-15', type='SCHOOL', day='X', week='0', comment='INICIO_CUATRIMESTRE'}, Calendario{date='2021-09-16', type='SCHOOL', day='J', week='0', comment='null'}, Calendario{date='2021-09-17', type='SCHOOL', day='V', week='0', comment='INICIO_CUATRIMESTRE'}, Calendario{date='2021-09-18', type='FESTIVE', day='S', week='', comment='null'}, Calendario{date='2021-09-19', type='FESTIVE', day='D', week='', comment='INICIO_CUATRIMESTRE'}, Calendario{date='2021-09-24', type='SCHOOL', day='V', week='a1', comment='null'}, Calendario{date='2021-09-25', type='FESTIVE', day='S', week='', comment='null'}, Calendario{date='2021-09-20', type='FESTIVE', day='L', week='a1', comment='Festividad'}, Calendario{date='2021-09-21', type='FESTIVE', day='M', week='a1', comment='Festividad'}, Calendario{date='2021-09-22', type='FESTIVE', day='X', week='a1', comment='Festividad'}, Calendario{date='2021-09-23', type='FESTIVE', day='J', week='a1', comment='Festividad'}]";
        assertEquals(DB.toString(),DC);
    }
}