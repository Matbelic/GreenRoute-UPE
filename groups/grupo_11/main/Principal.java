package main;

import java.util.Scanner;
import controller.SistemaController;
import model.*;

public class Principal {
    public static void main(String[] args) {
        SistemaController controller = new SistemaController();
        Scanner scanner = new Scanner(System.in);
        
        controller.getRepo().salvarCidade(new Cidade(1, "Sorocaba", "SP", 100.0));
        controller.getRepo().salvarVeiculo(new VeiculoEletrico(1, "BYD Dolphin", 300.0, 30.0, 0.15, 400, "CCS2", 45));
        controller.getRepo().salvarEletroposto(new Eletroposto(1, "Posto Shell Eco", "Rodovia Raposo Tavares, Km 98", 1, "CCS2, Tipo 2", 150.0, 1.90, 4));

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n========================================");
            System.out.println("       GREENROUTE - LOGÍSTICA INTELIGENTE");
            System.out.println("========================================");
            System.out.println("1 - Gerenciar Veículos (CRUD)");
            System.out.println("2 - Gerenciar Eletropostos (CRUD)");
            System.out.println("3 - Gerenciar Cidades (CRUD)");
            System.out.println("4 - 🚀 Simular Viagem (Regra de Negócio)");
            System.out.println("0 - Sair do Sistema");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    menuVeiculos(scanner, controller);
                    break;
                case 2:
                    menuEletropostos(scanner, controller);
                    break;
                case 3:
                    menuCidades(scanner, controller);
                    break;
                case 4:
                    System.out.print("Digite o ID do Veículo: ");
                    int vId = scanner.nextInt();
                    System.out.print("Digite o ID da Cidade de destino: ");
                    int cId = scanner.nextInt();
                    controller.simularViagem(vId, cId);
                    break;
                case 0:
                    System.out.println("Finalizando o GreenRoute... Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }

    private static void menuVeiculos(Scanner scan, SistemaController ctrl) {
        System.out.println("\n--- MENU VEÍCULOS ---");
        System.out.println("1. Cadastrar Veículo");
        System.out.println("2. Listar Veículos");
        System.out.println("3. Excluir Veículo");
        System.out.print("Escolha: ");
        int op = scan.nextInt();
        
        if (op == 1) {
            System.out.print("ID: "); int id = scan.nextInt(); scan.nextLine();
            System.out.print("Modelo: "); String mod = scan.nextLine();
            System.out.print("Autonomia Máxima (km): "); double aut = scan.nextDouble();
            System.out.print("Carga Atual (% de 0 a 100): "); double carga = scan.nextDouble();
            System.out.print("Consumo (Kwh/Km): "); double cons = scan.nextDouble();
            System.out.print("Tempo Recarga Completa (min): "); int tempo = scan.nextInt();
            
            VeiculoEletrico ve = new VeiculoEletrico(id, mod, aut, carga, cons, tempo, "CCS2", 30);
            ctrl.getRepo().salvarVeiculo(ve);
            System.out.println("✅ Veículo cadastrado!");
        } else if (op == 2) {
            for (Veiculo v : ctrl.getRepo().listarVeiculos()) {
                System.out.println("ID: " + v.getId() + " | Modelo: " + v.getModelo() + " | Bateria: " + v.getCargaBateriaAtual() + "%");
            }
        } else if (op == 3) {
            System.out.print("Digite o ID para excluir: ");
            int id = scan.nextInt();
            if (ctrl.getRepo().excluirVeiculo(id)) System.out.println("✅ Excluído!");
            else System.out.println("❌ Não encontrado.");
        }
    }

    private static void menuEletropostos(Scanner scan, SistemaController ctrl) {
        System.out.println("\n--- MENU ELETROPOSTOS ---");
        System.out.println("1. Cadastrar Eletroposto");
        System.out.println("2. Listar Eletropostos");
        System.out.print("Escolha: ");
        int op = scan.nextInt();

        if (op == 1) {
            System.out.print("ID: "); int id = scan.nextInt(); scan.nextLine();
            System.out.print("Nome: "); String nome = scan.nextLine();
            System.out.print("Endereço: "); String end = scan.nextLine();
            System.out.print("ID da Cidade onde se localiza: "); int cidId = scan.nextInt();
            
            Eletroposto ep = new Eletroposto(id, nome, end, cidId, "CCS2", 50.0, 2.0, 2);
            ctrl.getRepo().salvarEletroposto(ep);
            System.out.println("✅ Eletroposto cadastrado!");
        } else if (op == 2) {
            for (Eletroposto e : ctrl.getRepo().listarEletropostos()) {
                System.out.println("ID: " + e.getId() + " | Nome: " + e.getNome() + " | Pertence à Cidade ID: " + e.getCidadeId());
            }
        }
    }

    private static void menuCidades(Scanner scan, SistemaController ctrl) {
        System.out.println("\n--- MENU CIDADES ---");
        System.out.println("1. Cadastrar Cidade");
        System.out.println("2. Listar Cidades");
        System.out.print("Escolha: ");
        int op = scan.nextInt();

        if (op == 1) {
            System.out.print("ID: "); int id = scan.nextInt(); scan.nextLine();
            System.out.print("Nome da Cidade: "); String nome = scan.nextLine();
            System.out.print("Estado (UF): "); String uf = scan.nextLine();
            System.out.print("Distância da capital (km): "); double dist = scan.nextDouble();
            
            Cidade c = new Cidade(id, nome, uf, dist);
            ctrl.getRepo().salvarCidade(c);
            System.out.println("✅ Cidade cadastrada!");
        } else if (op == 2) {
            for (Cidade c : ctrl.getRepo().listarCidades()) {
                System.out.println("ID: " + c.getId() + " | " + c.getNome() + " - " + c.getEstado() + " (" + c.getDistanciaDaCapital() + " km da Capital)");
            }
        }
    }
}