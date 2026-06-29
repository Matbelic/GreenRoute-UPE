package br.upe.greenroute.view;
import br.upe.greenroute.model.ChargingStationModel;

import java.util.Scanner;
public class ChargingStationView extends BaseView{
    private final Scanner scanner;
    public ChargingStationView (Scanner scanner) {
        super(scanner);
        this.scanner=scanner;
    }
    public void displayChargingStation (ChargingStationModel chargingStation) {
        String conectores = String.join(" | ",chargingStation.getAvailableConnectorsType());
        System.out.println("\n=== Dados do eletroposto ===");
        System.out.println("\nID: "+chargingStation.getId());
        System.out.println("Nome: "+chargingStation.getName());
        System.out.println("Localização: "+chargingStation.getLocation());
        System.out.println("Conectores disponiveis: "+conectores);
        System.out.println("Potencia máxima do carregador: "+chargingStation.getChargingPowerKW());
        System.out.printf("Preço por kWh: R$%.2f%n", chargingStation.getPricePerKWh());
        System.out.printf("Vagas disponiveis: %d%n", chargingStation.getAvailableVacancies());
    }
    public String[] requestDataForCreate() {
        System.out.println("Digite o nome do eletroposto: ");
        String name = scanner.nextLine();
        System.out.println("Digite a localização (endereço/rodovia): ");
        String location = scanner.nextLine();
        System.out.println("Digite o ID da cidade: ");
        String cityIdStr = scanner.nextLine();
        System.out.println("Digite os tipos de conectores disponiveis separados por ',': ");
        String availableConnectorsTypeStr = scanner.nextLine().replace(";.",",");
        System.out.println("Digite a potência do carregador (Kw): ");
        String chargingPowerKWStr = scanner.nextLine();
        System.out.println("Digite o preço cobrado por kWh: ");
        String pricePerKWhStr = scanner.nextLine();
        System.out.println("Digite a quantidade de vagas disponiveis: ");
        String availableVacanciesStr = scanner.nextLine();
        return new String[] {name, location, cityIdStr, availableConnectorsTypeStr, chargingPowerKWStr, pricePerKWhStr, availableVacanciesStr};
    }
    public String[] requestDataForUpdate() {
        System.out.println("Digite o novo nome do eletroposto: ");
        String name = scanner.nextLine();
        System.out.println("Digite a nova localização (endereço/rodovia): ");
        String location = scanner.nextLine();
        System.out.println("Digite os novos tipos de conectores disponiveis separados por ',': ");
        String availableConnectorsTypeStr = scanner.nextLine().replace(";.",",");
        System.out.println("Digite a nova potência do carregador (Kw): ");
        String chargingPowerKWStr = scanner.nextLine();
        System.out.println("Digite o novo preço cobrado por kWh: ");
        String pricePerKWhStr = scanner.nextLine();
        System.out.println("Digite a nova quantidade de vagas disponiveis: ");
        String availableVacanciesStr = scanner.nextLine();
        return new String[] {name, location, availableConnectorsTypeStr, chargingPowerKWStr, pricePerKWhStr, availableVacanciesStr};
    }
    public int requestId() {
        System.out.println("Digite o id do eletroposto: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }
}
