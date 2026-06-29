package controller;

import model.Veiculo;
import model.VeiculoEletrico;
import model.VeiculoHibrido;
import repository.VeiculoRepository;
import java.util.Scanner;

public class VeiculoController {
    private VeiculoRepository repository;
    private Scanner scanner;

    public VeiculoController(VeiculoRepository repository, Scanner scanner) {
        this.repository = repository;
        this.scanner = scanner;
    }

    public void cadastrarEletrico() {
        System.out.println("\n--- CADASTRAR VEÍCULO ELÉTRICO ---");

        int id = lerIdUnico();
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Autonomia Máxima (km): ");
        double autonomia = Double.parseDouble(scanner.nextLine());

        System.out.print("Carga Atual da Bateria (%): ");
        double carga = Double.parseDouble(scanner.nextLine());

        System.out.print("Consumo (kWh/km): ");
        double consumo = Double.parseDouble(scanner.nextLine());

        System.out.print("Tempo de Recarga Completa (min): ");
        int tempoCompleto = Integer.parseInt(scanner.nextLine());

        System.out.print("Tipo de Conector (ex: CCS2, Tipo 2, CHAdeMO): ");
        String conector = scanner.nextLine();

        System.out.print("Tempo de Recarga Rápida (min): ");
        int tempoRapido = Integer.parseInt(scanner.nextLine());

        VeiculoEletrico ve = new VeiculoEletrico(id, modelo, autonomia, carga,
                consumo, tempoCompleto, conector, tempoRapido);
        if (repository.adicionar(ve)) {
            System.out.println("✔ Veículo elétrico cadastrado com sucesso!");
        } else {
            System.out.println("✘ Erro: ID já cadastrado.");
        }
    }

    public void cadastrarHibrido() {
        System.out.println("\n--- CADASTRAR VEÍCULO HÍBRIDO ---");

        int id = lerIdUnico();
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Autonomia Máxima Elétrica (km): ");
        double autonomia = Double.parseDouble(scanner.nextLine());

        System.out.print("Carga Atual da Bateria (%): ");
        double carga = Double.parseDouble(scanner.nextLine());

        System.out.print("Consumo Elétrico (kWh/km): ");
        double consumo = Double.parseDouble(scanner.nextLine());

        System.out.print("Tempo de Recarga Completa (min): ");
        int tempoCompleto = Integer.parseInt(scanner.nextLine());

        System.out.print("Capacidade do Tanque (litros): ");
        double tanque = Double.parseDouble(scanner.nextLine());

        System.out.print("Consumo Combustível (km/l): ");
        double consumoComb = Double.parseDouble(scanner.nextLine());

        System.out.print("Tipo de Combustível (Gasolina/Etanol): ");
        String tipoComb = scanner.nextLine();

        VeiculoHibrido vh = new VeiculoHibrido(id, modelo, autonomia, carga,
                consumo, tempoCompleto, tanque, consumoComb, tipoComb);
        if (repository.adicionar(vh)) {
            System.out.println("✔ Veículo híbrido cadastrado com sucesso!");
        } else {
            System.out.println("✘ Erro: ID já cadastrado.");
        }
    }

    public void listarTodos() {
        System.out.println("\n--- LISTA DE VEÍCULOS ---");
        Veiculo[] todos = repository.listarTodos();
        if (todos.length == 0) {
            System.out.println("Nenhum veículo cadastrado.");
            return;
        }
        for (Veiculo v : todos) {
            System.out.println(v); // Chama o toString() de cada veículo
        }
        System.out.println("Total: " + todos.length + " veículo(s).");
    }

    public void buscarPorId() {
        System.out.println("\n--- BUSCAR VEÍCULO ---");
        System.out.print("Informe o ID do veículo: ");
        int id = Integer.parseInt(scanner.nextLine());

        Veiculo v = repository.buscarPorId(id);
        if (v != null) {
            System.out.println("Veículo encontrado:");
            System.out.println(v);
        } else {
            System.out.println("✘ Veículo com ID " + id + " não encontrado.");
        }
    }

    public void atualizar() {
        System.out.println("\n--- ATUALIZAR VEÍCULO ---");
        System.out.print("Informe o ID do veículo a atualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Veiculo existente = repository.buscarPorId(id);
        if (existente == null) {
            System.out.println("✘ Veículo não encontrado.");
            return;
        }

        System.out.println("Veículo atual: " + existente);
        System.out.println("Tipo: " + existente.getTipo());
        System.out.println("Preencha os novos dados (mesmo tipo: " + existente.getTipo() + "):");
        Veiculo atualizado = null;

        if (existente instanceof VeiculoEletrico) {
            VeiculoEletrico ve = (VeiculoEletrico) existente;

            System.out.print("Novo modelo [" + ve.getModelo() + "]: ");
            String modelo = lerOuManter(ve.getModelo());

            System.out.print("Nova autonomia máxima [" + ve.getAutonomiaMaxima() + "]: ");
            double auto = lerDoubleOuManter(ve.getAutonomiaMaxima());

            System.out.print("Nova carga atual (%) [" + ve.getCargaBateriaAtual() + "]: ");
            double carga = lerDoubleOuManter(ve.getCargaBateriaAtual());

            System.out.print("Novo consumo kWh/km [" + ve.getConsumoKwhPorKm() + "]: ");
            double consumo = lerDoubleOuManter(ve.getConsumoKwhPorKm());

            System.out.print("Novo tempo recarga completa [" + ve.getTempoRecargaCompleta() + "]: ");
            int tempoC = lerIntOuManter(ve.getTempoRecargaCompleta());

            System.out.print("Novo tipo conector [" + ve.getTipoConector() + "]: ");
            String conector = lerOuManter(ve.getTipoConector());

            System.out.print("Novo tempo recarga rápida [" + ve.getTempoRecargaRapida() + "]: ");
            int tempoR = lerIntOuManter(ve.getTempoRecargaRapida());

            atualizado = new VeiculoEletrico(id, modelo, auto, carga, consumo, tempoC, conector, tempoR);

        } else if (existente instanceof VeiculoHibrido) {
            VeiculoHibrido vh = (VeiculoHibrido) existente;

            System.out.print("Novo modelo [" + vh.getModelo() + "]: ");
            String modelo = lerOuManter(vh.getModelo());

            System.out.print("Nova autonomia máxima [" + vh.getAutonomiaMaxima() + "]: ");
            double auto = lerDoubleOuManter(vh.getAutonomiaMaxima());

            System.out.print("Nova carga atual (%) [" + vh.getCargaBateriaAtual() + "]: ");
            double carga = lerDoubleOuManter(vh.getCargaBateriaAtual());

            System.out.print("Novo consumo kWh/km [" + vh.getConsumoKwhPorKm() + "]: ");
            double consumo = lerDoubleOuManter(vh.getConsumoKwhPorKm());

            System.out.print("Novo tempo recarga completa [" + vh.getTempoRecargaCompleta() + "]: ");
            int tempoC = lerIntOuManter(vh.getTempoRecargaCompleta());

            System.out.print("Nova capacidade tanque [" + vh.getCapacidadeTanqueCombustivel() + "]: ");
            double tanque = lerDoubleOuManter(vh.getCapacidadeTanqueCombustivel());

            System.out.print("Novo consumo combustível [" + vh.getConsumoCombustivel() + "]: ");
            double consumoComb = lerDoubleOuManter(vh.getConsumoCombustivel());

            System.out.print("Novo tipo combustível [" + vh.getTipoCombustivel() + "]: ");
            String tipoComb = lerOuManter(vh.getTipoCombustivel());

            atualizado = new VeiculoHibrido(id, modelo, auto, carga, consumo, tempoC, tanque, consumoComb, tipoComb);
        }

        if (atualizado != null && repository.atualizar(atualizado)) {
            System.out.println("✔ Veículo atualizado com sucesso!");
        } else {
            System.out.println("✘ Erro ao atualizar.");
        }
    }

    public void remover() {
        System.out.println("\n--- REMOVER VEÍCULO ---");
        System.out.print("Informe o ID do veículo a remover: ");
        int id = Integer.parseInt(scanner.nextLine());

        Veiculo v = repository.buscarPorId(id);
        if (v == null) {
            System.out.println("✘ Veículo não encontrado.");
            return;
        }

        System.out.println("Veículo a remover: " + v);
        System.out.print("Confirmar remoção? (s/n): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("s")) {
            repository.remover(id);
            System.out.println("✔ Veículo removido com sucesso!");
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    private int lerIdUnico() {
        int id;
        do {
            System.out.print("ID (único): ");
            id = Integer.parseInt(scanner.nextLine());
            if (repository.idExiste(id)) {
                System.out.println("✘ ID já cadastrado. Escolha outro.");
            }
        } while (repository.idExiste(id));
        return id;
    }

    private String lerOuManter(String atual) {
        String entrada = scanner.nextLine();
        return entrada.isEmpty() ? atual : entrada;
    }

    private double lerDoubleOuManter(double atual) {
        String entrada = scanner.nextLine();
        return entrada.isEmpty() ? atual : Double.parseDouble(entrada);
    }

    private int lerIntOuManter(int atual) {
        String entrada = scanner.nextLine();
        return entrada.isEmpty() ? atual : Integer.parseInt(entrada);
    }

    public VeiculoRepository getRepository() {
        return repository;
    }
}
