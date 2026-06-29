package Model;

public class VeiculoEletrico extends Veiculo {
    private int tempoRecargaRapida; // (em minutos em carregadores de alta potência)
    
    public VeiculoEletrico(int id, String modelo, String tipoConector, double cargaBateriaAtual, double autonomiaMaxima, double consumoKwhPorKm, int tempoRecargaCompleta, int tempoRecargaRapida, double capacidadeTotalBateria) {
        super(id, tempoRecargaCompleta, modelo, autonomiaMaxima, cargaBateriaAtual, capacidadeTotalBateria);
        this.tempoRecargaRapida = tempoRecargaRapida;
        super.setTipoConector(tipoConector); 
    }
    @Override
    public String toString(){
        return "ID: " + getId() + "; Modelo: " + getModelo() + "; Tipo de Conector: " + getTipoConector() + "; Carga: " + getCargaBateriaAtual() + "%; Autonomia: " + getAutonomiaMaxima() + " km; Tempo de Recarga Rápida: " + tempoRecargaRapida + " min";    
    }
    // Getters e Setters
    public int getTempoRecargaRapida() { return tempoRecargaRapida; }
    public void setTempoRecargaRapida(int tempoRecargaRapida) { this.tempoRecargaRapida = tempoRecargaRapida; }
}