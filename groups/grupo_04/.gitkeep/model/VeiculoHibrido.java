package model;

public class VeiculoHibrido extends Veiculo {

    private double capacidadeTanqueCombustivel;
    private double consumoCombustivel;
    private String tipoCombustivel;

    public VeiculoHibrido(int id,
                          String modelo,
                          double autonomiaMaxima,
                          double cargaBateriaAtual,
                          double consumoKwhPorKm,
                          int tempoRecargaCompleta,
                          double capacidadeTanqueCombustivel,
                          double consumoCombustivel,
                          String tipoCombustivel) {

        super(id, modelo, autonomiaMaxima,
              cargaBateriaAtual, consumoKwhPorKm,
              tempoRecargaCompleta);

        this.capacidadeTanqueCombustivel =
                capacidadeTanqueCombustivel;

        this.consumoCombustivel =
                consumoCombustivel;

        this.tipoCombustivel =
                tipoCombustivel;
    }

    @Override
    public String tipoVeiculo() {
        return "Híbrido";
    }

    public double getCapacidadeTanqueCombustivel() {
        return capacidadeTanqueCombustivel;
    }

    public void setCapacidadeTanqueCombustivel(
            double capacidadeTanqueCombustivel) {

        this.capacidadeTanqueCombustivel =
                capacidadeTanqueCombustivel;
    }

    public double getConsumoCombustivel() {
        return consumoCombustivel;
    }

    public void setConsumoCombustivel(
            double consumoCombustivel) {

        this.consumoCombustivel =
                consumoCombustivel;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(
            String tipoCombustivel) {

        this.tipoCombustivel =
                tipoCombustivel;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Tipo: " + tipoVeiculo()
                + ", Combustível: "
                + tipoCombustivel;
    }
}
