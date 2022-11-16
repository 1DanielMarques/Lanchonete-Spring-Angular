package com.lanchonete.lanchoneteSpring.services;

import com.lanchonete.lanchoneteSpring.entities.Endereco;
import com.lanchonete.lanchoneteSpring.repositories.IEnderecoRepository;
import com.lanchonete.lanchoneteSpring.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    IEnderecoRepository repository;

    public Endereco insert(Endereco obj) {
        return repository.save(obj);
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
        repository.delete(obj);
    }

    public Endereco update(Long id, Endereco obj) {
        Endereco e1 = findById(id);
        e1 = updateData(e1, obj);
        return repository.save(e1);
    }

    private Endereco updateData(Endereco e1, Endereco e2) {
        e1.setBairro(e2.getBairro());
        e1.setRua(e2.getRua());
        e1.setNumero(e2.getNumero());
        e1.setMorador(e2.getMorador());
        return e1;
    }

}
