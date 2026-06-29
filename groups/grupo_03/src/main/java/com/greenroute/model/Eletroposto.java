package com.greenroute.model;

public class Eletroposto {

    private int id;
    private String nome;
    private String localizacao;
    private int cidadeId;
    private String tiposConectoresDisponiveis;
    private double potenciaCargaKw;
    private double precoPorKwh;
    private int vagasDisponiveis;

    //CRIANDO O CONSTRUTOR

    public Eletroposto(int id, String nome, String localizacao, int cidadeId, String tiposConectoresDisponiveis, double potenciaCargaKw, double precoPorKwh, int vagasDisponiveis){
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.cidadeId = cidadeId;
        this.tiposConectoresDisponiveis = tiposConectoresDisponiveis;
        this.potenciaCargaKw = potenciaCargaKw;
        this.precoPorKwh = precoPorKwh;
        this.vagasDisponiveis = vagasDisponiveis;
    }

    //CRIANDO OS GETTERS

    public int getId(){
        return id;
    }
    public String getNome(){
        return nome;
    }
    public String getLocalizacao(){
        return localizacao;
    }
    public int getCidadeId(){
        return cidadeId;
    }
    public String getTiposConectoresDisponiveis(){
        return tiposConectoresDisponiveis;
    }
    public double getPotenciaCargaKw(){
        return potenciaCargaKw;
    }
    public double getPrecoPorKwh(){
        return precoPorKwh;
    }
    public int getVagasDisponiveis(){
        return vagasDisponiveis;
    }


    //CRIANDO OS SETTERS

    public void setId(int id){
        this.id = id;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setLocalizacao(String localizacao){
        this.localizacao = localizacao;
    }
    public void setCidadeId(int cidadeId){
        this.cidadeId = cidadeId;
    }
    public void setTiposConectoresDisponiveis(String tiposConectoresDisponiveis){
        this.tiposConectoresDisponiveis = tiposConectoresDisponiveis;
    }

    public void setPotenciaCargaKw(double potenciaCargaKw){
        this.potenciaCargaKw = potenciaCargaKw;
    }
    public void setPrecoPorKwh( double precoPorKwh){
        this.precoPorKwh = precoPorKwh;
    }
    public void setVagasDisponiveis(int vagasDisponiveis){
        this.vagasDisponiveis = vagasDisponiveis;
    }

    @Override
    public String toString(){
        return "Eletroposto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", localizacao='" + localizacao + '\'' +
                ", cidadeId=" + cidadeId +
                ", tiposConectoresDisponiveis='" + tiposConectoresDisponiveis + '\'' +
                ", potenciaCargaKw=" + potenciaCargaKw +
                ", precoPorKwh=" + precoPorKwh +
                ", vagasDisponiveis=" + vagasDisponiveis +
                '}';
    }
}
