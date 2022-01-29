package com.backend.Backend.repository;

import com.backend.Backend.model.Horario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class HorarioRepositoryTest {
    @Autowired
    HorarioRepository repository;

    @Test
    void getHorarios() {
        Horario horarioAux = new Horario(0L,1,"S1",1,"Prueba Test");

    }

    @Test
    void getHorarioId() {
    }

    @Test
    void getHorarioByNombrePlanAndCursoAndSemestreAndGrupo() {
    }
}