package com.greenroute.model;

public class VeiculoEletrico extends Veiculo {
    private String tipoConector;
    private int tempoRecargaRapida;

    public VeiculoEletrico(int id, String modelo, double autonomiaMaxima, double cargaBateriaAtual, double consumoKwhPorKm, int tempoRecargaCompleta, String tipoConector, int tempoRecargaRapida) {

        super(id, modelo, autonomiaMaxima, cargaBateriaAtual, consumoKwhPorKm, tempoRecargaCompleta);

        this.tipoConector = tipoConector;
        this.tempoRecargaRapida = tempoRecargaRapida;
    }

    // MÉTODOS GET PARA CADA ATRIBUTO

    public String getTipoConector() {
        return tipoConector;
    }

    public int getTempoRecargaRapida() {
        return tempoRecargaRapida;
    }

    // MÉTODOS SET PARA CADA ATRIBUTO

    public void setTipoConector(String tipoConector) {
        this.tipoConector = tipoConector;
    }

    public void setTempoRecargaRapida(int tempoRecargaRapida) {
        this.tempoRecargaRapida = tempoRecargaRapida;
    }

    @Override
    public double calcularAutonomia() {
        return getAutonomiaMaxima() * (getCargaBateriaAtual() / 100.0);
    }
}