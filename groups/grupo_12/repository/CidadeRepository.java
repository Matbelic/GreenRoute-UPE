package repository;
import model.Cidade;
public class CidadeRepository {
    private Cidade[] cidades = new Cidade[5];
    private int quantidadeAtual = 0;

    public void cadastrar(Cidade cidade) {
        if (quantidadeAtual == cidades.length) {
            expandirArray();
        }
        cidades[quantidadeAtual] = cidade;
        quantidadeAtual++;
    }

    private void expandirArray() {
        Cidade[] novoArray = new Cidade[cidades.length * 2];
        for (int i = 0; i < cidades.length; i++) {
            novoArray[i] = cidades[i];
        }
        cidades = novoArray;
    }

    public Cidade buscarPorId(int id) {
        for (int i = 0; i < quantidadeAtual; i++) {
            if (cidades[i].getId() == id) {
                return cidades[i];
            }
        }
        return null;
    }

    public Cidade[] listarTodos() {
        Cidade[] listaLimpa = new Cidade[quantidadeAtual];
        for (int i = 0; i < quantidadeAtual; i++) {
            listaLimpa[i] = cidades[i];
        }
        return listaLimpa;
    }

    public boolean atualizar(Cidade cidadeAtualizada) {
        for (int i = 0; i < quantidadeAtual; i++) {
            if (cidades[i].getId() == cidadeAtualizada.getId()) {
                cidades[i] = cidadeAtualizada;
                return true;
            }
        }
        return false;
    }

    public boolean excluir(int id) {
        for (int i = 0; i < quantidadeAtual; i++) {
            if (cidades[i].getId() == id) {
                for (int j = i; j < quantidadeAtual - 1; j++) {
                    cidades[j] = cidades[j + 1];
                }
                cidades[quantidadeAtual - 1] = null;
                quantidadeAtual--;
                return true;
            }
        }
        return false;
    }
}
