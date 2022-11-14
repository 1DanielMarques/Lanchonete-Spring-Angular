package com.lanchonete.lanchoneteSpring.config;

import com.lanchonete.lanchoneteSpring.entities.*;
import com.lanchonete.lanchoneteSpring.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@Configuration
public class Inicializacao implements CommandLineRunner {

    @Autowired
    private ILancheRepository lancheRepository;

    @Autowired
    private IBebidaRepository bebidaRepository;

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IEnderecoRepository enderecoRepository;

    @Autowired
    private IPedidoRepository pedidoRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        Cliente c1 = new Cliente(null, "Daniel Marques", "33397902", sdf.parse("09/09/2003"));
        Cliente c2 = new Cliente(null, "Ricardo Lopes", "2342 8826", sdf.parse("23/05/1997"));

        Endereco e1 = new Endereco(null, "Marieta", "Jose Ruzzon", 133, c1);
        Endereco e2 = new Endereco(null, "Violin", "Maria Sinop", 147, c2);

        Pedido p1 = new Pedido(null, c1);
        Pedido p2 = new Pedido(null, c1);

        Lanche l1 = new Lanche(null, "X-Frango", 10.0, "Descricao do X-Frango", p1);
        Lanche l2 = new Lanche(null, "X-Bacon", 16.55, "Descricao do X-Bacon", p2);

        Bebida b1 = new Bebida(null, "Refrigerante", "Coca-Cola", "2L", "Cola", 12.55, p1);
        Bebida b2 = new Bebida(null, "Suco", "Natu", "1L", "Laranja", 8.00, p2);

        clienteRepository.saveAll(Arrays.asList(c1, c2));
        enderecoRepository.saveAll(Arrays.asList(e1, e2));
        pedidoRepository.saveAll(Arrays.asList(p1, p2));
        lancheRepository.saveAll(Arrays.asList(l1, l2));
        bebidaRepository.saveAll(Arrays.asList(b1, b2));


    }
}
