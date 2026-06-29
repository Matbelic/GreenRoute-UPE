package controller;

import java.util.Scanner;

import model.Cidade;
import repository.CidadeRepository;

public class CidadeController {

    private CidadeRepository repository;
    private Scanner sc;

    public CidadeController(CidadeRepository repository, Scanner sc) {
        this.repository = repository;
        this.sc = sc;
    }

    public void cadastrar() {

        System.out.print("id: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Estado: ");
        String estado = sc.nextLine();

        System.out.print("Distância da capital: ");
        double distancia = sc.nextDouble();

        Cidade cidade = new Cidade(id, nome, estado, distancia);

        repository.cadastrar(cidade);

        System.out.println("Cidade cadastrada.");
    }

    public void buscar() {

        System.out.print("id da cidade: ");
        int id = sc.nextInt();

        Cidade cidade = repository.buscarPorId(id);

        if (cidade != null) {
            System.out.println(cidade);
        } else {
            System.out.println("Cidade não encontrada.");
        }
    }

    public void listar() {

        Cidade[] cidades = repository.listar();

        for (int i = 0; i < repository.getQuantidade(); i++) {
            System.out.println(cidades[i]);
        }
    }

    public void excluir() {

        System.out.print("id da cidade: ");
        int id = sc.nextInt();

        if (repository.excluir(id)) {
            System.out.println("Cidade removida.");
        } else {
            System.out.println("Cidade nao encontrada.");
        }
    }

    public void atualizar() {

        System.out.print("Novo id da cidade: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Novo nome: ");
        String nome = sc.nextLine();

        System.out.print("Novo estado: ");
        String estado = sc.nextLine();

        System.out.print("Nova distancia: ");
        double distancia = sc.nextDouble();

        Cidade cidade = new Cidade(id, nome, estado, distancia);

        if (repository.atualizar(id, cidade)) {
            System.out.println("Cidade atualizada.");
        } else {
            System.out.println("Cidade nao encontrada.");
        }
    }
}