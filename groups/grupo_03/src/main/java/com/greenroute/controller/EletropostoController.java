package com.greenroute.controller;

import com.greenroute.model.Eletroposto;
import com.greenroute.repository.EletropostoRepository;

import java.util.Scanner;

public class EletropostoController {

    private EletropostoRepository eletropostoRepository = new EletropostoRepository();
    private Scanner scanner = new Scanner(System.in);

    public Eletroposto[] getEletropostos() {
        return eletropostoRepository.getEletropostos();
    }

    public int getQuantidadeEletropostos() {
        return eletropostoRepository.getQuantidadeEletropostos();
    }

    //MENU RESPONSAVEL PELO ELETROPOSTO
    public void menuEletroposto() {

        int opcao;

        do {
            System.out.println("=== MENU ELETROPOSTO ===");
            System.out.println("1 - Cadastrar Eletroposto");
            System.out.println("2 - Listar Eletropostos");
            System.out.println("3 - Buscar Eletroposto");
            System.out.println("4 - Atualizar Eletroposto");
            System.out.println("5 - Remover Eletroposto");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                    cadastrarEletroposto();
                    break;

                case 2:
                    listarEletropostos();
                    break;

                case 3:
                    buscarEletroposto();
                    break;

                case 4:
                    atualizarEletroposto();
                    break;

                case 5:
                    removerEletroposto();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    // CADASTRAR ELETROPOSTO NO ARRAY
    private void cadastrarEletroposto() {

        System.out.print("Digite o ID do eletroposto: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (id <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        if (eletropostoRepository.buscarEletroposto(id) != null) {
            System.out.println("Já existe um eletroposto com esse ID!");
            return;
        }

        System.out.print("Digite o nome do eletroposto: ");
        String nome = scanner.nextLine();

        if (nome.trim().isEmpty()) {
            System.out.println("Nome inválido!");
            return;
        }

        System.out.print("Digite a localização: ");
        String localizacao = scanner.nextLine();

        if (localizacao.trim().isEmpty()) {
            System.out.println("Localização inválida!");
            return;
        }

        System.out.print("Digite o ID da cidade: ");
        int cidadeId = scanner.nextInt();
        scanner.nextLine();

        if (cidadeId <= 0) {
            System.out.println("ID da cidade inválido!");
            return;
        }

        System.out.print("Digite os conectores disponíveis: ");
        String tiposConectoresDisponiveis = scanner.nextLine();

        if (tiposConectoresDisponiveis.trim().isEmpty()) {
            System.out.println("Conector inválido!");
            return;
        }

        System.out.print("Digite a potência de carga (kW): ");
        double potenciaCargaKw = scanner.nextDouble();

        if (potenciaCargaKw <= 0) {
            System.out.println("Potência inválida!");
            return;
        }

        System.out.print("Digite o preço por kWh: ");
        double precoPorKwh = scanner.nextDouble();

        if (precoPorKwh <= 0) {
            System.out.println("Preço inválido!");
            return;
        }

        System.out.print("Digite a quantidade de vagas disponíveis: ");
        int vagasDisponiveis = scanner.nextInt();

        if (vagasDisponiveis < 0) {
            System.out.println("Quantidade de vagas inválida!");
            return;
        }

        Eletroposto eletroposto = new Eletroposto(
                id,
                nome,
                localizacao,
                cidadeId,
                tiposConectoresDisponiveis,
                potenciaCargaKw,
                precoPorKwh,
                vagasDisponiveis
        );

        eletropostoRepository.cadastrarEletroposto(eletroposto);

        System.out.println("Eletroposto cadastrado com sucesso!");
    }

    //LISTAR OS ELETROPOSTOS DO ARRAY
    private void listarEletropostos() {
        eletropostoRepository.listarEletropostos();
    }

    //BUSCAR O ELETROPOSTO CADASTRADO NO ARRAY
    private void buscarEletroposto() {

        System.out.print("Digite o ID do eletroposto que deseja buscar: ");
        int id = scanner.nextInt();

        if (id <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        Eletroposto eletroposto = eletropostoRepository.buscarEletroposto(id);

        if (eletroposto != null) {
            System.out.println(eletroposto);
        } else {
            System.out.println("Eletroposto não encontrado!");
        }
    }

    //ATUALIZAR O ELETROPOSTO CADASTRADO NO ARRAY
    private void atualizarEletroposto() {

        System.out.print("Digite o ID do eletroposto que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (id <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        if (eletropostoRepository.buscarEletroposto(id) == null) {
            System.out.println("Eletroposto não encontrado!");
            return;
        }

        System.out.print("Digite o novo nome: ");
        String nome = scanner.nextLine();

        if (nome.trim().isEmpty()) {
            System.out.println("Nome inválido!");
            return;
        }

        System.out.print("Digite a nova localização: ");
        String localizacao = scanner.nextLine();

        if (localizacao.trim().isEmpty()) {
            System.out.println("Localização inválida!");
            return;
        }

        System.out.print("Digite o novo ID da cidade: ");
        int cidadeId = scanner.nextInt();
        scanner.nextLine();

        if (cidadeId <= 0) {
            System.out.println("ID da cidade inválido!");
            return;
        }

        System.out.print("Digite os novos conectores disponíveis: ");
        String tiposConectoresDisponiveis = scanner.nextLine();

        if (tiposConectoresDisponiveis.trim().isEmpty()) {
            System.out.println("Conector inválido!");
            return;
        }

        System.out.print("Digite a nova potência de carga (kW): ");
        double potenciaCargaKw = scanner.nextDouble();

        if (potenciaCargaKw <= 0) {
            System.out.println("Potência inválida!");
            return;
        }

        System.out.print("Digite o novo preço por kWh: ");
        double precoPorKwh = scanner.nextDouble();

        if (precoPorKwh <= 0) {
            System.out.println("Preço inválido!");
            return;
        }

        System.out.print("Digite a nova quantidade de vagas disponíveis: ");
        int vagasDisponiveis = scanner.nextInt();

        if (vagasDisponiveis < 0) {
            System.out.println("Quantidade de vagas inválida!");
            return;
        }

        Eletroposto eletropostoAtualizado = new Eletroposto(
                id,
                nome,
                localizacao,
                cidadeId,
                tiposConectoresDisponiveis,
                potenciaCargaKw,
                precoPorKwh,
                vagasDisponiveis
        );

        boolean atualizou = eletropostoRepository.atualizarEletroposto(eletropostoAtualizado);

        if (atualizou) {
            System.out.println("Eletroposto atualizado com sucesso!");
        } else {
            System.out.println("Eletroposto não encontrado!");
        }
    }

    //REMOVER O ELETROPOSTO CADASTRADO NO ARRAY
    private void removerEletroposto() {

        System.out.print("Digite o ID do eletroposto que deseja remover: ");
        int id = scanner.nextInt();

        if (id <= 0) {
            System.out.println("ID inválido!");
            return;
        }

        boolean removeu = eletropostoRepository.removerEletroposto(id);

        if (removeu) {
            System.out.println("Eletroposto removido com sucesso!");
        } else {
            System.out.println("Eletroposto não encontrado!");
        }
    }
}