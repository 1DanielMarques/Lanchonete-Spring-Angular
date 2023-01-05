package com.lanchonete.lanchoneteSpring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_lanche")
@Data
@NoArgsConstructor
public class Lanche implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double preco;
    private String descricao;

    private int qtd;

    @JsonIgnore
    @ManyToMany(mappedBy = "lanches")
    private List<Pedido> pedidoLanche = new ArrayList<>();

    public Lanche(Long id, String nome, double preco, String descricao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.qtd = 0;
    }

}
