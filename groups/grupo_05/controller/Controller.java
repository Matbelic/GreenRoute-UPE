package controller;

import repository.Repository;
import model.*;

public class Controller {
    private Repository repository = new Repository();

    public void cadastrarVeiculo(Veiculo v) { repository.adicionarVeiculo(v); }
    public Veiculo[] listarVeiculos() { return repository.listarVeiculos(); }
    public Veiculo buscarVeiculo(int id) { return repository.buscarVeiculo(id); }
    public boolean atualizarVeiculo(int id, Veiculo v) { return repository.atualizarVeiculo(id, v); }
    public boolean excluirVeiculo(int id) { return repository.excluirVeiculo(id); }

    public void cadastrarEletroposto(Eletroposto e) { repository.adicionarEletroposto(e); }
    public Eletroposto[] listarEletropostos() { return repository.listarEletropostos(); }
    public Eletroposto buscarEletroposto(int id) { return repository.buscarEletroposto(id); }
    public boolean atualizarEletroposto(int id, Eletroposto e) { return repository.atualizarEletroposto(id, e); }
    public boolean excluirEletroposto(int id) { return repository.excluirEletroposto(id); }

    public void cadastrarCidade(Cidade c) { repository.adicionarCidade(c); }
    public Cidade[] listarCidades() { return repository.listarCidades(); }
    public Cidade buscarCidade(int id) { return repository.buscarCidade(id); }
    public boolean atualizarCidade(int id, Cidade c) { return repository.atualizarCidade(id, c); }
    public boolean excluirCidade(int id) { return repository.excluirCidade(id); }

    public void simularViagem(int veiculoId, int cidadeIdDestino) {
        Veiculo veiculo = repository.buscarVeiculo(veiculoId);
        Cidade cidade = repository.buscarCidade(cidadeIdDestino);

        if (veiculo == null || cidade == null) {
            System.out.println("\n[Erro] Veículo ou Cidade não encontrados para a simulação.");
            return;
        }

        double autonomiaAtual = veiculo.getAutonomiaMaxima() * (veiculo.getCargaBateriaAtual() / 100.0);
        double distanciaNecessaria = cidade.getDistanciaDaCapital();

        System.out.println("\n--- SIMULAÇÃO DE VIAGEM ---");
        System.out.println("Veículo: " + veiculo.getModelo() + " (Autonomia Atual: " + autonomiaAtual + " km)");
        System.out.println("Destino: " + cidade.getNome() + " (Distância da Capital: " + distanciaNecessaria + " km)");

        if (autonomiaAtual >= distanciaNecessaria) {
            System.out.println("Sucesso! O veículo possui carga suficiente para completar a viagem sem paradas.");
        } else {
            System.out.println("Atenção! A autonomia atual NÃO é suficiente.");
            System.out.println("Sugestão de Eletropostos na cidade de destino para reabastecimento:");
            
            Eletroposto[] todosPostos = repository.listarEletropostos();
            boolean encontrouPosto = false;

            for (Eletroposto posto : todosPostos) {
                if (posto.getCidadeId() == cidadeIdDestino) {
                    System.out.println(" -> " + posto.getNome() + " | Localização: " + posto.getLocalizacao() + " | Conectores: " + posto.getTiposConectoresDisponiveis());
                    encontrouPosto = true;
                }
            }

            if (!encontrouPosto) {
                System.out.println("Nenhum eletroposto cadastrado nesta cidade de destino.");
            }
        }
    }
}
