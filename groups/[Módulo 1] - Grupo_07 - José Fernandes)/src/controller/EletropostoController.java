package controller;

import model.Eletroposto;
import repository.EletropostoRepository;
import repository.CidadeRepository;
import java.util.Scanner;

public class EletropostoController {

    private EletropostoRepository repository;
    private CidadeRepository cidadeRepository; // Para validar se o cidadeId existe
    private Scanner scanner;

    public EletropostoController(EletropostoRepository repository,
                                 CidadeRepository cidadeRepository,
                                 Scanner scanner) {
        this.repository = repository;
        this.cidadeRepository = cidadeRepository;
        this.scanner = scanner;
    }

    public void cadastrar() {
        System.out.println("\n--- CADASTRAR ELETROPOSTO ---");

        int id = lerIdUnico();

        System.out.print("Nome do posto: ");
        String nome = scanner.nextLine();

        System.out.print("Localização (endereço/rodovia): ");
        String local = scanner.nextLine();

        // Valida se o cidadeId existe
        int cidadeId = lerCidadeIdValido();

        System.out.print("Tipos de Conectores (ex: CCS2, Tipo 2): ");
        String conectores = scanner.nextLine();

        System.out.print("Potência do Carregador (kW): ");
        double potencia = Double.parseDouble(scanner.nextLine());

        System.out.print("Preço por kWh (R$): ");
        double preco = Double.parseDouble(scanner.nextLine());

        System.out.print("Vagas Disponíveis: ");
        int vagas = Integer.parseInt(scanner.nextLine());

        Eletroposto e = new Eletroposto(id, nome, local, cidadeId, conectores, potencia, preco, vagas);

        if (repository.adicionar(e)) {
            System.out.println("✔ Eletroposto cadastrado com sucesso!");
        } else {
            System.out.println("✘ Erro: ID já cadastrado.");
        }
    }

    public void listarTodos() {
        System.out.println("\n--- LISTA DE ELETROPOSTOS ---");
        Eletroposto[] todos = repository.listarTodos();
        if (todos.length == 0) {
            System.out.println("Nenhum eletroposto cadastrado.");
            return;
        }
        for (Eletroposto e : todos) {
            System.out.println(e);
        }
        System.out.println("Total: " + todos.length + " eletroposto(s).");
    }

    public void buscarPorId() {
        System.out.println("\n--- BUSCAR ELETROPOSTO ---");
        System.out.print("Informe o ID do eletroposto: ");
        int id = Integer.parseInt(scanner.nextLine());

        Eletroposto e = repository.buscarPorId(id);
        if (e != null) {
            System.out.println("Eletroposto encontrado:");
            System.out.println(e);
        } else {
            System.out.println("✘ Eletroposto com ID " + id + " não encontrado.");
        }
    }

    public void atualizar() {
        System.out.println("\n--- ATUALIZAR ELETROPOSTO ---");
        System.out.print("Informe o ID do eletroposto a atualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Eletroposto existente = repository.buscarPorId(id);
        if (existente == null) {
            System.out.println("✘ Eletroposto não encontrado.");
            return;
        }

        System.out.println("Eletroposto atual: " + existente);
        System.out.println("Pressione Enter para manter o valor atual.");

        System.out.print("Novo nome [" + existente.getNome() + "]: ");
        String nome = lerOuManter(existente.getNome());

        System.out.print("Nova localização [" + existente.getLocalizacao() + "]: ");
        String local = lerOuManter(existente.getLocalizacao());

        System.out.print("Novo CidadeID [" + existente.getCidadeId() + "]: ");
        String cidadeStr = scanner.nextLine();
        int cidadeId = cidadeStr.isEmpty() ? existente.getCidadeId() : Integer.parseInt(cidadeStr);

        System.out.print("Novos conectores [" + existente.getTiposConectoresDisponiveis() + "]: ");
        String conectores = lerOuManter(existente.getTiposConectoresDisponiveis());

        System.out.print("Nova potência kW [" + existente.getPotenciaCargaKw() + "]: ");
        double potencia = lerDoubleOuManter(existente.getPotenciaCargaKw());

        System.out.print("Novo preço/kWh [" + existente.getPrecoPorKwh() + "]: ");
        double preco = lerDoubleOuManter(existente.getPrecoPorKwh());

        System.out.print("Novas vagas [" + existente.getVagasDisponiveis() + "]: ");
        int vagas = lerIntOuManter(existente.getVagasDisponiveis());

        Eletroposto atualizado = new Eletroposto(id, nome, local, cidadeId,
                conectores, potencia, preco, vagas);
        if (repository.atualizar(atualizado)) {
            System.out.println("✔ Eletroposto atualizado com sucesso!");
        } else {
            System.out.println("✘ Erro ao atualizar.");
        }
    }

    public void remover() {
        System.out.println("\n--- REMOVER ELETROPOSTO ---");
        System.out.print("Informe o ID do eletroposto a remover: ");
        int id = Integer.parseInt(scanner.nextLine());

        Eletroposto e = repository.buscarPorId(id);
        if (e == null) {
            System.out.println("✘ Eletroposto não encontrado.");
            return;
        }

        System.out.println("Eletroposto a remover: " + e);
        System.out.print("Confirmar remoção? (s/n): ");
        String conf = scanner.nextLine();

        if (conf.equalsIgnoreCase("s")) {
            repository.remover(id);
            System.out.println("✔ Eletroposto removido com sucesso!");
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    private int lerIdUnico() {
        int id;
        do {
            System.out.print("ID (único): ");
            id = Integer.parseInt(scanner.nextLine());
            if (repository.idExiste(id)) System.out.println("✘ ID já cadastrado.");
        } while (repository.idExiste(id));
        return id;
    }

    private int lerCidadeIdValido() {
        int cidadeId;
        while (true) {
            System.out.print("ID da Cidade (deve existir no sistema): ");
            cidadeId = Integer.parseInt(scanner.nextLine());
            if (cidadeRepository.buscarPorId(cidadeId) != null) {
                return cidadeId;
            }
            System.out.println("✘ Cidade com ID " + cidadeId + " não encontrada. Cadastre a cidade primeiro.");
        }
    }

    private String lerOuManter(String atual) {
        String e = scanner.nextLine();
        return e.isEmpty() ? atual : e;
    }

    private double lerDoubleOuManter(double atual) {
        String e = scanner.nextLine();
        return e.isEmpty() ? atual : Double.parseDouble(e);
    }

    private int lerIntOuManter(int atual) {
        String e = scanner.nextLine();
        return e.isEmpty() ? atual : Integer.parseInt(e);
    }

    public EletropostoRepository getRepository() { return repository; }
}
