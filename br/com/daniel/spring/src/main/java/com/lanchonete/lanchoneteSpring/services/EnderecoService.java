package com.lanchonete.lanchoneteSpring.services;

import com.lanchonete.lanchoneteSpring.entities.Endereco;
import com.lanchonete.lanchoneteSpring.repositories.EnderecoRepository;
import com.lanchonete.lanchoneteSpring.services.exceptions.DatabaseException;
import com.lanchonete.lanchoneteSpring.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private EnderecoRepository repository;

    public EnderecoService(EnderecoRepository repository) {
        this.repository = repository;
    }

    public Endereco insert(Endereco endereco) {
        return repository.save(endereco);
    }

    public List<Endereco> insertAll(List<Endereco> enderecoList) {
        for (Endereco e : enderecoList) {
            insert(e);
        }
        return enderecoList;
    }

    public List<Endereco> findAll() {
        return repository.findAll();
    }

    public Endereco findById(Long id) {
        Optional<Endereco> endereco = repository.findById(id);
        return endereco.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public void delete(Long id) {
        try {
            repository.delete(findById(id));
        } catch (
                EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (
                DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Endereco update(Long id, Endereco enderecoNovo) {
        Endereco endereco = findById(id);
        try {
            endereco = updateData(endereco, enderecoNovo);
            return repository.save(endereco);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private Endereco updateData(Endereco endereco, Endereco enderecoNovo) {
        endereco.setBairro(enderecoNovo.getBairro());
        endereco.setRua(enderecoNovo.getRua());
        endereco.setNumero(enderecoNovo.getNumero());
        return endereco;
    }

}