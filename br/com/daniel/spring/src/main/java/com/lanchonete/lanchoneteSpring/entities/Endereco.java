package com.lanchonete.lanchoneteSpring.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_endereco")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    @Setter
    private String bairro;
    @Getter
    @Setter
    private String rua;
    @Getter
    @Setter
    private int numero;

    @OneToOne(mappedBy = "endereco")
    private Pedido pedido;

    public Endereco(Long id, String bairro, String rua, int numero) {
        this.id = id;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }


}
