package view;

import model.*;
import model.repository.*;
import java.util.Scanner;

public class View {
    private static final Scanner scanner = new Scanner(System.in);
    private static final VeiculoRepository veiculoRepo = new VeiculoRepository();
    private static final EletropostoRepository eletropostoRepo = new EletropostoRepository();
    private static final CidadeRepository cidadeRepo = new CidadeRepository();

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerOpcao();
            switch (opcao) {
                case 1:
                    menuVeiculos();
                    break;
                case 2:
                    menuEletropostos();
                    break;
                case 3:
                    menuCidades();
                    break;
                case 0:
                    System.out.println("\nGreenRoute encerrado. Boa viagem!");
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n=================================");
        System.out.println("      SISTEMA GREENROUTE         ");
        System.out.println("=================================");
        System.out.println("1. Gerenciar Veículos");
        System.out.println("2. Gerenciar Eletropostos");
        System.out.println("3. Gerenciar Cidades");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // =================================
    //       MENU DE VEÍCULOS
    // =================================
    private static void menuVeiculos() {
        System.out.println("\n--- GERENCIAR VEÍCULOS ---");
        System.out.println("1. Cadastrar Veículo Elétrico");
        System.out.println("2. Cadastrar Veículo Híbrido");
        System.out.println("3. Listar Todos os Veículos");
        System.out.println("4. Excluir Veículo");
        System.out.print("Escolha uma opção: ");
        int op = lerOpcao();

        switch (op) {
            case 1: {
                System.out.print("ID: "); int id = Integer.parseInt(scanner.nextLine());
                System.out.print("Modelo: "); String mod = scanner.nextLine();
                System.out.print("Autonomia Máxima (km): "); double aut = Double.parseDouble(scanner.nextLine());
                System.out.print("Carga Atual (%): "); double carga = Double.parseDouble(scanner.nextLine());
                System.out.print("Consumo (kWh/km): "); double cons = Double.parseDouble(scanner.nextLine());
                System.out.print("Tempo Recarga Completa (min): "); int tempo = Integer.parseInt(scanner.nextLine());
                System.out.print("Tipo de Conector: "); String con = scanner.nextLine();
                System.out.print("Tempo Recarga Rápida (min): "); int rapido = Integer.parseInt(scanner.nextLine());

                veiculoRepo.cadastrar(new VeiculoEletrico(id, mod, aut, carga, cons, tempo, con, rapido));
                System.out.println("Veículo Elétrico cadastrado com sucesso!");
            } break;
            case 2: {
                System.out.print("ID: "); int id = Integer.parseInt(scanner.nextLine());
                System.out.print("Modelo: "); String mod = scanner.nextLine();
                System.out.print("Autonomia Máxima (km): "); double aut = Double.parseDouble(scanner.nextLine());
                System.out.print("Carga Atual (%): "); double carga = Double.parseDouble(scanner.nextLine());
                System.out.print("Consumo (kWh/km): "); double cons = Double.parseDouble(scanner.nextLine());
                System.out.print("Tempo Recarga Completa (min): "); int tempo = Integer.parseInt(scanner.nextLine());
                System.out.print("Capacidade do Tanque (L): "); double tanque = Double.parseDouble(scanner.nextLine());
                System.out.print("Consumo Combustível (km/L): "); double consC = Double.parseDouble(scanner.nextLine());
                System.out.print("Tipo de Combustível: "); String comb = scanner.nextLine();

                veiculoRepo.cadastrar(new VeiculoHibrido(id, mod, aut, carga, cons, tempo, tanque, consC, comb));
                System.out.println("Veículo Híbrido cadastrado com sucesso!");
            } break;
            case 3: {
                System.out.println("\n--- LISTA DE VEÍCULOS ---");
                Veiculo[] lista = veiculoRepo.listarTodos();
                if (lista.length == 0) System.out.println("Nenhum veículo cadastrado.");
                for (Veiculo v : lista) {
                    String tipo = (v instanceof VeiculoEletrico) ? "Elétrico" : "Híbrido";
                    System.out.printf("[%s] ID: %d | Modelo: %s | Carga: %.1f%%\n", tipo, v.getId(), v.getModelo(), v.getCargaBateriaAtual());
                }
            } break;
            case 4: {
                System.out.print("Digite o ID do veículo a ser excluído: ");
                int id = Integer.parseInt(scanner.nextLine());
                if (veiculoRepo.excluir(id)) System.out.println("Veículo removido.");
                else System.out.println("Veículo não encontrado.");
            } break;
        }
    }

    // =================================
    //       MENU DE ELETROPOSTOS
    // =================================
    private static void menuEletropostos() {
        System.out.println("\n--- GERENCIAR ELETROPOSTOS ---");
        System.out.println("1. Cadastrar Eletroposto");
        System.out.println("2. Listar Todos os Eletropostos");
        System.out.println("3. Excluir Eletroposto");
        System.out.print("Escolha uma opção: ");
        int op = lerOpcao();

        switch (op) {
            case 1: {
                System.out.print("ID: "); int id = Integer.parseInt(scanner.nextLine());
                System.out.print("Nome: "); String nome = scanner.nextLine();
                System.out.print("Localização/Endereço: "); String loc = scanner.nextLine();
                System.out.print("ID da Cidade: "); int cidadeId = Integer.parseInt(scanner.nextLine());
                System.out.print("Conectores Disponíveis: "); String con = scanner.nextLine();
                System.out.print("Potência (kW): "); double pot = Double.parseDouble(scanner.nextLine());
                System.out.print("Preço por kWh: R$ "); double preco = Double.parseDouble(scanner.nextLine());
                System.out.print("Vagas Disponíveis: "); int vagas = Integer.parseInt(scanner.nextLine());

                eletropostoRepo.cadastrar(new Eletroposto(id, nome, loc, cidadeId, con, pot, preco, vagas));
                System.out.println("Eletroposto cadastrado com sucesso!");
            } break;
            case 2: {
                System.out.println("\n--- LISTA DE ELETROPOSTOS ---");
                Eletroposto[] lista = eletropostoRepo.listarTodos();
                if (lista.length == 0) System.out.println("Nenhum eletroposto cadastrado.");
                for (Eletroposto e : lista) {
                    System.out.printf("ID: %d | Nome: %s | Vagas: %d | Preço/kWh: R$ %.2f\n", e.getId(), e.getNome(), e.getVagasDisponiveis(), e.getPrecoPorKwh());
                }
            } break;
            case 3: {
                System.out.print("Digite o ID do eletroposto a ser excluído: ");
                int id = Integer.parseInt(scanner.nextLine());
                if (eletropostoRepo.excluir(id)) System.out.println("Eletroposto removido.");
                else System.out.println("Eletroposto não encontrado.");
            } break;
        }
    }

    // =================================
    //       MENU DE CIDADES
    // =================================
    private static void menuCidades() {
        System.out.println("\n--- GERENCIAR CIDADES ---");
        System.out.println("1. Cadastrar Cidade");
        System.out.println("2. Listar Todas as Cidades");
        System.out.println("3. Excluir Cidade");
        System.out.print("Escolha uma opção: ");
        int op = lerOpcao();

        switch (op) {
            case 1: {
                System.out.print("ID: "); int id = Integer.parseInt(scanner.nextLine());
                System.out.print("Nome: "); String nome = scanner.nextLine();
                System.out.print("Estado (UF): "); String est = scanner.nextLine();
                System.out.print("Distância da Capital (km): "); double dist = Double.parseDouble(scanner.nextLine());

                cidadeRepo.cadastrar(new Cidade(id, nome, est, dist));
                System.out.println("Cidade cadastrada com sucesso!");
            } break;
            case 2: {
                System.out.println("\n--- LISTA DE CIDADES ---");
                Cidade[] lista = cidadeRepo.listarTodos();
                if (lista.length == 0) System.out.println("Nenhuma cidade cadastrada.");
                for (Cidade c : lista) {
                    System.out.printf("ID: %d | Nome: %s - %s | Distância Capital: %.1f km\n", c.getId(), c.getNome(), c.getEstado(), c.getDistanciaDaCapital());
                }
            } break;
            case 3: {
                System.out.print("Digite o ID da cidade a ser excluída: ");
                int id = Integer.parseInt(scanner.nextLine());
                if (cidadeRepo.excluir(id)) System.out.println("Cidade removida.");
                else System.out.println("Cidade não encontrada.");
            } break;
        }
    }
}

