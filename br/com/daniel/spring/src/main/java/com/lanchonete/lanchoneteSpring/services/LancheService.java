package com.lanchonete.lanchoneteSpring.services;

import com.lanchonete.lanchoneteSpring.entities.Lanche;
import com.lanchonete.lanchoneteSpring.entities.Pedido;
import com.lanchonete.lanchoneteSpring.repositories.ILancheRepository;
import com.lanchonete.lanchoneteSpring.services.exceptions.DatabaseException;
import com.lanchonete.lanchoneteSpring.services.exceptions.ResourceNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class LancheService {

    private ILancheRepository repository;

    private PedidoService pedidoService;

    public LancheService(ILancheRepository repository, @Lazy PedidoService pedidoService) {
        this.repository = repository;
        this.pedidoService = pedidoService;
    }

    public Lanche insert(Lanche lanche) {
        format(lanche);
        return repository.save(lanche);
    }

    public List<Lanche> insertAll(List<Lanche> obj) {
        for (Lanche l : obj) {
            insert(l);
        }
        return obj;
    }

    public List<Lanche> findAll() {
        for (Lanche lanche : repository.findAll()) {
            lanche.setQtd(0);
        }
        return repository.findAll();
    }

    public List<Lanche> findLanchesPedido(Long id) {
        Pedido pedido = pedidoService.findById(id);
        int aux;
        for (int i = 0; i < pedido.getLanches().size(); i++) {
            aux = 1;
            for (int j = 0; j < pedido.getLanches().size(); j++) {
                if (pedido.getLanches().get(i).getId() == pedido.getLanches().get(j).getId()) {
                    pedido.getLanches().get(i).setQtd(aux);
                    aux++;
                }
            }
        }

        for (int i = 0; i < repository.findAll().size(); i++) {
            Lanche lanche = repository.findAll().get(i);
            if (!pedido.getLanches().contains(lanche)) {
                lanche.setQtd(0);
            }
        }

        for (Lanche lanche : pedido.getLanches()) {
            insert(lanche);
        }
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
        try {
            l1 = updateData(l1, obj);
            return repository.save(l1);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private Lanche updateData(Lanche l1, Lanche l2) {
        l1.setNome(l2.getNome());
        l1.setPreco(l2.getPreco());
        format(l1);
        l1.setDescricao(l2.getDescricao());

        for (Pedido pedido : pedidoService.findAll()) {
            pedido.calcTotal();
        }
        return l1;
    }

    private void format(Lanche lanche) {
        DecimalFormat format = new DecimalFormat("#,##0.00");
        format.setRoundingMode(RoundingMode.DOWN);
        lanche.setPreco(Double.parseDouble(format.format(lanche.getPreco()).replaceAll("\\.", "").replace(",", ".")));
    }

}