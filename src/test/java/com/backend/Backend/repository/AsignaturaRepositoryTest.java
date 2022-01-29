package com.backend.Backend.repository;

import com.backend.Backend.model.Asignatura;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AsignaturaRepositoryTest {

    String NOMBRE_PLAN = "PruebaTest";
    @Autowired
    AsignaturaRepository repository;

    @BeforeEach
    void setUp() {
            Asignatura asign1 = new Asignatura(1L, "Test Asignautura",1L, 1L, 1, 1, "1S"
			,0,NOMBRE_PLAN);
        Asignatura asign2 = new Asignatura(2L, "Test Asignautura",1L, 1L, 2, 2, "2S"
                ,0,NOMBRE_PLAN);
        Asignatura asign3 = new Asignatura(3L, "Test Asignautura",1L, 1L, 3, 3, "3S"
                ,0,NOMBRE_PLAN);

        repository.save(asign1);
        repository.save(asign2);
        repository.save(asign3);

    }

    @AfterEach
    void tearDown() {
        Asignatura asign1 = new Asignatura(1L, "Test Asignautura",1L, 1L, 1, 1, "1S"
                ,0,NOMBRE_PLAN);
        Asignatura asign2 = new Asignatura(2L, "Test Asignautura",1L, 1L, 2, 2, "2S"
                ,0,NOMBRE_PLAN);
        Asignatura asign3 = new Asignatura(3L, "Test Asignautura",1L, 1L, 3, 3, "3S"
                ,0,NOMBRE_PLAN);
        repository.delete(asign1);
        repository.delete(asign2);
        repository.delete(asign3);
    }

    @Test
    void getPlanes() {
        assertTrue(repository.getPlanes().contains(NOMBRE_PLAN));
    }

    @Test
    void getSemestres() {
        List<String> aux = new ArrayList<>();
        aux.add("1S");
        aux.add("2S");
        aux.add("3S");
        assertEquals(aux,repository.getSemestres(NOMBRE_PLAN));

    }

    @Test
    void getCursos() {
        List<String> aux = new ArrayList<>();
        aux.add("1");
        aux.add("2");
        aux.add("3");
        assertEquals(aux,repository.getCursos(NOMBRE_PLAN)) ;
    }

    @Test
    void updateAsignatura(){
        Asignatura asign1 = new Asignatura(4L, "Test Asignautura",1L, 1L, 1, 1, "1S"
                ,0,NOMBRE_PLAN);
        repository.save(asign1);
        repository.updateAsignatura(4l,"2S",1,"Cambio Test",1);
        Asignatura asign4 = new Asignatura(4L, "Cambio Test",1L, 1L, 1, 1, "2S"
                ,0,NOMBRE_PLAN);
        assertEquals(repository.getAsignaturaById(4L).getNombre(),asign4.getNombre());
        repository.delete(asign4);
    }
}