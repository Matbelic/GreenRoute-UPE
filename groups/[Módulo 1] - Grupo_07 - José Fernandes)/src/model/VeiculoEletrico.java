package model;

public class VeiculoEletrico extends Veiculo {
    private String tipoConector;
    private int tempoRecargaRapida;
    public VeiculoEletrico(int id, String modelo, double autonomiaMaxima,
                           double cargaBateriaAtual, double consumoKwhPorKm,
                           int tempoRecargaCompleta, String tipoConector,
                           int tempoRecargaRapida) {
        super(id, modelo, autonomiaMaxima, cargaBateriaAtual,
                consumoKwhPorKm, tempoRecargaCompleta);
        this.tipoConector = tipoConector;
        this.tempoRecargaRapida = tempoRecargaRapida;
    }

    public String getTipoConector() { return tipoConector; }
    public void setTipoConector(String tipoConector) { this.tipoConector = tipoConector; }
    public int getTempoRecargaRapida() { return tempoRecargaRapida; }
    public void setTempoRecargaRapida(int tempoRecargaRapida) { this.tempoRecargaRapida = tempoRecargaRapida; }
    @Override
    public String getTipo() {
        return "Elétrico";
    }
    public boolean isCompativel(String conectoresDoPostoString) {
        return conectoresDoPostoString.toLowerCase().contains(tipoConector.toLowerCase());
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
                " | Conector: %s | Recarga Rápida: %d min",
                tipoConector, tempoRecargaRapida
        );
    }
}
