package repository;

import model.Cidade;

public class CidadeRepository {

    private Cidade[] cidades;
    private int quantidade;

    public CidadeRepository() {

        cidades = new Cidade[10];
        quantidade = 0;
    }

    private void aumentarArray() {

        Cidade[] novoArray =
                new Cidade[cidades.length * 2];

        for (int i = 0; i < cidades.length; i++) {
            novoArray[i] = cidades[i];
        }

        cidades = novoArray;
    }

    public boolean existeId(int id) {

        for (int i = 0; i < quantidade; i++) {

            if (cidades[i].getId() == id) {
                return true;
            }
        }

        return false;
    }

    public boolean cadastrar(Cidade cidade) {

        if (existeId(cidade.getId())) {
            return false;
        }

        if (quantidade == cidades.length) {
            aumentarArray();
        }

        cidades[quantidade] = cidade;
        quantidade++;

        return true;
    }

    public Cidade buscarPorId(int id) {

        for (int i = 0; i < quantidade; i++) {

            if (cidades[i].getId() == id) {
                return cidades[i];
            }
        }

        return null;
    }

    public Cidade[] listar() {

        Cidade[] lista = new Cidade[quantidade];

        for (int i = 0; i < quantidade; i++) {
            lista[i] = cidades[i];
        }

        return lista;
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

                for (int j = i;
                     j < quantidade - 1;
                     j++) {

                    cidades[j] = cidades[j + 1];
                }

                cidades[quantidade - 1] = null;
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
