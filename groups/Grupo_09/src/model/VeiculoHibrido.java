package model;

public class VeiculoHibrido extends Veiculo {
    private double capacidadeTanqueCombustivel;
    private double consumoCombustivel;
    private String tipoCombustivel;

    // Construtor
    public VeiculoHibrido(int id, String modelo, double autonomiaMaxima, double cargaBateriaAtual, double consumoKwhPorKm, int tempoRecargaCompleta, double capacidadeTanqueCombustivel, double consumoCombustivel, String tipoCombustivel){
        super(id, modelo, autonomiaMaxima, cargaBateriaAtual, consumoKwhPorKm, tempoRecargaCompleta);
        this.capacidadeTanqueCombustivel = capacidadeTanqueCombustivel;
        this.consumoCombustivel = consumoCombustivel;
        this.tipoCombustivel = tipoCombustivel;
    }

    // Getters
    public double getCapacidadeTanqueCombustivel() {return capacidadeTanqueCombustivel;}
    public double getConsumoCombustivel() {return consumoCombustivel;}
    public String getTipoCombustivel() {return tipoCombustivel;}

    // Setters
    public void setCapacidadeTanqueCombustivel(double capacidadeTanqueCombustivel) {
        if(capacidadeTanqueCombustivel >= 0){
            this.capacidadeTanqueCombustivel = capacidadeTanqueCombustivel;
        } else {
            throw new IllegalArgumentException("Erro! A capacidade do tanque não pode ser menor que 0.");
        }
    }
    public void setConsumoCombustivel(double consumoCombustivel) {
        if (consumoCombustivel > 0) {
            this.consumoCombustivel = consumoCombustivel;
        } else {
            throw new IllegalArgumentException("Erro! O consumo de combustível não pode ser menor ou igual a 0.");
        }
    }
    public void setTipoCombustivel(String tipoCombustivel) {this.tipoCombustivel = tipoCombustivel;}

}
