package com.lanchonete.lanchoneteSpring.entities;

import com.lanchonete.lanchoneteSpring.entities.enums.TipoPagamento;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pedido")
@Data
@NoArgsConstructor
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "pedido_lanche",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "lanche_id"))
    private List<Lanche> lanches = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "pedido_bebida",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "bebida_id"))
    private List<Bebida> bebidas = new ArrayList<>();


    @NotNull
    @Column(length = 10, nullable = false)
    private Integer tipoPagamento;

    @Column(length = 5)
    private double taxa;

    @Column(length = 10)
    private int qtdLanches;

    @Column(length = 10)
    private int qtdBebidas;

    @Column(length = 10)
    private double total;

    @NotNull
    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    public Pedido(Long id, List<Lanche> lanches, List<Bebida> bebidas, TipoPagamento tipoPagamento, Endereco endereco) {
        this.id = id;
        this.endereco = endereco;
        for (Lanche l : lanches) {
            qtdLanches += 1;
        }
        for (Bebida b : bebidas) {
            qtdBebidas += 1;
        }
        this.lanches = lanches;
        this.bebidas = bebidas;
        setTipoPagamento(tipoPagamento);
        calcTaxa();
        calcTotal();
    }

    public void calcTotal() {
        total = 0;
        for (Lanche l : getLanches()) {
            total += l.getPreco();
        }
        for (Bebida b : getBebidas()) {
            total += b.getPreco();
        }
        total += getTaxa();
    }


    public void calcTaxa() {
        switch (getTipoPagamento().getCodigo()) {
            case 1:
                setTaxa(0);
                break;
            case 2:
                setTaxa(1.25);
                break;
            case 3:
                setTaxa(2.75);
                break;
        }
    }

    public TipoPagamento getTipoPagamento() {
        return TipoPagamento.valueOf(tipoPagamento);
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        if (tipoPagamento != null) {
            this.tipoPagamento = tipoPagamento.getCodigo();
        }
    }

}
