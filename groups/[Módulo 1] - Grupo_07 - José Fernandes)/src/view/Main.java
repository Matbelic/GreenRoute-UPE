package view;

import controller.CidadeController;
import controller.EletropostoController;
import controller.RouteController;
import controller.VeiculoController;
import repository.CidadeRepository;
import repository.EletropostoRepository;
import repository.VeiculoRepository;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // --- Inicialização dos repositórios (armazenamento em memória) ---
        CidadeRepository cidadeRepo = new CidadeRepository();
        VeiculoRepository veiculoRepo = new VeiculoRepository();
        EletropostoRepository eletropostoRepo = new EletropostoRepository();

        Scanner scanner = new Scanner(System.in);

        CidadeController cidadeCtrl = new CidadeController(cidadeRepo, scanner);
        VeiculoController veiculoCtrl = new VeiculoController(veiculoRepo, scanner);
        EletropostoController eletropostoCtrl = new EletropostoController(eletropostoRepo, cidadeRepo, scanner);
        RouteController routeCtrl = new RouteController(veiculoRepo, cidadeRepo, eletropostoRepo, scanner);

        carregarDadosExemplo(cidadeRepo, veiculoRepo, eletropostoRepo);

        boolean executando = true;
        while (executando) {
            exibirMenuPrincipal();
            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1":
                    menuVeiculos(veiculoCtrl, scanner);
                    break;
                case "2":
                    menuEletropostos(eletropostoCtrl, scanner);
                    break;
                case "3":
                    menuCidades(cidadeCtrl, scanner);
                    break;
                case "4":
                    routeCtrl.simularViagem();
                    break;
                case "0":
                    System.out.println("\nEncerrando GreenRoute. Até logo!");
                    executando = false;
                    break;
                default:
                    System.out.println("✘ Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    // =========================================================
    // MENUS
    // =========================================================

    private static void exibirMenuPrincipal() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║         GREENROUTE v1.0              ║");
        System.out.println("║  Logística Inteligente para VEs      ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  1. Gerenciar Veículos               ║");
        System.out.println("║  2. Gerenciar Eletropostos           ║");
        System.out.println("║  3. Gerenciar Cidades                ║");
        System.out.println("║  4. Simular Viagem                   ║");
        System.out.println("║  0. Sair                             ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("Escolha: ");
    }

    private static void menuVeiculos(VeiculoController ctrl, Scanner scanner) {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- VEÍCULOS ---");
            System.out.println("1. Cadastrar Veículo Elétrico");
            System.out.println("2. Cadastrar Veículo Híbrido");
            System.out.println("3. Listar Todos");
            System.out.println("4. Buscar por ID");
            System.out.println("5. Atualizar");
            System.out.println("6. Remover");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            switch (scanner.nextLine().trim()) {
                case "1": ctrl.cadastrarEletrico(); break;
                case "2": ctrl.cadastrarHibrido(); break;
                case "3": ctrl.listarTodos(); break;
                case "4": ctrl.buscarPorId(); break;
                case "5": ctrl.atualizar(); break;
                case "6": ctrl.remover(); break;
                case "0": voltar = true; break;
                default: System.out.println("✘ Opção inválida.");
            }
        }
    }

    private static void menuEletropostos(EletropostoController ctrl, Scanner scanner) {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- ELETROPOSTOS ---");
            System.out.println("1. Cadastrar");
            System.out.println("2. Listar Todos");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Atualizar");
            System.out.println("5. Remover");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            switch (scanner.nextLine().trim()) {
                case "1": ctrl.cadastrar(); break;
                case "2": ctrl.listarTodos(); break;
                case "3": ctrl.buscarPorId(); break;
                case "4": ctrl.atualizar(); break;
                case "5": ctrl.remover(); break;
                case "0": voltar = true; break;
                default: System.out.println("✘ Opção inválida.");
            }
        }
    }

    private static void menuCidades(CidadeController ctrl, Scanner scanner) {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- CIDADES ---");
            System.out.println("1. Cadastrar");
            System.out.println("2. Listar Todas");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Atualizar");
            System.out.println("5. Remover");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");

            switch (scanner.nextLine().trim()) {
                case "1": ctrl.cadastrar(); break;
                case "2": ctrl.listarTodas(); break;
                case "3": ctrl.buscarPorId(); break;
                case "4": ctrl.atualizar(); break;
                case "5": ctrl.remover(); break;
                case "0": voltar = true; break;
                default: System.out.println("✘ Opção inválida.");
            }
        }
    }
    private static void carregarDadosExemplo(CidadeRepository cidadeRepo,
                                             VeiculoRepository veiculoRepo,
                                             EletropostoRepository eletropostoRepo) {
        cidadeRepo.adicionar(new model.Cidade(1, "Recife",     "PE",   0.0));
        cidadeRepo.adicionar(new model.Cidade(2, "Caruaru",    "PE", 130.0));
        cidadeRepo.adicionar(new model.Cidade(3, "Petrolina",  "PE", 710.0));
        cidadeRepo.adicionar(new model.Cidade(4, "Garanhuns",  "PE", 230.0));
        cidadeRepo.adicionar(new model.Cidade(5, "Limoeiro",   "PE",  80.0));

        veiculoRepo.adicionar(new model.VeiculoEletrico(
                1, "BYD Dolphin", 400.0, 80.0, 0.15, 480, "CCS2", 60));
        veiculoRepo.adicionar(new model.VeiculoEletrico(
                2, "Volvo EX30", 300.0, 30.0, 0.18, 540, "Tipo 2", 90));
        veiculoRepo.adicionar(new model.VeiculoHibrido(
                3, "Toyota Corolla Cross Híbrido", 50.0, 60.0, 0.20, 360,
                50.0, 15.0, "Gasolina"));

        eletropostoRepo.adicionar(new model.Eletroposto(
                1, "EletroRio Shopping", "Shopping RioMar, Recife", 1,
                "CCS2, Tipo 2, CHAdeMO", 150.0, 2.50, 4));
        eletropostoRepo.adicionar(new model.Eletroposto(
                2, "Posto Verde Caruaru", "BR-232, Km 130, Caruaru", 2,
                "CCS2, Tipo 2", 50.0, 2.20, 2));
        eletropostoRepo.adicionar(new model.Eletroposto(
                3, "ChargePoint Garanhuns", "Av. Principal, Garanhuns", 4,
                "Tipo 2", 22.0, 1.90, 3));

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║  Dados de exemplo carregados!        ║");
        System.out.println("║  3 cidades | 3 veículos | 3 postos   ║");
        System.out.println("╚══════════════════════════════════════╝");
    }
}
