package com.greenroute.controller;

import com.greenroute.model.Veiculo;
import com.greenroute.repository.VeiculoRepository;

public class VeiculoController {

    private VeiculoRepository repository;

    public VeiculoController() {
        repository = new VeiculoRepository();
    }

    public void cadastrar(Veiculo veiculo) {
        repository.adicionar(veiculo);
    }

    public Veiculo procurarId(int id) {
        return repository.buscar(id);
    }

    public void atualizarVeiculo(int id, Veiculo novoVeiculo) {
        repository.atualizar(id, novoVeiculo);
    }

    public void remover(int id) {
        repository.remover(id); 
    }

    public void listar() {
        repository.listar();
    }
}