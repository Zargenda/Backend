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
    public Aula create (Aula aula) {
        return aulaRepository.save(aula);
    }

    public List<Aula> getAllAulas (){
        return aulaRepository.findAll();
    }

    public void delete (Aula aula) {
        aulaRepository.delete(aula);
    }

    public Optional<Aula> findById (Long id) {
        return aulaRepository.findById(id);
    }
}
