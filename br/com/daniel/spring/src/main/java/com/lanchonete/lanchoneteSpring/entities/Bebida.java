package com.lanchonete.lanchoneteSpring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotBlank
    @NotNull
    @Length(min = 3, max = 50)
    @Column(length = 50, nullable = false)
    private String nome;
    @NotBlank
    @NotNull
    @Length(max = 50)
    @Column(length = 50, nullable = false)
    private String marca;
    @NotBlank
    @NotNull
    @Length(max = 5)
    @Column(length = 5, nullable = false)
    private String litragem;

    @NotBlank
    @Length(max = 50)
    @Column(length = 50)
    private String sabor;

    @NotNull
    @Column(nullable = false)
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
