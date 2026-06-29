package controller;

import java.util.Scanner;

import model.Veiculo;
import model.VeiculoEletrico;
import model.VeiculoHibrido;
import repository.VeiculoRepository;

public class VeiculoController {

    private final VeiculoRepository repository;
    private final Scanner sc;

    public VeiculoController(VeiculoRepository repository, Scanner sc) {
        this.repository = repository;
        this.sc = sc;
    }

    public void cadastrar() {

        System.out.println("\n1 -Veiculo Eletrico");
        System.out.println("2 -Veiculo Hibrido");
        System.out.print("Escolha: ");
        int tipo = sc.nextInt();
        sc.nextLine();

        System.out.print("id: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (repository.buscarPorId(id) != null) {
            System.out.println("Já existe um veiculo com esse ID.");
            return;
        }

        System.out.print("Modelo do veículo: ");
        String modelo = sc.nextLine();

        System.out.print("Autonomia maxima (km): ");
        double autonomia = sc.nextDouble();

        System.out.print("Carga da bateria atual (%): ");
        double bateria = sc.nextDouble();

        System.out.print("Consumo kWh/km: ");
        double consumo = sc.nextDouble();

        System.out.print("Tempo de recarga completa (min): ");
        int tempoRecarga = sc.nextInt();
        sc.nextLine();

        if (tipo == 1) {

            System.out.print("Tipo de conector: ");
            String conector = sc.nextLine();

            System.out.print("Tempo de recarga rapida (min): ");
            int tempoRapido = sc.nextInt();

            VeiculoEletrico veiculo = new VeiculoEletrico(
                    id,
                    modelo,
                    autonomia,
                    bateria,
                    consumo,
                    tempoRecarga,
                    conector,
                    tempoRapido
            );

            repository.cadastrar(veiculo);

        } else if (tipo == 2) {

            System.out.print("Capacidade do tanque: ");
            double tanque = sc.nextDouble();

            System.out.print("Consumo combustivel (km/l): ");
            double consumoComb = sc.nextDouble();
            sc.nextLine();

            System.out.print("Tipo de combustivel: ");
            String combustivel = sc.nextLine();

            VeiculoHibrido veiculo = new VeiculoHibrido(
                    id,
                    modelo,
                    autonomia,
                    bateria,
                    consumo,
                    tempoRecarga,
                    tanque,
                    consumoComb,
                    combustivel
            );

            repository.cadastrar(veiculo);

        } else {

            System.out.println("Tipo inválido.");
            return;
        }

        System.out.println("Veiculo cadastrado.");
    }

    public void buscar() {

        System.out.print("id do veiculo: ");
        int id = sc.nextInt();

        Veiculo veiculo = repository.buscarPorId(id);

        if (veiculo != null) {

            System.out.println("ID: " + veiculo.getId());
            System.out.println("Modelo: " + veiculo.getModelo());
            System.out.println("Autonomia Máxima: " + veiculo.getAutonomiaMaxima() + " km");
            System.out.println("Carga da Bateria: " + veiculo.getCargaBateriaAtual() + "%");
            System.out.println("Consumo: " + veiculo.getConsumoKwhPorKm() + " kWh/km");
            System.out.println("Tempo de Recarga Completa: " + veiculo.getTempoRecargaCompleta() + " min");

        } else {
            System.out.println("Veiculo não encontrado.");
        }
    }

public void listar() {
//E AQ
    Veiculo[] lista = repository.listar();

    if (repository.getQuantidade() == 0) {
        System.out.println("Nenhum veículo cadastrado.");
        return;
    }

    for (int i = 0; i < repository.getQuantidade(); i++) {

        System.out.println("ID: " + lista[i].getId());
        System.out.println("Modelo: " + lista[i].getModelo());
        System.out.println("Autonomia Máxima: " + lista[i].getAutonomiaMaxima() + " km");
        System.out.println("Carga da Bateria: " + lista[i].getCargaBateriaAtual() + "%");
        System.out.println("Consumo: " + lista[i].getConsumoKwhPorKm() + " kWh/km");
        System.out.println("Tempo de Recarga Completa: " + lista[i].getTempoRecargaCompleta() + " min");
        System.out.println();
    }
}
    public void excluir() {

        System.out.print("id do veiculo: ");
        int id = sc.nextInt();

        if (repository.excluir(id)) {
            System.out.println("Veiculo excluído.");
        } else {
            System.out.println("Veiculo não encontrado.");
        }
    }

    public void atualizar() {

        System.out.print("id do veiculo que deseja atualizar: ");
        int id = sc.nextInt();

        Veiculo antigo = repository.buscarPorId(id);

        if (antigo == null) {
            System.out.println("Veiculo nao encontrado.");
            return;
        }

        sc.nextLine();

        System.out.print("Modelo: ");
        String modelo = sc.nextLine();

        System.out.print("Autonomia maxima: ");
        double autonomia = sc.nextDouble();

        System.out.print("Carga bateria atual (%): ");
        double bateria = sc.nextDouble();

        System.out.print("Consumo kWh/km: ");
        double consumo = sc.nextDouble();

        System.out.print("Tempo recarga completa (min): ");
        int tempoRecarga = sc.nextInt();
        sc.nextLine();

        if (antigo instanceof VeiculoEletrico) {

            System.out.print("Tipo conector: ");
            String conector = sc.nextLine();

            System.out.print("Tempo recarga rapida (min): ");
            int tempoRapido = sc.nextInt();

            VeiculoEletrico novo = new VeiculoEletrico(
                    id,
                    modelo,
                    autonomia,
                    bateria,
                    consumo,
                    tempoRecarga,
                    conector,
                    tempoRapido
            );

            repository.atualizar(id, novo);

        } else if (antigo instanceof VeiculoHibrido) {

            System.out.print("Capacidade tanque: ");
            double tanque = sc.nextDouble();

            System.out.print("Consumo combustivel: ");
            double consumoComb = sc.nextDouble();
            sc.nextLine();

            System.out.print("Tipo combustivel: ");
            String combustivel = sc.nextLine();

            VeiculoHibrido novo = new VeiculoHibrido(
                    id,
                    modelo,
                    autonomia,
                    bateria,
                    consumo,
                    tempoRecarga,
                    tanque,
                    consumoComb,
                    combustivel
            );

            repository.atualizar(id, novo);
        }

        System.out.println("Veiculo atualizado.");
    }
}
