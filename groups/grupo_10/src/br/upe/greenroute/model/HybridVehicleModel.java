package br.upe.greenroute.model;

public class HybridVehicleModel extends VehicleModel {
    private double fuelTankCapacity;
    private double fuelConsumption;
    private String fuelType;

    public HybridVehicleModel(int id, String model, double maximumAutonomy, double currentBatteryCharge, double consumptionKWhPerKm, int fullRechargeTime, double fuelTankCapacity, double fuelConsumption, String fuelType) {
        this(model, maximumAutonomy, currentBatteryCharge, consumptionKWhPerKm, fullRechargeTime,fuelTankCapacity, fuelConsumption, fuelType);
        this.id = id;
    }
    public HybridVehicleModel(String model, double maximumAutonomy, double currentBatteryCharge, double consumptionKWhPerKm, int fullRechargeTime, double fuelTankCapacity, double fuelConsumption, String fuelType) {
        super ( model, maximumAutonomy, currentBatteryCharge, consumptionKWhPerKm, fullRechargeTime);
        setFuelTankCapacity(fuelTankCapacity);
        setFuelConsumption(fuelConsumption);
        this.fuelType = fuelType;
    }

    public double getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public void setFuelTankCapacity(double fuelTankCapacity) {
        if (fuelTankCapacity > 0) {
            this.fuelTankCapacity = fuelTankCapacity;
        }
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        if (fuelConsumption > 0) {
            this.fuelConsumption = fuelConsumption;
        }
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}