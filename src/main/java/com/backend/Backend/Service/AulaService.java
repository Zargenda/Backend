package com.backend.Backend.Service;

import com.backend.Backend.model.Aula;
import com.backend.Backend.repository.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AulaService {
    @Autowired
    private AulaRepository aulaRepository;

    //Dada un aula la almacena el la DB
    public Aula create (Aula aula) {
        return aulaRepository.save(aula);
    }

    //Devuelve todas las aulas almacenadas en la DB
    public List<Aula> getAllAulas (){
        return aulaRepository.findAll();
    }

    //Dada un aula la elimina de la DB
    public void delete (Aula aula) {
        aulaRepository.delete(aula);
    }

    //Dada un id devuelve el aula con ese id
    public Optional<Aula> findById (Long id) {
        return aulaRepository.findById(id);
    }
}
