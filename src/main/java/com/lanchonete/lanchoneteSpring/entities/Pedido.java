package com.lanchonete.lanchoneteSpring.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @JsonIgnore
    @OneToMany(mappedBy = "pedido")
    private List<Lanche> lanches = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "pedido")
    private List<Bebida> bebidas = new ArrayList<>();
    private int qtdLanches;
    private int qtdBebidas;

    public Pedido() {

    }

    public Pedido(Long id, Cliente cliente, List<Lanche> lanches, List<Bebida> bebidas, int qtdLanches, int qtdBebidas) {
        this.id = id;
        this.cliente = cliente;
        this.lanches = lanches;
        this.bebidas = bebidas;
        this.qtdLanches = qtdLanches;
        this.qtdBebidas = qtdBebidas;
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
        return bebidas;
    }

    public int getQtdLanches() {
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
