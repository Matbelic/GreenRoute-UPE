package repository;

import model.Veiculo;

public class VeiculoRepository {
    private Veiculo[] veiculos = new Veiculo[5];
    private int quantidadeAtual = 0;

    public void cadastrar(Veiculo veiculo){
        if (quantidadeAtual == veiculos.length){
            expandirArray();
        }
        veiculos[quantidadeAtual] = veiculo;
        quantidadeAtual++;
    }
    private void expandirArray(){
        Veiculo[] novoArray = new Veiculo[veiculos.length * 2];
        for (int i = 0; i < veiculos.length; i++){
            novoArray[i] = veiculos[i];
        }
        veiculos = novoArray;
    }

    public Veiculo buscarPorId(int id){
        for (int i = 0; i < quantidadeAtual; i++){
            if (veiculos[i].getId() == id){
                return veiculos[i];
            }
        }
        return null;
    }

    public Veiculo[] listarTodos(){
        Veiculo[] listaLimpa = new Veiculo[quantidadeAtual];
        for (int i = 0; i < quantidadeAtual; i++){
            listaLimpa[i] = veiculos[i];
        }
        return listaLimpa;
    }

    public boolean atualizar(Veiculo veiculoAtualizado) {
        for (int i = 0; i < quantidadeAtual; i++) {
            if (veiculos[i].getId() == veiculoAtualizado.getId()) {
                veiculos[i] = veiculoAtualizado;
                return true;
            }
        }
        return false;
    }

    public boolean excluir(int id){
        for (int i = 0; i <  quantidadeAtual; i++){
            if (veiculos[i].getId() == id){
                for (int j = i; j < quantidadeAtual - 1; j++){
                    veiculos[j] = veiculos[j + 1];
                }
                veiculos[quantidadeAtual - 1] = null;
                quantidadeAtual--;
                return true;
            }
        }
        return false;
    }
}
