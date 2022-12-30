package com.lanchonete.lanchoneteSpring.services;

import com.lanchonete.lanchoneteSpring.entities.Bebida;
import com.lanchonete.lanchoneteSpring.entities.Lanche;
import com.lanchonete.lanchoneteSpring.entities.Pedido;

public class CalculoTotalImpl implements ICalculoTotal {
    @Override
    public double calculoTotal(Pedido pedido) {
        this.calculoTaxa(pedido);
        double total = 0;
        for (Lanche l : pedido.getLanches()) {
            total += l.getPreco();
        }
        for (Bebida b : pedido.getBebidas()) {
            total += b.getPreco();
        }
        return total + pedido.getTaxa();
    }

    @Override
    public void calculoTaxa(Pedido pedido) {
        switch (pedido.getTipoPagamento().getCodigo()) {
            case 1:
                pedido.setTaxa(0);
                break;
            case 2:
                pedido.setTaxa(1.25);
                break;
            case 3:
                pedido.setTaxa(2.75);
                break;
        }
    }
}
