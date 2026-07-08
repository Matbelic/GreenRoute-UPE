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

public class RotaController {
    private VeiculoRepository veiculoRepo;
    private CidadeRepository cidadeRepo;
    private EletropostoRepository eletropostoRepo;

    public RotaController(VeiculoRepository veiculoRepo, CidadeRepository cidadeRepo, EletropostoRepository eletropostoRepo){
        this.veiculoRepo = veiculoRepo;
        this.cidadeRepo= cidadeRepo;
        this.eletropostoRepo = eletropostoRepo;
    }
    public void simularViagem(int veiculoId, int cidadeId) {
        Veiculo veiculo = veiculoRepo.buscarVeiculo(veiculoId);
        Cidade cidade = cidadeRepo.buscarCidade(cidadeId);

        if (veiculo == null) {
            System.out.println("\n[ERRO] Simulação abortada. Veículo não encontrado!");
            return;
        }
        if (cidade == null) {
            System.out.println("\n[ERRO] Simulação abortada. Cidade de destino não encontrada!");
            return;
        }

        double autonomiaAtual = veiculo.getAutonomiaMaxima() * (veiculo.getCargaBateriaAtual() / 100.0);
        double distanciaNecessaria = cidade.getDistanciaDaCapital();

        System.out.println("\n=============================================");
        System.out.println("        SIMULAÇÃO DE ROTA - GREENROUTE       ");
        System.out.println("=============================================");
        System.out.println("Veículo selecionado: " + veiculo.getModelo());
        System.out.println("Cidade de destino:   " + cidade.getNome() + " (" + cidade.getEstado() + ")");
        System.out.println("Distância total:     " + distanciaNecessaria + " km");
        System.out.printf("Autonomia atual:     %.2f km\n", autonomiaAtual);
        System.out.println("---------------------------------------------");

        if (veiculo.getCargaBateriaAtual() < distanciaNecessaria) {
            System.out.println("[ALERTA] Autonomia INSUFICIENTE para completar a viagem diretamente!");
            System.out.println("A procurar eletropostos sugeridos na cidade/rota para paragem...");

            boolean encontrouEletroposto = false;


            List<Eletroposto> todosEletropostos = eletropostoRepo.getEletropostos();


            for (Eletroposto ep : todosEletropostos) {
                if (ep.getCidadeId() == cidadeId) {
                    if (!encontrouEletroposto) {
                        System.out.println("\n[SUGESTÃO] Encontrámos o(s) seguinte(s) eletroposto(s) para recarga:");
                        encontrouEletroposto = true;
                    }
                    System.out.println("-> Nome: " + ep.getNome() + " | Localização: " + ep.getLocalizacao() +
                            " | Conectores: [" + ep.getTiposConectoresDisponiveis() + "]" +
                            " | Potência: " + ep.getPotenciaCargaKw() + "kW | Preço: R$" + ep.getPrecoPorKwh() + "/kWh");
                }
            }

            if (!encontrouEletroposto) {
                System.out.println("\n[AVISO] Nenhum eletroposto disponível cadastrado nesta cidade de destino.");
            }
        } else {
            System.out.println("\n[SUCESSO] O veículo possui autonomia suficiente (" + veiculo.getCargaBateriaAtual() + " km) para chegar ao destino diretamente!");
        }
        System.out.println("=============================================\n");
    }
}
