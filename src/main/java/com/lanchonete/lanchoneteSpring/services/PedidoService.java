package com.lanchonete.lanchoneteSpring.services;

import com.lanchonete.lanchoneteSpring.entities.Pedido;
import com.lanchonete.lanchoneteSpring.repositories.IPedidoRepository;
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
public class PedidoService {

    @Autowired
    IPedidoRepository repository;

    public Pedido insert(Pedido obj) {
        return repository.save(obj);
    }

    public List<Pedido> insertAll(List<Pedido> obj) {
        for (Pedido p : obj) {
            insert(p);
        }
        return obj;
    }

    public List<Pedido> findAll() {
        return repository.findAll();
    }

    public Pedido findById(Long id) {
        Optional<Pedido> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public void delete(Long id) {
        Pedido obj = findById(id);
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

    public Pedido update(Long id, Pedido obj) {
        Pedido p1 = findById(id);
        try {
            p1 = updateData(p1, obj);
            return repository.save(p1);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private Pedido updateData(Pedido p1, Pedido p2) {
        p1.setCliente(p2.getCliente());
        p1.setQtdBebidas(p2.getQtdBebidas());
        p1.setQtdBebidas(p2.getQtdBebidas());
        return p1;
    }

}
