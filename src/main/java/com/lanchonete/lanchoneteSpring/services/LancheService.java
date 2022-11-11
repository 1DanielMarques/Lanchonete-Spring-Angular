package com.lanchonete.lanchoneteSpring.services;

import com.lanchonete.lanchoneteSpring.entities.Lanche;
import com.lanchonete.lanchoneteSpring.repositories.ILancheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LancheService {

    @Autowired
    ILancheRepository repository;

    public Lanche insert(Lanche obj) {
        return repository.save(obj);
    }

    public List<Lanche> findAll() {
        return repository.findAll();
    }

    public Lanche findById(Long id) {
        Optional<Lanche> obj = repository.findById(id);
        return obj.orElseThrow();
    }

}
