import java.util.Scanner;
import controller.*;
import model.*;
import repository.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Inicializa as fontes de dados (Repositories)
        VeiculoRepository veiculoRepo = new VeiculoRepository();
        CidadeRepository cidadeRepo = new CidadeRepository();
        EletropostoRepository eletropostoRepo = new EletropostoRepository();

        // 2. Inicializa os Controllers passando os repositories correspondentes
        VeiculoController veiculoController = new VeiculoController(veiculoRepo);
        CidadeController cidadeController = new CidadeController(cidadeRepo);
        EletropostoController eletropostoController = new EletropostoController(eletropostoRepo);
        SimulacaoController simulacaoController = new SimulacaoController(veiculoRepo, cidadeRepo, eletropostoRepo);

        // Popula o sistema com dados automáticos para facilitar sua apresentação
        inicializarDadosExemplo(veiculoRepo, cidadeRepo, eletropostoRepo);

        int opcaoPrincipal = -1;

        while (opcaoPrincipal != 0) {
            System.out.println("\n==================================================");
            System.out.println("          GREENROUTE - SISTEMA DE LOGÍSTICA       ");
            System.out.println("==================================================");
            System.out.println("1 - Gerenciar Veículos");
            System.out.println("2 - Gerenciar Cidades");
            System.out.println("3 - Gerenciar Eletropostos");
            System.out.println("4 - Simular Rota de Viagem (Regra de Negócio)");
            System.out.println("0 - Sair do Sistema");
            System.out.println("==================================================");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcaoPrincipal = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcaoPrincipal = -1;
            }

            switch (opcaoPrincipal) {
                case 1:
                    menuVeiculos(scanner, veiculoController);
                    break;
                case 2:
                    menuCidades(scanner, cidadeController);
                    break;
                case 3:
                    menuEletropostos(scanner, eletropostoController);
                    break;
                case 4:
                    System.out.println("\n--- SIMULAÇÃO DE ROTA INTERMUNICIPAL ---");
                    try {
                        System.out.print("Digite o ID do Veículo para a viagem: ");
                        int vId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Digite o ID da Cidade de destino: ");
                        int cId = Integer.parseInt(scanner.nextLine());
                        
                        // Executa a regra de negócio isolada no controller
                        simulacaoController.rodarSimulacao(vId, cId);
                    } catch (NumberFormatException e) {
                        System.out.println("[Erro] Digite apenas IDs numéricos válidos!");
                    }
                    break;
                case 0:
                    System.out.println("\nEncerrando o GreenRoute. Histórico salvo na sessão.");
                    break;
                default:
                    System.out.println("[Aviso] Opção inválida! Tente novamente.");
            }
        }
        scanner.close();
    }

    // ==========================================
    // SUBMENU: GERENCIAMENTO DE VEÍCULOS (CRUD)
    // ==========================================
    private static void menuVeiculos(Scanner scanner, VeiculoController ctrl) {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- GERENCIAR VEÍCULOS ---");
            System.out.println("1 - Cadastrar Veículo Elétrico");
            System.out.println("2 - Cadastrar Veículo Híbrido");
            System.out.println("3 - Listar Todos os Veículos");
            System.out.println("4 - Atualizar Dados de um Veículo");
            System.out.println("5 - Excluir Veículo");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Opção: ");
            try { op = Integer.parseInt(scanner.nextLine()); } catch (NumberFormatException e) { op = -1; }

            try {
                switch (op) {
                    case 1:
                        System.out.print("ID do Veículo: "); int idE = Integer.parseInt(scanner.nextLine());
                        System.out.print("Modelo: "); String modE = scanner.nextLine();
                        System.out.print("Autonomia Máxima (km): "); double autE = Double.parseDouble(scanner.nextLine());
                        System.out.print("Carga de Bateria Atual (%): "); double carE = Double.parseDouble(scanner.nextLine());
                        System.out.print("Consumo Médio (Kwh/km): "); double consE = Double.parseDouble(scanner.nextLine());
                        System.out.print("Tempo Recarga Completa (min): "); int tempE = Integer.parseInt(scanner.nextLine());
                        System.out.print("Tipo do Conector (Ex: CCS2): "); String conE = scanner.nextLine();
                        System.out.print("Tempo de Recarga Rápida (min): "); int rapE = Integer.parseInt(scanner.nextLine());

                        VeiculoEletrico ve = new VeiculoEletrico(idE, modE, autE, carE, consE, tempE, conE, rapE);
                        if (ctrl.cadastrarVeiculo(ve)) System.out.println("[Sucesso] Veículo Elétrico cadastrado!");
                        else System.out.println("[Erro] ID já existente no sistema.");
                        break;

                    case 2:
                        System.out.print("ID do Veículo: "); int idH = Integer.parseInt(scanner.nextLine());
                        System.out.print("Modelo: "); String modH = scanner.nextLine();
                        System.out.print("Autonomia Máxima (km): "); double autH = Double.parseDouble(scanner.nextLine());
                        System.out.print("Carga de Bateria Atual (%): "); double carH = Double.parseDouble(scanner.nextLine());
                        System.out.print("Consumo Médio (Kwh/km): "); double consH = Double.parseDouble(scanner.nextLine());
                        System.out.print("Tempo Recarga Completa (min): "); int tempH = Integer.parseInt(scanner.nextLine());
                        System.out.print("Capacidade do Tanque (L): "); double capT = Double.parseDouble(scanner.nextLine());
                        System.out.print("Consumo Combustível (km/l): "); double consC = Double.parseDouble(scanner.nextLine());
                        System.out.print("Tipo do Combustível: "); String tipoC = scanner.nextLine();

                        VeiculoHibrido vh = new VeiculoHibrido(idH, modH, autH, carH, consH, tempH, capT, consC, tipoC);
                        if (ctrl.cadastrarVeiculo(vh)) System.out.println("[Sucesso] Veículo Híbrido cadastrado!");
                        else System.out.println("[Erro] ID já existente no sistema.");
                        break;

                    case 3:
                        System.out.println("\n--- VEÍCULOS CADASTRADOS ---");
                        Veiculo[] listaV = ctrl.listarVeiculos();
                        if (listaV.length == 0) System.out.println("Nenhum veículo na frota.");
                        for (Veiculo v : listaV) System.out.println(v);
                        break;

                    case 4:
                        System.out.print("Digite o ID do veículo que deseja atualizar: ");
                        int idAt = Integer.parseInt(scanner.nextLine());
                        Veiculo existente = ctrl.buscarVeiculo(idAt);
                        if (existente == null) {
                            System.out.println("[Erro] Veículo não encontrado.");
                            break;
                        }
                        System.out.println("Editando dados de: " + existente.getModelo());
                        System.out.print("Novo Modelo: "); String nm = scanner.nextLine();
                        System.out.print("Nova Carga de Bateria (%): "); double nc = Double.parseDouble(scanner.nextLine());
                        
                        if (existente instanceof VeiculoEletrico) {
                            VeiculoEletrico original = (VeiculoEletrico) existente;
                            VeiculoEletrico atualizado = new VeiculoEletrico(idAt, nm, original.getAutonomiaMaxima(), nc, original.getConsumoKwhPorKm(), original.getTempoRecargaCompleta(), original.getTipoConector(), original.getTempoRecargaRapida());
                            ctrl.atualizarVeiculo(idAt, atualizado);
                        } else {
                            VeiculoHibrido original = (VeiculoHibrido) existente;
                            VeiculoHibrido atualizado = new VeiculoHibrido(idAt, nm, original.getAutonomiaMaxima(), nc, original.getConsumoKwhPorKm(), original.getTempoRecargaCompleta(), original.getCapacidadeTanqueCombustivel(), original.getConsumoCombustivel(), original.getTipoCombustivel());
                            ctrl.atualizarVeiculo(idAt, atualizado);
                        }
                        System.out.println("[Sucesso] Dados atualizados localmente.");
                        break;

                    case 5:
                        System.out.print("Digite o ID do veículo para excluir: ");
                        int idEx = Integer.parseInt(scanner.nextLine());
                        if (ctrl.excluirVeiculo(idEx)) System.out.println("[Sucesso] Veículo removido da memória.");
                        else System.out.println("[Erro] Veículo não encontrado.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("[Erro] Entrada de dados incorreta. Operação cancelada.");
            }
        }
    }

    // ==========================================
    // SUBMENU: GERENCIAMENTO DE CIDADES (CRUD)
    // ==========================================
    private static void menuCidades(Scanner scanner, CidadeController ctrl) {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- GERENCIAR CIDADES ---");
            System.out.println("1 - Cadastrar Cidade");
            System.out.println("2 - Listar Cidades");
            System.out.println("3 - Excluir Cidade");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            try { op = Integer.parseInt(scanner.nextLine()); } catch (NumberFormatException e) { op = -1; }

            try {
                switch (op) {
                    case 1:
                        System.out.print("ID da Cidade (Código IBGE): "); int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Nome da Cidade: "); String nome = scanner.nextLine();
                        System.out.print("Estado (UF): "); String uf = scanner.nextLine();
                        System.out.print("Distância até a Capital (km): "); double dist = Double.parseDouble(scanner.nextLine());

                        Cidade cid = new Cidade(id, nome, uf, dist);
                        if (ctrl.cadastrarCidade(cid)) System.out.println("[Sucesso] Cidade cadastrada!");
                        else System.out.println("[Erro] ID de cidade já cadastrado.");
                        break;

                    case 2:
                        System.out.println("\n--- CIDADES MAPEADAS ---");
                        Cidade[] listaC = ctrl.listarCidades();
                        if (listaC.length == 0) System.out.println("Nenhuma cidade mapeada.");
                        for (Cidade c : listaC) System.out.println(c);
                        break;

                    case 3:
                        System.out.print("Digite o ID da cidade para exclusão: ");
                        int idEx = Integer.parseInt(scanner.nextLine());
                        if (ctrl.excluirCidade(idEx)) System.out.println("[Sucesso] Cidade removida do mapa de rotas.");
                        else System.out.println("[Erro] Cidade não encontrada.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("[Erro] Dados inválidos digitados.");
            }
        }
    }

    // ==========================================
    // SUBMENU: GERENCIAMENTO DE ELETROPOSTOS (CRUD)
    // ==========================================
    private static void menuEletropostos(Scanner scanner, EletropostoController ctrl) {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- GERENCIAR ELETROPOSTOS ---");
            System.out.println("1 - Cadastrar Eletroposto");
            System.out.println("2 - Listar Eletropostos");
            System.out.println("3 - Excluir Eletroposto");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            try { op = Integer.parseInt(scanner.nextLine()); } catch (NumberFormatException e) { op = -1; }

            try {
                switch (op) {
                    case 1:
                        System.out.print("ID do Eletroposto: "); int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Nome do Ponto de Carga: "); String nome = scanner.nextLine();
                        System.out.print("Localização (Rodovia/KM/Endereço): "); String loc = scanner.nextLine();
                        System.out.print("ID da Cidade Pertencente: "); int cidId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Tipos de Conectores (Ex: CCS2, Tipo 2): "); String con = scanner.nextLine();
                        System.out.print("Potência Máxima de Carga (kW): "); double pot = Double.parseDouble(scanner.nextLine());
                        System.out.print("Preço cobrado por kWh (R$): "); double preco = Double.parseDouble(scanner.nextLine());
                        System.out.print("Quantidade de Vagas Disponíveis: "); int vagas = Integer.parseInt(scanner.nextLine());

                        Eletroposto ep = new Eletroposto(id, nome, loc, cidId, con, pot, preco, vagas);
                        if (ctrl.cadastrarEletroposto(ep)) System.out.println("[Sucesso] Eletroposto registrado com sucesso!");
                        else System.out.println("[Erro] ID de eletroposto duplicado.");
                        break;

                    case 2:
                        System.out.println("\n--- REDE DE ELETROPOSTOS ---");
                        Eletroposto[] listaE = ctrl.listarEletropostos();
                        if (listaE.length == 0) System.out.println("Nenhum eletroposto na malha logística.");
                        for (Eletroposto e : listaE) System.out.println(e);
                        break;

                    case 3:
                        System.out.print("Digite o ID do eletroposto para remover: ");
                        int idEx = Integer.parseInt(scanner.nextLine());
                        if (ctrl.excluirEletroposto(idEx)) System.out.println("[Sucesso] Eletroposto deletado.");
                        else System.out.println("[Erro] Registro não encontrado.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("[Erro] Digitação inválida detectada.");
            }
        }
    }

    // CARGA DE DADOS AUTOMÁTICA EM MEMÓRIA
    private static void inicializarDadosExemplo(VeiculoRepository vR, CidadeRepository cR, EletropostoRepository eR) {
        vR.cadastrar(new VeiculoEletrico(1, "BYD Dolphin", 300.0, 40.0, 0.15, 40, "CCS2", 30));
        vR.cadastrar(new VeiculoHibrido(2, "Toyota Corolla Cross", 600.0, 100.0, 0.12, 0, 45.0, 15.0, "Gasolina"));
        
        cR.cadastrar(new Cidade(10, "Caruaru", "PE", 140.0));
        cR.cadastrar(new Cidade(20, "Gravatá", "PE", 85.0));
        
        eR.cadastrar(new Eletroposto(101, "Shell Recharge Caruaru", "BR-232, Km 132", 10, "CCS2, Tipo 2", 150.0, 2.15, 4));
        eR.cadastrar(new Eletroposto(102, "Eletroposto Shopping Caruaru", "Av. Adjar da Silva Casé", 10, "Tipo 2", 22.0, 0.0, 2));
    }
}
