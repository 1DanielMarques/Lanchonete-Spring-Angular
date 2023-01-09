package com.lanchonete.lanchoneteSpring.repositories;

import com.lanchonete.lanchoneteSpring.entities.Lanche;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancheRepository extends JpaRepository<Lanche, Long> {
}
