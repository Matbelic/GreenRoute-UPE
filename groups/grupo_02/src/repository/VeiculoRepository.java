package repository;

import model.Veiculo;

public class VeiculoRepository {

    private Veiculo[] veiculos;
    private int quantidade;

    public VeiculoRepository() {
        veiculos = new Veiculo[10];
        quantidade = 0;
    }

    private void aumentarCapacidade() {

        Veiculo[] novoArray = new Veiculo[veiculos.length * 2];

        System.arraycopy(veiculos, 0, novoArray, 0, veiculos.length);

        veiculos = novoArray;
    }

    public void cadastrar(Veiculo veiculo) {

        if (quantidade == veiculos.length) {
            aumentarCapacidade();
        }

        veiculos[quantidade] = veiculo;
        quantidade++;
    }

    public Veiculo buscarPorId(int id) {

        for (int i = 0; i < quantidade; i++) {

            if (veiculos[i].getId() == id) {
                return veiculos[i];
            }
        }

        return null;
    }

    public void atualizar(int id, Veiculo novoVeiculo) {

        for (int i = 0; i < quantidade; i++) {

            if (veiculos[i].getId() == id) {
                veiculos[i] = novoVeiculo;
                return;
            }
        }

    }

    public boolean excluir(int id) {

        for (int i = 0; i < quantidade; i++) {

            if (veiculos[i].getId() == id) {

                for (int j = i; j < quantidade - 1; j++) {
                    veiculos[j] = veiculos[j + 1];
                }

                veiculos[quantidade - 1] = null;
                quantidade--;

                return true;
            }
        }

        return false;
    }

    public Veiculo[] listar() {
        return veiculos;
    }

    public int getQuantidade() {
        return quantidade;
    }
}