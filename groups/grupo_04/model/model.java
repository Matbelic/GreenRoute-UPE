package model;

public abstract class Veiculo {

    private int id;
    private String modelo;
    private double autonomiaMaxima;
    private double cargaBateriaAtual;
    private double consumoKwhPorKm;
    private int tempoRecargaCompleta;

    public Veiculo(int id,
                   String modelo,
                   double autonomiaMaxima,
                   double cargaBateriaAtual,
                   double consumoKwhPorKm,
                   int tempoRecargaCompleta) {

        this.id = id;
        this.modelo = modelo;
        this.autonomiaMaxima = autonomiaMaxima;
        this.cargaBateriaAtual = cargaBateriaAtual;
        this.consumoKwhPorKm = consumoKwhPorKm;
        this.tempoRecargaCompleta = tempoRecargaCompleta;
    }

    public double autonomiaAtual() {
        return autonomiaMaxima * (cargaBateriaAtual / 100.0);
    }

    // Método abstrato para demonstrar abstração
    public abstract String tipoVeiculo();

    public int getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getAutonomiaMaxima() {
        return autonomiaMaxima;
    }

    public void setAutonomiaMaxima(double autonomiaMaxima) {
        this.autonomiaMaxima = autonomiaMaxima;
    }

    public double getCargaBateriaAtual() {
        return cargaBateriaAtual;
    }

    public void setCargaBateriaAtual(double cargaBateriaAtual) {
        this.cargaBateriaAtual = cargaBateriaAtual;
    }

    public double getConsumoKwhPorKm() {
        return consumoKwhPorKm;
    }

    public void setConsumoKwhPorKm(double consumoKwhPorKm) {
        this.consumoKwhPorKm = consumoKwhPorKm;
    }

    public int getTempoRecargaCompleta() {
        return tempoRecargaCompleta;
    }

    public void setTempoRecargaCompleta(int tempoRecargaCompleta) {
        this.tempoRecargaCompleta = tempoRecargaCompleta;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Modelo: " + modelo +
                ", Autonomia Máxima: " + autonomiaMaxima +
                " km, Bateria: " + cargaBateriaAtual + "%";
    }
}

package model;

public class VeiculoEletrico extends Veiculo {

    private String tipoConector;
    private int tempoRecargaRapida;

    public VeiculoEletrico(int id,
                           String modelo,
                           double autonomiaMaxima,
                           double cargaBateriaAtual,
                           double consumoKwhPorKm,
                           int tempoRecargaCompleta,
                           String tipoConector,
                           int tempoRecargaRapida) {

        super(id, modelo, autonomiaMaxima,
              cargaBateriaAtual, consumoKwhPorKm,
              tempoRecargaCompleta);

        this.tipoConector = tipoConector;
        this.tempoRecargaRapida = tempoRecargaRapida;
    }

    @Override
    public String tipoVeiculo() {
        return "Elétrico";
    }

    public String getTipoConector() {
        return tipoConector;
    }

    public void setTipoConector(String tipoConector) {
        this.tipoConector = tipoConector;
    }

    public int getTempoRecargaRapida() {
        return tempoRecargaRapida;
    }

    public void setTempoRecargaRapida(int tempoRecargaRapida) {
        this.tempoRecargaRapida = tempoRecargaRapida;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Tipo: " + tipoVeiculo()
                + ", Conector: " + tipoConector
                + ", Recarga Rápida: "
                + tempoRecargaRapida + " min";
    }
}

package model;

public class VeiculoHibrido extends Veiculo {

    private double capacidadeTanqueCombustivel;
    private double consumoCombustivel;
    private String tipoCombustivel;

    public VeiculoHibrido(int id,
                          String modelo,
                          double autonomiaMaxima,
                          double cargaBateriaAtual,
                          double consumoKwhPorKm,
                          int tempoRecargaCompleta,
                          double capacidadeTanqueCombustivel,
                          double consumoCombustivel,
                          String tipoCombustivel) {

        super(id, modelo, autonomiaMaxima,
              cargaBateriaAtual, consumoKwhPorKm,
              tempoRecargaCompleta);

        this.capacidadeTanqueCombustivel =
                capacidadeTanqueCombustivel;

        this.consumoCombustivel =
                consumoCombustivel;

        this.tipoCombustivel =
                tipoCombustivel;
    }

    @Override
    public String tipoVeiculo() {
        return "Híbrido";
    }

    public double getCapacidadeTanqueCombustivel() {
        return capacidadeTanqueCombustivel;
    }

    public void setCapacidadeTanqueCombustivel(
            double capacidadeTanqueCombustivel) {

        this.capacidadeTanqueCombustivel =
                capacidadeTanqueCombustivel;
    }

    public double getConsumoCombustivel() {
        return consumoCombustivel;
    }

    public void setConsumoCombustivel(
            double consumoCombustivel) {

        this.consumoCombustivel =
                consumoCombustivel;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(
            String tipoCombustivel) {

        this.tipoCombustivel =
                tipoCombustivel;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Tipo: " + tipoVeiculo()
                + ", Combustível: "
                + tipoCombustivel;
    }
}

package model;

public class VeiculoHibrido extends Veiculo {

    private double capacidadeTanqueCombustivel;
    private double consumoCombustivel;
    private String tipoCombustivel;

    public VeiculoHibrido(int id,
                          String modelo,
                          double autonomiaMaxima,
                          double cargaBateriaAtual,
                          double consumoKwhPorKm,
                          int tempoRecargaCompleta,
                          double capacidadeTanqueCombustivel,
                          double consumoCombustivel,
                          String tipoCombustivel) {

        super(id, modelo, autonomiaMaxima,
              cargaBateriaAtual, consumoKwhPorKm,
              tempoRecargaCompleta);

        this.capacidadeTanqueCombustivel =
                capacidadeTanqueCombustivel;

        this.consumoCombustivel =
                consumoCombustivel;

        this.tipoCombustivel =
                tipoCombustivel;
    }

    @Override
    public String tipoVeiculo() {
        return "Híbrido";
    }

    public double getCapacidadeTanqueCombustivel() {
        return capacidadeTanqueCombustivel;
    }

    public void setCapacidadeTanqueCombustivel(
            double capacidadeTanqueCombustivel) {

        this.capacidadeTanqueCombustivel =
                capacidadeTanqueCombustivel;
    }

    public double getConsumoCombustivel() {
        return consumoCombustivel;
    }

    public void setConsumoCombustivel(
            double consumoCombustivel) {

        this.consumoCombustivel =
                consumoCombustivel;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(
            String tipoCombustivel) {

        this.tipoCombustivel =
                tipoCombustivel;
    }

    @Override
    public String toString() {
        return super.toString()
                + ", Tipo: " + tipoVeiculo()
                + ", Combustível: "
                + tipoCombustivel;
    }
}


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
    
