package br.com.upe.controller;
import br.com.upe.model.Eletroposto;
import br.com.upe.repository.EletropostoRepository;
import br.com.upe.repository.CidadeRepository;
import java.util.List;
public class EletropostoController {
    private EletropostoRepository eletropostoRepo;
    private CidadeRepository cidadeRepo; // Permite validar se a cidade informada existe no sistema

    public EletropostoController(EletropostoRepository eletropostoRepo, CidadeRepository cidadeRepo) {
        this.eletropostoRepo = eletropostoRepo;
        this.cidadeRepo = cidadeRepo;
    }

    public boolean cadastrarEletroposto(int id, String nome, String localizacao, int cidadeId,
                                        String tiposConectoresDisponiveis, double potenciaCargaKw,
                                        double precoPorKwh, int vagasDisponiveis) {

        if (eletropostoRepo.buscarEletroposto(id) != null) {
            System.out.println("\n[ERRO] Já existe um eletroposto cadastrado com o ID: " + id);
            return false;
        }

        if (cidadeRepo.buscarCidade(cidadeId) == null) {
            System.out.println("\n[ERRO] Não é possível cadastrar. A cidade com o ID " + cidadeId + " não está cadastrada!");
            return false;
        }

        if (nome == null || nome.trim().isEmpty() ||
                localizacao == null || localizacao.trim().isEmpty() ||
                tiposConectoresDisponiveis == null || tiposConectoresDisponiveis.trim().isEmpty()) {
            System.out.println("\n[ERRO] Todos os campos de texto do eletroposto são obrigatórios!");
            return false;
        }

        if (potenciaCargaKw <= 0 || precoPorKwh < 0 || vagasDisponiveis < 0) {
            System.out.println("\n[ERRO] Valores de potência, preço ou vagas inválidos!");
            return false;
        }

        Eletroposto novoEletroposto = new Eletroposto(id, nome, localizacao, cidadeId,
                tiposConectoresDisponiveis, potenciaCargaKw,
                precoPorKwh, vagasDisponiveis);

        eletropostoRepo.cadastrarEletroposto(novoEletroposto);
        System.out.println("\n[SUCESSO] Eletroposto '" + nome + "' cadastrado com êxito!");
        return true;
    }

    public Eletroposto buscarEletroposto(int id) {
        Eletroposto eletroposto = eletropostoRepo.buscarEletroposto(id);
        if (eletroposto == null) {
            System.out.println("\n[AVISO] Nenhum eletroposto encontrado com o ID: " + id);
        }
        return eletroposto;
    }

    public boolean atualizarEletroposto(int id, String nome, String localizacao, int cidadeId,
                                        String tiposConectoresDisponiveis, double potenciaCargaKw,
                                        double precoPorKwh, int vagasDisponiveis) {

        if (eletropostoRepo.buscarEletroposto(id) == null) {
            System.out.println("\n[ERRO] Não é possível atualizar. Eletroposto ID " + id + " não encontrado!");
            return false;
        }

        if (cidadeRepo.buscarCidade(cidadeId) == null) {
            System.out.println("\n[ERRO] A nova cidade com ID " + cidadeId + " não existe!");
            return false;
        }

        if (nome == null || nome.trim().isEmpty() || localizacao == null || localizacao.trim().isEmpty()) {
            System.out.println("\n[ERRO] Nome e Localização não podem ficar em branco!");
            return false;
        }
        if (potenciaCargaKw <= 0 || precoPorKwh < 0 || vagasDisponiveis < 0) {
            System.out.println("\n[ERRO] Valores numéricos inválidos para atualização!");
            return false;
        }

        Eletroposto eletropostoAtualizado = new Eletroposto(id, nome, localizacao, cidadeId,
                tiposConectoresDisponiveis, potenciaCargaKw,
                precoPorKwh, vagasDisponiveis);

        boolean atualizou = eletropostoRepo.atualizarEletroposto(id, eletropostoAtualizado);
        if (atualizou) {
            System.out.println("\n[SUCESSO] Eletroposto com ID " + id + " atualizado com sucesso!");
        }
        return atualizou;
    }

    public boolean excluirEletroposto(int id) {
        boolean excluiu = eletropostoRepo.excluirEletroposto(id);
        if (excluiu) {
            System.out.println("\n[SUCESSO] Eletroposto com ID " + id + " excluído do sistema!");
        } else {
            System.out.println("\n[ERRO] Falha ao excluir. Eletroposto com ID " + id + " não existe!");
        }
        return excluiu;
    }
}
