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

    public Conflicto create (Conflicto conflicto) {
        return conflictoRepository.save(conflicto);
    }

    public List<Conflicto> getAllConflictos (){
        return conflictoRepository.findAll();
    }

    public void delete (Conflicto conflicto) {
        conflictoRepository.delete(conflicto);
    }

    public Optional<Conflicto> findById (Long id) {
        return conflictoRepository.findById(id);
    }
}
