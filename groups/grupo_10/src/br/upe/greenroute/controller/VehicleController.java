package br.upe.greenroute.controller;

import br.upe.greenroute.model.ElectricVehicleModel;
import br.upe.greenroute.model.HybridVehicleModel;
import br.upe.greenroute.model.VehicleModel;
import br.upe.greenroute.repository.VehicleRepository;
import br.upe.greenroute.view.VehicleView;

public class VehicleController extends BaseController{
    private final VehicleRepository repository;
    private final VehicleView view;

    public VehicleController(VehicleRepository repository, VehicleView view) {
        this.repository = repository;
        this.view = view;
    }

    public void addVehicle() {
        String error;
        String type = view.requestVehicleType();
        String[] dates = view.requestDataForCreate();
        String model = dates[0];
        String maximumAutonomyStr = dates[1];
        String currentBatteryChargeStr = dates[2];
        String consumeKwhPerKmStr = dates[3];
        String fullRechargeTimeStr = dates[4];
        error = isAnyBlank(model, maximumAutonomyStr, currentBatteryChargeStr, consumeKwhPerKmStr, fullRechargeTimeStr);
        if (error == null) {
            error = isDouble(maximumAutonomyStr,"A autonomia máxima deve ser um valor valido!");
        }
        if (error == null) {
            error = isDouble(currentBatteryChargeStr, "A carga atual da bateria deve ser um valor valido!");
        }
        if (error == null) {
            error = isDouble(consumeKwhPerKmStr, "O consumo de energia deve ser um valor valido!");
        }
        if (error == null) {
            error = isInt(fullRechargeTimeStr, "O tempo de recarga completa deve ser um valor valido!");
        }
        if (error != null) {
            view.displayError(error);
            view.Enter();
            return;
        }
        switch (type) {
            case "1" -> addElectricVehicle(model, maximumAutonomyStr, currentBatteryChargeStr, consumeKwhPerKmStr, fullRechargeTimeStr);
            case "2" -> addHybridVehicle(model, maximumAutonomyStr, currentBatteryChargeStr, consumeKwhPerKmStr, fullRechargeTimeStr);
        }
    }
    private void addElectricVehicle(String model, String maximumAutonomyStr, String currentBatteryChargeStr, String consumeKwhPerKmStr, String fullRechargeTimeStr) {
        String error;
        String[] dates = view.requestDataForCreateElectricVehicle();
        String connectorType = dates[0];
        String fastChargingStr = dates[1];

        error = isAnyBlank(connectorType, fastChargingStr);
        if (error == null) {
            error = isInt(fastChargingStr, "O tempo de recarga rápida deve se um valor inteiro!");
            }
        if (error != null) {
            view.displayError(error);
            view.Enter();
            return;
        }
        double maximumAutonomy = Double.parseDouble(maximumAutonomyStr);
        double currentBatteryCharge = Double.parseDouble(currentBatteryChargeStr);
        double consumeKwhPerKm = Double.parseDouble(consumeKwhPerKmStr);
        int fullRechargeTime = Integer.parseInt(fullRechargeTimeStr);
        int fastCharging = Integer.parseInt(fastChargingStr);

        if (maximumAutonomy <= 0) {
            error = ("A autonomia máxima deve ser um valor positivo!");
        }else if (currentBatteryCharge < 0 || currentBatteryCharge > 100) {
            error = ("A bateria só pode ir de 0% à 100%");
        }
        else if (consumeKwhPerKm <= 0) {
            error = ("O consumo de kWh por Km deve ser um valor positivo!");
        }
        else if (fullRechargeTime <= 0) {
            error = ("o tempo de recarga da bateria deve ser um valor positivo!");
        }
        else if (fastCharging < 0) {
            error = ("O tempo de recarga rápida da bateria deve ser um valor positivo!");
        }
        if (error != null) {
            view.displayError(error);
            view.Enter();
            return;
        }
        VehicleModel vehicle = new ElectricVehicleModel(model, maximumAutonomy, currentBatteryCharge, consumeKwhPerKm, fullRechargeTime, connectorType, fastCharging);
        repository.add(vehicle);
        view.displayMessage("Veiculo cadastrado no sistema!");
        view.displayVehicleData(vehicle);
        view.Enter();
    }
    private void addHybridVehicle (String model, String maximumAutonomyStr, String currentBatteryChargeStr, String consumeKwhPerKmStr, String fullRechargeTimeStr) {
        String error;
        String[] dates = view.requestDataForCreateHybridVehicle();
        String fuelTankCapacityStr = dates[0];
        String fuelConsumptionStr = dates[1];
        String fuelType = dates[2];

        error = isAnyBlank(fuelTankCapacityStr, fuelConsumptionStr, fuelType);
        if (error == null) {
            error = isDouble(fuelTankCapacityStr, "A capacidade do tanque de combustivel deve ser um valor valido!");
        }
        if (error == null) {
            error = isDouble(fuelConsumptionStr, "O consumo de combustivel do motor deve ser um valor valido!");
        }
        if (error != null) {
            view.displayError(error);
            view.Enter();
            return;
        }
        double maximumAutonomy = Double.parseDouble(maximumAutonomyStr);
        double currentBatteryCharge = Double.parseDouble(currentBatteryChargeStr);
        double consumeKwhPerKm = Double.parseDouble(consumeKwhPerKmStr);
        int fullRechargeTime = Integer.parseInt(fullRechargeTimeStr);
        double fuelTankCapacity = Double.parseDouble(fuelTankCapacityStr);
        double fuelConsumption = Double.parseDouble(fuelConsumptionStr);

        if (maximumAutonomy <= 0) {
            error = ("A autonomia máxima deve ser um valor positivo!");
        }else if (currentBatteryCharge < 0 || currentBatteryCharge > 100) {
            error = ("A bateria só pode ir de 0% à 100%");
        }else if (consumeKwhPerKm <= 0) {
            error = ("O consumo de kWh por Km deve ser um valor positivo!");
        }else if (fullRechargeTime <= 0) {
            error = ("o tempo de recarga da bateria deve ser um valor positivo!");
        }else if (fuelTankCapacity <= 0) {
            error = ("A capacidade do tanque de combustivel deve ser valor positivo!");
        }else if (fuelConsumption <= 0) {
            error = ("O consumo do combustivel deve ser valor positivo!");
        }
        if (error != null) {
            view.displayError(error);
            view.Enter();
            return;
        }
        VehicleModel vehicle = new HybridVehicleModel(model, maximumAutonomy, currentBatteryCharge, consumeKwhPerKm, fullRechargeTime, fuelTankCapacity, fuelConsumption, fuelType);
        repository.add(vehicle);
        view.displayMessage("Veiculo cadastrado no sistema!");
        view.displayVehicleData(vehicle);
        view.Enter();
    }
    public String vehicleType(int id) {
        VehicleModel vehicleFound = repository.searchById(id);
        if (vehicleFound != null) {
            if (vehicleFound instanceof ElectricVehicleModel) {
                return "1";
            }else if (vehicleFound instanceof HybridVehicleModel) {
                return "2";
            }
        }
        return "veiculo não encontrado no sistema!";
    }
    public void searchVehicleById () {
        int id = view.requestId();
        VehicleModel vehicleFound = repository.searchById(id);
        String result = vehicleType(id);
        switch (result) {
            case "1" -> {
                view.displayMessage("Veiculo encontrado!");
                view.displayVehicleData((ElectricVehicleModel) vehicleFound);
            }
            case "2" -> {
                view.displayMessage("Veiculo encontrado!");
                view.displayVehicleData((HybridVehicleModel) vehicleFound);
            }
            default -> view.displayError(result);
        }
        view.Enter();
    }
    public void updateVehicle() {
        int id = view.requestId();
        String type = vehicleType(id);
        String[] dates = view.requestDataForUpdate();
        String model = dates[0];
        String maximumAutonomyStr = dates[1];
        String currentBatteryChargeStr = dates[2];
        String consumeKwhPerKmStr = dates[3];
        String fullRechargeTimeStr = dates[4];
        VehicleModel vehicleFound = repository.searchById(id);
        if (vehicleFound != null) {
            if (model != null && !model.isBlank()) {
                vehicleFound.setModel(model);
            }
            if (maximumAutonomyStr != null && !maximumAutonomyStr.isBlank()) {
                if (validDouble(maximumAutonomyStr)) {
                    double maximumAutonomy = Double.parseDouble(maximumAutonomyStr);
                    if (maximumAutonomy > 0) {
                        vehicleFound.setMaximumAutonomy(maximumAutonomy);
                    }else {
                        view.displayError("A autonomia máxima deve ser um valor positivo! (Será mantido o valor anterior)");
                    }
                }else {
                    view.displayError("A autonomia máxima deve ser um valor valido! (Será mantido o valor anterior)");
                }
            }
            if (currentBatteryChargeStr != null && !currentBatteryChargeStr.isBlank()) {
                if (validDouble(currentBatteryChargeStr)) {
                    double currentBatteryCharge = Double.parseDouble(currentBatteryChargeStr);
                    if (currentBatteryCharge >= 0 && currentBatteryCharge <= 100) {
                        vehicleFound.setCurrentBatteryCharge(currentBatteryCharge);
                    }else {
                        view.displayError("A bateria só pode ir de 0% à 100% (Será mantido o valor anterior)");
                    }
                }else {
                    view.displayError("A carga atual da bateria deve ser um valor valido! (Será mantido o valor anterior)");
                }
            }
            if (consumeKwhPerKmStr != null && !consumeKwhPerKmStr.isBlank()) {
                if (validDouble(consumeKwhPerKmStr)) {
                    double consumeKwhPerKm = Double.parseDouble(consumeKwhPerKmStr);
                    if (consumeKwhPerKm > 0) {
                        vehicleFound.setConsumeKwhPerKm(consumeKwhPerKm);
                    }else {
                        view.displayError("O consumo de kWh por Km deve ser um valor positivo! (Será mantido o valor anterior)");
                    }
                }else {
                    view.displayError("O consumo de energia deve ser um valor valido! (Será mantido o valor anterior)");
                }
            }
            if (fullRechargeTimeStr != null && !fullRechargeTimeStr.isBlank()) {
                if (validInt(fullRechargeTimeStr)) {
                    int fullrechargeTime = Integer.parseInt(fullRechargeTimeStr);
                    if (fullrechargeTime > 0) {
                        vehicleFound.setFullRechargeTime(fullrechargeTime);
                    }else {
                        view.displayError("O tempo de recarga da bateria deve ser um valor positivo! (Será mantido o valor anterior)");
                    }
                }else {
                        view.displayError("O tempo de recarga completa deve ser um valor valido! (Será mantido o valor anterior)");
                }
            }
            switch (type) {
                case "1" -> updateElectricVehicle(vehicleFound);
                case "2" -> updateHybridVehicle(vehicleFound);
            }
        }else {
            view.displayError(type);
        }
    }
    private void updateElectricVehicle (VehicleModel vehicle) {
        ElectricVehicleModel electricVehicle = (ElectricVehicleModel) vehicle;
        String[] dates = view.requestDataForUpdateElectricVehicle();
        String connectorType = dates[0];
        String fastChargingStr = dates[1];
        if (connectorType != null && !connectorType.isBlank()) {
            electricVehicle.setConnectorType(connectorType);
        }
        if (fastChargingStr != null && !fastChargingStr.isBlank()) {
            if (validInt(fastChargingStr)) {
                int fastCharging = Integer.parseInt(fastChargingStr);
                if (fastCharging > 0 && fastCharging < electricVehicle.getFullRechargeTime()) {
                    electricVehicle.setFastCharging(fastCharging);
                } else {
                    view.displayError("O tempo de recarga rápida da bateria deve ser um valor positivo! (Será mantido o valor anterior)");
                }
            } else {
                view.displayError("O tempo de recarga rápida deve se um valor válido! (Será mantido o valor anterior)");
            }
        }
        boolean result = repository.update(electricVehicle);
        if (result) {
            view.displayMessage("Veiculo atualizado com sucesso!");
            view.displayVehicleData(electricVehicle);
        } else {
            view.displayError("veiculo não pode ser atualizado!");
        }
        view.Enter();
    }
    private void updateHybridVehicle (VehicleModel vehicle) {
        HybridVehicleModel hybridVehicle = (HybridVehicleModel) vehicle;
        String[] dates = view.requestDataForUpdateHybridVehicle();
        String fuelTankCapacityStr = dates[0];
        String fuelConsumptionStr = dates[1];
        String fuelType = dates[2];
        if (fuelType != null && !fuelType.isBlank()) {
            hybridVehicle.setFuelType(fuelType);
        }
        if (fuelTankCapacityStr != null && !fuelTankCapacityStr.isBlank()) {
            if (validDouble(fuelTankCapacityStr)) {
                double fuelTankCapacity = Double.parseDouble(fuelTankCapacityStr);
                if (fuelTankCapacity > 0) {
                    hybridVehicle.setFuelTankCapacity(fuelTankCapacity);
                } else {
                    view.displayError("A capacidade do tanque de combustivel deve ser valor positivo! (Será mantido o valor anterior)");
                }
            } else {
                view.displayError("A capacidade do tanque de combustivel deve ser um valor válido! (Será mantido o valor anterior)");
            }
        }
        if (fuelConsumptionStr != null && !fuelConsumptionStr.isBlank()) {
            if (!validDouble(fuelConsumptionStr)) {
                double fuelConsumption = Double.parseDouble(fuelConsumptionStr);
                if (fuelConsumption > 0) {
                    hybridVehicle.setFuelConsumption(fuelConsumption);
                } else {
                    view.displayError("O consumo do combustivel deve ser valor positivo! (Será mantido o valor anterior)");
                }
            } else {
                view.displayError("O consumo de combustivel do motor deve ser um valor valido! (Será mantido o valor anterior)");
            }
        }
        boolean result = repository.update(hybridVehicle);
        if (result) {
            view.displayMessage("Veiculo atualizado com sucesso!");
            view.displayVehicleData(hybridVehicle);
        } else {
            view.displayError("veiculo não pode ser atualizado!");
        }
    }
    public void deleteVehicleById() {
        int id = view.requestId();
        VehicleModel vehicleFound = repository.searchById(id);
        if (vehicleFound == null) {
            view.displayError("Veiculo não encontrado no sistema!");
            view.Enter();
            return;
        }
        view.displayMessage("Você está prestes a excluir o segguinte veiculo: ");
        String type = vehicleType(id);
        switch (type) {
            case "1" -> view.displayVehicleData((ElectricVehicleModel) vehicleFound);
            case "2" -> view.displayVehicleData((HybridVehicleModel) vehicleFound);
        }
        boolean confirmation = view.askForDeleteConfirmation();
        if (confirmation) {
            boolean result = repository.deleteById(id);
            if (result) {
                view.displayMessage("Veiculo deletado com sucesso!");
            }
        }else {
            view.displayMessage("Ação cancelada, o veiculo não foi removido!");
        }
        view.Enter();
    }
    public void listVehicles() {
        VehicleModel[] vehicles = repository.getVehicles();
        if (vehicles != null && vehicles.length > 0) {
            for (VehicleModel vehicle : vehicles) {
                if (vehicle != null) {
                    String type = vehicleType(vehicle.getId());
                    switch (type) {
                        case "1" -> view.displayVehicleData((ElectricVehicleModel) vehicle);
                        case "2" -> view.displayVehicleData((HybridVehicleModel) vehicle);
                    }
                }
            }
        } else {
            view.displayError("Nenhum veiculo cadastrado no sistema!");
        }
        view.Enter();
    }
}