package com.backend.Backend.model;

import com.backend.Backend.Utils.ListIntToString;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("Id")
    private @Id Long Id;
    @JsonProperty("Subject")
    private String subject; //  Asignatura
    @JsonProperty("StartTime")
    private Date StartTime;
    @JsonProperty("EndTime")
    private Date EndTime;
    @JsonProperty("CalendarId")
    private int  CalendarId;// tipo
    @JsonProperty("Description")
    private String Description; //aulas
    @JsonProperty("Frecuency")
    private String Frecuency; //Semanal
    private Long idPadre;

    public HorarioAsignatura(Long Id, String Subject, Date StartTime, Date EndTime, int CalendarId, String Description, String Frecuency, Long idPadre) {
        this.Id = Id;
        this.subject = Subject;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
        this.CalendarId = CalendarId;
        this.Description = Description;
        this.Frecuency = Frecuency;
        this.idPadre = idPadre;
    }
}
