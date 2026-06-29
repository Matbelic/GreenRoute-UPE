package controller;

import Model.Cidade;
import repository.CidadeRepository;

public class CidadeController {
    private CidadeRepository repository = new CidadeRepository();
    public void RegisterCity(Cidade cidade) {
        if (cidade == null) {
            System.out.println("A cidade tem que existir!!!");
            return;
        }
        if (cidade.getId() < 0) {
            System.out.println("ID menor que 0. Digite um número maior que 0!");
            return;
        }
        repository.RegisterCity(cidade.getId(), cidade.getNome(), cidade.getEstado(), cidade.getDistanciaDaCapital());
    }
    public String ListCity() {
        return repository.ListCity();
    }
    public void SearchCity(int idProcurado) {
        repository.SearchCity(idProcurado);
    }
    public void UpdateCity(Cidade cidade) {
        if (cidade == null || cidade.getId() < 0) {
            System.out.println("A cidade tem que existir!!!");
            return;
        }
        repository.UpdateCity(cidade.getId(), cidade.getNome(), cidade.getEstado(), cidade.getDistanciaDaCapital());
    }
    public void DeleteCity(int idProcurado) {
        repository.DeleteCity(idProcurado);
    }
    public void CalculateRouteListCity() {}
    public double Calculatedist(int idOrigem, int idDestino) {
        double dados = repository.Calculatedist(idOrigem, idDestino);
        return dados;
    }
    public String Local(int cidadeIdPosto) {
        return repository.Local(cidadeIdPosto);
    }
}
