package com.lanchonete.lanchoneteSpring.services;

import com.lanchonete.lanchoneteSpring.entities.Pedido;
import com.lanchonete.lanchoneteSpring.repositories.IPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    IPedidoRepository repository;

    public Pedido insert(Pedido obj) {
        return repository.save(obj);
    }

    public List<Pedido> findAll() {
        return repository.findAll();
    }

    public Pedido findById(Long id) {
        Optional<Pedido> obj = repository.findById(id);
        return obj.orElseThrow();
    }

    public void delete(Long id) {
        Pedido obj = findById(id);
        repository.delete(obj);
    }

    public Pedido update(Long id, Pedido obj) {
        Pedido p1 = findById(id);
        p1 = updateData(p1, obj);
        return repository.save(p1);
    }

    private Pedido updateData(Pedido p1, Pedido p2) {
        p1.setCliente(p2.getCliente());
        p1.setQtdBebidas(p2.getQtdBebidas());
        p1.setQtdBebidas(p2.getQtdBebidas());
        return p1;
    }

}
