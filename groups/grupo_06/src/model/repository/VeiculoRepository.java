package model.repository;
import model.Veiculo;
import java.util.Arrays;

public class VeiculoRepository {
    // Inicial pequeno
    private Veiculo[] veiculos = new Veiculo[2];
    private int qtd = 0;

    public void cadastrar(Veiculo v) {
        if (qtd == veiculos.length) {
            //Dobra o tamanho
            veiculos = Arrays.copyOf(veiculos, veiculos.length * 2);    
        }
        veiculos[qtd++] = v;
    }

    public Veiculo[] listarTodos() {
        //Retorna apenas as posições preenchidas
        return Arrays.copyOf(veiculos, qtd);
    }

    public Veiculo buscarPorId(int id) {
        for (int i = 0; i < qtd; i++) {
            if (veiculos[i].getId() == id) {
                return veiculos[i];
            }
        }
        return null;
    }

    public boolean atualizar(int id, Veiculo novoVeiculo) {
        boolean a = false;

        for (int i = 0; i < qtd; i++) {
            if (veiculos[i].getId() == id) {
                novoVeiculo.setId(id);
                veiculos[i] = novoVeiculo;
                a = true;
            }
        }
        return a;
    }

    public boolean excluir(int id) {
        boolean a = false;

        for (int i = 0; i < qtd; i++) {
            if (veiculos[i].getId() == id) {
                //Desloca os elementos para preencher o buraco
                for (int j = i; j < qtd - 1; j++) {
                    veiculos[j] = veiculos[j + 1];
                }
                veiculos[--qtd] = null;
                a = true;
            }
        }
        return a;
    }
}
