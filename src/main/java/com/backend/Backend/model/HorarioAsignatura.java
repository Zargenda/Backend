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
    private @Id Long id;
    private String Subject; //  Asignatura
    private Date StartTime;
    private Date EndTime;
    private int  CalendarId;// tipo
    private String Description; //aulas
    private String Frecuency; //Semanal
    private Long idPadre;
}
