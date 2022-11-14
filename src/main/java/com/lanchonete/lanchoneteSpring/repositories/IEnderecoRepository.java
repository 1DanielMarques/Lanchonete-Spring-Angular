package com.lanchonete.lanchoneteSpring.repositories;

import com.lanchonete.lanchoneteSpring.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEnderecoRepository extends JpaRepository<Endereco, Long> {
}
