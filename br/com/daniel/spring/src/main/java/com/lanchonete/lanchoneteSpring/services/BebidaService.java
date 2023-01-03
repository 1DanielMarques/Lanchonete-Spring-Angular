package com.lanchonete.lanchoneteSpring.services;

import com.lanchonete.lanchoneteSpring.entities.Bebida;
import com.lanchonete.lanchoneteSpring.repositories.IBebidaRepository;
import com.lanchonete.lanchoneteSpring.services.exceptions.DatabaseException;
import com.lanchonete.lanchoneteSpring.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class BebidaService {

    private IBebidaRepository repository;

    public BebidaService(IBebidaRepository repository) {
        this.repository = repository;
    }

    public Bebida insert(Bebida obj) {
        return repository.save(obj);
    }

    public List<Bebida> insertAll(List<Bebida> obj) {
        for (Bebida b : obj) {
            insert(b);
        }
        return obj;
    }

    public List<Bebida> findAll() {
        return repository.findAll();
    }

    public Bebida findById(Long id) {
        Optional<Bebida> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public void delete(Long id) {
        Bebida obj = findById(id);
        try {
            repository.delete(obj);
        } catch (
                EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (
                DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Bebida update(Long id, Bebida obj) {
        Bebida b1 = findById(id);
        try {
            b1 = updateData(b1, obj);
            return repository.save(b1);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private Bebida updateData(Bebida b1, Bebida b2) {
        b1.setNome(b2.getNome());
        b1.setMarca(b2.getMarca());
        b1.setLitragem(b2.getLitragem());
        b1.setSabor(b2.getSabor());
        b1.setPreco(b2.getPreco());
        return b1;
    }

}