package com.backend.Backend.Service;

import com.backend.Backend.model.Conflictos;
import com.backend.Backend.model.HorarioAsignatura;
import com.backend.Backend.repository.ConflictoRepository;
import com.backend.Backend.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConflictoService {
    @Autowired
    private ConflictoRepository conflictoRepository;

    public Conflictos create (Conflictos conflictos) {
        return conflictoRepository.save(conflictos);
    }

    public List<Conflictos> getAllConflictos (){
        return conflictoRepository.findAll();
    }

    public void delete (Conflictos conflicto) {
        conflictoRepository.delete(conflicto);
    }

    public Optional<Conflictos> findById (Long id) {
        return conflictoRepository.findById(id);
    }
}


