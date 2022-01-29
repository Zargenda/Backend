package com.backend.Backend.Service;

import com.backend.Backend.model.Horario;
import com.backend.Backend.model.HorarioAsignatura;
import com.backend.Backend.repository.ConflictoRepository;
import com.backend.Backend.repository.HorarioAsignaturaRepository;
import com.backend.Backend.repository.HorarioRepository;
import com.backend.Backend.repository.ReservasRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HorarioServiceTest {
    @Autowired
    HorarioService service;
    @Autowired
    HorarioRepository repository;
    @Autowired
    HorarioAsignaturaRepository repositoryAsign;
    @Autowired
    ConflictoRepository conflictoRepository;
    @Autowired
    ReservasRepository reservasRepository;

    @BeforeEach
    void destroy(){
        repository.deleteAll();
        repositoryAsign.deleteAll();
        conflictoRepository.deleteAll();
        reservasRepository.deleteAll();
    }

    @Test
    void create() {
        Horario horarioAux = new Horario(0L,1,"S1",1,"Prueba Test");
        List<HorarioAsignatura> lista = new ArrayList<>();
        HorarioAsignatura asign1 = new HorarioAsignatura(1L,"PruebaSubject", new Date(10L),new Date(10L),1,"Ssaa","asa",0L);
        HorarioAsignatura asign2 = new HorarioAsignatura(1L,"PruebaSubject2", new Date(10L),new Date(10L),1,"ASAAS","SASA",0L);
        lista.add(asign1);
        lista.add(asign2);
        service.create(horarioAux,lista);
        assertEquals( lista.size(),service.getHorario("Prueba Test","S1",1,1).size());
    }

    @Test
    void onUpdate() {
        Horario horarioAux = new Horario(200L,1,"S1",1,"Prueba Test");
        List<HorarioAsignatura> lista = new ArrayList<>();
        HorarioAsignatura asign1 = new HorarioAsignatura(1L,"PruebaSubject", new Date(10L),new Date(10L),1,"Ssaa","asa",200L);
        HorarioAsignatura asign2 = new HorarioAsignatura(1L,"PruebaSubject2", new Date(10L),new Date(10L),1,"ASAAS","SASA",200L);
        List<HorarioAsignatura> lista2   = new ArrayList<>();
        HorarioAsignatura asign3 = new HorarioAsignatura(1L,"PruebaSubject", new Date(10L),new Date(10L),1,"Cambios","asa",200L);
        lista.add(asign1);
        lista.add(asign2);
        lista2.add(asign3);
        repository.save(horarioAux);
        repositoryAsign.saveAll(lista);
        service.onUpdate(lista2,200L);
        assertTrue( repositoryAsign.findAll().size() == 1);

    }
    @Test
    void conflictos(){
        Horario horarioAux = new Horario(0L,1,"S1",1,"Prueba Test");
        List<HorarioAsignatura> lista = new ArrayList<>();
        HorarioAsignatura asign1 = new HorarioAsignatura(1L,"PruebaSubject", new Date(10L),new Date(11L),1,"Ssaa","asa",0L);
        HorarioAsignatura asign2 = new HorarioAsignatura(1L,"PruebaSubject2", new Date(10L),new Date(11L),1,"ASAAS","SASA",0L);
        lista.add(asign1);
        lista.add(asign2);
        service.create(horarioAux,lista);
        service.create(horarioAux,lista);
        assertTrue(conflictoRepository.findAll().size() > 0);
        assertTrue(reservasRepository.findAll().size() > 0);

    }





}