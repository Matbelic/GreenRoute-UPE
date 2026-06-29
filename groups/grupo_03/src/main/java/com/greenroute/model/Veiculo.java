package com.greenroute.model;

public abstract class Veiculo {
    private int id;
    private String modelo;
    private double autonomiaMaxima;
    private double cargaBateriaAtual;
    private double consumoKwhPorKm;
    private int tempoRecargaCompleta;

    public Veiculo(int id, String modelo, double autonomiaMaxima, double cargaBateriaAtual, double consumoKwhPorKm, int tempoRecargaCompleta) {
        this.id = id;
        this.modelo = modelo;
        this.autonomiaMaxima = autonomiaMaxima;
        this.cargaBateriaAtual = cargaBateriaAtual;
        this.consumoKwhPorKm = consumoKwhPorKm;
        this.tempoRecargaCompleta = tempoRecargaCompleta;
    }

    // MÉTODOS GET DE CADA ATRIBUTO

    public int getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public double getAutonomiaMaxima() {
        return autonomiaMaxima;
    }

    public double getCargaBateriaAtual() {
        return cargaBateriaAtual;
    }

    public double getConsumoKwhPorKm() {
        return consumoKwhPorKm;
    }

    public int getTempoRecargaCompleta() {
        return tempoRecargaCompleta;
    }

    // MÉTODOS SET DE CADA ATRIBUTO

    public void setId(int id) {
        this.id = id;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAutonomiaMaxima(double autonomiaMaxima) {
        this.autonomiaMaxima = autonomiaMaxima;
    }

    public void setCargaBateriaAtual(double cargaBateriaAtual) {
        this.cargaBateriaAtual = cargaBateriaAtual;
    }

    public void setConsumoKwhPorKm(double consumoKwhPorKm) {
        this.consumoKwhPorKm = consumoKwhPorKm;
    }

    public void setTempoRecargaCompleta(int tempoRecargaCompleta) {
        this.tempoRecargaCompleta = tempoRecargaCompleta;
    }

    public abstract double calcularAutonomia();

    @Override
    public String toString() {
        return "ID: " + id +
            ", Modelo: " + modelo +
            ", Autonomia: " + calcularAutonomia() + " km";
    }
}