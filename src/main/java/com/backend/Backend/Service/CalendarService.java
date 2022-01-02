package com.backend.Backend.Service;

import com.backend.Backend.model.Calendario;
import com.backend.Backend.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarService {
    @Autowired
    private CalendarRepository calendarRepository;
    public Calendario create (Calendario calendario){ return calendarRepository.save(calendario);}
    public Iterable<Calendario> getCalendario (){return calendarRepository.findAll();}
    public void dropCalendario () {calendarRepository.deleteAll();}
}
