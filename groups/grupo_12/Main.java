import controller.RotaControle;
import repository.CidadeRepository;
import repository.EletropostoRepository;
import repository.VeiculoRepository;
import view.SistemaView;

public class Main {

    public static void main(String[] args) {

        VeiculoRepository veiculoRepo = new VeiculoRepository();
        CidadeRepository cidadeRepo = new CidadeRepository();
        EletropostoRepository eletropostoRepo = new EletropostoRepository();

        RotaControle controller =
                new RotaControle(
                        veiculoRepo,
                        cidadeRepo,
                        eletropostoRepo
                );

        SistemaView view =
                new SistemaView(
                        controller,
                        veiculoRepo,
                        cidadeRepo,
                        eletropostoRepo
                );

        view.exibirMenuPrincipal();
    }
}