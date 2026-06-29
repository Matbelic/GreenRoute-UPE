package com.greenroute.repository;

import com.greenroute.model.Eletroposto;

public class EletropostoRepository {

    private Eletroposto[] eletropostos = new Eletroposto[5];
    private int quantidadeEletropostos;

    public Eletroposto[] getEletropostos() {
        return eletropostos;
    }

    public int getQuantidadeEletropostos() {
        return quantidadeEletropostos;
    }

    // CADASTRAR ELETROPOSTO NO ARRAY
    public void cadastrarEletroposto(Eletroposto eletroposto) {

        if (quantidadeEletropostos < eletropostos.length) {
            eletropostos[quantidadeEletropostos] = eletroposto;
            quantidadeEletropostos++;
        } else {

            Eletroposto[] arrayEletropostoMaior =
                    new Eletroposto[quantidadeEletropostos * 2];

            for (int i = 0; i < eletropostos.length; i++) {
                arrayEletropostoMaior[i] = eletropostos[i];
            }

            eletropostos = arrayEletropostoMaior;
            eletropostos[quantidadeEletropostos] = eletroposto;
            quantidadeEletropostos++;
        }
    }

    // LISTAR ELETROPOSTOS CADASTRADOS NO ARRAY
    public void listarEletropostos() {

        if (quantidadeEletropostos == 0) {
            System.out.println("Nenhum eletroposto cadastrado!");
            return;
        }

        for (int i = 0; i < quantidadeEletropostos; i++) {
            System.out.println(eletropostos[i]);
        }
    }

    // REMOVER ELETROPOSTO CADASTRADO NO ARRAY
    public boolean removerEletroposto(int id) {

        for (int i = 0; i < quantidadeEletropostos; i++) {

            if (id == eletropostos[i].getId()) {

                for (int j = i; j < quantidadeEletropostos - 1; j++) {
                    eletropostos[j] = eletropostos[j + 1];
                }

                eletropostos[quantidadeEletropostos - 1] = null;
                quantidadeEletropostos--;

                return true;
            }
        }

        return false;
    }

    // ATUALIZAR ELETROPOSTO CADASTRADO NO ARRAY
    public boolean atualizarEletroposto(Eletroposto eletropostoAtualizado) {

        for (int i = 0; i < quantidadeEletropostos; i++) {

            if (eletropostoAtualizado.getId() == eletropostos[i].getId()) {

                eletropostos[i] = eletropostoAtualizado;
                return true;
            }
        }

        return false;
    }

    // BUSCAR ELETROPOSTO POR ID CADASTRADO NO ARRAY
    public Eletroposto buscarEletroposto(int id) {

        for (int i = 0; i < quantidadeEletropostos; i++) {

            if (id == eletropostos[i].getId()) {
                return eletropostos[i];
            }
        }

        return null;
    }
}