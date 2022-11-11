package com.lanchonete.lanchoneteSpring.config;

import com.lanchonete.lanchoneteSpring.entities.Lanche;
import com.lanchonete.lanchoneteSpring.repositories.ILancheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Inicializacao implements CommandLineRunner {

    @Autowired
    private ILancheRepository lancheRepository;

    @Override
    public void run(String... args) throws Exception {

        Lanche l1 = new Lanche(null, "X-Frango", 10.0, "Descricao do X-Frango");
        Lanche l2 = new Lanche(null, "X-Bacon", 16.55, "Descricao do X-Bacon");

        lancheRepository.saveAll(Arrays.asList(l1, l2));
    }
}
