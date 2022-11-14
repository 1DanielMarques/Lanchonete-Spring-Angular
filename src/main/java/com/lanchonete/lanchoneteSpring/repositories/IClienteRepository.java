package com.lanchonete.lanchoneteSpring.repositories;

import com.lanchonete.lanchoneteSpring.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {
}
