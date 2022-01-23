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
    private Set<String> semana;
    @Autowired
    private CalendarRepository calendarRepository;
    //Funcion que toma los datos del endpoint y lanza tres subprocesos
    public void iniciarCalendario(String date1Ini, String date1Fin,String date2Ini, String date2Fin, String date3Ini, String date3Fin) throws ParseException {
        if (!calendarRepository.existsById(date1Ini)){
            genCalendaraux(date1Ini, date1Fin);
            genCalendaraux(date2Ini, date2Fin);
            genCalendaraux(date3Ini, date3Fin);
            semana = Set.of("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo");
        }
    }

    //devuelve el calendario almacenado en la DB
    public Iterable<Calendario> getCalendario (){return calendarRepository.findAll();}
    //Elimina el calendario almacenado en la DB
    public void dropCalendario () {calendarRepository.deleteAll();}

    //Dada una fecha, modifica dicha fecha con los datos y comentarios pasados desde el front asi como realizar los
    //cambios que resulten pertinentes como establecer el nuevo tipo o cambiar la letra que representa el dia.
    public void modFecha(String date,String comment, String Day, String week){
        Optional<Calendario> calendarioOpcional = calendarRepository.findById(date);
        if (calendarioOpcional.isPresent()) {
            Calendario calendario = calendarioOpcional.get();
            if (semana.contains(Day)){
                System.out.println("cambio de dia");
                calendario.setDay(shortName(Day));
                calendario.setType("CHANGE_DAY");
                calendario.setComment("horario de" + comment);
            }else if(comment.contains("eval")){
                calendario.setComment(comment);
                System.out.println("cont conv");
                calendario.setType("CONTINUE_CONVOCATORY");

            }else if (comment.contains("Exámenes")){
                calendario.setComment(comment);
                if (comment.contains("1ª")) {
                    System.out.println("primer conve");
                    calendario.setType("CONVOCATORY");
                }else if (comment.contains("2ª")){
                    calendario.setType("SECOND_CONVOCATORY");
                    System.out.println("segunfa con");
                }else {
                    System.out.println("examenes");
                    calendario.setType("CULM_EXAM");
                }
            }else{
                calendario.setComment(comment);
                System.out.println("festivo");
                calendario.setType("FESTIVE");
            }
            calendarRepository.save(calendario);
        }
    }

    //Funcion que itera entre dos fechas realizando la modificacion de aquellos eventos que se efectuen en un periodo
    // de tiempo determinado y señalado desde el Front
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

    //Funcion auxiliar de "iniciarCalendario" encargada de generar el calendario sin ningun tipo de modificacion con las
    //fechas, inicializarlas a festivo o lectivo y señalar el tipo de semana a o b. La pprimera fecha se señala como
    //inicio del cuatrimestre para facilitar la visualizacion en el front
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

    //Funcion que dada una fecha devuelve la incial correspondiente a esta
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

    //Funcion que dado un dia en forma de string (ej. Lunes) devuelve su inicial correspondiente
    private String shortName (String day){
        String shorted = "error";
        switch (day){
            case "Lunes":
                shorted = "L";
                break;
            case "Martes":
                shorted = "M";
                break;
            case "Miercoles":
                shorted = "X";
                break;
            case  "Jueves":
                shorted = "J";
                break;
            case "Viernes":
                shorted = "V";
                break;
            case "Sabado":
                shorted = "S";
                break;
            case "Domingo":
                shorted = "D";
                break;

        }
        return shorted;



    }


}
