package br.upe.greenroute.model;

public class ChargingStationModel {
    private int id;
    private String name;
    private String location;
    private int cityId;
    private String[] availableConnectorsType;
    private double chargingPowerKW;
    private double pricePerKWh;
    private int availableVacancies;

    public ChargingStationModel(int id, String name, String location, int cityId, String[] availableConnectorsType, double chargingPowerKW, double pricePerKWh, int availableVacancies) {
        this(name, location, cityId, availableConnectorsType, chargingPowerKW, pricePerKWh, availableVacancies);
        this.id = id;
    }
    public ChargingStationModel(String name, String location, int cityId, String[] availableConnectorsType, double chargingPowerKW, double pricePerKWh, int availableVacancies) {
        this.name = name;
        this.location = location;
        this.cityId = cityId;
        this.availableConnectorsType = availableConnectorsType;
        this.chargingPowerKW = chargingPowerKW;
        this.pricePerKWh = pricePerKWh;
        this.availableVacancies = availableVacancies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCityId() {
        return cityId;
    }

    public String[] getAvailableConnectorsType() {
        return availableConnectorsType;
    }

    public void setAvailableConnectorsType(String[] availableConnectorsType) {
        this.availableConnectorsType = availableConnectorsType;
    }

    public double getChargingPowerKW() {
        return chargingPowerKW;
    }

    public void setChargingPowerKW(double chargingPowerKW) {
        if (chargingPowerKW > 0) {
            this.chargingPowerKW = chargingPowerKW;
        }
    }

    public double getPricePerKWh() {
        return pricePerKWh;
    }

    public void setPricePerKWh(double pricePerKWh) {
        if (pricePerKWh >= 0) {
            this.pricePerKWh = pricePerKWh;
        }
    }

    public int getAvailableVacancies() {
        return availableVacancies;
    }

    public void setAvailableVacancies(int availableVacancies) {
        if (availableVacancies >= 0) {
            this.availableVacancies = availableVacancies;
        }
    }
}
