package model;

public class Cidade {

    private int id;
    private String nome;
    private String estado;
    private double distanciaDaCapital;

    public Cidade(int id,
                  String nome,
                  String estado,
                  double distanciaDaCapital) {

        this.id = id;
        this.nome = nome;
        this.estado = estado;
        this.distanciaDaCapital =
                distanciaDaCapital;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getDistanciaDaCapital() {
        return distanciaDaCapital;
    }

    public void setDistanciaDaCapital(
            double distanciaDaCapital) {

        this.distanciaDaCapital =
                distanciaDaCapital;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + ", Nome: " + nome
                + ", Estado: " + estado
                + ", Distância da Capital: "
                + distanciaDaCapital + " km";
    }
}
