package br.upe.greenroute.view;
import java.util.Scanner;
import br.upe.greenroute.model.ElectricVehicleModel;
import br.upe.greenroute.model.HybridVehicleModel;
import br.upe.greenroute.model.VehicleModel;

public class VehicleView extends BaseView{
    private final Scanner scanner;

    public VehicleView(Scanner scanner) {
        super(scanner);
        this.scanner = scanner;
    }

    public void displayVehicleData(VehicleModel vehicle) {
        System.out.println("\n=== Dados do Veiculo ===");
        System.out.println("\nID: " + vehicle.getId());
        System.out.println("Modelo: " + vehicle.getModel());
        System.out.printf("Autonomia Máxima: %.2f Km%n", vehicle.getMaximumAutonomy());
        System.out.printf("Carga da Bateria: %.2f %% %n", vehicle.getCurrentBatteryCharge());
        System.out.printf("Consumo: %.2f kWh/Km%n", vehicle.getConsumeKwhPerKm());
        System.out.printf("Tempo de Recarga Completa: %d min%n", vehicle.getFullRechargeTime());
    }

    public void displayVehicleData(ElectricVehicleModel electricVehicle) {
        displayVehicleData((VehicleModel) electricVehicle);
        System.out.println("Tipo de Conector: " + electricVehicle.getConnectorType());
        System.out.printf("Tempo de Recarga Rápida: %d min%n", electricVehicle.getFastCharging());
    }
    public void displayVehicleData(HybridVehicleModel hybridVehicle) {
        displayVehicleData((VehicleModel) hybridVehicle);
        System.out.printf("Capacidade do tanque de combustivel: %.2f l%n", hybridVehicle.getFuelTankCapacity());
        System.out.printf("Consumo do combustivel: %.2f Km/l%n", hybridVehicle.getFuelConsumption());
        System.out.println("tipo de combustivel: "+ hybridVehicle.getFuelType());
    }
    public String requestVehicleType() {
        System.out.println("Digite o tipo de veículo: ");
        System.out.println("1-Elétrico");
        System.out.println("2-Híbrido");
        String type = scanner.nextLine();
        if (type.equals("1") || type.equals("2")) {
            return type;
        }else {
            displayError("Deve digitar 1 para eletricos ou 2 para híbridos\n");
            return requestVehicleType();
        }
    }
    public String[] requestDataForCreate() {
        System.out.println("Digite o modelo do veículo: ");
        String model = scanner.nextLine();
        System.out.println("Digite a autonomia máxima: ");
        String maximumAutonomyStr = scanner.nextLine();
        System.out.println("Digite o carga atual da bateria: ");
        String currentBatteryChargeStr = scanner.nextLine();
        System.out.println("Digite o consumo (em kWh/Km): ");
        String consumptionKWhPerKmStr = scanner.nextLine();
        System.out.println("Digite o tempo de recarga (em minutos): ");
        String fullRechargeTimeStr = scanner.nextLine();
        return new String[] {model, maximumAutonomyStr, currentBatteryChargeStr, consumptionKWhPerKmStr, fullRechargeTimeStr};
    }

    public String[] requestDataForCreateElectricVehicle() {
        System.out.println("Digite o tipo de conector: ");
        String connectorType = scanner.nextLine();
        System.out.println("Digite o tempo de recarga rápida (em minutos) em carregadores de alta potencia: ");
        String fastChargingStr = scanner.nextLine();
        return new String[] {connectorType, fastChargingStr};
    }
    public String[] requestDataForCreateHybridVehicle() {
        System.out.println("Digite a capacidade do tanque de combustivel (em litros): ");
        String fuelTankCapacityStr = scanner.nextLine();
        System.out.println("Digite o consumo de combustivel (em Km/l) do motor a combustão: ");
        String fuelConsumptionStr = scanner.nextLine();
        System.out.println("Digite o tipo de combustivel do veiculo: ");
        String fuelType = scanner.nextLine();
        return new String[] {fuelTankCapacityStr, fuelConsumptionStr, fuelType};
    }
    public String[] requestDataForUpdate() {
        System.out.println("Digite o novo modelo do veículo: ");
        String model = scanner.nextLine();
        System.out.println("Digite a nova autonomia máxima: ");
        String maximumAutonomyStr = scanner.nextLine();
        System.out.println("Digite a nova carga atual da bateria: ");
        String currentBatteryChargeStr = scanner.nextLine();
        System.out.println("Digite o novo consumo (kWh/Km): ");
        String consumptionKWhPerKmStr = scanner.nextLine();
        System.out.println("Digite o novo tempo de recarga (em minutos): ");
        String fullRechargeTimeStr = scanner.nextLine();
        return new String[] {model, maximumAutonomyStr, currentBatteryChargeStr,consumptionKWhPerKmStr, fullRechargeTimeStr};
    }
    public String[] requestDataForUpdateElectricVehicle() {
        System.out.println("Digite o novo tipo de conector: ");
        String connectorType = scanner.nextLine();
        System.out.println("Digite o novo tempo de recarga rapida (em minutos) em carregadores de alta potencia: ");
        String fastChargingStr = scanner.nextLine();
        return new String[]{connectorType, fastChargingStr};
    }
   public String[] requestDataForUpdateHybridVehicle() {
        System.out.println("Digite a nova capacidade do tanque de combustivel (em litros): ");
        String fuelTankCapacityStr = scanner.nextLine();
        System.out.println("Digite o novo consumo de combustivel (em Km/l) do motor a combustão: ");
        String fuelConsumptionStr = scanner.nextLine();
        System.out.println("Digite o novo tipo de combustivel do veiculo: ");
        String fuelType = scanner.nextLine();
        return new String[] {fuelTankCapacityStr, fuelConsumptionStr, fuelType};
    }
    public int requestId() {
        System.out.println("Digite o id do veículo: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }
}
