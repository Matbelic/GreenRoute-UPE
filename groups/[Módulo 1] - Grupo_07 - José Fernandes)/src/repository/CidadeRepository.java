package repository;
import model.Cidade;

public class CidadeRepository {

    private Cidade[] cidades;
    private int totalCidades;
    private static final int TAMANHO_INICIAL = 5;

    public CidadeRepository() {
        cidades = new Cidade[TAMANHO_INICIAL];
        totalCidades = 0;
    }

    public boolean idExiste(int id) {
        for (int i = 0; i < totalCidades; i++) {
            if (cidades[i].getId() == id) return true;
        }
        return false;
    }

    private void expandirArray() {
        int novoTamanho = cidades.length * 2;
        Cidade[] novoArray = new Cidade[novoTamanho];
        for (int i = 0; i < totalCidades; i++) {
            novoArray[i] = cidades[i];
        }
        cidades = novoArray;
        System.out.println("[Sistema] Array de cidades expandido para " + novoTamanho + " posições.");
    }

    public boolean adicionar(Cidade c) {
        if (idExiste(c.getId())) return false;
        if (totalCidades == cidades.length) expandirArray();
        cidades[totalCidades] = c;
        totalCidades++;
        return true;
    }

    public Cidade buscarPorId(int id) {
        for (int i = 0; i < totalCidades; i++) {
            if (cidades[i].getId() == id) return cidades[i];
        }
        return null;
    }

    public Cidade buscarPorNome(String nome) {
        for (int i = 0; i < totalCidades; i++) {
            if (cidades[i].getNome().equalsIgnoreCase(nome)) return cidades[i];
        }
        return null;
    }

    public Cidade[] listarTodos() {
        Cidade[] resultado = new Cidade[totalCidades];
        for (int i = 0; i < totalCidades; i++) resultado[i] = cidades[i];
        return resultado;
    }

    public boolean atualizar(Cidade atualizada) {
        for (int i = 0; i < totalCidades; i++) {
            if (cidades[i].getId() == atualizada.getId()) {
                cidades[i] = atualizada;
                return true;
            }
        }
        return false;
    }

    public boolean remover(int id) {
        for (int i = 0; i < totalCidades; i++) {
            if (cidades[i].getId() == id) {
                for (int j = i; j < totalCidades - 1; j++) {
                    cidades[j] = cidades[j + 1];
                }
                cidades[totalCidades - 1] = null;
                totalCidades--;
                return true;
            }
        }
        return false;
    }

    public int getTotalCidades() { return totalCidades; }
}
