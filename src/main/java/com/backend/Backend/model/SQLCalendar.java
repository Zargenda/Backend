package com.backend.Backend.model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
class DiaCalendario {
    public String date;
    public String type;
    public String day;
    public String week;

    @Override
    public String toString() {
        return "DiaCalendario{" +
                "date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", day='" + day + '\'' +
                ", week='" + week + '\'' +
                '}';
    }
}

public class SQLCalendar {
    public static void GenCalendar(String date1Ini, String date1Fin, String date2Ini, String date2Fin) throws ParseException {
        genCalendaraux(date1Ini, date1Fin);
        genCalendaraux(date2Ini, date2Fin);
    }

    private  static void genCalendaraux (String date1Ini,String date1Fin) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        ArrayList<DiaCalendario> calendario = new ArrayList<DiaCalendario>();
        Boolean star_week = true;
        Calendar start = Calendar.getInstance();
        start.setTime(sdf.parse(date1Ini));
        Calendar end = Calendar.getInstance();
        end.setTime(sdf.parse(date1Fin));
        String abweek = "";
        int number_of_week = 0;

        for(Date fechaactual = start.getTime();start.before(end) || start.equals(end) ; start.add(Calendar.DATE,1),fechaactual=start.getTime()){
            DiaCalendario aux = new DiaCalendario();
            aux.date = sdf.format(fechaactual);
            aux.day = GetDay(fechaactual);
            if (aux.day.equals("Sun") || aux.day.equals("Sat")){
                aux.type = "FESTIVE";
                if (aux.day.equals("Sun") ){
                    if (abweek == "" || abweek == "b"){
                        number_of_week ++;
                        abweek = "a";
                    }else {
                        abweek = "b";
                    }
                }
                aux.week = "";
            }else{
                aux.type = "SCHOOL";
                aux.week = abweek + Integer.toString(number_of_week);

            }
            calendario.add(aux);
        }
        for (int i = 0; i < calendario.size(); i++) {
            System.out.println((calendario.get(i).toString()));
        }
    }
    private static String GetDay(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        sdf.applyPattern("EEE");
        return sdf.format(date);
    }
}

