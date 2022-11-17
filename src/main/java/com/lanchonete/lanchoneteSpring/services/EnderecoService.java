package com.lanchonete.lanchoneteSpring.services;

import com.lanchonete.lanchoneteSpring.entities.Endereco;
import com.lanchonete.lanchoneteSpring.repositories.IEnderecoRepository;
import com.lanchonete.lanchoneteSpring.services.exceptions.DatabaseException;
import com.lanchonete.lanchoneteSpring.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    IEnderecoRepository repository;

    public Endereco insert(Endereco obj) {
        return repository.save(obj);
    }

    public List<Endereco> insertAll(List<Endereco> obj) {
        for (Endereco e : obj) {
            insert(e);
        }
        return obj;
    }

    public List<Endereco> findAll() {
        return repository.findAll();
    }

    public Endereco findById(Long id) {
        Optional<Endereco> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public void delete(Long id) {
        Endereco obj = findById(id);
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

    public Endereco update(Long id, Endereco obj) {
        Endereco e1 = findById(id);
        try {
            e1 = updateData(e1, obj);
            return repository.save(e1);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private Endereco updateData(Endereco e1, Endereco e2) {
        e1.setBairro(e2.getBairro());
        e1.setRua(e2.getRua());
        e1.setNumero(e2.getNumero());
        e1.setMorador(e2.getMorador());
        return e1;
    }

}