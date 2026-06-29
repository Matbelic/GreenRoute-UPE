package repository;

import Model.Cidade;

public class CidadeRepository {
    private int capacity = 10;
    private Cidade[] citys = new Cidade[capacity];
    private int totalCitys = 0;
    public void RegisterCity(int id, String nome,String estado, double distanciaDaCapital){
        if (totalCitys == capacity) {
            capacity += 5;
            Cidade[] novoArray = new Cidade[capacity];
            for (int i = 0; i < totalCitys; i++) {
                novoArray[i] = citys[i];
            }
            citys = novoArray;
        }
        for (int i = 0; i < totalCitys; i++) {
            if (citys[i].getId() == id) {
                System.out.println("Id já registrado!");
                return;
            }
        }
        citys[totalCitys] = new Cidade(id, nome, estado, distanciaDaCapital);
        totalCitys++;
        System.out.println("Cidade cadastrada com sucesso!");
    }
    public String ListCity() {
        if (totalCitys == 0) {
            return "Não tem nenhuma cidade cadastrada.";
        }
        for (int i = 0; i < totalCitys; i++) {
            Cidade city = citys[i];
            System.out.println("ID: " + city.getId() + "; Nome: " + city.getNome() + "; Estado: " + city.getEstado() + "; Distância da Capital: " + city.getDistanciaDaCapital());
        }
        return "";
    }
    public void SearchCity(int idProcurado){
        for (int i = 0; i < totalCitys; i++) {
            if (citys[i].getId() == idProcurado) {
                Cidade city = citys[i];
                System.out.println("ID: " + city.getId() + "; Nome da Cidade: " + city.getNome() + "; Estado: " + city.getEstado());
                return;
            }
        }
        System.out.println("Cidade não encontrada.");
    }
    public void UpdateCity(int id,String nome,String estado,double distanciaDaCapital){
        for (int i = 0; i < totalCitys; i++) {
            if (citys[i].getId() == id) {
                citys[i].setNome(nome);
                citys[i].setEstado(estado);
                citys[i].setDistanciaDaCapital(distanciaDaCapital);
                System.out.println("Cidade atualizada!");
                return;
            }
        }
        System.out.println("Cidade não encontrada.");
    }
    public void DeleteCity(int idProcurado) {
        for (int i = 0; i < totalCitys; i++) {
            if (citys[i].getId() == idProcurado) {
                for (int j = i; j < totalCitys - 1; j++) {
                    citys[j] = citys[j + 1];
                }
                citys[totalCitys - 1] = null;
                totalCitys--;
                System.out.println("Cidade com ID "+ idProcurado+ " excluída com sucesso!");
                return;
            }
        }
        System.out.println("Cidade não encontrada.");
    }
    public double Calculatedist(int idOrigem,int idDestino){
        Cidade origem = null;
        Cidade destino = null;
    
        for (int i = 0; i < totalCitys; i++) {
            if (citys[i].getId() == idOrigem)
                origem = citys[i];
            if (citys[i].getId() == idDestino)
                destino = citys[i];
        }
        if (origem == null || destino == null) {
            return -1;
        }
        return Math.abs(origem.getDistanciaDaCapital() - destino.getDistanciaDaCapital());
    }
    public String Local(int cidadeIdPosto){
        for (int i = 0; i < totalCitys; i++) {
            if (citys[i].getId() == cidadeIdPosto) {
                return citys[i].getNome();
            }
        }
        return "Cidade não encontrado.\n";
    }
}