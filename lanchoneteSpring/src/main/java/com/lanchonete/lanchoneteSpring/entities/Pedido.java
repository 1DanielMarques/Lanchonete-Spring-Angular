package com.lanchonete.lanchoneteSpring.entities;

import com.lanchonete.lanchoneteSpring.entities.enums.TipoPagamento;
import com.lanchonete.lanchoneteSpring.services.CalculoTotalImpl;

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

    @OneToMany
    private List<Lanche> lanches = new ArrayList<>();

    @OneToMany
    private List<Bebida> bebidas = new ArrayList<>();

    private Integer tipoPagamento;

    private double taxa;


    private int qtdLanches;
    private int qtdBebidas;

    private double total;

    @OneToOne
    @JoinColumn(name = "pedido_endereco")
    private Endereco endereco;

    public Pedido() {

    }

    public Pedido(Long id, List<Lanche> lanches, List<Bebida> bebidas, TipoPagamento tipoPagamento, Endereco endereco) {
        this.id = id;
        this.lanches = lanches;
        this.bebidas = bebidas;
        for (Lanche l : lanches) {
            this.qtdLanches += 1;
        }
        for (Bebida b : bebidas) {
            this.qtdBebidas += 1;
        }
        this.endereco = endereco;
        setTipoPagamento(tipoPagamento);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getTotal() {
        CalculoTotalImpl calc = new CalculoTotalImpl();
        this.total = calc.calculoTotal(this);
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTaxa() {
        CalculoTotalImpl calc = new CalculoTotalImpl();
        calc.calculoTaxa(this);
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }

    public TipoPagamento getTipoPagamento() {
        return TipoPagamento.valueOf(tipoPagamento);
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        if (tipoPagamento != null) {
            this.tipoPagamento = tipoPagamento.getCodigo();
        }
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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
