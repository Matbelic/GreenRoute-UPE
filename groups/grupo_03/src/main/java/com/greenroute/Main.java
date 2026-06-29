package com.greenroute;

import java.util.Scanner;

import com.greenroute.controller.VeiculoController;
import com.greenroute.controller.CidadeController;
import com.greenroute.controller.EletropostoController;
import com.greenroute.controller.PlanejamentoViagemController;

import com.greenroute.model.Veiculo;
import com.greenroute.model.VeiculoEletrico;
import com.greenroute.model.VeiculoHibrido;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        VeiculoController controller = new VeiculoController();
        CidadeController cidadeController = new CidadeController();
        EletropostoController eletropostoController = new EletropostoController();

        PlanejamentoViagemController planejamentoViagemController =
                new PlanejamentoViagemController(
                        controller,
                        cidadeController,
                        eletropostoController
                );

        int opcao;

        do {

            System.out.println("\n===== GREEN ROUTE =====");
            System.out.println("1 - Cadastrar Veículo");
            System.out.println("2 - Listar Veículos");
            System.out.println("3 - Buscar Veículo");
            System.out.println("4 - Atualizar Veículo");
            System.out.println("5 - Remover Veículo");
            System.out.println("6 - Calcular Autonomia");
            System.out.println("7 - Gerenciar Cidades");
            System.out.println("8 - Gerenciar Eletropostos");
            System.out.println("9 - Planejamento de Viagem");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();

            switch (opcao) {

                case 1:

                    System.out.println("1 - Veículo Elétrico");
                    System.out.println("2 - Veículo Híbrido");

                    int tipo = sc.nextInt();

                    System.out.print("ID: ");
                    int id = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Modelo: ");
                    String modelo = sc.nextLine();

                    System.out.print("Autonomia Máxima: ");
                    double autonomiaMaxima = sc.nextDouble();

                    System.out.print("Carga da Bateria (%): ");
                    double cargaBateria = sc.nextDouble();

                    System.out.print("Consumo kWh/km: ");
                    double consumo = sc.nextDouble();

                    System.out.print("Tempo de Recarga Completa: ");
                    int tempoRecarga = sc.nextInt();

                    if (tipo == 1) {

                        sc.nextLine();

                        System.out.print("Tipo de Conector: ");
                        String conector = sc.nextLine();

                        System.out.print("Tempo de Recarga Rápida: ");
                        int tempoRapida = sc.nextInt();

                        VeiculoEletrico eletrico = new VeiculoEletrico(
                                id,
                                modelo,
                                autonomiaMaxima,
                                cargaBateria,
                                consumo,
                                tempoRecarga,
                                conector,
                                tempoRapida
                        );

                        controller.cadastrar(eletrico);

                    } else {

                        System.out.print("Capacidade do Tanque: ");
                        double tanque = sc.nextDouble();

                        System.out.print("Consumo Combustível (km/l): ");
                        double consumoComb = sc.nextDouble();

                        sc.nextLine();

                        System.out.print("Tipo de Combustível: ");
                        String tipoComb = sc.nextLine();

                        System.out.print("Nível de combustível atual: ");
                        double combAtual = sc.nextDouble();

                        VeiculoHibrido hibrido = new VeiculoHibrido(
                                id,
                                modelo,
                                autonomiaMaxima,
                                cargaBateria,
                                consumo,
                                tempoRecarga,
                                tanque,
                                consumoComb,
                                tipoComb,
                                combAtual
                        );

                        controller.cadastrar(hibrido);
                    }

                    break;

                case 2:

                    controller.listar();
                    break;

                case 3:

                    System.out.print("Digite o ID: ");
                    int idBusca = sc.nextInt();

                    Veiculo encontrado = controller.procurarId(idBusca);

                    System.out.println(encontrado);

                    break;

                case 4:

                    System.out.print("ID do veículo: ");
                    int idAtualizar = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Novo modelo: ");
                    String novoModelo = sc.nextLine();

                    System.out.print("Nova autonomia máxima: ");
                    double novaAutonomia = sc.nextDouble();

                    System.out.print("Nova carga da bateria: ");
                    double novaCarga = sc.nextDouble();

                    System.out.print("Novo consumo: ");
                    double novoConsumo = sc.nextDouble();

                    System.out.print("Novo tempo de recarga: ");
                    int novoTempo = sc.nextInt();

                    VeiculoEletrico atualizado = new VeiculoEletrico(
                            idAtualizar,
                            novoModelo,
                            novaAutonomia,
                            novaCarga,
                            novoConsumo,
                            novoTempo,
                            "CCS2",
                            30
                    );

                    controller.atualizarVeiculo(idAtualizar, atualizado);

                    break;

                case 5:

                    System.out.print("ID para remover: ");
                    int idRemover = sc.nextInt();

                    controller.remover(idRemover);

                    break;

                case 6:

                    System.out.print("ID do veículo: ");
                    int idAutonomia = sc.nextInt();

                    Veiculo veiculo = controller.procurarId(idAutonomia);

                    System.out.println("Autonomia Atual: " + veiculo.calcularAutonomia() + " km");

                    break;

                case 7:

                    cidadeController.menuCidade();
                    break;

                case 8:

                    eletropostoController.menuEletroposto();
                    break;

                case 9:

                    planejamentoViagemController.menuPlanejamento();
                    break;

                case 0:

                    System.out.println("Sistema encerrado.");
                    break;

                default:

                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
}