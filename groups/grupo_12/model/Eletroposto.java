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

    public Eletroposto(int id, String nome, String localizacao, int cidadeId,
                       String tiposConectoresDisponiveis, double potenciaCargaKw, double precoPorKwh,
                       int vagasDisponiveis){

        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.cidadeId = cidadeId;
        this.tiposConectoresDisponiveis = tiposConectoresDisponiveis;
        this.potenciaCargaKw = potenciaCargaKw;
        this.precoPorKwh = precoPorKwh;
        this.vagasDisponiveis = vagasDisponiveis;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public int getCidadeId() {
        return cidadeId;
    }
    public void setCidadeId(int cidadeId) {
        this.cidadeId = cidadeId;
    }

    public String getTiposConectoresDisponiveis() {
        return tiposConectoresDisponiveis;
    }
    public void setTiposConectoresDisponiveis(String tiposConectoresDisponiveis) {
        this.tiposConectoresDisponiveis = tiposConectoresDisponiveis;
    }

    public double getPotenciaCargaKw() {
        return potenciaCargaKw;
    }
    public void setPotenciaCargaKw(double potenciaCargaKw) {
        this.potenciaCargaKw = potenciaCargaKw;
    }

    public double getPrecoPorKwh() {
        return precoPorKwh;
    }
    public void setPrecoPorKwh(double precoPorKwh) {
        this.precoPorKwh = precoPorKwh;
    }

    public int getVagasDisponiveis() {
        return vagasDisponiveis;
    }
    public void setVagasDisponiveis(int vagasDisponiveis) {
        this.vagasDisponiveis = vagasDisponiveis;
    }
}
