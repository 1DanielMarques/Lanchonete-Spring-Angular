package com.lanchonete.lanchoneteSpring.services;

import com.lanchonete.lanchoneteSpring.entities.Cliente;
import com.lanchonete.lanchoneteSpring.repositories.IClienteRepository;
import com.lanchonete.lanchoneteSpring.services.exceptions.DatabaseException;
import com.lanchonete.lanchoneteSpring.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    IClienteRepository repository;

    public Cliente insert(Cliente obj) {
        return repository.save(obj);
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente findById(Long id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public void delete(Long id) {
        Cliente obj = findById(id);
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

    public Cliente update(Long id, Cliente obj) {
        Cliente c1 = findById(id);
        c1 = updateData(c1, obj);
        return repository.save(c1);
    }

    private Cliente updateData(Cliente c1, Cliente c2) {
        c1.setNome(c2.getNome());
        c1.setEndereco(c2.getEndereco());
        c1.setTelefone(c2.getTelefone());
        c1.setAniversario(c2.getAniversario());
        return c1;
    }

}
