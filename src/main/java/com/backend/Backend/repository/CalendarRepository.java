package com.backend.Backend.repository;

import com.backend.Backend.model.Calendario;
import org.springframework.data.repository.CrudRepository;

public interface CalendarRepository extends CrudRepository<Calendario,String> {

}
