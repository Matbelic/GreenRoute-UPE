package repository;

import model.Cidade;

public class CidadeRepository {

    private Cidade[] cidades;
    private int quantidade;

    public CidadeRepository() {
        cidades = new Cidade[10];
        quantidade = 0;
    }

    private void aumentarCapacidade() {

        Cidade[] novoArray = new Cidade[cidades.length * 2];

        System.arraycopy(cidades, 0, novoArray, 0, cidades.length);

        cidades = novoArray;
    }

    public void cadastrar(Cidade cidade) {

        if (quantidade == cidades.length) {
            aumentarCapacidade();
        }

        cidades[quantidade] = cidade;
        quantidade++;
    }

    public Cidade buscarPorId(int id) {

        for (int i = 0; i < quantidade; i++) {

            if (cidades[i].getId() == id) {
                return cidades[i];
            }
        }

        return null;
    }

    public boolean atualizar(int id, Cidade novaCidade) {

        for (int i = 0; i < quantidade; i++) {

            if (cidades[i].getId() == id) {
                cidades[i] = novaCidade;
                return true;
            }
        }

        return false;
    }

    public boolean excluir(int id) {

        for (int i = 0; i < quantidade; i++) {

            if (cidades[i].getId() == id) {

                for (int j = i; j < quantidade - 1; j++) {
                    cidades[j] = cidades[j + 1];
                }

                cidades[quantidade - 1] = null;
                quantidade--;

                return true;
            }
        }

        return false;
    }

    public Cidade[] listar() {
        return cidades;
    }

    public int getQuantidade() {
        return quantidade;
    }
}