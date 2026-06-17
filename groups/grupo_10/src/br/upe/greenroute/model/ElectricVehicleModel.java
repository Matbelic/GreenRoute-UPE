package br.upe.greenroute.model;

public class ElectricVehicleModel extends VehicleModel {
    private String connectorType;
    private int fastCharging;

    public ElectricVehicleModel(int id, String model, double maximumAutonomy, double currentBatteryCharge, double consumptionKWhPerKm, int fullRechargeTime, String connectorType, int fastCharging) {
        this(model, maximumAutonomy, currentBatteryCharge, consumptionKWhPerKm, fullRechargeTime, connectorType, fastCharging);
        this.id = id;

    }
    public ElectricVehicleModel (String model, double maximumAutonomy, double currentBatteryCharge, double consumptionKWhPerKm, int fullRechargeTime, String connectorType, int fastCharging) {
        super (model, maximumAutonomy, currentBatteryCharge, consumptionKWhPerKm, fullRechargeTime);
        this.connectorType = connectorType;
        setFastCharging(fastCharging);
    }

    public String getConnectorType() {
        return connectorType;
    }

    public void setConnectorType(String connectorType) {
        this.connectorType = connectorType;
    }

    public int getFastCharging() {
        return fastCharging;
    }

    public void setFastCharging(int fastCharging) {
        if (fastCharging > 0 && fastCharging < fullRechargeTime) {
            this.fastCharging = fastCharging;
        }
    }
}