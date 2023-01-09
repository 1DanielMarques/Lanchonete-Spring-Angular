package com.lanchonete.lanchoneteSpring.services;

import com.lanchonete.lanchoneteSpring.entities.Bebida;
import com.lanchonete.lanchoneteSpring.entities.Pedido;
import com.lanchonete.lanchoneteSpring.repositories.BebidaRepository;
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
public class BebidaService {

    private BebidaRepository repository;

    private PedidoService pedidoService;

    public BebidaService(BebidaRepository repository, @Lazy PedidoService pedidoService) {
        this.repository = repository;
        this.pedidoService = pedidoService;
    }

    public Bebida insert(Bebida bebida) {
        format(bebida);
        return repository.save(bebida);
    }

    public List<Bebida> insertAll(List<Bebida> bebidaList) {
        for (Bebida b : bebidaList) {
            insert(b);
        }
        return bebidaList;
    }

    public List<Bebida> findAll() {
        for (Bebida bebida : repository.findAll()) {
            bebida.setQtd(0);
        }
        return repository.findAll();
    }

    public List<Bebida> findBebidasPedido(Long id) {
        Pedido pedido = pedidoService.findById(id);
        int aux;
        for (int i = 0; i < pedido.getBebidas().size(); i++) {
            aux = 1;
            for (int j = 0; j < pedido.getBebidas().size(); j++) {
                if (pedido.getBebidas().get(i).getId() == pedido.getBebidas().get(j).getId()) {
                    pedido.getBebidas().get(i).setQtd(aux);
                    aux++;
                }
            }
        }

        for (int i = 0; i < repository.findAll().size(); i++) {
            Bebida bebida = repository.findAll().get(i);
            if (!pedido.getBebidas().contains(bebida)) {
                bebida.setQtd(0);
            }
        }
        for (Bebida bebida : pedido.getBebidas()) {
            insert(bebida);
        }
        return repository.findAll();
    }

    public Bebida findById(Long id) {
        Optional<Bebida> bebida = repository.findById(id);
        return bebida.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public void delete(Long id) {
        Bebida bebida = findById(id);
        try {
            repository.delete(bebida);
        } catch (
                EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (
                DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Bebida update(Long id, Bebida bebidaNova) {
        Bebida bebida = findById(id);
        try {
            bebida = updateData(bebida, bebidaNova);
            return repository.save(bebida);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private Bebida updateData(Bebida bebida, Bebida bebidaNova) {
        bebida.setNome(bebidaNova.getNome());
        bebida.setMarca(bebidaNova.getMarca());
        bebida.setLitragem(bebidaNova.getLitragem());
        bebida.setSabor(bebidaNova.getSabor());
        bebida.setPreco(bebidaNova.getPreco());
        format(bebida);
        for (Pedido pedido : pedidoService.findAll()) {
            pedido.calcTotal();
        }
        return bebida;
    }

    private void format(Bebida bebida) {
        DecimalFormat format = new DecimalFormat("#,##0.00");
        format.setRoundingMode(RoundingMode.DOWN);
        bebida.setPreco(Double.parseDouble(format.format(bebida.getPreco()).replaceAll("\\.", "").replace(",", ".")));
    }

}