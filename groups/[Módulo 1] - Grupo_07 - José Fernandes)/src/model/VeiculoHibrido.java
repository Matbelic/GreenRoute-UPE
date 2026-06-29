package model;

public class VeiculoHibrido extends Veiculo {

    private double capacidadeTanqueCombustivel;
    private double consumoCombustivel;
    private String tipoCombustivel;

    public VeiculoHibrido(int id, String modelo, double autonomiaMaxima,
                          double cargaBateriaAtual, double consumoKwhPorKm,
                          int tempoRecargaCompleta, double capacidadeTanqueCombustivel,
                          double consumoCombustivel, String tipoCombustivel) {
        super(id, modelo, autonomiaMaxima, cargaBateriaAtual,
                consumoKwhPorKm, tempoRecargaCompleta);
        this.capacidadeTanqueCombustivel = capacidadeTanqueCombustivel;
        this.consumoCombustivel = consumoCombustivel;
        this.tipoCombustivel = tipoCombustivel;
    }

    public double getCapacidadeTanqueCombustivel() { return capacidadeTanqueCombustivel; }
    public void setCapacidadeTanqueCombustivel(double cap) { this.capacidadeTanqueCombustivel = cap; }
    public double getConsumoCombustivel() { return consumoCombustivel; }
    public void setConsumoCombustivel(double consumo) { this.consumoCombustivel = consumo; }
    public String getTipoCombustivel() { return tipoCombustivel; }
    public void setTipoCombustivel(String tipo) { this.tipoCombustivel = tipo; }
    public double getAutonomiaMotorCombustao() {
        return capacidadeTanqueCombustivel * consumoCombustivel;
    }

    @Override
    public String getTipo() {
        return "Híbrido";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
                " | Tanque: %.1f L (%s) | Autonomia Combustão: %.1f km",
                capacidadeTanqueCombustivel, tipoCombustivel, getAutonomiaMotorCombustao()
        );
    }
}
