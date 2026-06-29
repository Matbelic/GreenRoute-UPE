package controller;

import model.Eletroposto;
import model.Veiculo;
import model.Cidade;
import repository.VeiculoRepository;

public class VeiculoController {
    private VeiculoRepository veiculoRepository;
    private EletropostoController eletropostoController;
    private CidadeController cidadeController;

    // Construtor
    public VeiculoController(VeiculoRepository veiculoRepository, EletropostoController eletropostoController, CidadeController cidadeController) {
        this.veiculoRepository = veiculoRepository;
        this.eletropostoController = eletropostoController;
        this.cidadeController = cidadeController;
    }

    // CRUD: Cadastrar Veículo (Create)
    public void cadastrarVeiculoController(Veiculo veiculo) {
        veiculoRepository.cadastrarVeiculo(veiculo);
    }

    // CRUD: Listar todos os Veículos (Read)
    public Veiculo[] listarTodosVeiculosController() {
        return veiculoRepository.listarTodosVeiculos();
    }

    // CRUD: Buscar Veículo por ID (Read)
    public Veiculo buscarVeiculoPorId(int id) {
        return veiculoRepository.buscarPorId(id);
    }

    // CRUD: Atualizar Veículo (Update)
    public boolean atualizarVeiculoController(int id, Veiculo veiculoAtualizado) {return veiculoRepository.atualizarVeiculo(id, veiculoAtualizado);}

    // CRUD: Apagar Veículo (Delete)
    public boolean apagarVeiculoController(int id) {
        return veiculoRepository.apagarVeiculo(id);
    }

    // CRUD: Simular rota:
    public String simularRota(int veiculoId, int cidadeDestinoId) {

        Veiculo veiculo = veiculoRepository.buscarPorId(veiculoId);
        Cidade destino = cidadeController.buscarCidadePorIdController(cidadeDestinoId);

        double autonomiaAtual = veiculo.getAutonomiaMaxima() * (veiculo.getCargaBateriaAtual() / 100.0);
        double distanciaDestino = destino.getDistanciaDaCapital();

        StringBuilder resultado = new StringBuilder();
        resultado.append("\n======== Resultado da Simulação de Rota ========\n");
        resultado.append("Veículo: ").append(veiculo.getModelo()).append("\n");
        resultado.append("Bateria atual: ").append(veiculo.getCargaBateriaAtual()).append("%\n");
        resultado.append("Autonomia atual: ").append(String.format("%.1f", autonomiaAtual)).append(" km\n");
        resultado.append("Destino: ").append(destino.getNome()).append(" - ").append(destino.getEstado()).append("\n");
        resultado.append("Distância: ").append(distanciaDestino).append(" km\n");
        resultado.append("================================================\n");


        if (autonomiaAtual >= distanciaDestino) {

            double kmSobrando = autonomiaAtual - distanciaDestino;
            resultado.append("Autonomia suficiente para chegar ao destino!\n");
            resultado.append("Sobram aproximadamente ").append(String.format("%.1f", kmSobrando)).append(" km de autonomia.\n");

        } else {
            double kmFaltando = distanciaDestino - autonomiaAtual;
            resultado.append("Autonomia insuficiente. Faltam ").append(String.format("%.1f", kmFaltando)).append(" km.\n");
            resultado.append("\nEletropostos disponíveis em ").append(destino.getNome()).append(" para reabastecimento:\n");

            Eletroposto[] eletropostos = eletropostoController.buscarEletropostosPorCidadeController(cidadeDestinoId);

            if (eletropostos.length == 0) {
                resultado.append("Nenhum eletroposto cadastrado nesta cidade.\n");
                resultado.append("Sugestão: recarregue o veículo antes de partir.\n");

            } else {
                for (Eletroposto e : eletropostos) {
                    resultado.append("\n------------------------------------------\n");
                    resultado.append("Nome: ").append(e.getNome()).append("\n");
                    resultado.append("Localização: ").append(e.getLocalizacao()).append("\n");
                    resultado.append("Conectores: ").append(e.getTiposConectoresDisponiveis()).append("\n");
                    resultado.append("Potência: ").append(e.getPotenciaCargaKw()).append(" kW\n");
                    resultado.append("Preço: R$ ").append(e.getPrecoPorKwh()).append("/kWh\n");
                    resultado.append("Vagas: ").append(e.getVagasDisponiveis()).append("\n");
                }
                resultado.append("------------------------------------------\n");
            }
        }

        return resultado.toString();
    }
}
