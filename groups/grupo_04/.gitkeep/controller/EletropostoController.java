package controller;

import model.Eletroposto;
import repository.EletropostoRepository;

public class EletropostoController {

    private EletropostoRepository repository;

    // AGORA RECEBE O REPOSITÓRIO UNIFICADO
    public EletropostoController(EletropostoRepository repository) {
        this.repository = repository;
    }

    public boolean cadastrarEletroposto(Eletroposto eletroposto) {
        return repository.cadastrar(eletroposto);
    }

    public Eletroposto buscarEletroposto(int id) {
        return repository.buscarPorId(id);
    }

    public Eletroposto[] listarEletropostos() {
        return repository.listar();
    }

    public boolean atualizarEletroposto(int id, Eletroposto novoEletroposto) {
        return repository.atualizar(id, novoEletroposto);
    }

    public boolean excluirEletroposto(int id) {
        return repository.excluir(id);
    }

    public int quantidadeEletropostos() {
        return repository.quantidade();
    }
}
