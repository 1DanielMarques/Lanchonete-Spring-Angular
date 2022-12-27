package com.lanchonete.lanchoneteSpring.services;

import com.lanchonete.lanchoneteSpring.entities.Bebida;
import com.lanchonete.lanchoneteSpring.entities.Endereco;
import com.lanchonete.lanchoneteSpring.entities.Lanche;
import com.lanchonete.lanchoneteSpring.entities.Pedido;
import com.lanchonete.lanchoneteSpring.entities.enums.TipoPagamento;
import com.lanchonete.lanchoneteSpring.repositories.IPedidoRepository;
import com.lanchonete.lanchoneteSpring.services.exceptions.DatabaseException;
import com.lanchonete.lanchoneteSpring.services.exceptions.ResourceNotFoundException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    IPedidoRepository repository;

    @Autowired
    LancheService lancheService;

    @Autowired
    BebidaService bebidaService;

    @Autowired
    EnderecoService enderecoService;


    public Pedido insert(String jsonRequest) {
        String json = jsonRequest;

        JSONObject obj = new JSONObject(json);

        TipoPagamento tipoPagamento = TipoPagamento.valueOf(obj.getString("tipoPagamento"));

        String bairro = obj.getJSONObject("endereco").getString("bairro");
        String rua = obj.getJSONObject("endereco").getString("rua");
        int numero = obj.getJSONObject("endereco").getInt("numero");
        Endereco endereco = new Endereco(null, bairro, rua, numero);
        enderecoService.insert(endereco);

        JSONArray lanches = obj.getJSONArray("lanches");
        List<Lanche> lancheList = new ArrayList<>();
        for (int i = 0; i < lanches.length(); i++) {
            Long id = lanches.getJSONObject(i).getLong("id");
            Lanche found = lancheService.findById(id);
            Lanche l = new Lanche(null, found.getNome(), found.getPreco(), found.getDescricao());
            lancheService.insert(l);
            lancheList.add(l);
        }

        JSONArray bebidas = obj.getJSONArray("bebidas");
        List<Bebida> bebidaList = new ArrayList<>();
        for (int i = 0; i < bebidas.length(); i++) {
            Long id = bebidas.getJSONObject(i).getLong("id");
            Bebida found = bebidaService.findById(id);
            Bebida b = new Bebida(null, found.getNome(), found.getMarca(), found.getLitragem(), found.getSabor(), found.getPreco());
            bebidaService.insert(b);
            bebidaList.add(b);
        }

        Pedido p = new Pedido(null, lancheList, bebidaList, tipoPagamento, endereco);

        return repository.save(p);
    }

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
        CalculoTotalImpl calc = new CalculoTotalImpl();
        p1.setTipoPagamento(p2.getTipoPagamento());

        p1.setEndereco(p2.getEndereco());


        p1.setLanches(p2.getLanches());
        for (Lanche l : p2.getLanches()) {
            l.setPedidoLanche(p1);
        }


        p1.setBebidas(p2.getBebidas());
        for (Bebida b : p2.getBebidas()) {
            b.setPedidoBebida(p1);
        }


        return p1;
    }

}