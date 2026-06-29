package Model;

public class Cidade {
    private int id; //(Código IBGE ou identificador numérico único)
    private String nome;
    private String estado; //(UF)
    private double distanciaDaCapital; //(em km)
    //Construtores
    public Cidade(int id,String nome, String estado, Double distanciaDaCapital){
        this.id = id;
        this.nome = nome;
        this.estado = estado; 
        this.distanciaDaCapital = distanciaDaCapital;
    }
    //get/Set
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
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public double getDistanciaDaCapital() {
        return distanciaDaCapital;
    }
    public void setDistanciaDaCapital(double distanciaDaCapital) {
        this.distanciaDaCapital = distanciaDaCapital;
    }
}