package com.lanchonete.lanchoneteSpring.repositories;

import com.lanchonete.lanchoneteSpring.entities.Bebida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBebidaRepository extends JpaRepository<Bebida, Long> {
}
