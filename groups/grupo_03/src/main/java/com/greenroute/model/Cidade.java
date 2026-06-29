package com.greenroute.model;

public class Cidade {

    private int id;
    private String nome;
    private String estado;
    private double distanciaDaCapital;

    //CONSTRUTOR

    public Cidade(int id, String nome, String estado, double distanciaDaCapital){
        this.id = id;
        this.nome = nome;
        this.estado = estado;
        this.distanciaDaCapital = distanciaDaCapital;
    }

    //CRIANDO OS GETTERS

    public int getId(){
        return id;
    }
    public String getNome(){
        return nome;
    }
    public String getEstado(){
        return estado;
    }
    public double getDistanciaDaCapital(){
        return  distanciaDaCapital;
    }

    //CRIANDO OS SETTERS

    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setEstado(String estado){
        this.estado = estado;
    }
    public void setDistanciaDaCapital( double distanciaDaCapital){
        this.distanciaDaCapital = distanciaDaCapital;
    }

    @Override
    public String toString(){
        return "Cidade{ id=" + id + ", nome=" + nome + ", estado=" + estado + ", distanciaDaCapital=" + distanciaDaCapital + "km}";
    }

}

