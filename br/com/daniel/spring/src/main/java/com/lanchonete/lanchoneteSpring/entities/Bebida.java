package com.lanchonete.lanchoneteSpring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_bebida")
@Data
@NoArgsConstructor
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
    private int qtd;

    @JsonIgnore
    @ManyToMany(mappedBy = "bebidas")
    private List<Pedido> pedidoBebida;

    public Bebida(Long id, String nome, String marca, String litragem, String sabor, double preco) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
        this.litragem = litragem;
        this.sabor = sabor;
        this.preco = preco;
        this.qtd = 0;

    }

}
