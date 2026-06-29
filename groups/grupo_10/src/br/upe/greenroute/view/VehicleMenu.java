package br.upe.greenroute.view;
import br.upe.greenroute.controller.VehicleController;

import java.util.Scanner;
public class VehicleMenu extends BaseMenu{
    private final Scanner scanner;
    private final VehicleView view;
    private final VehicleController controller;
    public VehicleMenu(Scanner scanner, VehicleView vehicleView, VehicleController vehicleController) {
        this.scanner = scanner;
        this.view = vehicleView;
        this.controller = vehicleController;
    }
    public void showMenu() {
        String opcao;
        boolean executando = true;
        while (executando) {
            System.out.println("\n=== Menu Veículos ===");
            System.out.println("\nEscolha uma opção: ");
            System.out.println("\n1. Cadastrar veículo");
            System.out.println("2. Atualizar veículo");
            System.out.println("3. Buscar veículo");
            System.out.println("4. Remover veículo");
            System.out.println("5. Listar veículos");
            System.out.println("0. Voltar para o menu principal");
            opcao = scanner.nextLine();
            switch (opcao) {
                case "1" -> controller.addVehicle();
                case "2" -> controller.updateVehicle();
                case "3" -> controller.searchVehicleById();
                case "4" -> controller.deleteVehicleById();
                case "5" -> controller.listVehicles();
                case "0" -> {
                    System.out.println("Voltando . . .");
                    executando = false;
                }
                default -> view.displayError("\nDigite uma opção válida!");
            }
        }
    }
}