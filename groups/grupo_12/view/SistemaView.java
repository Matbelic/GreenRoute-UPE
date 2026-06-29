package view;

import controller.RotaControle;
import model.Cidade;
import model.Eletroposto;
import model.Veiculo;
import model.VeiculoEletrico;
import model.VeiculoHibrido;
import repository.CidadeRepository;
import repository.EletropostoRepository;
import repository.VeiculoRepository;

import java.util.Scanner;

public class SistemaView {
    private RotaControle controller;
    private VeiculoRepository veiculoRepo;
    private CidadeRepository cidadeRepo;
    private EletropostoRepository eletropostoRepo;

    // Geradores sequenciais de IDs para evitar repetições manuais
    private int idVeiculoSequencial = 1;
    private int idCidadeSequencial = 1;
    private int idEletropostoSequencial = 1;

    public SistemaView(RotaControle controller, VeiculoRepository veiculoRepo,
                       CidadeRepository cidadeRepo, EletropostoRepository eletropostoRepo) {
        this.controller = controller;
        this.veiculoRepo = veiculoRepo;
        this.cidadeRepo = cidadeRepo;
        this.eletropostoRepo = eletropostoRepo;
    }

    private void executarSimulacaoRota(Scanner scanner) {

        System.out.print("ID do veículo: ");
        int veiculoId = Integer.parseInt(scanner.nextLine());

        System.out.print("ID da cidade destino: ");
        int cidadeId = Integer.parseInt(scanner.nextLine());

        controller.simularViagem(veiculoId, cidadeId);
    }

    public void exibirMenuPrincipal() {
        Scanner scanner = new Scanner(System.in);

        int opcaoPrincipal = -1;

        while (opcaoPrincipal != 0) {
            System.out.println("\n------GREENROUTE - MENU PRINCIPAL------");
            System.out.println("1. Gerenciar Veículos");
            System.out.println("2. Gerenciar Cidades");
            System.out.println("3. Gerenciar Eletropostos");
            System.out.println("4. Simular Rota Intermunicipal");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcaoPrincipal = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número inteiro válido.");
                continue;
            }

            switch (opcaoPrincipal) {
                case 1:
                    menuVeiculos(scanner);
                    break;
                case 2:
                    menuCidades(scanner);
                    break;
                case 3:
                    menuEletropostos(scanner);
                    break;
                case 4:
                    executarSimulacaoRota(scanner);
                    break;
                case 0:
                    System.out.println("\nEncerrando o sistema GreenRoute. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        scanner.close();
    }

    private void menuVeiculos(Scanner scanner) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- SUBMENU: GERENCIAR VEÍCULOS ---");
            System.out.println("1. Cadastrar Veículo Elétrico");
            System.out.println("2. Cadastrar Veículo Híbrido");
            System.out.println("3. Listar Frota de Veículos");
            System.out.println("4. Atualizar Veículo por ID");
            System.out.println("5. Excluir Veículo por ID");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1: // Elétrico
                    System.out.print("Modelo: ");
                    String modE = scanner.nextLine();
                    System.out.print("Autonomia Máxima (km): ");
                    double autE = Double.parseDouble(scanner.nextLine());
                    System.out.print("Carga Atual da Bateria (%): ");
                    double cargaE = Double.parseDouble(scanner.nextLine());
                    System.out.print("Tipo de Conector (ex: CCS2): ");
                    String conector = scanner.nextLine();
                    System.out.print("Tempo Recarga Rápida (min): ");
                    int tRapido = Integer.parseInt(scanner.nextLine());
                    System.out.print("Consumo (kWh por km): ");
                    double consE = Double.parseDouble(scanner.nextLine());
                    System.out.print("Tempo Recarga Completa (min): ");
                    int tCompletoE = Integer.parseInt(scanner.nextLine());

                    VeiculoEletrico ve = new VeiculoEletrico(idVeiculoSequencial++, modE, autE, cargaE, conector, tRapido, consE, tCompletoE);
                    veiculoRepo.cadastrar(ve);
                    System.out.println("Veículo Elétrico cadastrado com ID: " + (idVeiculoSequencial - 1));
                    break;

                case 2: // Híbrido
                    System.out.print("Modelo: ");
                    String modH = scanner.nextLine();
                    System.out.print("Autonomia Máxima (km): ");
                    double autH = Double.parseDouble(scanner.nextLine());
                    System.out.print("Carga Atual da Bateria (%): ");
                    double cargaH = Double.parseDouble(scanner.nextLine());
                    System.out.print("Consumo (kWh por km): ");
                    double consH = Double.parseDouble(scanner.nextLine());
                    System.out.print("Tempo Recarga Completa (min): ");
                    int tCompletoH = Integer.parseInt(scanner.nextLine());
                    System.out.print("Capacidade do Tanque (L): ");
                    double tanque = Double.parseDouble(scanner.nextLine());
                    System.out.print("Consumo de Combustível (km/L): ");
                    double consComb = Double.parseDouble(scanner.nextLine());
                    System.out.print("Tipo de Combustível: ");
                    String tComb = scanner.nextLine();

                    VeiculoHibrido vh = new VeiculoHibrido(idVeiculoSequencial++, modH, autH, cargaH, consH, tCompletoH, tanque, consComb, tComb);
                    veiculoRepo.cadastrar(vh);
                    System.out.println("Veículo Híbrido cadastrado com ID: " + (idVeiculoSequencial - 1));
                    break;

                case 3: // Listar
                    Veiculo[] frota = veiculoRepo.listarTodos();
                    if (frota.length == 0) {
                        System.out.println("Nenhum veículo cadastrado na frota.");
                    } else {
                        System.out.println("\n--- FROTA REGISTRADA ---");
                        for (Veiculo v : frota) {
                            String tipo = (v instanceof VeiculoEletrico) ? "[ELÉTRICO]" : "[HÍBRIDO]";
                            System.out.println("ID: " + v.getId() + " " + tipo + " | Modelo: " + v.getModelo() + " | Bateria: " + v.getCargaBateriaAtual() + "% | Autonomia Max: " + v.getAutonomiaMaxima() + "km");
                        }
                    }
                    break;

                case 4: // Atualizar
                    System.out.print("ID do veículo: ");
                    int id = Integer.parseInt(scanner.nextLine());

                    Veiculo v = veiculoRepo.buscarPorId(id);

                    if(v != null){
                        System.out.print("Novo modelo: ");
                        v.setModelo(scanner.nextLine());

                        veiculoRepo.atualizar(v);

                        System.out.println("Atualizado.");
                    } else {
                        System.out.println("Erro: Veículo não encontrado.");
                    }
                    break;

                case 5: // Excluir
                    System.out.print("Digite o ID do veículo a remover: ");
                    int idRemover = Integer.parseInt(scanner.nextLine());
                    if (veiculoRepo.excluir(idRemover)) {
                        System.out.println("Veículo removido com sucesso.");
                    } else {
                        System.out.println("Erro: Veículo não encontrado.");
                    }
                    break;

                default:
                    if (opcao != 0) {
                        System.out.println("Opção inválida! Tente novamente.");
                    }
            }
        }
    }

    private void menuCidades(Scanner scanner) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- SUBMENU: GERENCIAR CIDADES ---");
            System.out.println("1. Cadastrar Cidade");
            System.out.println("2. Listar Cidades");
            System.out.println("3. Atualizar Cidade por ID");
            System.out.println("4. Excluir Cidade por ID");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Nome da Cidade: ");
                    String nome = scanner.nextLine();
                    System.out.print("Estado (UF): ");
                    String uf = scanner.nextLine();
                    System.out.print("Distância até a Capital (km): ");
                    double dist = Double.parseDouble(scanner.nextLine());

                    Cidade novaCidade = new Cidade(idCidadeSequencial++, nome, uf, dist);
                    cidadeRepo.cadastrar(novaCidade);
                    System.out.println("Cidade cadastrada com ID: " + (idCidadeSequencial - 1));
                    break;

                case 2:
                    Cidade[] cidades = cidadeRepo.listarTodos();
                    if (cidades.length == 0) {
                        System.out.println("Nenhuma cidade cadastrada.");
                    } else {
                        System.out.println("\n--- MALHA DE CIDADES ---");
                        for (Cidade c : cidades) {
                            System.out.println("ID: " + c.getId() + " | " + c.getNome() + " - " + c.getEstado() + " | Distância da Capital: " + c.getDistanciaDaCapital() + " km");
                        }
                    }
                    break;


                case 3:
                    System.out.print("ID da cidade: ");
                    int id = Integer.parseInt(scanner.nextLine());

                    Cidade c = cidadeRepo.buscarPorId(id);

                    if(c != null){
                        System.out.print("Novo nome: ");
                        c.setNome(scanner.nextLine());

                        cidadeRepo.atualizar(c);

                        System.out.println("Atualizado.");
                    } else {
                        System.out.println("Erro: Cidade não encontrada.");
                    }
                    break;


                case 4:
                    System.out.print("Digite o ID da cidade a remover: ");
                    int idRemover = Integer.parseInt(scanner.nextLine());
                    if (cidadeRepo.excluir(idRemover)) {
                        System.out.println("Cidade removida com sucesso.");
                    } else {
                        System.out.println("Erro: Cidade não encontrada.");
                    }
                    break;

                default:
                    if (opcao != 0) {
                        System.out.println("Opção inválida! Tente novamente.");
                    }
            }
        }
    }

    private void menuEletropostos(Scanner scanner) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- SUBMENU: GERENCIAR ELETROPOSTOS ---");
            System.out.println("1. Cadastrar Eletroposto");
            System.out.println("2. Listar Todos os Eletropostos");
            System.out.println("3. Atualizar Eletroposto por ID");
            System.out.println("4. Excluir Eletroposto por ID");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Nome do Eletroposto: ");
                    String nome = scanner.nextLine();
                    System.out.print("Endereço/Localização: ");
                    String loc = scanner.nextLine();
                    System.out.print("ID da Cidade onde se localiza: ");
                    int cidId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Tipos de Conectores (ex: CCS2, Tipo 2): ");
                    String conectores = scanner.nextLine();
                    System.out.print("Potência Máxima de Carga (kW): ");
                    double pot = Double.parseDouble(scanner.nextLine());
                    System.out.print("Preço por kWh (R$): ");
                    double preco = Double.parseDouble(scanner.nextLine());
                    System.out.print("Quantidade de Vagas Livres: ");
                    int vagas = Integer.parseInt(scanner.nextLine());

                    Eletroposto ep = new Eletroposto(idEletropostoSequencial++, nome, loc, cidId, conectores, pot, preco, vagas);
                    eletropostoRepo.cadastrar(ep);
                    System.out.println("Eletroposto cadastrado com ID: " + (idEletropostoSequencial - 1));
                    break;

                case 2:
                    Eletroposto[] postos = eletropostoRepo.listarTodos();
                    if (postos.length == 0) {
                        System.out.println("Nenhum eletroposto registrado.");
                    } else {
                        System.out.println("\n--- REDE DE INFRAESTRUTURA DE RECARGA ---");
                        for (Eletroposto e : postos) {
                            System.out.println("ID: " + e.getId() + " | Nome: " + e.getNome() + " | Cidade ID: " + e.getCidadeId() + " | Vagas Livres: " + e.getVagasDisponiveis());
                        }
                    }
                    break;

                case 3:
                    System.out.print("ID do eletroposto: ");
                    int id = Integer.parseInt(scanner.nextLine());

                    Eletroposto epAtualizar = eletropostoRepo.buscarPorId(id);

                    if (epAtualizar != null) {
                        System.out.print("Novo nome: ");
                        epAtualizar.setNome(scanner.nextLine());

                        eletropostoRepo.atualizar(epAtualizar);

                        System.out.println("Atualizado.");
                    } else {
                        System.out.println("Eletroposto não encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Digite o ID do eletroposto a remover: ");
                    int idRemover = Integer.parseInt(scanner.nextLine());
                    if (eletropostoRepo.excluir(idRemover)) {
                        System.out.println("Eletroposto removido com sucesso.");
                    } else {
                        System.out.println("Erro: Eletroposto não encontrado.");
                    }
                    break;

                default:
                    if (opcao != 0) {
                        System.out.println("Opção inválida! Tente novamente.");
                    }
            }
        }
    }
}
