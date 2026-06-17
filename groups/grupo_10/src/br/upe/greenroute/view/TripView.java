package br.upe.greenroute.view;

import java.util.Scanner;

public class TripView extends BaseView{
    private final Scanner scanner;

    public TripView(Scanner scanner) {
        super(scanner);
        this.scanner = scanner;
    }
    public boolean askWantToMakeAStop() {
        System.out.println("Deseja fazer uma parada? (S/N)");
        String answer = scanner.nextLine();
        return answer.equalsIgnoreCase("S");
    }
    public int requestVehicleId() {
        System.out.println("Digite o id do veículo: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }
    public int requestCityId() {
        System.out.println("Digite o id da cidade: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }
}
