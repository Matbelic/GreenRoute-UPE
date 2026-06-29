package repository;
import model.Veiculo;

public class VeiculoRepository {

    private Veiculo[] veiculos;
    private int totalVeiculos;
    private static final int TAMANHO_INICIAL = 5;

    public VeiculoRepository() {
        veiculos = new Veiculo[TAMANHO_INICIAL];
        totalVeiculos = 0;
    }

    public boolean idExiste(int id) {
        for (int i = 0; i < totalVeiculos; i++) {
            if (veiculos[i].getId() == id) {
                return true;
            }
        }
        return false;
    }

    private void expandirArray() {
        int novoTamanho = veiculos.length * 2;
        Veiculo[] novoArray = new Veiculo[novoTamanho];
        for (int i = 0; i < totalVeiculos; i++) {
            novoArray[i] = veiculos[i];
        }
        veiculos = novoArray;
        System.out.println("[Sistema] Array expandido para " + novoTamanho + " posições.");
    }


    public boolean adicionar(Veiculo v) {
        if (idExiste(v.getId())) {
            return false;
        }
        if (totalVeiculos == veiculos.length) {
            expandirArray();
        }
        veiculos[totalVeiculos] = v;
        totalVeiculos++;
        return true;
    }


    public Veiculo buscarPorId(int id) {
        for (int i = 0; i < totalVeiculos; i++) {
            if (veiculos[i].getId() == id) {
                return veiculos[i];
            }
        }
        return null;
    }


    public Veiculo[] listarTodos() {
        Veiculo[] resultado = new Veiculo[totalVeiculos];
        for (int i = 0; i < totalVeiculos; i++) {
            resultado[i] = veiculos[i];
        }
        return resultado;
    }

    public boolean atualizar(Veiculo veiculoAtualizado) {
        for (int i = 0; i < totalVeiculos; i++) {
            if (veiculos[i].getId() == veiculoAtualizado.getId()) {
                veiculos[i] = veiculoAtualizado;
                return true;
            }
        }
        return false;
    }

    public boolean remover(int id) {
        for (int i = 0; i < totalVeiculos; i++) {
            if (veiculos[i].getId() == id) {
                for (int j = i; j < totalVeiculos - 1; j++) {
                    veiculos[j] = veiculos[j + 1];
                }
                veiculos[totalVeiculos - 1] = null;
                totalVeiculos--;
                return true;
            }
        }
        return false;
    }

    public int getTotalVeiculos() {
        return totalVeiculos;
    }
}
