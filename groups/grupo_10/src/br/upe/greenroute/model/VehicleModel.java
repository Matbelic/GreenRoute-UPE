package br.upe.greenroute.model;

public abstract class VehicleModel {
    protected int id;
    protected String model;
    protected double maximumAutonomy;
    protected double currentBatteryCharge;
    protected double consumeKwhPerKm;
    protected int fullRechargeTime;

    public VehicleModel(int id, String model, double maximumAutonomy, double currentBatteryCharge, double consumeKwhPerKm, int fullRechargeTime) {
        this(model, maximumAutonomy, currentBatteryCharge, consumeKwhPerKm, fullRechargeTime);
        this.id = id;
    }
    public VehicleModel (String model, double maximumAutonomy, double currentBatteryCharge, double consumeKwhPerKm, int fullRechargeTime) {
        this.model = model;
        setMaximumAutonomy(maximumAutonomy);
        setCurrentBatteryCharge(currentBatteryCharge);
        setConsumeKwhPerKm(consumeKwhPerKm);
        setFullRechargeTime(fullRechargeTime);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getMaximumAutonomy() {
        return maximumAutonomy;
    }

    public void setMaximumAutonomy(double maximumAutonomy) {
        if (maximumAutonomy > 0) {
            this.maximumAutonomy = maximumAutonomy;
        }
    }

    public double getCurrentBatteryCharge() { return currentBatteryCharge; }

    public void setCurrentBatteryCharge(double currentBatteryCharge) {
        if (currentBatteryCharge >= 0 && currentBatteryCharge <= 100) {
            this.currentBatteryCharge = currentBatteryCharge;
        }
    }

    public double getConsumeKwhPerKm() {
        return consumeKwhPerKm;
    }

    public void setConsumeKwhPerKm(double consumeKwhPerKm) {
        if (consumeKwhPerKm > 0) {
            this.consumeKwhPerKm = consumeKwhPerKm;
        }
    }

    public int getFullRechargeTime() {
        return fullRechargeTime;
    }

    public void setFullRechargeTime(int fullRechargeTime) {
        if (fullRechargeTime > 0) {
            this.fullRechargeTime = fullRechargeTime;
        }
    }

    public double calculateCurrentAutonomy() {
        return maximumAutonomy * (currentBatteryCharge / 100);
    }
}
