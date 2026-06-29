package model;

public abstract class Veiculo {

    protected  int id;
    protected String modelo;
    protected double autonomiaMaxima;
    protected double cargaBateriaAtual;
    protected double consumoKwhPorKm;
    protected int tempoRecargaCompleta;

    public Veiculo(int id, String modelo, double autonomiaMaxima, double cargaBateriaAtual, double consumoKwhPorKm,
                   int tempoRecargaCompleta){

        this.id = id;
        this.modelo = modelo;
        this.autonomiaMaxima = autonomiaMaxima;
        this.cargaBateriaAtual = cargaBateriaAtual;
        this.consumoKwhPorKm = consumoKwhPorKm;
        this.tempoRecargaCompleta = tempoRecargaCompleta;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getAutonomiaMaxima() {
        return autonomiaMaxima;
    }
    public void setAutonomiaMaxima(double autonomiaMaxima) {
        this.autonomiaMaxima = autonomiaMaxima;
    }

    public double getCargaBateriaAtual() {
        return cargaBateriaAtual;
    }
    public void setCargaBateriaAtual(double cargaBateriaAtual) {
        this.cargaBateriaAtual = cargaBateriaAtual;
    }

    public double getConsumoKwhPorKm() {
        return consumoKwhPorKm;
    }
    public void setConsumoKwhPorKm(double consumoKwhPorKm) {
        this.consumoKwhPorKm = consumoKwhPorKm;
    }

    public int getTempoRecargaCompleta() {
        return tempoRecargaCompleta;
    }
    public void setTempoRecargaCompleta(int tempoRecargaCompleta) {
        this.tempoRecargaCompleta = tempoRecargaCompleta;
    }
}
