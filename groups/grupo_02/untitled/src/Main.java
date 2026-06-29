import java.util.Scanner;

import controller.CidadeController;
import controller.EletropostoController;
import controller.RotaController;
import controller.VeiculoController;

import repository.CidadeRepository;
import repository.EletropostoRepository;
import repository.VeiculoRepository;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        VeiculoRepository veiculoRepository =
                new VeiculoRepository();

        CidadeRepository cidadeRepository =
                new CidadeRepository();

        EletropostoRepository eletropostoRepository =
                new EletropostoRepository();

        VeiculoController veiculoController =
                new VeiculoController(veiculoRepository, sc);

        CidadeController cidadeController =
                new CidadeController(cidadeRepository, sc);

        EletropostoController eletropostoController =
                new EletropostoController(eletropostoRepository, sc);

        RotaController rotaController =
                new RotaController(
                        veiculoRepository,
                        cidadeRepository,
                        eletropostoRepository
                );

        int opcao;

        do {

            System.out.println("\n===== GREEN ROUTE =====");
            System.out.println("1 - Gerenciar Dados");
            System.out.println("2 - Simular Viagem");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();

            switch (opcao) {

                case 1:
                    menuCrud(
                            sc,
                            veiculoController,
                            cidadeController,
                            eletropostoController
                    );
                    break;

                case 2:

                    System.out.print("ID do veiculo: ");
                    int veiculoId = sc.nextInt();

                    System.out.print("ID da cidade destino: ");
                    int cidadeId = sc.nextInt();

                    rotaController.simularViagem(
                            veiculoId,
                            cidadeId
                    );

                    break;

                case 0:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }

        } while (opcao != 0);

        sc.close();
    }

    public static void menuCrud(
            Scanner sc,
            VeiculoController veiculoController,
            CidadeController cidadeController,
            EletropostoController eletropostoController) {

        int opcao;

        do {

            System.out.println("\n===== GERENCIAR DADOS =====");
            System.out.println("1 - Veiculos");
            System.out.println("2 - Cidades");
            System.out.println("3 - Eletropostos");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();

            switch (opcao) {

                case 1:
                    menuVeiculos(sc, veiculoController);
                    break;

                case 2:
                    menuCidades(sc, cidadeController);
                    break;

                case 3:
                    menuEletropostos(sc, eletropostoController);
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }

        } while (opcao != 0);
    }

    public static void menuVeiculos(
            Scanner sc,
            VeiculoController controller) {

        int opcao;

        do {

            System.out.println("\n===== VEICULOS =====");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Buscar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("5 - Listar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();

            switch (opcao) {

                case 1:
                    controller.cadastrar();
                    break;

                case 2:
                    controller.buscar();
                    break;

                case 3:
                    controller.atualizar();
                    break;

                case 4:
                    controller.excluir();
                    break;

                case 5:
                    controller.listar();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }

        } while (opcao != 0);
    }

    public static void menuCidades(
            Scanner sc,
            CidadeController controller) {

        int opcao;

        do {

            System.out.println("\n===== CIDADES =====");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Buscar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("5 - Listar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();

            switch (opcao) {

                case 1:
                    controller.cadastrar();
                    break;

                case 2:
                    controller.buscar();
                    break;

                case 3:
                    controller.atualizar();
                    break;

                case 4:
                    controller.excluir();
                    break;

                case 5:
                    controller.listar();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }

        } while (opcao != 0);
    }

    public static void menuEletropostos(
            Scanner sc,
            EletropostoController controller) {

        int opcao;

        do {

            System.out.println("\n===== ELETROPOSTOS =====");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Buscar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("5 - Listar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();

            switch (opcao) {

                case 1:
                    controller.cadastrar();
                    break;

                case 2:
                    controller.buscar();
                    break;

                case 3:
                    controller.atualizar();
                    break;

                case 4:
                    controller.excluir();
                    break;

                case 5:
                    controller.listar();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }

        } while (opcao != 0);
    }
}