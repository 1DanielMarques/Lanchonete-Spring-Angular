package com.lanchonete.lanchoneteSpring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotBlank
    @NotNull
    @Length(max = 50)
    @Column(length = 50, nullable = false)
    private String nome;

    @NotNull
    @Column(nullable = false)
    private double preco;

    @NotBlank
    @NotNull
    @Length(min = 5, max = 150)
    @Column(length = 150, nullable = false)
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
