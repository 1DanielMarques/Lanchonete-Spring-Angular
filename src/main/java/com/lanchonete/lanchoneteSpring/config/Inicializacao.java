package com.lanchonete.lanchoneteSpring.config;

import com.lanchonete.lanchoneteSpring.entities.Bebida;
import com.lanchonete.lanchoneteSpring.entities.Lanche;
import com.lanchonete.lanchoneteSpring.repositories.IBebidaRepository;
import com.lanchonete.lanchoneteSpring.repositories.ILancheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Inicializacao implements CommandLineRunner {

    @Autowired
    private ILancheRepository lancheRepository;

    @Autowired
    private IBebidaRepository bebidaRepository;

    @Override
    public void run(String... args) throws Exception {

        Lanche l1 = new Lanche(null, "X-Frango", 10.0, "Descricao do X-Frango");
        Lanche l2 = new Lanche(null, "X-Bacon", 16.55, "Descricao do X-Bacon");

        Bebida b1 = new Bebida(null, "Refrigerante", "Coca-Cola", "2L", "Cola", 12.55);
        Bebida b2 = new Bebida(null, "Suco", "Natu", "1L", "Laranja", 8.00);

        lancheRepository.saveAll(Arrays.asList(l1, l2));
        bebidaRepository.saveAll(Arrays.asList(b1, b2));
    }
}
