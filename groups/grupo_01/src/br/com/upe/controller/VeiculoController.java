package br.com.upe.controller;

import br.com.upe.model.Veiculo;
import br.com.upe.model.VeiculoEletrico;
import br.com.upe.model.VeiculoHibrido;
import br.com.upe.model.Cidade;
import br.com.upe.model.Eletroposto;
import br.com.upe.repository.VeiculoRepository;
import br.com.upe.repository.CidadeRepository;
import br.com.upe.repository.EletropostoRepository;
import java.util.List;

public class VeiculoController {
    private VeiculoRepository veiculoRepo;
    private CidadeRepository cidadeRepo;
    private EletropostoRepository eletropostoRepo;

    public VeiculoController(VeiculoRepository veiculoRepo, CidadeRepository cidadeRepo, EletropostoRepository eletropostoRepo) {
        this.veiculoRepo = veiculoRepo;
        this.cidadeRepo = cidadeRepo;
        this.eletropostoRepo = eletropostoRepo;
    }

    public boolean cadastrarVeiculoEletrico(int id, String modelo, double autonomiaMaxima, double cargaBateriaAtual,
                                            double consumoKwhPorKm, int tempoRecargaCompleta, String tipoConector, int tempoRecargaRapida) {
        if (veiculoRepo.buscarVeiculo(id) != null) {
            System.out.println("\n[ERRO] Já existe um veículo cadastrado com o ID: " + id);
            return false;
        }
        if (modelo == null || modelo.trim().isEmpty() || tipoConector == null || tipoConector.trim().isEmpty()) {
            System.out.println("\n[ERRO] O modelo e o tipo de conector são obrigatórios!");
            return false;
        }
        if (cargaBateriaAtual < 0 || cargaBateriaAtual > 100) {
            System.out.println("\n[ERRO] A carga da bateria deve estar entre 0.0% e 100.0%!");
            return false;
        }
        if (autonomiaMaxima <= 0 || consumoKwhPorKm <= 0 || tempoRecargaCompleta <= 0 || tempoRecargaRapida <= 0) {
            System.out.println("\n[ERRO] Valores numéricos inválidos para o veículo elétrico!");
            return false;
        }

        VeiculoEletrico novoEletrico = new VeiculoEletrico(id, modelo, autonomiaMaxima, cargaBateriaAtual,
                consumoKwhPorKm, tempoRecargaCompleta, tipoConector, tempoRecargaRapida);
        veiculoRepo.cadastrarVeiculo(novoEletrico);
        System.out.println("\n[SUCESSO] Veículo Elétrico '" + modelo + "' cadastrado com êxito!");
        return true;
    }

    public boolean cadastrarVeiculoHibrido(int id, String modelo, double autonomiaMaxima, double cargaBateriaAtual,
                                           double consumoKwhPorKm, int tempoRecargaCompleta, double capacidadeTanqueCombustivel,
                                           double consumoCombustivel, String tipoCombustivel) {
        if (veiculoRepo.buscarVeiculo(id) != null) {
            System.out.println("\n[ERRO] Já existe um veículo cadastrado com o ID: " + id);
            return false;
        }
        if (modelo == null || modelo.trim().isEmpty() || tipoCombustivel == null || tipoCombustivel.trim().isEmpty()) {
            System.out.println("\n[ERRO] O modelo e o tipo de combustível são obrigatórios!");
            return false;
        }
        if (cargaBateriaAtual < 0 || cargaBateriaAtual > 100) {
            System.out.println("\n[ERRO] A carga da bateria deve estar entre 0.0% e 100.0%!");
            return false;
        }
        if (autonomiaMaxima <= 0 || consumoKwhPorKm <= 0 || tempoRecargaCompleta <= 0 || capacidadeTanqueCombustivel <= 0 || consumoCombustivel <= 0) {
            System.out.println("\n[ERRO] Valores numéricos inválidos para o veículo híbrido!");
            return false;
        }

        VeiculoHibrido novoHibrido = new VeiculoHibrido(id, modelo, autonomiaMaxima, cargaBateriaAtual,
                consumoKwhPorKm, tempoRecargaCompleta, capacidadeTanqueCombustivel,
                consumoCombustivel, tipoCombustivel);
        veiculoRepo.cadastrarVeiculo(novoHibrido);
        System.out.println("\n[SUCESSO] Veículo Híbrido '" + modelo + "' cadastrado com êxito!");
        return true;
    }

    public Veiculo buscarVeiculo(int id) {
        Veiculo veiculo = veiculoRepo.buscarVeiculo(id);
        if (veiculo == null) {
            System.out.println("\n[AVISO] Nenhum veículo encontrado com o ID: " + id);
        }
        return veiculo;
    }

    public boolean atualizarVeiculoEletrico(int id, String modelo, double autonomiaMaxima, double cargaBateriaAtual,
                                            double consumoKwhPorKm, int tempoRecargaCompleta, String tipoConector, int tempoRecargaRapida) {
        if (veiculoRepo.buscarVeiculo(id) == null) {
            System.out.println("\n[ERRO] Não é possível atualizar. Veículo com ID " + id + " não encontrado!");
            return false;
        }
        // Reaproveita as validações de consistência
        if (cargaBateriaAtual < 0 || cargaBateriaAtual > 100 || autonomiaMaxima <= 0) return false;

        VeiculoEletrico eletricoAtualizado = new VeiculoEletrico(id, modelo, autonomiaMaxima, cargaBateriaAtual,
                consumoKwhPorKm, tempoRecargaCompleta, tipoConector, tempoRecargaRapida);
        return veiculoRepo.atualizarVeiculo(id, eletricoAtualizado);
    }

    public boolean atualizarVeiculoHibrido(int id, String modelo, double autonomiaMaxima, double cargaBateriaAtual,
                                           double consumoKwhPorKm, int tempoRecargaCompleta, double capacidadeTanqueCombustivel,
                                           double consumoCombustivel, String tipoCombustivel) {
        if (veiculoRepo.buscarVeiculo(id) == null) {
            System.out.println("\n[ERRO] Não é possível atualizar. Veículo com ID " + id + " não encontrado!");
            return false;
        }
        if (cargaBateriaAtual < 0 || cargaBateriaAtual > 100 || autonomiaMaxima <= 0) return false;

        VeiculoHibrido hibridoAtualizado = new VeiculoHibrido(id, modelo, autonomiaMaxima, cargaBateriaAtual,
                consumoKwhPorKm, tempoRecargaCompleta, capacidadeTanqueCombustivel,
                consumoCombustivel, tipoCombustivel);
        return veiculoRepo.atualizarVeiculo(id, hibridoAtualizado);
    }

    public boolean excluirVeiculo(int id) {
        boolean excluiu = veiculoRepo.excluirVeiculo(id);
        if (excluiu) {
            System.out.println("\n[SUCESSO] Veículo com ID " + id + " excluído com sucesso do sistema!");
        } else {
            System.out.println("\n[ERRO] Falha ao excluir. Veículo com ID " + id + " não existe!");
        }
        return excluiu;
    }
}
