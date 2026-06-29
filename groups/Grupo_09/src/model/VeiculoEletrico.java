package model;

public class VeiculoEletrico extends Veiculo {
    private String tipoConector;
    private int tempoRecargaRapida;

    public VeiculoEletrico(int id, String modelo, double autonomiaMaxima, double cargaBateriaAtual, double consumoKwhPorKm, int tempoRecargaCompleta, String tipoConector, int tempoRecargaRapida){
        super(id, modelo, autonomiaMaxima, cargaBateriaAtual, consumoKwhPorKm, tempoRecargaCompleta);
        this.tipoConector = tipoConector;
        this.tempoRecargaRapida = tempoRecargaRapida;
    }

    // Getters
    public String getTipoConector() {return tipoConector;}
    public int getTempoRecargaRapida() {return tempoRecargaRapida;}

    // Setters
    public void setTipoConector(String tipoConector) {this.tipoConector = tipoConector;}
    public void setTempoRecargaRapida(int tempoRecargaRapida) {
        if(tempoRecargaRapida >= 0){
            this.tempoRecargaRapida = tempoRecargaRapida;
        } else {
            throw new IllegalArgumentException("Erro! O tempo de recarga rápida não pode ser menor que 0s.");
        }
    }

}
