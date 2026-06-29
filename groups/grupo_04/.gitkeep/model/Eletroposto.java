package model;

public class Eletroposto {

    private int id;
    private String nome;
    private String localizacao;
    private int cidadeId;
    private String tiposConectoresDisponiveis;
    private double potenciaCargaKw;
    private double precoPorKwh;
    private int vagasDisponiveis;

    public Eletroposto(int id,
                       String nome,
                       String localizacao,
                       int cidadeId,
                       String tiposConectoresDisponiveis,
                       double potenciaCargaKw,
                       double precoPorKwh,
                       int vagasDisponiveis) {

        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.cidadeId = cidadeId;
        this.tiposConectoresDisponiveis =
                tiposConectoresDisponiveis;

        this.potenciaCargaKw =
                potenciaCargaKw;

        this.precoPorKwh =
                precoPorKwh;

        this.vagasDisponiveis =
                vagasDisponiveis;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public int getCidadeId() {
        return cidadeId;
    }

    public String getTiposConectoresDisponiveis() {
        return tiposConectoresDisponiveis;
    }

    public double getPotenciaCargaKw() {
        return potenciaCargaKw;
    }

    public double getPrecoPorKwh() {
        return precoPorKwh;
    }

    public int getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    @Override
    public String toString() {

        return "ID: " + id
                + ", Nome: " + nome
                + ", Cidade ID: " + cidadeId
                + ", Potência: "
                + potenciaCargaKw + " KW";
    }
}
