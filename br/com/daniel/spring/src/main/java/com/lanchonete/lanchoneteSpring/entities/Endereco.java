package com.lanchonete.lanchoneteSpring.entities;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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

    @NotBlank
    @NotNull
    @Getter
    @Setter
    @Length(max = 50)
    @Column(length = 50, nullable = false)
    private String bairro;

    @NotBlank
    @NotNull
    @Getter
    @Setter
    @Length(max = 50)
    @Column(length = 50, nullable = false)
    private String rua;

    @NotNull
    @Getter
    @Setter
    @Column(nullable = false)
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
