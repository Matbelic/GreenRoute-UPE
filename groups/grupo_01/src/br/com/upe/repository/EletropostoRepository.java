package br.com.upe.repository;
import br.com.upe.model.Eletroposto;
import java.util.ArrayList;
import java.util.List;

public class EletropostoRepository {
    private List<Eletroposto> eletropostos = new ArrayList<>();

    public void cadastrarEletroposto(Eletroposto novoEletroposto) {
        eletropostos.add(novoEletroposto);
    }

    public Eletroposto buscarEletroposto(int id) {
        for (Eletroposto ep : eletropostos) {
            if (ep.getId() == id) {
                return ep;
            }
        }
        return null;
    }

    public boolean atualizarEletroposto(int id, Eletroposto eletropostoAtualizado) {
        for (int i = 0; i < eletropostos.size(); i++) {
            if (eletropostos.get(i).getId() == id) {
                eletropostos.set(i, eletropostoAtualizado);
                return true;
            }
        }
        return false;
    }

    public boolean excluirEletroposto(int id) {
        return eletropostos.removeIf(ep -> ep.getId() == id);
    }

    public List<Eletroposto> getEletropostos() {
        return eletropostos;
    }
}
