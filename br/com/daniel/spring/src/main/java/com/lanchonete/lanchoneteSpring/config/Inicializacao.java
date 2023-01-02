package com.lanchonete.lanchoneteSpring.config;

import com.lanchonete.lanchoneteSpring.entities.Bebida;
import com.lanchonete.lanchoneteSpring.entities.Endereco;
import com.lanchonete.lanchoneteSpring.entities.Lanche;
import com.lanchonete.lanchoneteSpring.entities.Pedido;
import com.lanchonete.lanchoneteSpring.entities.enums.TipoPagamento;
import com.lanchonete.lanchoneteSpring.services.BebidaService;
import com.lanchonete.lanchoneteSpring.services.EnderecoService;
import com.lanchonete.lanchoneteSpring.services.LancheService;
import com.lanchonete.lanchoneteSpring.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;

//@Configuration
public class Inicializacao implements CommandLineRunner {


    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private LancheService lancheService;

    @Autowired
    private BebidaService bebidaService;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Endereco e1 = new Endereco(null, "Via Lactea", "Saturno", 255);
        Endereco e2 = new Endereco(null, "Violin", "Maria Sinop", 147);
        Endereco e3 = new Endereco(null, "Bandeirantes", "Rua Teste", 222);
        enderecoService.insertAll(Arrays.asList(e1, e2, e3));

        Lanche l1 = new Lanche(null, "X-Frango", 10.0, "Descricao do X-Frango");
        Lanche l2 = new Lanche(null, "X-Bacon", 16.55, "Descricao do X-Bacon");
        lancheService.insertAll(Arrays.asList(l1, l2));

        Bebida b1 = new Bebida(null, "Refrigerante", "Coca-Cola", "2L", "Cola", 12.55);
        Bebida b2 = new Bebida(null, "Suco", "Natu", "1L", "Laranja", 8.00);
        bebidaService.insertAll(Arrays.asList(b1, b2));

        Pedido p1 = new Pedido(null,Arrays.asList(l1),Arrays.asList(b1,b2), TipoPagamento.DINHEIRO, e1);
        Pedido p2 = new Pedido(null,Arrays.asList(l1,l2),Arrays.asList(b2),  TipoPagamento.CREDITO, e2);
        pedidoService.insertAll(Arrays.asList(p1, p2));
    }
}
