package repository;
import model.Cidade;

public class CidadeRepository {
    private Cidade[] cidades = new Cidade[10];
    private int quantidadeCidadesCadastradas = 0;
    private int proximoId = 0 ;


    // Muda o tamanho do array caso ele fique cheio, cria um novo array com o dobro do tamanho e substitue o antigo
    private void redimensionarArray() {
        if (quantidadeCidadesCadastradas == cidades.length) {
            Cidade[] novoArray = new Cidade[cidades.length * 2];

            for (int i = 0; i < cidades.length; i++) {
                novoArray[i] = cidades[i];
            }
            cidades = novoArray;
        }
    }

    // CRUD: Cadastro de cidades:
    public void cadastrarCidade(Cidade cidade) {
        redimensionarArray();
        cidade.setId(proximoId++);
        cidades[quantidadeCidadesCadastradas] = cidade;
        quantidadeCidadesCadastradas++;
    }

    // CRUD: Procuriar ID da cidade:
    public Cidade buscarPorId(int id) {
        for(int i = 0; i < quantidadeCidadesCadastradas; i++) {

            if (cidades[i].getId() == id) {
                return cidades[i];
            }
        }
        return null;
    }

    // CRUD; Listar as cidades
    public Cidade[] listarCidades() {
        Cidade[] resultado = new Cidade[quantidadeCidadesCadastradas];

        for (int i = 0; i < quantidadeCidadesCadastradas; i++) {
            resultado[i] = cidades[i];
        }
        return resultado;
    }

    // CRUD: Atualizar cidades:
    public boolean atualizarCidade(int id, Cidade cidadeAtualizada) {
        for (int i = 0; i<quantidadeCidadesCadastradas; i++) {

            if (cidades[i].getId() == id) {
                cidadeAtualizada.setId(id);
                cidades[i] = cidadeAtualizada;

                return true;
            }
        }
        return false;
    }

    //CRUD: Apagar cidades:
    public boolean apagarCidade(int id) {
        for (int i = 0; i < quantidadeCidadesCadastradas; i++) {

            if (cidades[i].getId() ==id) {

                for ( int j = i; j < quantidadeCidadesCadastradas - 1; j++) {
                    cidades[i] = cidades[j + 1];

                }
                cidades[quantidadeCidadesCadastradas - 1] = null;
                quantidadeCidadesCadastradas--;
                return true;
            }
        }
        return false;
    }
}
