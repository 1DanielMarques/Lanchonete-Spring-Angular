package com.lanchonete.lanchoneteSpring.services;

import com.lanchonete.lanchoneteSpring.entities.Bebida;
import com.lanchonete.lanchoneteSpring.repositories.IBebidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BebidaService {

    @Autowired
    IBebidaRepository repository;

    public Bebida insert(Bebida obj) {
        return repository.save(obj);
    }

    public List<Bebida> findAll() {
        return repository.findAll();
    }

    public Bebida findById(Long id) {
        Optional<Bebida> obj = repository.findById(id);
        return obj.orElseThrow();
    }

    public void delete(Long id) {
        Bebida obj = findById(id);
        repository.delete(obj);
    }

    public Bebida update(Long id, Bebida obj) {
        Bebida b1 = findById(id);
        b1 = updateData(b1, obj);
        return repository.save(b1);
    }

    private Bebida updateData(Bebida b1, Bebida b2) {
        b1.setNome(b2.getNome());
        b1.setMarca(b2.getMarca());
        b1.setLitragem(b2.getLitragem());
        b1.setPreco(b2.getPreco());
        return b1;
    }

}
