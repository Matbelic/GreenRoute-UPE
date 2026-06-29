package repository;

import model.Eletroposto;

public class EletropostoRepository {

    private Eletroposto[] eletropostos;
    private int quantidade;

    public EletropostoRepository() {

        eletropostos = new Eletroposto[10];
        quantidade = 0;
    }

    private void aumentarArray() {

        Eletroposto[] novoArray =
                new Eletroposto[eletropostos.length * 2];

        for (int i = 0; i < eletropostos.length; i++) {
            novoArray[i] = eletropostos[i];
        }

        eletropostos = novoArray;
    }

    public boolean existeId(int id) {

        for (int i = 0; i < quantidade; i++) {

            if (eletropostos[i].getId() == id) {
                return true;
            }
        }

        return false;
    }

    public boolean cadastrar(Eletroposto eletroposto) {

        if (existeId(eletroposto.getId())) {
            return false;
        }

        if (quantidade == eletropostos.length) {
            aumentarArray();
        }

        eletropostos[quantidade] = eletroposto;
        quantidade++;

        return true;
    }

    public Eletroposto buscarPorId(int id) {

        for (int i = 0; i < quantidade; i++) {

            if (eletropostos[i].getId() == id) {
                return eletropostos[i];
            }
        }

        return null;
    }

    public Eletroposto[] listar() {

        Eletroposto[] lista =
                new Eletroposto[quantidade];

        for (int i = 0; i < quantidade; i++) {
            lista[i] = eletropostos[i];
        }

        return lista;
    }

    public boolean atualizar(int id,
                             Eletroposto novoEletroposto) {

        for (int i = 0; i < quantidade; i++) {

            if (eletropostos[i].getId() == id) {

                eletropostos[i] = novoEletroposto;
                return true;
            }
        }

        return false;
    }

    public boolean excluir(int id) {

        for (int i = 0; i < quantidade; i++) {

            if (eletropostos[i].getId() == id) {

                for (int j = i;
                     j < quantidade - 1;
                     j++) {

                    eletropostos[j] =
                            eletropostos[j + 1];
                }

                eletropostos[quantidade - 1] = null;
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
