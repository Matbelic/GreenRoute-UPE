package repository;

import model.Eletroposto;

public class EletropostoRepository {

    private Eletroposto[] eletropostos;
    private int quantidade;

    public EletropostoRepository() {
        eletropostos = new Eletroposto[10];
        quantidade = 0;
    }
    private void aumentarCapacidade() {
        Eletroposto[] novoArray = new Eletroposto[eletropostos.length * 2];

        System.arraycopy(eletropostos, 0, novoArray, 0, eletropostos.length);

        eletropostos = novoArray;
    }

    public void cadastrar(Eletroposto eletroposto) {

        if (quantidade == eletropostos.length) {
            aumentarCapacidade();
        }

        eletropostos[quantidade] = eletroposto;
        quantidade++;
    }

    public Eletroposto buscarPorId(int id) {

        for (int i = 0; i < quantidade; i++) {

            if (eletropostos[i].getId() == id) {
                return eletropostos[i];
            }
        }

        return null;
    }

    public boolean atualizar(int id, Eletroposto novoEletroposto) {

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

                for (int j = i; j < quantidade - 1; j++) {
                    eletropostos[j] = eletropostos[j + 1];
                }

                eletropostos[quantidade - 1] = null;
                quantidade--;

                return true;
            }
        }
        return false;
    }
    public Eletroposto[] listar() {
        return eletropostos;
    }
    public int getQuantidade() {
        return quantidade;
    }
}