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


    // Construtor
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

    // Getters
    public int getId() {return id;}
    public String getNome() {return nome;}
    public String getLocalizacao() {return localizacao;}
    public int getCidadeId() {return cidadeId;}
    public String getTiposConectoresDisponiveis() {return tiposConectoresDisponiveis;}
    public double getPotenciaCargaKw() {return potenciaCargaKw;}
    public double getPrecoPorKwh() {return precoPorKwh;}
    public int getVagasDisponiveis() {return vagasDisponiveis;}

    // Setters
    public void setId(int id) {this.id = id;}
    public void setNome(String nome) {this.nome = nome;}
    public void setLocalizacao(String localizacao) {this.localizacao = localizacao;}
    public void setCidadeId(int cidadeId){
        if(cidadeId > 0){
            this.cidadeId = cidadeId;
        } else {
            throw new IllegalArgumentException("O id da cidade não pode conter números negativos. (Menores que 0)");
        }
    }
    public void setTiposConectoresDisponiveis(String tiposConectoresDisponiveis) {this.tiposConectoresDisponiveis = tiposConectoresDisponiveis;}
    public void setPotenciaCargaKw(double potenciaCargaKw){
        if(potenciaCargaKw > 0){
            this.potenciaCargaKw = potenciaCargaKw;
        } else {
            throw new IllegalArgumentException("Erro! A potência de carga Kw não pode ser menor ou igual a 0Kw.");
        }
    }
    public void setPrecoPorKwh(double precoPorKwh){
        if(precoPorKwh >= 0){
            this.precoPorKwh = precoPorKwh;
        } else {
            throw new IllegalArgumentException("Erro! O preço não pode ser menor que 0.");
        }
    }
    public void setVagasDisponiveis(int vagasDisponiveis){
        if(vagasDisponiveis >= 0){
            this.vagasDisponiveis = vagasDisponiveis;
        } else {
            throw new IllegalArgumentException("Erro! A quantidade de vagas não pode ser menor que 0.");
        }
    }
}
