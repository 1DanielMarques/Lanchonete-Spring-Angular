package com.lanchonete.lanchoneteSpring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_lanche")
public class Lanche implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double preco;
    private String descricao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pedidoLanche_id")
    private Pedido pedidoLanche;

    public Lanche() {

    }

    public Lanche(Long id, String nome, double preco, String descricao, Pedido pedido) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.pedidoLanche = pedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Pedido getPedidoLanche() {
        return pedidoLanche;
    }

    public void setPedidoLanche(Pedido pedidoLanche) {
        this.pedidoLanche = pedidoLanche;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lanche lanche = (Lanche) o;
        return id.equals(lanche.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
