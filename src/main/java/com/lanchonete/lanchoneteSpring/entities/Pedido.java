package com.lanchonete.lanchoneteSpring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lanchonete.lanchoneteSpring.entities.enums.TipoPagamento;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedidoLanche")
    private List<Lanche> lanches = new ArrayList<>();

    private Integer tipoPagamento;

    @OneToMany(mappedBy = "pedidoBebida")
    private List<Bebida> bebidas = new ArrayList<>();
    private int qtdLanches;
    private int qtdBebidas;

    private double total;

    public Pedido() {

    }

    public Pedido(Long id, Cliente cliente, TipoPagamento tipoPagamento) {
        this.id = id;
        this.cliente = cliente;
        this.qtdLanches = 0;
        this.qtdBebidas = 0;
        this.lanches = lanches;
        this.bebidas = bebidas;
        setTipoPagamento(tipoPagamento);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Lanche> getLanches() {
        return lanches;
    }

    public List<Bebida> getBebidas() {
        for (Bebida b : bebidas) {
            qtdBebidas += 1;
        }
        return bebidas;
    }

    public int getQtdLanches() {
        for (Lanche l : lanches) {
            qtdLanches += 1;
        }
        return qtdLanches;
    }

    public void setQtdLanches(int qtdLanches) {
        this.qtdLanches = qtdLanches;
    }

    public int getQtdBebidas() {
        return qtdBebidas;
    }

    public void setQtdBebidas(int qtdBebidas) {
        this.qtdBebidas = qtdBebidas;
    }

    public double getTotal() {
        for (Lanche l : lanches) {
            total += l.getPreco();
        }
        for (Bebida b : bebidas) {
            total += b.getPreco();
        }
        return total;
    }

    public TipoPagamento getTipoPagamento() {
        return TipoPagamento.valueOf(tipoPagamento);
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        if (tipoPagamento != null) {
            this.tipoPagamento = tipoPagamento.getCodigo();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return id.equals(pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
