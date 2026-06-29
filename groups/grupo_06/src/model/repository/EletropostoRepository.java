package model.repository;
import model.Eletroposto;
import java.util.Arrays;

public class EletropostoRepository {
    // Inicial pequeno
    private Eletroposto[] eletropostos = new Eletroposto[2];
    private int qtd = 0;

    public void cadastrar(Eletroposto e) {
        if (qtd == eletropostos.length) {
            //Dobra o tamanho
            eletropostos = Arrays.copyOf(eletropostos, eletropostos.length * 2);    
        }
        eletropostos[qtd++] = e;
    }

    public Eletroposto[] listarTodos() {
        //Retorna apenas as posições preenchidas
        return Arrays.copyOf(eletropostos, qtd);
    }

    public Eletroposto buscarPorId(int id) {
        for (int i = 0; i < qtd; i++) {
            if (eletropostos[i].getId() == id) {
                return eletropostos[i];
            }
        }
        return null;
    }

    public boolean atualizar(int id, Eletroposto novoEletroposto) {
        boolean a = false;

        for (int i = 0; i < qtd; i++) {
            if (eletropostos[i].getId() == id) {
                novoEletroposto.setId(id);
                eletropostos[i] = novoEletroposto;
                a = true;
            }
        }
        return a;
    }

    public boolean excluir(int id) {
        boolean a = false;

        for (int i = 0; i < qtd; i++) {
            if (eletropostos[i].getId() == id) {
                //Desloca os elementos para preencher o buraco
                for (int j = i; j < qtd - 1; j++) {
                    eletropostos[j] = eletropostos[j + 1];
                }
                eletropostos[--qtd] = null;
                a = true;
            }
        }
        return a;
    }
}
