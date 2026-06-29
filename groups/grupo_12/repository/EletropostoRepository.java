package repository;

import model.Eletroposto;

public class EletropostoRepository {
    private Eletroposto[] eletropostos = new Eletroposto[5];
    private int quantidadeAtual = 0;

    public void cadastrar(Eletroposto eletroposto) {
        if (quantidadeAtual == eletropostos.length) {
            expandirArray();
        }
        eletropostos[quantidadeAtual] = eletroposto;
        quantidadeAtual++;
    }

    private void expandirArray() {
        Eletroposto[] novoArray = new Eletroposto[eletropostos.length * 2];
        for (int i = 0; i < eletropostos.length; i++) {
            novoArray[i] = eletropostos[i];
        }
        eletropostos = novoArray;
    }

    public Eletroposto buscarPorId(int id) {
        for (int i = 0; i < quantidadeAtual; i++) {
            if (eletropostos[i].getId() == id) {
                return eletropostos[i];
            }
        }
        return null;
    }

    public Eletroposto[] listarTodos() {
        Eletroposto[] listaLimpa = new Eletroposto[quantidadeAtual];
        for (int i = 0; i < quantidadeAtual; i++) {
            listaLimpa[i] = eletropostos[i];
        }
        return listaLimpa;
    }

    public boolean atualizar(Eletroposto eletropostoAtualizado) {
        for (int i = 0; i < quantidadeAtual; i++) {
            if (eletropostos[i].getId() == eletropostoAtualizado.getId()) {
                eletropostos[i] = eletropostoAtualizado;
                return true;
            }
        }
        return false;
    }

    public boolean excluir(int id) {
        for (int i = 0; i < quantidadeAtual; i++) {
            if (eletropostos[i].getId() == id) {
                for (int j = i; j < quantidadeAtual - 1; j++) {
                    eletropostos[j] = eletropostos[j + 1];
                }
                eletropostos[quantidadeAtual - 1] = null;
                quantidadeAtual--;
                return true;
            }
        }
        return false;
    }

    public Eletroposto[] buscarPorCidade(int cidadeId) {
        int contador = 0;
        for (int i = 0; i < quantidadeAtual; i++) {
            if (eletropostos[i].getCidadeId() == cidadeId) {
                contador++;
            }
        }
        Eletroposto[] resultado = new Eletroposto[contador];
        int index = 0;
        for (int i = 0; i < quantidadeAtual; i++) {
            if (eletropostos[i].getCidadeId() == cidadeId) {
                resultado[index++] = eletropostos[i];
            }
        }
        return resultado;
    }
}
