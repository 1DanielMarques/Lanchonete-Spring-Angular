package com.lanchonete.lanchoneteSpring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_bebida")
public class Bebida implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String marca;
    private String litragem;
    private String sabor;
    private double preco;

    @JsonIgnore
    @ManyToMany(mappedBy = "bebidas")
    private List<Pedido> pedidoBebida;

    public Bebida() {

    }

    public Bebida(Long id, String nome, String marca, String litragem, String sabor, double preco) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.litragem = litragem;
        this.sabor = sabor;
        this.preco = preco;

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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLitragem() {
        return litragem;
    }

    public void setLitragem(String litragem) {
        this.litragem = litragem;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public List<Pedido> getPedidoBebida() {
        return pedidoBebida;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bebida bebida = (Bebida) o;
        return id.equals(bebida.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
