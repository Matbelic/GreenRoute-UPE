package controller;

import repository.GreenRouteRepository;
import model.*;

public class SistemaController {
    private GreenRouteRepository repo = new GreenRouteRepository();

    public GreenRouteRepository getRepo() {
        return repo;
    }

    public void simularViagem(int veiculoId, int cidadeId) {
        Veiculo v = repo.buscarVeiculoPorId(veiculoId);
        Cidade c = repo.buscarCidadePorId(cidadeId);

        if (v == null || c == null) {
            System.out.println("❌ Erro: Veículo ou Cidade não encontrados!");
            return;
        }

        double autonomiaAtual = v.getAutonomiaMaxima() * (v.getCargaBateriaAtual() / 100.0);
        double distanciaNecessaria = c.getDistanciaDaCapital();

        System.out.println("\n--- 📊 Relatório de Simulação ---");
        System.out.println("Veículo: " + v.getModelo() + " | Autonomia Atual Real: " + autonomiaAtual + " km");
        System.out.println("Cidade Destino: " + c.getNome() + " | Distância: " + distanciaNecessaria + " km");

        if (autonomiaAtual >= distanciaNecessaria) {
            System.out.println("✅ Viagem Segura! O veículo tem carga suficiente para chegar ao destino.");
        } else {
            System.out.println("⚠️ Alerta: Carga INSUFICIENTE para completar a viagem diretamente!");
            System.out.println("Procurando pontos de recarga disponíveis na cidade de destino...");
            
            Eletroposto[] todosPostos = repo.listarEletropostos();
            boolean encontrouPosto = false;

            for (Eletroposto p : todosPostos) {
                if (p.getCidadeId() == cidadeId) {
                    System.out.println("   -> [Eletroposto Recomendado]: " + p.getNome() + " | Local: " + p.getLocalizacao() + " | Vagas: " + p.getVagasDisponiveis());
                    encontrouPosto = true;
                }
            }

            if (!encontrouPosto) {
                System.out.println("❌ Nenhum eletroposto foi cadastrado na cidade de destino ainda.");
            }
        }
    }
}