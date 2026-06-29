package repository;
import model.Eletroposto;

public class EletropostoRepository {
    private Eletroposto[] eletropostos = new Eletroposto[10];
    private int quantidadeEletropostosCadastrados = 0;
    private int proximoId = 1;

    // Redimensionamento de Arrays:
    private void redimensionarArray() {
        if (quantidadeEletropostosCadastrados == eletropostos.length) {
            Eletroposto[] novoArray = new Eletroposto[eletropostos.length * 2];

            for (int i = 0; i < quantidadeEletropostosCadastrados; i++) {
                novoArray[i] = eletropostos[i];
            }
            eletropostos = novoArray;
        }
    }

    // CRUD: Cadastrar eletroposto:
    public void cadastrarEletroposto(Eletroposto eletroposto) {
        redimensionarArray();
        eletroposto.setId(proximoId++);
        eletropostos[quantidadeEletropostosCadastrados] = eletroposto;
        quantidadeEletropostosCadastrados++;
    }

    // CRUD: Procurar ID eletroposto:
    public Eletroposto buscarPorId(int id) {
        for (int i =0;  i < quantidadeEletropostosCadastrados; i++ ) {

            if (eletropostos[i].getId() == id) {
                return eletropostos[i];
            }
        }
        return null;
    }

    // CRUD: Buscar eletroposto por cidade:
    public Eletroposto[] buscarPorCidadeId(int cidadeId) {
        int cont = 0;

        for (int i = 0; i < quantidadeEletropostosCadastrados; i++) {

            if (eletropostos[i].getCidadeId() == cidadeId) {
                cont++;
            }
        }

        Eletroposto[] resultado = new Eletroposto[cont];
        int idx = 0;

        for (int i = 0; i < quantidadeEletropostosCadastrados; i++) {

            if ( eletropostos[i].getCidadeId() == cidadeId) {
                resultado[idx++] = eletropostos[i];
            }
        }
        return resultado;
    }

    // CRUD: Listar os eeletropostos:
    public Eletroposto[] listarEletropostos() {
        Eletroposto[] resultado = new Eletroposto[quantidadeEletropostosCadastrados];

        for (int i = 0; i < quantidadeEletropostosCadastrados; i++) {
            resultado[i] = eletropostos[i];
        }
        return resultado;
    }

    // CRUD: Atualizar eletroposto:
    public  boolean atualizarEletroposto(int id, Eletroposto eletropostoAtualizado) {

        for (int i = 0; i < quantidadeEletropostosCadastrados; i++) {

            if (eletropostos[i].getId() == id) {
                eletropostoAtualizado.setId(id);
                eletropostos[i] = eletropostoAtualizado;
                return true;
            }
        }
        return false;
    }

    // CRUD: Apagar eletroposto
    public boolean apagarEletroposto(int id) {

        for (int i = 0; i < quantidadeEletropostosCadastrados; i++) {

            if (eletropostos[i].getId() == id) {

                for ( int j = i; j < quantidadeEletropostosCadastrados - 1; j++) {
                    eletropostos[j] = eletropostos[ j + 1];
                }

                eletropostos[quantidadeEletropostosCadastrados - 1] = null;
                quantidadeEletropostosCadastrados--;
                return true;
            }
        }
        return false;
    }
    
}
