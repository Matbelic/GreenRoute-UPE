package controller;

import model.Veiculo;
import repository.VeiculoRepository;

public class VeiculoController {

    private VeiculoRepository repository;

    // AGORA RECEBE O REPOSITÓRIO UNIFICADO
    public VeiculoController(VeiculoRepository repository) {
        this.repository = repository;
    }

    public boolean cadastrarVeiculo(Veiculo veiculo) {
        return repository.cadastrar(veiculo);
    }

    public Veiculo buscarVeiculo(int id) {
        return repository.buscarPorId(id);
    }

    public Veiculo[] listarVeiculos() {
        return repository.listar();
    }

    public boolean atualizarVeiculo(int id, Veiculo novoVeiculo) {
        return repository.atualizar(id, novoVeiculo);
    }

    public boolean excluirVeiculo(int id) {
        return repository.excluir(id);
    }

    public int quantidadeVeiculos() {
        return repository.quantidade();
    }
}
