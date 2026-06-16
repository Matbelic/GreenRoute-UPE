package controller;

import model.Cidade;
import model.Eletroposto;
import model.Veiculo;
import repository.CidadeRepository;
import repository.EletropostoRepository;
import repository.VeiculoRepository;

public class SimulacaoController {

    private VeiculoRepository veiculoRepo;
    private CidadeRepository cidadeRepo;
    private EletropostoRepository eletropostoRepo;

    // Construtor recebendo os mesmos repositórios globais do sistema
    public SimulacaoController(VeiculoRepository veiculoRepo, 
                               CidadeRepository cidadeRepo, 
                               EletropostoRepository eletropostoRepo) {
        this.veiculoRepo = veiculoRepo;
        this.cidadeRepo = cidadeRepo;
        this.eletropostoRepo = eletropostoRepo;
    }

    public void rodarSimulacao(int veiculoId, int cidadeId) {
        Veiculo veiculo = veiculoRepo.buscarPorId(veiculoId);
        Cidade cidade = cidadeRepo.buscarPorId(cidadeId);

        if (veiculo == null || cidade == null) {
            System.out.println("\n[Erro] Veículo ou Cidade não cadastrados!");
            return;
        }

        double autonomia = veiculo.autonomiaAtual();
        double distancia = cidade.getDistanciaDaCapital();

        System.out.println("\n=== SIMULAÇÃO DE VIAGEM ===");
        System.out.println("Veículo: " + veiculo.getModelo() + " | Autonomia: " + String.format("%.2f", autonomia) + " km");
        System.out.println("Destino: " + cidade.getNome() + " | Distância: " + distancia + " km");

        if (autonomia >= distancia) {
            System.out.println("\n[SUCESSO] O veículo tem carga suficiente para cobrir a distância!");
        } else {
            System.out.println("\n[ALERTA] Autonomia insuficiente! Paradas para reabastecimento necessárias.");
            listarEletropostosDaCidade(cidade);
        }
    }

    // Método interno modificado para buscar direto do repositório de eletropostos
    private void listarEletropostosDaCidade(Cidade cidade) {
        boolean encontrou = false;
        System.out.println("Eletropostos disponíveis em " + cidade.getNome() + ":");

        for (Eletroposto e : eletropostoRepo.listar()) {
            if (e != null && e.getCidadeId() == cidade.getId()) {
                System.out.println(" -> ID " + e.getId() + ": " + e.getNome() + " (" + e.getLocalizacao() + ") - Vagas: " + e.getVagasDisponiveis());
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum eletroposto encontrado cadastrado para esta cidade.");
        }
    }
}
