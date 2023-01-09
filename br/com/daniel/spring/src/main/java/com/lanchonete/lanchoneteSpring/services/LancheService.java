package com.lanchonete.lanchoneteSpring.services;

import com.lanchonete.lanchoneteSpring.entities.Lanche;
import com.lanchonete.lanchoneteSpring.entities.Pedido;
import com.lanchonete.lanchoneteSpring.repositories.LancheRepository;
import com.lanchonete.lanchoneteSpring.services.exceptions.DatabaseException;
import com.lanchonete.lanchoneteSpring.services.exceptions.ResourceNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@Service
public class LancheService {

    private LancheRepository repository;

    private PedidoService pedidoService;

    public LancheService(LancheRepository repository, @Lazy PedidoService pedidoService) {
        this.repository = repository;
        this.pedidoService = pedidoService;
    }

    public Lanche insert(Lanche lanche) {
        format(lanche);
        return repository.save(lanche);
    }

    public List<Lanche> insertAll(List<Lanche> lancheList) {
        for (Lanche l : lancheList) {
            insert(l);
        }
        return lancheList;
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
        Optional<Lanche> lanche = repository.findById(id);
        return lanche.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public void delete(Long id) {
        Lanche lanche = findById(id);
        try {
            repository.delete(lanche);
        } catch (
                EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (
                DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Lanche update(Long id, Lanche lancheNovo) {
        Lanche lanche = findById(id);
        try {
            lanche = updateData(lanche, lancheNovo);
            return repository.save(lanche);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private Lanche updateData(Lanche lanche, Lanche lancheNovo) {
        lanche.setNome(lancheNovo.getNome());
        lanche.setPreco(lancheNovo.getPreco());
        format(lanche);
        lanche.setDescricao(lancheNovo.getDescricao());

        for (Pedido pedido : pedidoService.findAll()) {
            pedido.calcTotal();
        }
        return lanche;
    }

    private void format(Lanche lanche) {
        DecimalFormat format = new DecimalFormat("#,##0.00");
        format.setRoundingMode(RoundingMode.DOWN);
        lanche.setPreco(Double.parseDouble(format.format(lanche.getPreco()).replaceAll("\\.", "").replace(",", ".")));
    }

}