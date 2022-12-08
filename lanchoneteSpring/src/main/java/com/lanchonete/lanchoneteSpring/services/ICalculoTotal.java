package com.lanchonete.lanchoneteSpring.services;

import com.lanchonete.lanchoneteSpring.entities.Pedido;

public interface ICalculoTotal {

    double calculoTotal(Pedido pedido);

    void calculoTaxa(Pedido pedido);

}
