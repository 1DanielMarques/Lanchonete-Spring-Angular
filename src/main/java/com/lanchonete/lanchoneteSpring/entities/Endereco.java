package com.lanchonete.lanchoneteSpring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_endereco")
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bairro;
    private String rua;
    private int numero;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "morador_id")
    private Cliente morador;

    public Endereco() {

    }

    public Endereco(Long id, String bairro, String rua, int numero, Cliente morador) {
        this.id = id;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.morador = morador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Cliente getMorador() {
        return morador;
    }

    public void setMorador(Cliente morador) {
        this.morador = morador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return id.equals(endereco.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
