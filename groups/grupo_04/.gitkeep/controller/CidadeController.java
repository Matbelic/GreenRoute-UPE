package controller;

import model.Cidade;
import repository.CidadeRepository;

public class CidadeController {

    private CidadeRepository repository;

    // AGORA RECEBE O REPOSITÓRIO UNIFICADO
    public CidadeController(CidadeRepository repository) {
        this.repository = repository;
    }

    public boolean cadastrarCidade(Cidade cidade) {
        return repository.cadastrar(cidade);
    }

    public Cidade buscarCidade(int id) {
        return repository.buscarPorId(id);
    }

    public Cidade[] listarCidades() {
        return repository.listar();
    }

    public boolean atualizarCidade(int id, Cidade novaCidade) {
        return repository.atualizar(id, novaCidade);
    }

    public boolean excluirCidade(int id) {
        return repository.excluir(id);
    }

    public int quantidadeCidades() {
        return repository.quantidade();
    }
}
