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

    public Eletroposto(int id, String nome, String localizacao, int cidadeId, String tiposConectoresDisponiveis, 
                       double potenciaCargaKw, double precoPorKwh, int vagasDisponiveis) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.cidadeId = cidadeId;
        this.tiposConectoresDisponiveis = tiposConectoresDisponiveis;
        this.potenciaCargaKw = potenciaCargaKw;
        this.precoPorKwh = precoPorKwh;
        this.vagasDisponiveis = vagasDisponiveis;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }
    public int getCidadeId() { return cidadeId; }
    public void setCidadeId(int cityId) { this.cidadeId = cityId; }
    public String getTiposConectoresDisponiveis() { return tiposConectoresDisponiveis; }
    public void setTiposConectoresDisponiveis(String tipos) { this.tiposConectoresDisponiveis = tipos; }
    public double getPotenciaCargaKw() { return potenciaCargaKw; }
    public void setPotenciaCargaKw(double potencia) { this.potenciaCargaKw = potencia; }
    public double getPrecoPorKwh() { return precoPorKwh; }
    public void setPrecoPorKwh(double preco) { this.precoPorKwh = preco; }
    public int getVagasDisponiveis() { return vagasDisponiveis; }
    public void setVagasDisponiveis(int vagas) { this.vagasDisponiveis = vagas; }
}