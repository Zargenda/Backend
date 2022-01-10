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
    private String subject; //  Asignatura
    private Date startTime;
    private Date endTime;
    private int  calendarId;// tipo
    private String description; //aulas
    private String frecuency; //Semanal

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="horario_fid", referencedColumnName = "horarioId")
    private Horario horario;
}
