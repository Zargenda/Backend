package com.backend.Backend.Service;

import com.backend.Backend.model.Calendario;
import com.backend.Backend.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class CalendarService {
    private String semana = "MONDAY TUESDAY WEDNESDAY THURSDAY FRIDAY SATURDAY SUNDAY";
    @Autowired
    private CalendarRepository calendarRepository;
    public void iniciarCalendario(String date1Ini, String date1Fin,String date2Ini, String date2Fin, String date3Ini, String date3Fin) throws ParseException {
        if (!calendarRepository.existsById(date1Ini)){
            genCalendaraux(date1Ini, date1Fin);
            genCalendaraux(date2Ini, date2Fin);
            genCalendaraux(date3Ini, date3Fin);
        }
    }

    public Iterable<Calendario> getCalendario (){return calendarRepository.findAll();}
    public void dropCalendario () {calendarRepository.deleteAll();}

    public void modFecha(String date,String comment, String Day, String week){
        Optional<Calendario> calendarioOpcional = calendarRepository.findById(date);
        if (calendarioOpcional.isPresent()) {
            Calendario calendario = calendarioOpcional.get();
            if (semana.contains(Day)){
                calendario.setDay(Day);
                calendario.setWeek(week);
                calendario.setType("CHANGE_DAY");
            }else if(comment.contains("Eval")){
                calendario.setComment(comment);
                if (comment.contains("1ª")){
                    calendario.setType("CONVOCATORY");
                }else if (comment.contains("2ª")){
                    calendario.setType("SECOND_CONVOCATORY");
                }else {
                    calendario.setType("CONTINUE_CONVOCATORY");
                }
                calendario.setComment(comment);
            }else if (comment.contains("Exámenes")){
                calendario.setType("CULM_EXAM");
                calendario.setComment(comment);
            }
            else{
                calendario.setComment(comment);
                calendario.setType("FESTIVE");
            }
            calendarRepository.save(calendario);
        }
    }
    public void modificarPeriodo(JSONObject jsonObj) throws JSONException, ParseException {
        String iniDate = jsonObj.getString("startDate");
        String finDate = jsonObj.getString("endDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        ArrayList<Calendario> calendariolista = new ArrayList<Calendario>();
        Calendar start = Calendar.getInstance();
        start.setTime(sdf.parse(iniDate));
        Calendar end = Calendar.getInstance();
        end.setTime(sdf.parse(finDate));

        for(Date fechaactual = start.getTime(); start.before(end) || start.equals(end) ; start.add(Calendar.DATE,1),fechaactual=start.getTime()) {
            Calendario aux = new Calendario();
            modFecha(sdf.format(fechaactual),jsonObj.getString("comment"),"","");
        }

    }


    private void genCalendaraux(String date1Ini, String date1Fin) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        ArrayList<Calendario> calendariolista = new ArrayList<Calendario>();
        Calendar start = Calendar.getInstance();
        start.setTime(sdf.parse(date1Ini));
        Calendar end = Calendar.getInstance();
        end.setTime(sdf.parse(date1Fin));
        String abweek = "";
        int number_of_week = 0;
        boolean inicioCuatri = true;

        for(Date fechaactual = start.getTime(); start.before(end) || start.equals(end) ; start.add(Calendar.DATE,1),fechaactual=start.getTime()) {
            Calendario aux = new Calendario();
            aux.setDate(sdf.format(fechaactual));
            aux.setDay(GetDay(fechaactual));
            if (aux.getDay().equals("D") || aux.getDay().equals("S")) {
                aux.setType("FESTIVE");
                if (aux.getDay().equals("D")) {
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
            if (inicioCuatri){
                aux.setComment("INICIO_CUATRIMESTRE");
                inicioCuatri=false;
            }
            calendariolista.add(aux);
        }
        calendarRepository.saveAll(calendariolista);
    }
    private static String GetDay(Date date) {
        Locale esLocale = new Locale("es", "ES");//para trabajar en español
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", esLocale);
        sdf.applyPattern("EEE");
        String aux = sdf.format(date);
        String dayLetter;
        if (aux.equals("mié.")){
            dayLetter="X";
        }else{
            dayLetter = Character.toUpperCase(aux.charAt(0))+"";
        }

        return dayLetter;
    }

}
