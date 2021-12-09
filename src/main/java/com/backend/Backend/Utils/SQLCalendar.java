package com.backend.Backend.Utils;

import com.backend.Backend.Service.CalendarService;
import com.backend.Backend.model.Calendario;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SQLCalendar {

    @Autowired
    CalendarService calendarService;

    public void genCalendar(String date1Ini, String date1Fin, String date2Ini, String date2Fin) throws ParseException {
        genCalendaraux(date1Ini, date1Fin);
        genCalendaraux(date2Ini, date2Fin);
    }

    private void genCalendaraux(String date1Ini, String date1Fin) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Calendar start = Calendar.getInstance();
        start.setTime(sdf.parse(date1Ini));
        Calendar end = Calendar.getInstance();
        end.setTime(sdf.parse(date1Fin));
        String abweek = "";
        int number_of_week = 0;

        for(Date fechaactual = start.getTime();start.before(end) || start.equals(end) ; start.add(Calendar.DATE,1),fechaactual=start.getTime()) {
            Calendario aux = new Calendario();
            aux.setDate(sdf.format(fechaactual));
            aux.setDay(GetDay(fechaactual));
            if (aux.getDay().equals("Sun") || aux.getDate().equals("Sat")) {
                aux.setType("FESTIVE");
                if (aux.getDate().equals("Sun")) {
                    if (abweek.equals("") || abweek.equals("b")) {
                        number_of_week++;
                        abweek = "a";
                    } else {
                        abweek = "b";
                    }
                }
                aux.setWeek("");
            } else {
                aux.setType("SCHOOL");
                aux.setWeek(abweek + (number_of_week));

            }
            calendarService.create(aux);
        }
    }
    private static String GetDay(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        sdf.applyPattern("EEE");
        return sdf.format(date);
    }
}
