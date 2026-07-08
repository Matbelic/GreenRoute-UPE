package br.com.upe.repository;
import br.com.upe.model.Cidade;
import java.util.ArrayList;
import java.util.List;

public class CidadeRepository {
    private List<Cidade> cidades = new ArrayList<>();
    public void cadastrarCidade(Cidade novaCidade) {
        cidades.add(novaCidade);
    }

    public Cidade buscarCidade(int id) {
        for (Cidade c : cidades) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public boolean atualizarCidade(int id, Cidade cidadeAtualizada) {
        for (int i = 0; i < cidades.size(); i++) {
            if (cidades.get(i).getId() == id) {
                cidades.set(i, cidadeAtualizada);
                return true;
            }
        }
        return false;

    }

    public boolean excluirCidade(int id) {
        return cidades.removeIf(c -> c.getId() == id);
    }

    public List<Cidade> getCidades() {
        return cidades;
    }
}
