package br.upe.greenroute.view;
import br.upe.greenroute.controller.ChargingStationController;

import java.util.Scanner;
public class ChargingStationMenu extends BaseMenu{
    private final Scanner scanner;
    private final ChargingStationController controller;
    private final ChargingStationView view;
    public ChargingStationMenu(Scanner scanner, ChargingStationView chargingStationView, ChargingStationController chargingStationController) {
        this.scanner = scanner;
        this.view = chargingStationView;
        this.controller = chargingStationController;
    }
    @Override
    public void showMenu() {
        String opcao;
        boolean executando = true;
        while (executando) {
            System.out.println("\n=== Menu Eletropostos ===");
            System.out.println("\nEscolha uma opção: ");
            System.out.println("\n1. Cadastrar eletroposto");
            System.out.println("2. Atualizar eletroposto");
            System.out.println("3. Buscar eletroposto");
            System.out.println("4. Remover eletroposto");
            System.out.println("5. Listar eletropostos");
            System.out.println("0. Voltar para o menu principal");
            opcao = scanner.nextLine();
            switch (opcao) {
                case "1" -> controller.addChargingStation();
                case "2" -> controller.updateChargingStation();
                case "3" -> controller.searchChargingStationById();
                case "4" -> controller.deleteChargingStationById();
                case "5" -> controller.listChargingStations();
                case "0" -> {
                    System.out.println("Voltando . . .");
                    executando = false;
                }
                default -> view.displayError("\nDigite uma opção válida!") ;
            }
        }
    }
}