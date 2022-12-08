package com.lanchonete.lanchoneteSpring.config;

import com.lanchonete.lanchoneteSpring.entities.*;
import com.lanchonete.lanchoneteSpring.entities.enums.TipoPagamento;
import com.lanchonete.lanchoneteSpring.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//@Configuration
public class Main implements CommandLineRunner {

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
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Set<Lanche> lanchesSet = new HashSet<>();
        Lanche l1 = new Lanche(null, "X-Frango", 10.0, "Descricao do X-Frango", null);
        Lanche l2 = new Lanche(null, "X-Bacon", 16.55, "Descricao do X-Bacon", null);
        lancheRepository.saveAll(Arrays.asList(l1, l2));
        lanchesSet.addAll(lancheRepository.findAll());

        Set<Bebida> bebidasSet = new HashSet<>();
        Bebida b1 = new Bebida(null, "Refrigerante", "Coca-Cola", "2L", "Cola", 12.55, null);
        Bebida b2 = new Bebida(null, "Suco", "Natu", "1L", "Laranja", 8.00, null);
        bebidaRepository.saveAll(Arrays.asList(b1, b2));
        bebidasSet.addAll(bebidaRepository.findAll());

        try {
            char opcaoCliente = 'n';
            do {

                System.out.println("--Cliente--");
                System.out.print("Nome: ");
                String nomeCliente = sc.nextLine();
                System.out.print("Telefone: ");
                String telefone = sc.next();
                System.out.print("Data nascimento (dd/MM/yyyy): ");
                String aniversario = sc.next();

                System.out.println("--Endereco--");
                System.out.print("Bairro: ");
                sc.nextLine();
                String bairro = sc.nextLine();
                System.out.print("Rua: ");
                String rua = sc.nextLine();
                System.out.print("Numero: ");
                int numero = sc.nextInt();

                Endereco endereco = new Endereco(null, bairro, rua, numero);
                enderecoRepository.save(endereco);

                Cliente cliente = new Cliente(null, nomeCliente, telefone, sdf.parse(aniversario), endereco);
                clienteRepository.save(cliente);

                Pedido p = new Pedido(null, cliente, TipoPagamento.DINHEIRO);
                pedidoRepository.save(p);
                
                int qtdPedidos = 1;
                int opcao = 0;

                do {

                    System.out.println("--Pedido " + qtdPedidos + "--");
                    System.out.println("1 - Lanches");
                    System.out.println("2 - Bebidas");
                    System.out.println("9 - Sair");
                    opcao = sc.nextInt();

                    if (opcao == 1) {
                        List<Lanche> lancheList = new ArrayList<>();
                        char opcaoLanche = 'n';
                        do {
                            for (Lanche l : lanchesSet) {
                                System.out.println(l.getId() + " - " + l.getNome() + " | " + String.format("%.2f", l.getPreco()));
                            }
                            opcao = sc.nextInt();
                            for (Lanche obj : lanchesSet) {
                                if (obj.getId() == opcao) {
                                    Lanche l = new Lanche(null, obj.getNome(), obj.getPreco(), obj.getDescricao(), null);
                                    lancheList.add(l);
                                    break;
                                }
                            }
                            System.out.println("Outro lanche? (s/n)");
                            opcaoLanche = sc.next().charAt(0);
                        } while (opcaoLanche != 'n');
                        for (Lanche l : lancheList) {
                            l.setPedidoLanche(p);
                            System.out.println(l.getNome());
                        }
                        lancheRepository.saveAll(lancheList);
                    } else if (opcao == 2) {
                        List<Bebida> bebidaList = new ArrayList<>();
                        char opcaoBebida = 'n';
                        do {
                            for (Bebida b : bebidasSet) {
                                System.out.println(b.getId() + " - " + b.getNome() + " | " + String.format("%.2f", b.getPreco()));
                            }
                            opcao = sc.nextInt();
                            for (Bebida obj : bebidasSet) {
                                if (obj.getId() == opcao) {
                                    Bebida b = new Bebida(null, obj.getNome(), obj.getMarca(), obj.getLitragem(), obj.getSabor(), obj.getPreco(), null);
                                    bebidaList.add(b);
                                    break;
                                }
                            }
                            System.out.println("Outra bebida? (s/n)");
                            opcaoBebida = sc.next().charAt(0);
                        } while (opcaoBebida != 'n');
                        for (Bebida b : bebidaList) {
                            b.setPedidoBebida(p);
                        }
                        bebidaRepository.saveAll(bebidaList);
                    }
                } while (opcao != 9);
                System.out.print("Registrar outro cliente? (s/n) ");
                opcaoCliente = sc.next().charAt(0);
                sc.nextLine();
            } while (opcaoCliente != 'n');

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}