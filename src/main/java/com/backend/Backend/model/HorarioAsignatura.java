package com.backend.Backend.model;

import com.backend.Backend.Utils.ListIntToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "HorarioAsignatura")
public class HorarioAsignatura {
    private @Id Long id;
    private String subject; //  Asignatura
    private Date startTime;
    private Date endTime;
    private int  calendarId;// tipo
    private String description; //aulas
    private String frecuency; //Semanal
}
