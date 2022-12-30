package com.lanchonete.lanchoneteSpring.entities.enums;

public enum TipoPagamento {

    DINHEIRO(1),
    DEBITO(2),
    CREDITO(3);

    private int codigo;

    TipoPagamento(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static TipoPagamento valueOf(int code) {
        for (TipoPagamento value : TipoPagamento.values()) {
            if (value.getCodigo() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code");
    }


}
