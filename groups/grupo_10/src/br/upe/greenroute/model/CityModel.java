package br.upe.greenroute.model;

public class CityModel {
    private int id;
    private String name;
    private String state;
    private double capitalDistance;

    public CityModel(int id, String name, String state, double capitalDistance) {
        this(name, state, capitalDistance);
        this.id = id;
    }
    public CityModel(String name, String state, double capitalDistance) {
        this.name = name;
        this.state = state;
        setCapitalDistance(capitalDistance);
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;}

    public String getState() {return state;}

    public void setState(String state) {this.state = state;}

    public double getCapitalDistance() {return capitalDistance;}

    public void setCapitalDistance(double capitalDistance) {
        if (capitalDistance >= 0) {
            this.capitalDistance = capitalDistance;
        }
    }
}