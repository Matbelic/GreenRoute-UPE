package Model;

public class Eletroposto {
    private int cidadeIdPosto;
    private int id; //(Identificador numérico único)
    private int vagasDisponiveis;
    private int cidadeId; //(ID numérico da cidade onde está localizado)
    private String nome;
    private String localCidade;
    private String localizacao; //(Endereço/Rodovia)
    private String tiposConectoresDisponiveis; //(Ex: "CCS2, Tipo 2" - aceita múltiplos)
    private double potenciaCargaKw; //(Potência do carregador, ex: 50.0 Kw, 150.0 Kw)
    private double precoPorKwh; //(Valor cobrado pelo quilowatt-hora)
    public Eletroposto(int cidadeIdPosto, int id, String nome, String localCidade, String localizacao, String tiposConectoresDisponiveis, int cidadeId, double potenciaCargaKw, double precoPorKwh, int vagasDisponiveis) {
        this.cidadeIdPosto = cidadeIdPosto;
        this.id = id;
        this.nome = nome;
        this.localCidade = localCidade;
        this.localizacao = localizacao;
        this.tiposConectoresDisponiveis = tiposConectoresDisponiveis;
        this.cidadeId = cidadeId;
        this.potenciaCargaKw = potenciaCargaKw;
        this.precoPorKwh = precoPorKwh;
        this.vagasDisponiveis = vagasDisponiveis;
    }
    // Getters
    public int getCidadeIdPosto() { return cidadeIdPosto; }
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getLocalCidade() { return localCidade; }
    public String getLocalizacao() { return localizacao; }
    public String getTiposConectoresDisponiveis() { return tiposConectoresDisponiveis; }
    public int getCidadeId() { return cidadeId; }
    public double getPotenciaCargaKw() { return potenciaCargaKw; }
    public double getPrecoPorKwh() { return precoPorKwh; }
    public int getVagasDisponiveis() { return vagasDisponiveis; }
    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }
    public void setTiposConectoresDisponiveis(String tiposConectoresDisponiveis) { this.tiposConectoresDisponiveis = tiposConectoresDisponiveis; }
    public void setCidadeId(int cityId) { this.cidadeId = cityId; }
    public void setPotenciaCargaKw(double potencia) { this.potenciaCargaKw = potencia; }
    public void setPrecoPorKwh(double preco) { this.precoPorKwh = preco; }
    public void setVagasDisponiveis(int vagas) { this.vagasDisponiveis = vagas; }

    @Override
    public String toString() {
        return "Id da cidade do Posto: " + cidadeIdPosto + "; ID: " + id + "; Nome: " + nome + "; Cidade local do Posto: " + localCidade + "; Endereço: " + localizacao + "; tipos de Conectores Disponiveis: " + tiposConectoresDisponiveis + "; Estado onde a Cidade está localizado: " + cidadeId + "; Potência do carregador: " + potenciaCargaKw + "kW; Valor cobrado por kWh: R$" + precoPorKwh + "; Vagas Disponiveis: " + vagasDisponiveis;
    }
}