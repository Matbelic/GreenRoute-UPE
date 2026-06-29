package model;
public abstract class Veiculo {
    private int id;
    private String modelo;
    private double autonomiaMaxima;
    private double cargaBateriaAtual;
    private double consumoKwhPorKm;
    private int tempoRecargaCompleta;
    public Veiculo(int id, String modelo, double autonomiaMaxima,
                   double cargaBateriaAtual, double consumoKwhPorKm,
                   int tempoRecargaCompleta) {
        this.id = id;
        this.modelo = modelo;
        this.autonomiaMaxima = autonomiaMaxima;
        this.cargaBateriaAtual = cargaBateriaAtual;
        this.consumoKwhPorKm = consumoKwhPorKm;
        this.tempoRecargaCompleta = tempoRecargaCompleta;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public double getAutonomiaMaxima() { return autonomiaMaxima; }
    public void setAutonomiaMaxima(double autonomiaMaxima) { this.autonomiaMaxima = autonomiaMaxima; }

    public double getCargaBateriaAtual() { return cargaBateriaAtual; }
    public void setCargaBateriaAtual(double cargaBateriaAtual) {
        if (cargaBateriaAtual >= 0.0 && cargaBateriaAtual <= 100.0) {
            this.cargaBateriaAtual = cargaBateriaAtual;
        } else {
            System.out.println("Erro: Carga deve ser entre 0 e 100%.");
        }
    }

    public double getConsumoKwhPorKm() { return consumoKwhPorKm; }
    public void setConsumoKwhPorKm(double consumoKwhPorKm) { this.consumoKwhPorKm = consumoKwhPorKm; }
    public int getTempoRecargaCompleta() { return tempoRecargaCompleta; }
    public void setTempoRecargaCompleta(int tempoRecargaCompleta) { this.tempoRecargaCompleta = tempoRecargaCompleta; }
    public double getAutonomiaAtual() {
        return autonomiaMaxima * (cargaBateriaAtual / 100.0);
    }
    public abstract String getTipo();
    @Override
    public String toString() {
        return String.format(
                "[ID: %d] %s (%s) | Autonomia Máx: %.1f km | Carga Atual: %.1f%% | Autonomia Atual: %.1f km",
                id, modelo, getTipo(), autonomiaMaxima, cargaBateriaAtual, getAutonomiaAtual()
        );
    }
}
