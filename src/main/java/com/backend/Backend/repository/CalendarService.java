package com.backend.Backend.repository;

import com.backend.Backend.model.Asignatura;
import com.backend.Backend.model.Calendario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarService {
    @Autowired
    private CalendarRepository calendarRepository;

    public Calendario create (Calendario calendario){ return calendarRepository.save(calendario);}
    public Iterable<Calendario> getCalendario (){return calendarRepository.findAll();}
    public void dropCalendario () {calendarRepository.deleteAll();}
}
