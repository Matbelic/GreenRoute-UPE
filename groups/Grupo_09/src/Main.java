import controller.EletropostoController;
import controller.CidadeController;
import controller.VeiculoController;
import repository.CidadeRepository;
import repository.EletropostoRepository;
import repository.VeiculoRepository;
import view.Menu;

public class Main {
    public static void main(String [] args){

        VeiculoRepository veiculoRepository = new VeiculoRepository();
        CidadeRepository cidadeRepository = new CidadeRepository();
        EletropostoRepository eletropostoRepository = new EletropostoRepository();


        CidadeController cidadeController = new CidadeController(cidadeRepository);
        EletropostoController eletropostoController = new EletropostoController(eletropostoRepository);
        VeiculoController veiculoController = new VeiculoController(veiculoRepository, eletropostoController, cidadeController);

        Menu menu = new Menu(veiculoController, eletropostoController, cidadeController);
        menu.exibirMenuPrincipal();
    }
}