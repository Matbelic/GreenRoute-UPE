package repository;

import Model.Eletroposto;

public class EletropostoRepository {
    private int capacity = 10;
    private Eletroposto[] postos = new Eletroposto[capacity]; // Agora guarda Objetos!
    private int totalpost = 0;
    public EletropostoRepository() {}

    public Eletroposto[] getPostos() { return this.postos; }
    public int getTotalpost() { return this.totalpost; }

    public void RegisterPost(int cidadeIdPosto, int id, String nome, String localCidade, String localizacao, String tiposConectoresDisponiveis, int cidadeId, double potenciaCargaKw, double precoPorKwh, int vagasDisponiveis) {
        for (int i = 0; i < totalpost; i++) {
            if (postos[i] != null && postos[i].getId() == id) {
                System.out.println("Id ja Registrado Porfavor coloque outro id!!\n");
                return;
            }
        }
        if (totalpost == capacity) {
            this.capacity = this.capacity + 5; 
            Eletroposto[] novoArray = new Eletroposto[this.capacity];
            for (int i = 0; i < postos.length; i++) {
                novoArray[i] = this.postos[i];
            }
            this.postos = novoArray;
        }
        this.postos[totalpost] = new Eletroposto(cidadeIdPosto, id, nome, localCidade, localizacao, tiposConectoresDisponiveis, cidadeId, potenciaCargaKw, precoPorKwh, vagasDisponiveis);
        totalpost++;
        System.out.println("Posto Cadastrado com Sucesso\n"); 
    }
    public void Listpost() {
        if (totalpost != 0) {

            
            System.out.println("Não tem nem um Posto Cadastrado\n");
            return;
        }
        for (int i = 0; i < totalpost; i++) {
            System.out.println(postos[i]);
        }
    }
    public void Searchpost(int idProcurado) {
        for (int i = 0; i < totalpost; i++) {
            if (postos[i] != null && postos[i].getId() == idProcurado) {
                System.out.println(postos[i]);
                return;
            }
        }
        System.out.println("Posto não encontrado.\n"); 
    }
    public void Updatepost(int id, String nome, String localizacao, String tiposConectoresDisponiveis, int cidadeId, double potenciaCargaKw, double precoPorKwh, int vagasDisponiveis) {
        for (int i = 0; i < totalpost; i++) {
            if (postos[i] != null && postos[i].getId() == id) {
                postos[i].setNome(nome);
                postos[i].setLocalizacao(localizacao);
                postos[i].setTiposConectoresDisponiveis(tiposConectoresDisponiveis);
                postos[i].setCidadeId(cidadeId);
                postos[i].setPotenciaCargaKw(potenciaCargaKw);
                postos[i].setPrecoPorKwh(precoPorKwh);
                postos[i].setVagasDisponiveis(vagasDisponiveis);
                System.out.println("Posto com o ID " + id + " atualizado com sucesso!\n");
                return;
            }
        }
        System.out.println("Posto para atualização não encontrada.\n");
    }
    public void Deletepost(int idProcurado) {
        for (int i = 0; i < totalpost; i++) {
            if (postos[i] != null && postos[i].getId() == idProcurado) {
                for (int j = i; j < totalpost - 1; j++) {
                    postos[j] = postos[j + 1];
                }
                postos[totalpost - 1] = null; 
                totalpost--; 
                System.out.println("Posto com o ID :" + idProcurado + " excluído com sucesso!\n");
                return;
            }
        }
        System.out.println("Posto Para Ser Exclusão Não Encontrado.\n");
    }
    public boolean SearchpostCity(int idOrigem) {
        boolean encontrou = true;
        if (encontrou){
            for (int i = 0; i < totalpost; i++) {
                if (postos[i] != null && postos[i].getCidadeIdPosto() == idOrigem) {
                    System.out.println("ID: " + postos[i].getId() + "; Nome: " + postos[i].getNome());
                    encontrou = false;
                }
            }
        }
        if (encontrou) {
            System.out.println("Nenhum posto encontrado para esta cidade.\n");
        }
        return encontrou;
    }
}