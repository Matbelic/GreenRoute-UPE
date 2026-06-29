package com.greenroute.model;

public class VeiculoHibrido extends Veiculo {
    private double capacidadeTanqueCombustivel;
    private double consumoCombustivel;
    private String tipoCombustivel;
    private double combustivelAtual;

    public VeiculoHibrido(int id, String modelo, double autonomiaMaxima, double cargaBateriaAtual, double consumoKwhPorKm, int tempoRecargaCompleta, double capacidadeTanqueCombustivel, double consumoCombustivel, String tipoCombustivel, double combustivelAtual) {

        super(id, modelo, autonomiaMaxima, cargaBateriaAtual, consumoKwhPorKm, tempoRecargaCompleta);

        this.capacidadeTanqueCombustivel = capacidadeTanqueCombustivel;
        this.consumoCombustivel = consumoCombustivel;
        this.tipoCombustivel = tipoCombustivel;
        this.combustivelAtual = combustivelAtual;
    }

    // MÉTODOS GET PARA CADA ATRIBUTO

    public double getCapacidadeTanqueCombustivel() {
        return capacidadeTanqueCombustivel;
    }

    public double getConsumoCombustivel() {
        return consumoCombustivel;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public double getCombustivelAtual() {
        return combustivelAtual;
    }

    // MÉTODOS SET PARA CADA ATRIBUTO

    public void setCapacidadeTanqueCombustivel(double capacidadeTanqueCombustivel) {
        this.capacidadeTanqueCombustivel = capacidadeTanqueCombustivel;
    }

    public void setConsumoCombustivel(double consumoCombustivel) {
        this.consumoCombustivel = consumoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public void setCombustivelAtual(double combustivelAtual) {
        this.combustivelAtual = combustivelAtual;
    }

    @Override
    public double calcularAutonomia() {

        double autonomiaEletrica =
                getAutonomiaMaxima() * (getCargaBateriaAtual() / 100.0);

        double autonomiaCombustivel =
                getCombustivelAtual() * getConsumoCombustivel();

        return autonomiaEletrica + autonomiaCombustivel;
    }
}
