package Model;

public class VeiculoHibrido extends Veiculo {
    private String tipoCombustivel;
    private double consumoCombustivel;
    private double capacidadeTanqueCombustivel;
    private double autonomiaMotorCombustao;

    public VeiculoHibrido(int id, String modelo, String tipoConector, double cargaBateriaAtual, double autonomiaEletrico, double consumoKwhPorKm, int tempoRecargaCompleta, String tipoCombustivel, double consumoCombustivel, double capacidadeTanqueCombustivel, double autonomiaMotorCombustao, double capacidadeTotalBateria) {
        super(id, tempoRecargaCompleta, modelo, autonomiaEletrico, cargaBateriaAtual, capacidadeTotalBateria);
        this.tipoCombustivel = tipoCombustivel;
        this.consumoCombustivel = consumoCombustivel;
        this.capacidadeTanqueCombustivel = capacidadeTanqueCombustivel;
        this.autonomiaMotorCombustao = autonomiaMotorCombustao;
        super.setTipoConector(tipoConector);
    }
    public double calcularConsumoMedio() {
        if (this.consumoCombustivel == 0) return 0;
        return 1 / ((super.getAutonomiaMaxima() / 8.9) + (1 / this.consumoCombustivel)); 
    }
    public double calcularAutonomiaTotal() {
        return super.getAutonomiaMaxima() + this.autonomiaMotorCombustao;
    }
    // Setters 
    public void setTipoCombustivel(String tipoCombustivel) { this.tipoCombustivel = tipoCombustivel; }
    public void setConsumoCombustivel(double consumoCombustivel) { this.consumoCombustivel = consumoCombustivel; }
    public void setCapacidadeTanqueCombustivel(double capacidadeTanqueCombustivel) { this.capacidadeTanqueCombustivel = capacidadeTanqueCombustivel; }
    public void setAutonomiaMotorCombustao(double autonomiaMotorCombustao) { this.autonomiaMotorCombustao = autonomiaMotorCombustao; }

    @Override
    public String toString() {
        return "ID: " + getId() + "; Modelo: " + getModelo() + "; Tipo do Combustível: " + tipoCombustivel + "; Capacidade do Tanque: " + capacidadeTanqueCombustivel + "L; Carga da Bateria: " + getCargaBateriaAtual() + "%; Autonomia Máxima: " + calcularAutonomiaTotal() + " km; Consumo Médio: " + calcularConsumoMedio();
    }
}