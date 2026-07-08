package br.com.upe.repository;
import br.com.upe.model.Veiculo;
import java.util.ArrayList;
import java.util.List;

public class VeiculoRepository {
    private List<Veiculo> veiculos = new ArrayList<>();

    public void cadastrarVeiculo(Veiculo novoVeiculo) {
        veiculos.add(novoVeiculo);
    }

    public Veiculo buscarVeiculo(int id) {
        for (Veiculo v : veiculos) {
            if (v.getId() == id) {
                return v;
            }
        }
        return null;
    }

    public boolean atualizarVeiculo(int id, Veiculo veiculoAtualizado) {
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).getId() == id) {
                veiculos.set(i, veiculoAtualizado);
                return true;
            }
        }
        return false;
    }

    public boolean excluirVeiculo(int id) {
        return veiculos.removeIf(v -> v.getId() == id);
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }
}
