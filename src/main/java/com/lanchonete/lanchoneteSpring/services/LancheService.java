package com.lanchonete.lanchoneteSpring.services;

import com.lanchonete.lanchoneteSpring.entities.Lanche;
import com.lanchonete.lanchoneteSpring.repositories.ILancheRepository;
import com.lanchonete.lanchoneteSpring.services.exceptions.DatabaseException;
import com.lanchonete.lanchoneteSpring.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public void delete(Long id) {
        Lanche obj = findById(id);
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

    public Lanche update(Long id, Lanche obj) {
        Lanche l1 = findById(id);
        l1 = updateData(l1, obj);
        return repository.save(l1);
    }

    private Lanche updateData(Lanche l1, Lanche l2) {
        l1.setNome(l2.getNome());
        l1.setPreco(l2.getPreco());
        l1.setDescricao(l2.getDescricao());
        return l1;
    }

}
