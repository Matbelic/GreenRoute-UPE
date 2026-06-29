package repository;
import model.Veiculo;

public class VeiculoRepository {
    private Veiculo[] veiculos = new Veiculo[10];
    private int quantidadeVeiculosCadastrados = 0;
    private int proximoId = 0;

    // Redimensionar array:
    private void redimensionarArray(){
        if (quantidadeVeiculosCadastrados == veiculos.length) {
            Veiculo[] novoArray =  new Veiculo[veiculos.length];

            for (int i = 0; i < quantidadeVeiculosCadastrados; i++) {
                novoArray[i] = veiculos[i];
            }
            veiculos = novoArray;
        }
    }

    // CRUD: Cadastro de Veículos:
    public void cadastrarVeiculo(Veiculo veiculo){
        redimensionarArray();
        veiculo.setId(proximoId++);
        veiculos[quantidadeVeiculosCadastrados] = veiculo;
        quantidadeVeiculosCadastrados++;
    }

    // CRUD: Buscar veiculo por ID:
    public  Veiculo buscarPorId(int id) {
        for (int i = 0; i < quantidadeVeiculosCadastrados; i++) {

            if (veiculos[i].getId() == id) {
                return veiculos [i];
            }
        }
        return null;
    }

    // CRUD: Listar todos os veículos:
    public Veiculo[] listarTodosVeiculos(){
        return veiculos;
    }

    // CRUD: Atualizar Veículo:
    public boolean atualizarVeiculo(int id, Veiculo veiculoAtualizado){
        for (int i = 0; i < quantidadeVeiculosCadastrados; i++){
            if (veiculos[i].getId() == id){
                veiculos[i] = veiculoAtualizado;
                return true;
            }
        }
        return false;
    }

    // CRUD: Apagar Veículo:
    public boolean apagarVeiculo(int id){
        for (int i = 0; i < quantidadeVeiculosCadastrados; i++){
            if (veiculos[i].getId() == id){
                for (int j = i; j < quantidadeVeiculosCadastrados - 1; j++){
                    veiculos[j] = veiculos[j + 1];
                }
                veiculos[quantidadeVeiculosCadastrados - 1] = null;
                quantidadeVeiculosCadastrados--;
                return true;
            }
        }
        return false;
    }
}
