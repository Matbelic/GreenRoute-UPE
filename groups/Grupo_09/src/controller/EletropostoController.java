package controller;

import model.Eletroposto;
import repository.EletropostoRepository;

public class EletropostoController {
    private EletropostoRepository eletropostoRepository;

    // Construtor
    public EletropostoController(EletropostoRepository eletropostoRepository) {this.eletropostoRepository = eletropostoRepository;
    }

    // CRUD: Cadastrar Eletroposto (Create)
    public void cadastrarEletropostoController(Eletroposto eletroposto) {eletropostoRepository.cadastrarEletroposto(eletroposto);
    }

    // CRUD: Listar todos os Eletropostos (Read)
    public Eletroposto[] listarEletropostoController() {
        return eletropostoRepository.listarEletropostos();
    }

    // CRUD: Buscar Eletropostos por Cidade (Read)
    public Eletroposto[] buscarEletropostosPorCidadeController(int cidadeId) {return eletropostoRepository.buscarPorCidadeId(cidadeId);
    }

    // CRUD: Busacar Eletroposto por ID:
    public Eletroposto buscarEletropostoPorIdController(int id) {return eletropostoRepository.buscarPorId(id);}

    // CRUD: Atualizar Eletroposto (Update)
    public boolean atualizarEletropostoController(int id, Eletroposto eletropostoAtualizado) {return eletropostoRepository.atualizarEletroposto(id, eletropostoAtualizado);
    }

    // CRUD: Apagar Eletroposto (Delete)
    public boolean apagarEletropostoController(int id) {
        return eletropostoRepository.apagarEletroposto(id);
    }
}
