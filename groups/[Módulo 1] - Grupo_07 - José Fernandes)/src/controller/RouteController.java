package controller;

import model.Cidade;
import model.Eletroposto;
import model.Veiculo;
import model.VeiculoEletrico;
import model.VeiculoHibrido;
import repository.VeiculoRepository;
import repository.CidadeRepository;
import repository.EletropostoRepository;
import java.util.Scanner;

public class RouteController {

    private VeiculoRepository veiculoRepo;
    private CidadeRepository cidadeRepo;
    private EletropostoRepository eletropostoRepo;
    private Scanner scanner;

    public RouteController(VeiculoRepository veiculoRepo,
                           CidadeRepository cidadeRepo,
                           EletropostoRepository eletropostoRepo,
                           Scanner scanner) {
        this.veiculoRepo = veiculoRepo;
        this.cidadeRepo = cidadeRepo;
        this.eletropostoRepo = eletropostoRepo;
        this.scanner = scanner;
    }

    public void simularViagem() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║      SIMULAÇÃO DE VIAGEM             ║");
        System.out.println("╚══════════════════════════════════════╝");

        if (veiculoRepo.getTotalVeiculos() == 0) {
            System.out.println("✘ Nenhum veículo cadastrado. Cadastre um veículo primeiro.");
            return;
        }

        System.out.println("\nVeículos disponíveis:");
        Veiculo[] veiculos = veiculoRepo.listarTodos();
        for (Veiculo v : veiculos) {
            System.out.println("  " + v);
        }

        System.out.print("\nInforme o ID do veículo: ");
        int veiculoId = Integer.parseInt(scanner.nextLine());
        Veiculo veiculo = veiculoRepo.buscarPorId(veiculoId);

        if (veiculo == null) {
            System.out.println("✘ Veículo não encontrado.");
            return;
        }

        if (cidadeRepo.getTotalCidades() == 0) {
            System.out.println("✘ Nenhuma cidade cadastrada. Cadastre uma cidade primeiro.");
            return;
        }

        System.out.println("\nCidades disponíveis:");
        Cidade[] cidades = cidadeRepo.listarTodos();
        for (Cidade c : cidades) {
            System.out.println("  " + c);
        }

        System.out.print("\nInforme o ID da cidade de destino: ");
        int cidadeId = Integer.parseInt(scanner.nextLine());
        Cidade destino = cidadeRepo.buscarPorId(cidadeId);

        if (destino == null) {
            System.out.println("✘ Cidade não encontrada.");
            return;
        }

        System.out.println("\n══════════════════════════════════════");
        System.out.println("ANÁLISE DA VIAGEM");
        System.out.println("══════════════════════════════════════");
        System.out.println("Veículo  : " + veiculo.getModelo() + " (" + veiculo.getTipo() + ")");
        System.out.println("Destino  : " + destino.getNome() + " - " + destino.getEstado());

        double distancia = destino.getDistanciaDaCapital();
        double autonomiaAtual = veiculo.getAutonomiaAtual();

        System.out.printf("Distância: %.1f km%n", distancia);
        System.out.printf("Autonomia atual do veículo: %.1f km (bateria em %.1f%%)%n",
                autonomiaAtual, veiculo.getCargaBateriaAtual());

        if (veiculo instanceof VeiculoHibrido) {
            VeiculoHibrido vh = (VeiculoHibrido) veiculo;
            double autonomiaCombustao = vh.getAutonomiaMotorCombustao();
            double autonomiaTotal = autonomiaAtual + autonomiaCombustao;
            System.out.printf("Autonomia motor combustão (tanque cheio): %.1f km%n", autonomiaCombustao);
            System.out.printf("Autonomia total combinada: %.1f km%n", autonomiaTotal);

            if (autonomiaTotal >= distancia) {
                System.out.println("\n✔ VIAGEM VIÁVEL! O veículo híbrido consegue chegar ao destino.");
                System.out.printf("  Margem de segurança: %.1f km%n", autonomiaTotal - distancia);
            } else {
                System.out.println("\n✘ VIAGEM INVIÁVEL! Nem com motor a combustão é suficiente.");
                System.out.printf("  Faltam %.1f km de autonomia.%n", distancia - autonomiaTotal);
                sugerirEletropostos(destino, veiculo);
            }

        } else {
            if (autonomiaAtual >= distancia) {
                System.out.println("\n✔ VIAGEM VIÁVEL! O veículo elétrico chega ao destino.");
                System.out.printf("  Margem de segurança: %.1f km%n", autonomiaAtual - distancia);
            } else {
                System.out.println("\n✘ VIAGEM INVIÁVEL! Autonomia insuficiente para a distância.");
                System.out.printf("  Faltam %.1f km de autonomia.%n", distancia - autonomiaAtual);
                sugerirEletropostos(destino, veiculo);
            }
        }
    }

    private void sugerirEletropostos(Cidade cidade, Veiculo veiculo) {
        System.out.println("\n--- ELETROPOSTOS EM " + cidade.getNome().toUpperCase() + " ---");

        Eletroposto[] postos = eletropostoRepo.buscarPorCidadeId(cidade.getId());

        if (postos.length == 0) {
            System.out.println("⚠ Nenhum eletroposto cadastrado nesta cidade.");
            System.out.println("  Considere planejar uma parada em outra cidade com infraestrutura.");
            return;
        }

        System.out.println("Sugestões de recarga na cidade de destino:");
        boolean encontrouCompativel = false;

        for (Eletroposto posto : postos) {
            boolean compativel = true;
            String statusCompatibilidade = "";

            if (veiculo instanceof VeiculoEletrico) {
                VeiculoEletrico ve = (VeiculoEletrico) veiculo;
                compativel = ve.isCompativel(posto.getTiposConectoresDisponiveis());
                statusCompatibilidade = compativel ? " [✔ COMPATÍVEL]" : " [✘ CONECTOR INCOMPATÍVEL]";
                if (compativel) encontrouCompativel = true;
            } else {
                encontrouCompativel = true;
            }

            System.out.println("  " + posto + statusCompatibilidade);

            if (compativel && veiculo instanceof VeiculoEletrico) {
                VeiculoEletrico ve = (VeiculoEletrico) veiculo;
                double kwhNecessario = (100.0 - ve.getCargaBateriaAtual()) / 100.0 * ve.getAutonomiaMaxima() * ve.getConsumoKwhPorKm();
                double custoEstimado = kwhNecessario * posto.getPrecoPorKwh();
                System.out.printf("    → Custo estimado para carga completa: R$ %.2f (%.1f kWh)%n",
                        custoEstimado, kwhNecessario);
            }
        }

        if (!encontrouCompativel && veiculo instanceof VeiculoEletrico) {
            System.out.println("\n⚠ Nenhum eletroposto compatível com o conector do veículo nesta cidade!");
            System.out.println("  Conector do veículo: " + ((VeiculoEletrico) veiculo).getTipoConector());
        }
    }
}
