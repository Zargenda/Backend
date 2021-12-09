package com.backend.Backend.repository;

import com.backend.Backend.model.Calendario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.List;

public interface CalendarRepository extends CrudRepository<Calendario,String> {

}
