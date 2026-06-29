package controller;

import model.Cidade;
import repository.CidadeRepository;
import java.util.Scanner;

public class CidadeController {

    private CidadeRepository repository;
    private Scanner scanner;

    public CidadeController(CidadeRepository repository, Scanner scanner) {
        this.repository = repository;
        this.scanner = scanner;
    }

    public void cadastrar() {
        System.out.println("\n--- CADASTRAR CIDADE ---");

        int id = lerIdUnico();

        System.out.print("Nome da cidade: ");
        String nome = scanner.nextLine();

        System.out.print("Estado (UF, ex: PE, SP): ");
        String estado = scanner.nextLine().toUpperCase();

        System.out.print("Distância da Capital (km): ");
        double dist = Double.parseDouble(scanner.nextLine());

        Cidade c = new Cidade(id, nome, estado, dist);
        if (repository.adicionar(c)) {
            System.out.println("✔ Cidade cadastrada com sucesso!");
        } else {
            System.out.println("✘ Erro: ID já cadastrado.");
        }
    }

    public void listarTodas() {
        System.out.println("\n--- LISTA DE CIDADES ---");
        Cidade[] todas = repository.listarTodos();
        if (todas.length == 0) {
            System.out.println("Nenhuma cidade cadastrada.");
            return;
        }
        for (Cidade c : todas) {
            System.out.println(c);
        }
        System.out.println("Total: " + todas.length + " cidade(s).");
    }

    public void buscarPorId() {
        System.out.println("\n--- BUSCAR CIDADE ---");
        System.out.print("Informe o ID da cidade: ");
        int id = Integer.parseInt(scanner.nextLine());

        Cidade c = repository.buscarPorId(id);
        if (c != null) {
            System.out.println("Cidade encontrada:");
            System.out.println(c);
        } else {
            System.out.println("✘ Cidade com ID " + id + " não encontrada.");
        }
    }

    public void atualizar() {
        System.out.println("\n--- ATUALIZAR CIDADE ---");
        System.out.print("Informe o ID da cidade a atualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Cidade existente = repository.buscarPorId(id);
        if (existente == null) {
            System.out.println("✘ Cidade não encontrada.");
            return;
        }

        System.out.println("Cidade atual: " + existente);
        System.out.println("Pressione Enter para manter o valor atual.");

        System.out.print("Novo nome [" + existente.getNome() + "]: ");
        String nome = lerOuManter(existente.getNome());

        System.out.print("Novo estado [" + existente.getEstado() + "]: ");
        String estadoInput = scanner.nextLine();
        String estado = estadoInput.isEmpty() ? existente.getEstado() : estadoInput.toUpperCase();

        System.out.print("Nova distância da capital [" + existente.getDistanciaDaCapital() + "]: ");
        double dist = lerDoubleOuManter(existente.getDistanciaDaCapital());

        Cidade atualizada = new Cidade(id, nome, estado, dist);
        if (repository.atualizar(atualizada)) {
            System.out.println("✔ Cidade atualizada com sucesso!");
        } else {
            System.out.println("✘ Erro ao atualizar.");
        }
    }


    public void remover() {
        System.out.println("\n--- REMOVER CIDADE ---");
        System.out.print("Informe o ID da cidade a remover: ");
        int id = Integer.parseInt(scanner.nextLine());

        Cidade c = repository.buscarPorId(id);
        if (c == null) {
            System.out.println("✘ Cidade não encontrada.");
            return;
        }

        System.out.println("Cidade a remover: " + c);
        System.out.print("Confirmar remoção? (s/n): ");
        String conf = scanner.nextLine();

        if (conf.equalsIgnoreCase("s")) {
            repository.remover(id);
            System.out.println("✔ Cidade removida com sucesso!");
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    private int lerIdUnico() {
        int id;
        do {
            System.out.print("ID (único, ex: código IBGE): ");
            id = Integer.parseInt(scanner.nextLine());
            if (repository.idExiste(id)) System.out.println("✘ ID já cadastrado.");
        } while (repository.idExiste(id));
        return id;
    }

    private String lerOuManter(String atual) {
        String e = scanner.nextLine();
        return e.isEmpty() ? atual : e;
    }

    private double lerDoubleOuManter(double atual) {
        String e = scanner.nextLine();
        return e.isEmpty() ? atual : Double.parseDouble(e);
    }

    public CidadeRepository getRepository() { return repository; }
}
