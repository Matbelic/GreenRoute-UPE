package com.greenroute.repository;

import com.greenroute.model.Cidade;

public class CidadeRepository {

    private Cidade[] cidades = new Cidade[5];
    private int quantidadeCidades;

    //CADASTRAR NOVAS CIDADES QUANDO O ARRAY ESTIVER CHEIO
    public void cadastrarCidade(Cidade cidade){

        if (quantidadeCidades < cidades.length){
            cidades[quantidadeCidades] = cidade;
            quantidadeCidades++;
        }
        else {
            Cidade[] arrayCidadeMaior = new Cidade[quantidadeCidades * 2];

            for (int i = 0; i < cidades.length; i++){
                arrayCidadeMaior[i] = cidades[i];
            }

            cidades = arrayCidadeMaior;
            cidades[quantidadeCidades] = cidade;
            quantidadeCidades++;
        }

    }

    //LISTAR AS CIDADES CADASTRADAS NO ARRAY
    public void listarCidade(){

        if (quantidadeCidades == 0){
            System.out.println("Nenhuma cidade cadastrada!");
            return;
        }

        for (int i = 0; i < quantidadeCidades; i++){
            System.out.println(cidades[i]);
        }
    }

    //REMOVER A CIDADE CADASTRADA NO ARRAY
    public boolean removerCidade(int id){
        for (int i = 0; i < quantidadeCidades; i++){
            if (id == cidades[i].getId()){

                for (int j = i; j < quantidadeCidades - 1; j++){
                    cidades[j] = cidades[j + 1];
                }

                cidades[quantidadeCidades - 1] = null;
                quantidadeCidades--;

                return true;
            }
        }

        return false;
    }

    //ATUALIZAR A CIDADE NO ARRAY PELO ID
    public boolean atualizarCidade(Cidade cidadeAtualizada){
        for (int i = 0; i < quantidadeCidades; i++){
            if (cidadeAtualizada.getId() == cidades[i].getId()){
                cidades[i] = cidadeAtualizada;
                return true;
            }
        }
        return false;
    }

    //BUSCAR CIDADE CADASTRADA NO ARRAY PELO ID
    public Cidade buscarCidade(int id){
        for (int i = 0; i < quantidadeCidades; i++){
            if (id == cidades[i].getId()){
                return cidades[i];
            }
        }
        return null;
    }
}