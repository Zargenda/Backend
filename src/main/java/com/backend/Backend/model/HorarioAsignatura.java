package com.backend.Backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Horario")
public class HorarioAsignatura {
    private @Id Long id;
    private String subject; //  Asignatura
    @Column
    @Convert(converter = ListIntToString.class)
    private List<Integer> startTime; //El plan al que pertenece

    @Column
    @Convert(converter = ListIntToString.class)
    private List<Integer> endTime; // horario de la universidad

    private int  calendarId;// tipo
    private String description; //aulas
    private String frecuency; //Semanal
}
