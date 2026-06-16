package repository;

import model.Veiculo;

public class VeiculoRepository {

    private Veiculo[] veiculos;
    private int quantidade;

    public VeiculoRepository() {
        veiculos = new Veiculo[10];
        quantidade = 0;
    }

    private void aumentarArray() {

        Veiculo[] novoArray =
                new Veiculo[veiculos.length * 2];

        for (int i = 0; i < veiculos.length; i++) {
            novoArray[i] = veiculos[i];
        }

        veiculos = novoArray;
    }

    public boolean existeId(int id) {

        for (int i = 0; i < quantidade; i++) {

            if (veiculos[i].getId() == id) {
                return true;
            }
        }

        return false;
    }

    public boolean cadastrar(Veiculo veiculo) {

        if (existeId(veiculo.getId())) {
            return false;
        }

        if (quantidade == veiculos.length) {
            aumentarArray();
        }

        veiculos[quantidade] = veiculo;
        quantidade++;

        return true;
    }

    public Veiculo buscarPorId(int id) {

        for (int i = 0; i < quantidade; i++) {

            if (veiculos[i].getId() == id) {
                return veiculos[i];
            }
        }

        return null;
    }

    public Veiculo[] listar() {

        Veiculo[] lista = new Veiculo[quantidade];

        for (int i = 0; i < quantidade; i++) {
            lista[i] = veiculos[i];
        }

        return lista;
    }

    public boolean atualizar(int id, Veiculo novoVeiculo) {

        for (int i = 0; i < quantidade; i++) {

            if (veiculos[i].getId() == id) {

                veiculos[i] = novoVeiculo;
                return true;
            }
        }

        return false;
    }

    public boolean excluir(int id) {

        for (int i = 0; i < quantidade; i++) {

            if (veiculos[i].getId() == id) {

                for (int j = i;
                     j < quantidade - 1;
                     j++) {

                    veiculos[j] = veiculos[j + 1];
                }

                veiculos[quantidade - 1] = null;
                quantidade--;

                return true;
            }
        }

        return false;
    }

    public int quantidade() {
        return quantidade;
    }
}
