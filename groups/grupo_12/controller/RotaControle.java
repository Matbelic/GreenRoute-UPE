package controller;

import model.Cidade;
import model.Eletroposto;
import model.Veiculo;
import repository.CidadeRepository;
import repository.EletropostoRepository;
import repository.VeiculoRepository;

public class RotaControle {

    private VeiculoRepository veiculoRepo;
    private CidadeRepository cidadeRepo;
    private EletropostoRepository eletropostoRepo;

    public RotaControle(VeiculoRepository vRepo, CidadeRepository cRepo, EletropostoRepository eRepo){
        this.veiculoRepo = vRepo;
        this.cidadeRepo = cRepo;
        this.eletropostoRepo = eRepo;
    }

    public void simularViagem(int veiculoId, int cidadeId){
        Veiculo veiculo = veiculoRepo.buscarPorId(veiculoId);
        Cidade destino = cidadeRepo.buscarPorId(cidadeId);

        if(veiculo == null || destino == null){
            System.out.println("ERRO: Cidade ou Veiculo não encontrados.");
            return;
        }
        double autonomiaAtual = veiculo.getAutonomiaMaxima() * (veiculo.getCargaBateriaAtual()/100.0);

        System.out.println("\n--- Resumo da Simulação ---");
        System.out.println("Veículo " + veiculo.getModelo() + " | Autonomia Atual: " + autonomiaAtual + " km");
        System.out.println("Destino: " + destino.getNome() + " | Distância: " + destino.getDistanciaDaCapital() + " km");

        if(autonomiaAtual >= destino.getDistanciaDaCapital()){
            System.out.println("Status: Viagem possível! A autonomia é suficiente para percorrer a distância");
        } else {
            System.out.println("Status: ATENÇÃO! Autonomia não suficiente.");
            System.out.println("Sugerindo Eletropostos na região para recarga:");
            sugerirEletropostos(cidadeId);
        }
        System.out.println("-------------------\n");
    }

    private void sugerirEletropostos(int cidadeId){
        Eletroposto[] todosEletropostos = eletropostoRepo.listarTodos();
        boolean encontrou = false;

        for(int i =0; i < todosEletropostos.length; i++){
            if(todosEletropostos[i] != null && todosEletropostos[i].getCidadeId() == cidadeId){
                System.out.println("- " + todosEletropostos[i].getNome() +
                        " | local: " + todosEletropostos[i].getLocalizacao() +
                        " | Conectores: " + todosEletropostos[i].getTiposConectoresDisponiveis());
                encontrou = true;
            }
        }

        if(!encontrou) {
            System.out.println("Nenhum eletroposto cadastrado nesta cidade ainda.");
        }
    }
}