package Model;

public class Veiculo { // Removido o 'abstract'
    private int id;
    private String modelo;
    private String tipoConector;
    private double capacidadeTotalBateria;
    private double cargaBateriaAtual;
    private double autonomiaMaxima;
    private double consumoKwhPorKm;
    private int tempoRecargaCompleta;

    public Veiculo(int id, int tempoRecargaCompleta, String modelo, double autonomiaMaxima, double cargaBateriaAtual, double capacidadeTotalBateria) {
        this.id = id;
        this.tempoRecargaCompleta = tempoRecargaCompleta;
        this.modelo = modelo;
        this.autonomiaMaxima = autonomiaMaxima;
        this.cargaBateriaAtual = cargaBateriaAtual;
        this.capacidadeTotalBateria = capacidadeTotalBateria;
        
        // Agora o cálculo funciona perfeitamente porque a capacidade não é mais zero
        this.consumoKwhPorKm = (this.capacidadeTotalBateria > 0) ? (this.autonomiaMaxima / this.capacidadeTotalBateria) : 0;
    }

    // Getters 
    public int getId() { return id; }
    public String getModelo() { return modelo; }
    public String getTipoConector() { return tipoConector; }
    public double getCargaBateriaAtual() { return cargaBateriaAtual; }
    public double getAutonomiaMaxima() { return autonomiaMaxima; }
    public double getCapacidadeTotalBateria() { return capacidadeTotalBateria; }
    public double getConsumoKwhPorKm() { return consumoKwhPorKm; } // Adicionado para o Controller não quebrar
    public int getTempoRecargaCompleta() { return tempoRecargaCompleta; }
    
    // Setters 
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setTipoConector(String tipoConector) { this.tipoConector = tipoConector; }
    public void setCargaBateriaAtual(double cargaBateriaAtual) { this.cargaBateriaAtual = cargaBateriaAtual; }
    public void setAutonomiaMaxima(double autonomiaMaxima) { this.autonomiaMaxima = autonomiaMaxima; }
    public void setConsumoKwhPorKm(double consumoKwhPorKm) { this.consumoKwhPorKm = consumoKwhPorKm; }
    public void setTempoRecargaCompleta(int tempoRecargaCompleta) { this.tempoRecargaCompleta = tempoRecargaCompleta; }
    public void setCapacidadeTotalBateria(double capacidadeTotalBateria) { this.capacidadeTotalBateria = capacidadeTotalBateria; }
}