package com.lanchonete.lanchoneteSpring.config;

import com.lanchonete.lanchoneteSpring.entities.*;
import com.lanchonete.lanchoneteSpring.entities.enums.TipoPagamento;
import com.lanchonete.lanchoneteSpring.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@Configuration
public class Inicializacao implements CommandLineRunner {

    /*@Autowired
    private ILancheRepository lancheRepository;

    @Autowired
    private IBebidaRepository bebidaRepository;

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IEnderecoRepository enderecoRepository;

    @Autowired
    private IPedidoRepository pedidoRepository;*/

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ClienteService clienteService;

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
        enderecoService.insertAll(Arrays.asList(e1, e2,e3));

        Cliente c1 = new Cliente(null, "Daniel Marques", "33397902", sdf.parse("09/09/2003"), e1);
        Cliente c2 = new Cliente(null, "Ricardo Lopes", "2342 8826", sdf.parse("23/05/1997"), e2);
        clienteService.insertAll(Arrays.asList(c1, c2));

        Pedido p1 = new Pedido(null, c1, TipoPagamento.DEBITO);
        Pedido p2 = new Pedido(null, c2, TipoPagamento.CREDITO);
        //pedidoRepository.saveAll(Arrays.asList(p1, p2));
        pedidoService.insertAll(Arrays.asList(p1, p2));

        Lanche l1 = new Lanche(null, "X-Frango", 10.0, "Descricao do X-Frango", null);
        Lanche l2 = new Lanche(null, "X-Bacon", 16.55, "Descricao do X-Bacon", p1);
        lancheService.insertAll(Arrays.asList(l1, l2));

        //l1.setPedidoLanche(p1);
        //lancheService.insert(l1);

        Bebida b1 = new Bebida(null, "Refrigerante", "Coca-Cola", "2L", "Cola", 12.55, p1);
        Bebida b2 = new Bebida(null, "Suco", "Natu", "1L", "Laranja", 8.00, p2);

        bebidaService.insertAll(Arrays.asList(b1, b2));


    }
}
