package com.lanchonete.lanchoneteSpring.config;

import com.lanchonete.lanchoneteSpring.entities.Cliente;
import com.lanchonete.lanchoneteSpring.repositories.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Scanner;

//@Configuration
public class Test implements CommandLineRunner {

    @Autowired
    IClienteRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("Nome: ");
        String nome = sc.next();

        Cliente c = new Cliente(null, nome, "1", new Date(), null);

        repository.save(c);


    }
}
