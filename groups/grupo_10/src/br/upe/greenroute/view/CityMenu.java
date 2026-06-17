package br.upe.greenroute.view;
import br.upe.greenroute.controller.CityController;

import java.util.Scanner;
public class CityMenu extends BaseMenu {
    private final Scanner scanner;
    private final CityView view;
    private final CityController controller;

    public CityMenu(Scanner scanner,CityView cityView, CityController cityController) {
        this.scanner = scanner;
        this.view = cityView;
        this.controller = cityController;
    }
    @Override
    public void showMenu() {
        String  opcao;
        boolean executando = true;
        while (executando) {
            System.out.println("\n=== Menu Cidades ===");
            System.out.println("\nEscolha uma opção: ");
            System.out.println("\n1. Cadastrar cidade");
            System.out.println("2. Atualizar cidade");
            System.out.println("3. Buscar cidade");
            System.out.println("4. Remover cidade");
            System.out.println("5. Listar cidades");
            System.out.println("0. Voltar para o menu principal");
            opcao = scanner.nextLine();
            switch (opcao) {
                case "1" -> controller.addCity();
                case "2" -> controller.updateCity();
                case "3" -> controller.searchCityById();
                case "4" -> controller.deleteCityById();
                case "5" -> controller.listCities();
                case "0" -> {
                    System.out.println("Voltando . . .");
                    executando = false;
                }
                default -> view.displayError("\nDigite uma opção válida!");
            }
        }
    }
}