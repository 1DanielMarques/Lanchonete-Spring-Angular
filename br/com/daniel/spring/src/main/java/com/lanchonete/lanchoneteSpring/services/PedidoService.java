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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private IPedidoRepository repository;
    private LancheService lancheService;
    private BebidaService bebidaService;
    private EnderecoService enderecoService;


    public PedidoService(IPedidoRepository repository, LancheService lancheService, BebidaService bebidaService, EnderecoService enderecoService) {
        this.repository = repository;
        this.lancheService = lancheService;
        this.bebidaService = bebidaService;
        this.enderecoService = enderecoService;
    }

    public Pedido insert(String jsonRequest) {
        String json = jsonRequest;

        JSONObject obj = new JSONObject(json);

        TipoPagamento tipoPagamento = TipoPagamento.valueOf(obj.getString("tipoPagamento").toUpperCase());

        String bairro = obj.getJSONObject("endereco").getString("bairro");
        String rua = obj.getJSONObject("endereco").getString("rua");
        int numero = obj.getJSONObject("endereco").getInt("numero");
        Endereco endereco = new Endereco(null, bairro, rua, numero);
        enderecoService.insert(endereco);

        JSONArray lanches = obj.getJSONArray("lanches");
        List<Lanche> lancheList = new ArrayList<>();
        for (int i = 0; i < lanches.length(); i++) {
            Long id = lanches.getJSONObject(i).getLong("id");
            Lanche lanche = lancheService.findById(id);
            lancheList.add(lanche);
        }

        JSONArray bebidas = obj.getJSONArray("bebidas");
        List<Bebida> bebidaList = new ArrayList<>();
        for (int i = 0; i < bebidas.length(); i++) {
            Long id = bebidas.getJSONObject(i).getLong("id");
            Bebida bebida = bebidaService.findById(id);
            bebidaList.add(bebida);
        }

        Pedido p = new Pedido(null, lancheList, bebidaList, tipoPagamento, endereco);
        p.setTaxa(p.getTaxa());
        p.setTotal(p.getTotal());
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

    public boolean findItem(Long id, String item) {
        switch (item) {
            case "lanche":
                Lanche lanche = lancheService.findById(id);
                for (Pedido p : findAll()) {
                    if (p.getLanches().contains(lanche)) {
                        return true;
                    }
                }
                break;
            case "bebida":
                Bebida bebida = bebidaService.findById(id);
                for (Pedido p : findAll()) {
                    if (p.getBebidas().contains(bebida)) {
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    public Pedido findById(Long id) {
        Optional<Pedido> pedido = repository.findById(id);
        return pedido.orElseThrow(() -> new ResourceNotFoundException(id));
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

    public void deleteItem(Long id, String item) {
        switch (item) {
            case "lanche":
                Lanche lanche = lancheService.findById(id);
                for (Pedido pedido : findAll()) {
                    if (pedido.getLanches().contains(lanche)) {
                        int size = pedido.getLanches().size();
                        for (int i = 0; i < size; i++) {
                            if (pedido.getLanches().contains(lanche)) {
                                pedido.getLanches().remove(lanche);
                            }
                        }
                    }
                    pedido.calcTotal();
                    ;
                }
                lancheService.delete(id);
                break;
            case "bebida":
                Bebida bebida = bebidaService.findById(id);
                for (Pedido pedido : findAll()) {
                    if (pedido.getBebidas().contains(bebida)) {
                        int size = pedido.getBebidas().size();
                        for (int i = 0; i < size; i++) {
                            if (pedido.getBebidas().contains(bebida)) {
                                pedido.getBebidas().remove(bebida);
                            }
                        }
                    }
                    pedido.calcTotal();
                }
                bebidaService.delete(id);
                break;
        }
    }

    public void updateQtd() {
        for (Pedido pedido : findAll()) {
            int qtd = 0;
            for (int i = 0; i < pedido.getLanches().size(); i++) {
                qtd++;
            }
            pedido.setQtdLanches(qtd);
            qtd = 0;
            for (int i = 0; i < pedido.getBebidas().size(); i++) {
                qtd++;
            }
            pedido.setQtdBebidas(qtd);
            repository.save(pedido);
        }

    }

    public Pedido update(Long id, String json) {
        Pedido pedido = findById(id);
        try {
            pedido = updateData(pedido, json);
            return repository.save(pedido);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private Pedido updateData(Pedido pedido, String jsonRequest) {
        String json = jsonRequest;

        JSONObject obj = new JSONObject(json);

        TipoPagamento tipoPagamento = TipoPagamento.valueOf(obj.getString("tipoPagamento"));
        pedido.setTipoPagamento(tipoPagamento);

        String bairro = obj.getJSONObject("endereco").getString("bairro");
        String rua = obj.getJSONObject("endereco").getString("rua");
        int numero = obj.getJSONObject("endereco").getInt("numero");
        pedido.getEndereco().setBairro(bairro);
        pedido.getEndereco().setRua(rua);
        pedido.getEndereco().setNumero(numero);

        JSONArray lanches = obj.getJSONArray("lanches");
        List<Lanche> lancheList = new ArrayList<>();
        int qtdLanches = 0;
        for (int i = 0; i < lanches.length(); i++) {
            Long id = lanches.getJSONObject(i).getLong("id");
            lancheList.add(lancheService.findById(id));
            qtdLanches++;
        }
        pedido.setQtdLanches(qtdLanches);
        pedido.setLanches(lancheList);

        JSONArray bebidas = obj.getJSONArray("bebidas");
        List<Bebida> bebidaList = new ArrayList<>();
        int qtdBebidas = 0;
        for (int i = 0; i < bebidas.length(); i++) {
            Long id = bebidas.getJSONObject(i).getLong("id");
            bebidaList.add(bebidaService.findById(id));
            qtdBebidas++;
        }
        pedido.setQtdBebidas(qtdBebidas);
        pedido.setBebidas(bebidaList);
        pedido.calcTaxa();
        pedido.calcTotal();

        return pedido;
    }

}