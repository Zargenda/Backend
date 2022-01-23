package com.backend.Backend.Service;


import com.backend.Backend.model.Conflicto;
import com.backend.Backend.repository.ConflictoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConflictoService {
    @Autowired
    private ConflictoRepository conflictoRepository;

    //Funcion que dado un conflicto lo almacena en la DB
    public Conflicto create (Conflicto conflicto) { return conflictoRepository.save(conflicto); }

    //Funcion que devuelve todos los conflictos
    public List<Conflicto> getAllConflictos (){
        return conflictoRepository.findAll();
    }

    //Funcion que dado un conflicto lo elimina de la DB
    public void delete (Conflicto conflicto) {
        conflictoRepository.delete(conflicto);
    }

    //Funcion que dado un id devuelve el conflicto correspondiente a ese id
    public Optional<Conflicto> findById (Long id) {
        return conflictoRepository.findById(id);
    }

    //Funcion que dada un aula devuelve todos los conflictos de la misma
    public List<Conflicto> getConflictoAula (String aula){return conflictoRepository.getConflictoAula(aula);}
}
