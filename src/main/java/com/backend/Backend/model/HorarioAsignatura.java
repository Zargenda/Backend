package com.backend.Backend.model;

import com.backend.Backend.Utils.ListIntToString;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class HorarioAsignatura {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long Id;
    private String Subject; //  Asignatura
    private Date StartTime;
    private Date EndTime;
    private int  CalendarId;// tipo
    private String Description; //aulas
    private String Frecuency; //Semanal
    private Long idPadre;

    public HorarioAsignatura(Long id, String subject, Date startTime, Date endTime, int calendarId, String description, String frecuency, Long idPadre) {
        Id = id;
        Subject = subject;
        StartTime = startTime;
        EndTime = endTime;
        CalendarId = calendarId;
        Description = description;
        Frecuency = frecuency;
        this.idPadre = idPadre;
    }
}
