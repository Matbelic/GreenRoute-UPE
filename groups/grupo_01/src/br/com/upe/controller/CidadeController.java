package br.com.upe.controller;
import br.com.upe.model.Cidade;
import br.com.upe.repository.CidadeRepository;
import java.util.List;
public class CidadeController {

    private CidadeRepository repository;

    public CidadeController(CidadeRepository repository) {
        this.repository = repository;
    }

    public boolean cadastrarCidade(int id, String nome, String estado, double distanciaDaCapital) {
        if (repository.buscarCidade(id) != null) {
            System.out.println("\n[ERRO] Já existe uma cidade cadastrada com o ID: " + id);
            return false;
        }

        if (nome == null || nome.trim().isEmpty() || estado == null || estado.trim().isEmpty()) {
            System.out.println("\n[ERRO] O nome da cidade e o estado (UF) são obrigatórios!");
            return false;
        }

        if (distanciaDaCapital < 0) {
            System.out.println("\n[ERRO] A distância da capital não pode ser um valor negativo!");
            return false;
        }

        Cidade novaCidade = new Cidade(id, nome, estado, distanciaDaCapital);

        repository.cadastrarCidade(novaCidade);
        System.out.println("\n[SUCESSO] Cidade '" + nome + "' cadastrada com êxito!");
        return true;
    }

    public Cidade buscarCidade(int id) {
        Cidade cidade = repository.buscarCidade(id);
        if (cidade == null) {
            System.out.println("\n[AVISO] Nenhuma cidade encontrada com o ID: " + id);
        }
        return cidade;
    }

    public boolean atualizarCidade(int id, String nome, String estado, double distanciaDaCapital) {
        Cidade cidadeExistente = repository.buscarCidade(id);
        if (cidadeExistente == null) {
            System.out.println("\n[ERRO] Não é possível atualizar. Cidade com ID " + id + " não encontrada!");
            return false;
        }

        if (nome == null || nome.trim().isEmpty() || estado == null || estado.trim().isEmpty()) {
            System.out.println("\n[ERRO] Os novos dados de nome e estado não podem ser vazios!");
            return false;
        }
        if (distanciaDaCapital < 0) {
            System.out.println("\n[ERRO] A nova distância não pode ser negativa!");
            return false;
        }

        Cidade cidadeAtualizada = new Cidade(id, nome, estado, distanciaDaCapital);

        boolean atualizou = repository.atualizarCidade(id, cidadeAtualizada);
        if (atualizou) {
            System.out.println("\n[SUCESSO] Cidade com ID " + id + " atualizada com sucesso!");
        }
        return atualizou;
    }

    public boolean excluirCidade(int id) {
        boolean excluiu = repository.excluirCidade(id);

        if (excluiu) {
            System.out.println("\n[SUCESSO] Cidade com ID " + id + " excluída com sucesso do sistema!");
        } else {
            System.out.println("\n[ERRO] Falha ao excluir. Cidade com ID " + id + " não existe!");
        }
        return excluiu;
    }
}
