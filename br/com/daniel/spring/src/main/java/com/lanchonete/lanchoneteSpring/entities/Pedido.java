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

    private Integer tipoPagamento;

    private double taxa;


    private int qtdLanches;
    private int qtdBebidas;

    private double total;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    public Pedido() {

    }

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

    public void setLanches(List<Lanche> lanches) {
        this.lanches = lanches;
    }

    public List<Bebida> getBebidas() {
        return bebidas;
    }


    public void setBebidas(List<Bebida> bebidas) {
        this.bebidas = bebidas;
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
