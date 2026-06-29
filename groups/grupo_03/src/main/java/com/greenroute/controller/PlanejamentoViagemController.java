package com.greenroute.controller;

import com.greenroute.model.Cidade;
import com.greenroute.model.Eletroposto;
import com.greenroute.model.Veiculo;

import java.util.Scanner;

public class PlanejamentoViagemController {

    private VeiculoController veiculoController;
    private CidadeController cidadeController;
    private EletropostoController eletropostoController;

    public PlanejamentoViagemController(
            VeiculoController veiculoController,
            CidadeController cidadeController,
            EletropostoController eletropostoController) {

        this.veiculoController = veiculoController;
        this.cidadeController = cidadeController;
        this.eletropostoController = eletropostoController;
    }

    public void menuPlanejamento() {

        Scanner sc = new Scanner(System.in);

        int opcao;

        do {

            System.out.println("\n=== PLANEJAMENTO DE VIAGEM ===");
            System.out.println("1 - Planejar Viagem");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();

            switch (opcao) {

                case 1:
                    planejarViagem();
                    break;

                case 0:
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
        sc.close();
    }

    public void planejarViagem() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o ID do veículo: ");
        int idVeiculo = sc.nextInt();

        System.out.print("Digite o ID da cidade de destino: ");
        int idCidade = sc.nextInt();

        Veiculo veiculo = veiculoController.procurarId(idVeiculo);
        Cidade cidade = cidadeController.procurarId(idCidade);

        if (veiculo == null) {
            System.out.println("Veículo não encontrado!");
            sc.close();
            return;
        }

        if (cidade == null) {
            System.out.println("Cidade não encontrada!");
            sc.close();
            return;
        }

        double autonomiaAtual = veiculo.calcularAutonomia();
        double distanciaDestino = cidade.getDistanciaDaCapital();

        System.out.println("\n=== RESULTADO DA VIAGEM ===");
        System.out.println("Veículo: " + veiculo.getModelo());
        System.out.println("Cidade: " + cidade.getNome());
        System.out.println("Autonomia Atual: " + autonomiaAtual + " km");
        System.out.println("Distância do destino: " + distanciaDestino + " km");

        if (autonomiaAtual >= distanciaDestino) {

            double autonomiaRestante = autonomiaAtual - distanciaDestino;

            System.out.println("Viagem possível sem necessidade de recarga.");
            System.out.println("Autonomia restante após a viagem: "
                    + autonomiaRestante + " km");

        } else {

            double distanciaFaltante = distanciaDestino - autonomiaAtual;

            System.out.println("Autonomia insuficiente para chegar ao destino.");
            System.out.println("Faltam " + distanciaFaltante
                    + " km de autonomia para concluir a viagem.");

            Eletroposto[] eletropostos =
                    eletropostoController.getEletropostos();

            int quantidade =
                    eletropostoController.getQuantidadeEletropostos();

            boolean encontrou = false;

            System.out.println("\n=== ELETROPOSTOS DISPONÍVEIS ===");

            for (int i = 0; i < quantidade; i++) {

                if (eletropostos[i].getCidadeId() == cidade.getId()) {

                    encontrou = true;

                    System.out.println("--------------------------------");
                    System.out.println("Nome: "
                            + eletropostos[i].getNome());
                    System.out.println("Localização: "
                            + eletropostos[i].getLocalizacao());
                    System.out.println("Conectores: "
                            + eletropostos[i].getTiposConectoresDisponiveis());
                    System.out.println("Potência: "
                            + eletropostos[i].getPotenciaCargaKw() + " kW");
                    System.out.println("Preço por kWh: R$ "
                            + eletropostos[i].getPrecoPorKwh());
                    System.out.println("Vagas disponíveis: "
                            + eletropostos[i].getVagasDisponiveis());
                }
            }

            if (!encontrou) {
                System.out.println(
                        "Nenhum eletroposto encontrado para esta cidade.");
            }
        sc.close();
        }
    }
}