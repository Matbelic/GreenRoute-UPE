package repository;
import model.Eletroposto;

public class EletropostoRepository {

    private Eletroposto[] eletropostos;
    private int totalEletropostos;
    private static final int TAMANHO_INICIAL = 5;

    public EletropostoRepository() {
        eletropostos = new Eletroposto[TAMANHO_INICIAL];
        totalEletropostos = 0;
    }

    public boolean idExiste(int id) {
        for (int i = 0; i < totalEletropostos; i++) {
            if (eletropostos[i].getId() == id) return true;
        }
        return false;
    }

    private void expandirArray() {
        int novoTamanho = eletropostos.length * 2;
        Eletroposto[] novoArray = new Eletroposto[novoTamanho];
        for (int i = 0; i < totalEletropostos; i++) {
            novoArray[i] = eletropostos[i];
        }
        eletropostos = novoArray;
        System.out.println("[Sistema] Array de eletropostos expandido para " + novoTamanho + " posições.");
    }

    public boolean adicionar(Eletroposto e) {
        if (idExiste(e.getId())) return false;
        if (totalEletropostos == eletropostos.length) expandirArray();
        eletropostos[totalEletropostos] = e;
        totalEletropostos++;
        return true;
    }

    public Eletroposto buscarPorId(int id) {
        for (int i = 0; i < totalEletropostos; i++) {
            if (eletropostos[i].getId() == id) return eletropostos[i];
        }
        return null;
    }

    public Eletroposto[] listarTodos() {
        Eletroposto[] resultado = new Eletroposto[totalEletropostos];
        for (int i = 0; i < totalEletropostos; i++) resultado[i] = eletropostos[i];
        return resultado;
    }

    public Eletroposto[] buscarPorCidadeId(int cidadeId) {
        int count = 0;
        for (int i = 0; i < totalEletropostos; i++) {
            if (eletropostos[i].getCidadeId() == cidadeId) count++;
        }
        Eletroposto[] resultado = new Eletroposto[count];
        int idx = 0;
        for (int i = 0; i < totalEletropostos; i++) {
            if (eletropostos[i].getCidadeId() == cidadeId) {
                resultado[idx++] = eletropostos[i];
            }
        }
        return resultado;
    }

    public boolean atualizar(Eletroposto atualizado) {
        for (int i = 0; i < totalEletropostos; i++) {
            if (eletropostos[i].getId() == atualizado.getId()) {
                eletropostos[i] = atualizado;
                return true;
            }
        }
        return false;
    }

    public boolean remover(int id) {
        for (int i = 0; i < totalEletropostos; i++) {
            if (eletropostos[i].getId() == id) {
                for (int j = i; j < totalEletropostos - 1; j++) {
                    eletropostos[j] = eletropostos[j + 1];
                }
                eletropostos[totalEletropostos - 1] = null;
                totalEletropostos--;
                return true;
            }
        }
        return false;
    }

    public int getTotalEletropostos() { return totalEletropostos; }
}
