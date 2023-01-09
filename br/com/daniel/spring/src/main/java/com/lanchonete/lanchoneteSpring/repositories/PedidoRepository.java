package com.lanchonete.lanchoneteSpring.repositories;

import com.lanchonete.lanchoneteSpring.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
