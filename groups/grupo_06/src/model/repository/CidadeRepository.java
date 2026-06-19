package model.repository;
import model.Cidade;
import java.util.Arrays;

public class CidadeRepository {
    //Inicial pequeno
    private Cidade[] cidades = new Cidade[2]; 
    private int qtd = 0;

    public void cadastrar(Cidade c) {
        if (qtd == cidades.length) {
            //Dobra o tamanho
            cidades = Arrays.copyOf(cidades, cidades.length * 2); 
        }
        cidades[qtd++] = c;
    }

    public Cidade[] listarTodos() {
        //Retorna apenas as posições preenchidas
        return Arrays.copyOf(cidades, qtd);
    }

    public Cidade buscarPorId(int id) {
        for (int i = 0; i < qtd; i++) {
            if (cidades[i].getId() == id) {
                return cidades[i];
            }
        }
        return null;
    }

    public boolean atualizar(int id, Cidade novaCidade) {
        boolean a = false;

        for (int i = 0; i < qtd; i++) {
            if (cidades[i].getId() == id) {
                novaCidade.setId(id);
                cidades[i] = novaCidade;
                a = true;
            }
        }
        return a;
    }

    public boolean excluir(int id) {
        boolean a = false;
        
        for (int i = 0; i < qtd; i++) {
            if (cidades[i].getId() == id) {
                //Desloca os elementos para preencher o buraco
                for (int j = i; j < qtd - 1; j++) {
                    cidades[j] = cidades[j + 1];
                }
                cidades[--qtd] = null;
                a = true;
            }
        }
        return a;
    }
}
