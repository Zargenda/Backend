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
    public void iniciarCalendario(String date1Ini, String date1Fin,String date2Ini, String date2Fin) throws ParseException {
        if (!calendarRepository.existsById(date1Ini)){
            genCalendaraux(date1Ini, date1Fin);
            genCalendaraux(date2Ini, date2Fin);
        }
    }
    public Iterable<Calendario> getCalendario (){return calendarRepository.findAll();}
    public void dropCalendario () {calendarRepository.deleteAll();}

    public void modFecha(String date,String comment, String Day, String week){
        Optional<Calendario> calendarioOpcional = calendarRepository.findById(date);
        if (calendarioOpcional.isPresent()) {
            System.out.println("estoy presente");
            Calendario calendario = calendarioOpcional.get();
            if (semana.contains(Day)){
                String aux = obtainName(Day);
                calendario.setDay(aux);
                calendario.setWeek(week);
                calendario.setComment(comment);
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

        for(Date fechaactual = start.getTime(); start.before(end) || start.equals(end) ; start.add(Calendar.DATE,1),fechaactual=start.getTime()) {
            Calendario aux = new Calendario();
            aux.setDate(sdf.format(fechaactual));
            aux.setDay(GetDay(fechaactual));
            if (aux.getDay().equals("Sun") || aux.getDay().equals("Sat")) {
                aux.setType("FESTIVE");
                if (aux.getDay().equals("Sun")) {
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
            calendariolista.add(aux);
        }
        calendarRepository.saveAll(calendariolista);
    }
    private static String GetDay(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        sdf.applyPattern("EEE");
        return sdf.format(date);
    }

    private static String obtainName(String date){
        String aux;
        switch (date){
            case "MONDAY":
                aux = "Mon";
                break;
            case "TUESDAY":
                aux = "Tue";
                break;
            case "WEDNESDAY":
                aux = "Wed";
                break;
            case "THURSDAY":
                aux = "Thu";
                break;
            case "FRIDAY":
                aux = "Fri";
                break;
            case "SATURDAY":
                aux = "Sat";
                break;
            case "SUNDAY":
                aux = "Sun";
                break;
            default:
                aux = "error";
                break;
        }
        return aux;
    }

}
