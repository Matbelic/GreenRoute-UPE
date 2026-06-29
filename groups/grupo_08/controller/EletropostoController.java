package controller;

import Model.Eletroposto;
import repository.EletropostoRepository;

public class EletropostoController {
    EletropostoRepository repository = new EletropostoRepository();
    public void Registerpost(int cidadeIdPosto,int id, String nome, String localCidade,String localizacao, String tiposConectoresDisponiveis, int cidadeId, double potenciaCargaKw, double precoPorKwh, int vagasDisponiveis){
        if(vagasDisponiveis > 0 && potenciaCargaKw > 0 && precoPorKwh > 0){
            repository.RegisterPost(cidadeIdPosto, id, nome, localCidade, localizacao, tiposConectoresDisponiveis, cidadeId, potenciaCargaKw, precoPorKwh, vagasDisponiveis);
        }else{
            System.out.print("Digite Valores maior que 0!!!!");
        }
    }
    public void Listpost(){
        repository.Listpost();
    }
    public void Searchpost(int idProcurado){
        repository.Searchpost(idProcurado);
    }
    public void Updatepost(int idProcurado, String nome , String localizacao, String tiposConectoresDisponiveis, int cidadeId, double potenciaCargaKw, double precoPorKwh, int vagasDisponiveis){
        repository.Updatepost(idProcurado, nome, localizacao, tiposConectoresDisponiveis, cidadeId, potenciaCargaKw, precoPorKwh, vagasDisponiveis);
    }
    public void Deletepost(int idProcurado){
        repository.Deletepost(idProcurado);
    }
    public boolean  SearchpostCity(int idOrigem){
        return repository.SearchpostCity(idOrigem);
    }
    public int getTotalpost() { 
        return repository.getTotalpost(); 
    }
    public Eletroposto getPostos(int indice) {
        return repository.getPostos()[indice]; 
    }
}
