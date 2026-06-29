package view;

import model.Eletroposto;
import model.Cidade;
import model.Veiculo;
import controller.CidadeController;
import controller.EletropostoController;
import controller.VeiculoController;
import model.VeiculoEletrico;
import model.VeiculoHibrido;

import java.util.Scanner;

public class Menu {
    private VeiculoController veiculoController;
    private EletropostoController eletropostoController;
    private CidadeController cidadeController;
    private Scanner sc = new Scanner(System.in);

    public Menu(VeiculoController veiculoController, EletropostoController eletropostoController, CidadeController cidadeController){
        this.veiculoController = veiculoController;
        this.eletropostoController = eletropostoController;
        this.cidadeController = cidadeController;
    }

    // Menu Veiculos:
    public void exibirMenuVeiculos(){
        int opcao;
        while(true){
            System.out.println("======== Menu de Veículos ========" + "\n");
            System.out.println("Escolha uma das opções abaixo: " + "\n");
            System.out.println("1 - Cadastrar um Novo Veículo");
            System.out.println("2 - Listar todos os Veículos Registrados");
            System.out.println("3 - Atualizar Dados de um Veículo Registrado");
            System.out.println("4 - Apagar um Veículo Registrado");
            System.out.println("0 - Voltar para o Menu Principal");
            opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao){
                case 1:
                    exibirMenuCadastrarVeiculos();
                    break;
                case 2:
                    exibirMenuListarVeiculos();
                    break;
                case 3:
                    exibirMenuAtualizarVeículos();
                    break;
                case 4:
                    exibirMenuApagarVeiculo();
                    break;
                case 0:
                    return;
            }
        }
    }
    public void exibirMenuCadastrarVeiculos(){
        int opcao;
        while (true){
            System.out.println("======== Cadastrar um Novo Veículo ========" + "\n");
            System.out.println("Qual será o tipo do seu veículo?" + "\n");
            System.out.println("1 - Elétrico");
            System.out.println("2 - Híbrido");
            System.out.println("0 - Retornar ao Menu Anterior");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao){
                case 1:
                    exibirMenuCadastrarVeiculoEletrico();
                    break;
                case 2:
                    exibirMenuCadastrarVeiculoHibrido();
                    break;
                case 0:
                    return;
            }
        }
    }

    public void exibirMenuCadastrarVeiculoEletrico(){
        System.out.println("======== Cadastrar Veículo Elétrico ========" + "\n");
        System.out.println("Qual o modelo do seu veículo? ");
        String modelo = sc.nextLine();

        System.out.println("Qual a autonomia máxima do seu Veículo?");
        double autonomiaMaxima = sc.nextDouble();
        sc.nextLine();

        System.out.println("Qual a carga atual da bateria do Veículo?");
        double cargaBateriaAtual = sc.nextDouble();
        sc.nextLine();

        System.out.println("Qual o consumo Kwh por Km do seu Veículo?");
        double consumoKwhPorKm = sc.nextDouble();
        sc.nextLine();

        System.out.println("Qual o tempo de recarga completa do seu Veículo? Em minutos.");
        int tempoRecargaCompleta = sc.nextInt();
        sc.nextLine();

        System.out.println("Qual o tipo de conector do seu Veículo?");
        String tipoConector = sc.nextLine();

        System.out.println("Qual o tempo de recarga rápida do seu Veículo? Em minutos.");
        int tempoRecargaRapida = sc.nextInt();
        sc.nextLine();

        VeiculoEletrico novoVeiculo = new VeiculoEletrico(0, modelo, autonomiaMaxima, cargaBateriaAtual, consumoKwhPorKm, tempoRecargaCompleta, tipoConector, tempoRecargaRapida);
        veiculoController.cadastrarVeiculoController(novoVeiculo);
        System.out.println("Seu veículo foi cadastrado com sucesso!");
    }

    public void exibirMenuCadastrarVeiculoHibrido(){
        System.out.println("======== Cadastrar Veículo Híbrido ========" + "\n");
        System.out.println("Qual o modelo do seu veículo? ");
        String modelo = sc.nextLine();

        System.out.println("Qual a autonomia máxima do seu Veículo?");
        double autonomiaMaxima = sc.nextDouble();

        System.out.println("Qual a carga atual da bateria do Veículo?");
        double cargaBateriaAtual = sc.nextDouble();

        System.out.println("Qual o consumo Kwh por Km do seu Veículo?");
        double consumoKwhPorKm = sc.nextDouble();

        System.out.println("Qual o tempo de recarga completa do seu Veículo? Em minutos.");
        int tempoRecargaCompleta = sc.nextInt();

        System.out.println("Qual o nivel de consumo de combustível do seu Veículo? Em km/l");
        double consumoCombustivel = sc.nextDouble();
        sc.nextLine();

        System.out.println("Qual o tipo de combustível a do seu Veículo?");
        String tipoCombustivel= sc.nextLine();

        System.out.println("Qual a capacidade do tanque de combustível do Veículo? Em litros");
        double capacidadeTanqueCombustivel = sc.nextDouble();
        sc.nextLine();

        VeiculoHibrido novoVeiculo = new VeiculoHibrido(0, modelo, autonomiaMaxima, cargaBateriaAtual, consumoKwhPorKm, tempoRecargaCompleta, capacidadeTanqueCombustivel, consumoCombustivel, tipoCombustivel);
        veiculoController.cadastrarVeiculoController(novoVeiculo);
        System.out.println("Seu veículo foi cadastrado com sucesso!");
    }

    public void exibirMenuListarVeiculos(){
        System.out.println("======== Lista de Veículos ========" + "\n");
        Veiculo[] veiculos = veiculoController.listarTodosVeiculosController();
        boolean encontrouVeiculo = false;
        for(Veiculo veiculo : veiculos){

            if (veiculo != null){
                encontrouVeiculo = true;

                System.out.println("ID do Veículo: " + veiculo.getId());
                System.out.println("Modelo: " + veiculo.getModelo());
                System.out.println("Autonomia Máxima: " + veiculo.getAutonomiaMaxima());
                System.out.println("Consumo Kwh Por Km: " + veiculo.getConsumoKwhPorKm());
                System.out.println("Tempo de Recarga Completa: " + veiculo.getTempoRecargaCompleta());

                if(veiculo instanceof VeiculoEletrico){
                    VeiculoEletrico eletrico = (VeiculoEletrico) veiculo;
                    System.out.println("Tipo de Conector: " + eletrico.getTipoConector());
                    System.out.println("Tempo de Recarga Rápida: " + eletrico.getTempoRecargaRapida());

                } else if (veiculo instanceof VeiculoHibrido){
                    VeiculoHibrido hibrido = (VeiculoHibrido) veiculo;
                    System.out.println("Capacidade do Tanque de Combustível: " + hibrido.getCapacidadeTanqueCombustivel());
                    System.out.println("Consumo de Combustível: " + hibrido.getConsumoCombustivel());
                    System.out.println("Tipo do Combustível: " + hibrido.getTipoCombustivel());
                }
            }
        } if(!encontrouVeiculo){
            System.out.println("Nenhum veículo foi registrado.");
        } return;
    }
    public void exibirMenuAtualizarVeículos(){
        System.out.println("======== Atualizando Dados ========" + "\n");
        System.out.println("Digite o ID do Veículo: ");
        int id = sc.nextInt();
        sc.nextLine();

        Veiculo veiculoExiste =  veiculoController.buscarVeiculoPorId(id);

        if (veiculoExiste == null) {
            System.out.println("Nenhum veículo encontrado. Verifique o ID.");
            return;
        }

        System.out.println("Qual será o tipo do seu veículo?" + "\n");
        System.out.println("1 - Elétrico");
        System.out.println("2 - Híbrido");
        int tipoDoVeiculo = sc.nextInt();
        sc.nextLine();

        System.out.println("Qual será o modelo");
        String modelo = sc.nextLine();

        System.out.println("Qual a Autonomia Máxima");
        double autonomiaMaxima = sc.nextDouble();

        System.out.println("Qual a Porcentagem da Bateria");
        double cargaBateriaAtual = sc.nextDouble();

        System.out.println("Qual o Consumo em Kwh por Km");
        double consumoKwhPorKm = sc.nextDouble();

        System.out.println("Quanto Tempo leva para uma Recarga Completa");
        int tempoRecargaBateria = sc.nextInt();
        sc.nextLine();

        if(tipoDoVeiculo == 1){
            System.out.println("Qual o tipo do conector? (Tipo 2, CCS2, CHAdeMO)");
            String tipoDoConector = sc.nextLine();

            System.out.println("Quanto tempo leva para realizar uma Recarga Rápida (Em minutos)? ");
            int tempoRecargaRapida = sc.nextInt();
            sc.nextLine();

            VeiculoEletrico veiculoAtualizado = new VeiculoEletrico(id, modelo, autonomiaMaxima, cargaBateriaAtual, consumoKwhPorKm, tempoRecargaBateria, tipoDoConector, tempoRecargaRapida);
            boolean sucesso = veiculoController.atualizarVeiculoController(id, veiculoAtualizado);
            System.out.println("Veículo cadastrado com sucesso!");

        } else if (tipoDoVeiculo == 2){
            System.out.println("Qual a capacidade total do tanque de combustível do Veículo (Em litros)? ");
            double capacidadeTanqueCombustivel = sc.nextDouble();

            System.out.println("Qual o consumo de combustível do veículo (Em km/l)? ");
            double consumoCombustível = sc.nextDouble();
            sc.nextLine();

            System.out.println("Qual o tipo de combustível do veículo?");
            String tipoCombustivel = sc.nextLine();

            VeiculoHibrido veiculoAtualizado = new VeiculoHibrido(id, modelo, autonomiaMaxima, cargaBateriaAtual, consumoKwhPorKm, tempoRecargaBateria, capacidadeTanqueCombustivel, consumoCombustível, tipoCombustivel);
            boolean sucesso = veiculoController.atualizarVeiculoController(id, veiculoAtualizado);
            System.out.println("Veículo cadastrado com sucesso!");
        }
    }

    public void exibirMenuApagarVeiculo() {
        System.out.println("======== Apagar Veículo ========" + "\n");
        System.out.println("Digite o id do veículo que você deseja apagar: ");
        int id = sc.nextInt();
        sc.nextLine();

        boolean apagarSucesso = veiculoController.apagarVeiculoController(id);
        if (apagarSucesso){
            System.out.println("O veículo foi apagado com sucesso!");
        } else {
            System.out.println("Erro! O veículo não foi apagado. (Tente verificar o id)");
        }
    }

    // CRUD: Menu Cidades:

    public void exibirMenuCidades(){
        int opcao;
        while(true){
            System.out.println("======== Menu de Cidades ========" + "\n");
            System.out.println("Escolha uma das opções abaixo: " + "\n");
            System.out.println("1 - Cadastrar uma Nova Cidade");
            System.out.println("2 - Listar todas as Cidades Registradas");
            System.out.println("3 - Atualizar uma Cidade Registrada");
            System.out.println("4 - Apagar uma Cidade Registrada");
            System.out.println("0 - Voltar para o Menu Principal");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao){
                case 1:
                    exibirMenuCadastrarCidade();
                    break;
                case 2:
                    exibirMenuListarCidades();
                    break;
                case 3:
                    exibirMenuAtualizarCidade();
                    break;
                case 4:
                    exibirMenuApagarCidade();
                    break;
                case 0:
                    return;
            }
        }
    }

    public void exibirMenuCadastrarCidade() {
        System.out.println("======== Cadastrar uma nova cidade ========");
        System.out.println("Nome da cidade: ");
        String nome = sc.nextLine();

        System.out.println("Estado (sigla, ex: PE): ");
        String estado = sc.nextLine();

        System.out.println("Distância da capital: ");
        double distanciaCapital = sc.nextDouble();
        sc.nextLine();

        Cidade novaCidade = new Cidade(0, nome, estado, distanciaCapital);
        cidadeController.cadastrarCidadeController(novaCidade);

        System.out.println("Cidade cadastrada com sucesso!");
    }

    public void exibirMenuListarCidades() {
        System.out.println("======== Lista de cidades ========");
        Cidade[] cidades = cidadeController.listarCidadesController();

        if (cidades.length == 0) {
            System.out.println("Nenhuma cidade cadastrada.");
        }

        for (Cidade cidade : cidades) {
            System.out.println("-----------------------------------");
            System.out.println("ID: " + cidade.getId());
            System.out.println("Nome: " + cidade.getNome());
            System.out.println("Estado: " + cidade.getEstado());
            System.out.println("Distância da capital: " + cidade.getDistanciaDaCapital());
        }
        System.out.println("-----------------------------------");
    }

    public void exibirMenuAtualizarCidade() {
        System.out.println("\n======== Atualizar Cidade ========");
        System.out.println("Digite o ID da cidade que deseja atualizar: ");
        int id = sc.nextInt();
        sc.nextLine();

        Cidade cidadeExiste =  cidadeController.buscarCidadePorIdController(id);

        if (cidadeExiste == null) {
            System.out.println("Nenhuma cidade encontrada. Verifique o ID.");
            return;
        }

        System.out.println("Digite o novo nome da cidade: ");
        String novoNomeCidade = sc.nextLine();

        System.out.println("Digite o novo estado da cidade(sigla): ");
        String novoEstadoCidade = sc.nextLine();

        System.out.println("Digite a nova distância da capital: ");
        double novaDistanciaCapital = sc.nextDouble();
        sc.nextLine();

        Cidade cidadeAtualizada = new Cidade(id, novoNomeCidade, novoEstadoCidade, novaDistanciaCapital);
        cidadeController.atualizarCidadeController(id, cidadeAtualizada);
        System.out.println("Cidade atualizada com sucesso!");
    }

    public void exibirMenuApagarCidade() {
        System.out.println("\n======== Apagar cidade ========");
        System.out.println("Digite o ID da cidade que deseja apagar:");
        int id = sc.nextInt();
        sc.nextLine();

        boolean sucesso = cidadeController.apagarCidadeController(id);
        if (sucesso) {
            System.out.println("\nCidade apagada com sucesso!");
        }else {
            System.out.println("\nNenhuma cidade encontrada. Verifique o ID.");
        }
    }

    // CRUD: Menu eletroposto:

    public void exibirMenuEletropostos(){
        int opcao;
        while(true){
            System.out.println("\n======== Menu de Eletropostos ========");
            System.out.println("\nEscolha uma das opções abaixo: " );
            System.out.println("1 - Cadastrar um Novo Eletroposto");
            System.out.println("2 - Listar todos os Eletropostos Registrados");
            System.out.println("3 - Atualizar um Eletroposto Registrado");
            System.out.println("4 - Apagar um Eletroposto Registrado");
            System.out.println("0 - Voltar para o Menu Principal");
            opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao){
                case 1:
                    exibirMenuCadastrarEletroposto();
                    break;
                case 2:
                    exibirMenuListarEletropostos();
                    break;
                case 3:
                    exibirMenuAtualizarEletroposto();
                    break;
                case 4:
                    exibirMenuApagarEletroposto();
                    break;
                case 0:
                    return;
            }

        }
    }

    public void exibirMenuCadastrarEletroposto () {
        System.out.println("\n======== Cadastrar eletroposto ========");
        System.out.println("\nDigite o nome do eletroposto: ");
        String nome = sc.nextLine();

        System.out.println("Digite a localização: ");
        String localizacao = sc.nextLine();

        System.out.println("Digite o ID da cidade onde está localizado: ");
        int cidadeId = sc.nextInt();
        sc.nextLine();

        if (cidadeController.buscarCidadePorIdController(cidadeId) == null) {
            System.out.println("Nenhuma cidade encontrada com o ID " + cidadeId + ". Coloque um ID válido." );
            return;
        }

        System.out.println("Digite os tipos de conectores disponíveis: ");
        String tiposConectores = sc.nextLine();

        System.out.println("Digite a potência de carga (em kW): ");
        double potenciaCarga = sc.nextDouble();

        System.out.println("Digite o preço por kWh (em R$): ");
        double precoKwh = sc.nextDouble();

        System.out.println("Digite a quantidade de vagas disponíveis: ");
        int vagasDisponiveis = sc.nextInt();
        sc.nextLine();

        Eletroposto novoEletroposto = new Eletroposto(0, nome, localizacao, cidadeId, tiposConectores, potenciaCarga, precoKwh, vagasDisponiveis);
        eletropostoController.cadastrarEletropostoController(novoEletroposto);
        System.out.println("Eletroposto cadastrado com sucesso!");
    }

    public void exibirMenuListarEletropostos () {
        System.out.println("======== Lista de Eletropostos ========");
        Eletroposto[] eletropostos = eletropostoController.listarEletropostoController();

        if (eletropostos.length == 0) {
            System.out.println("Nenhum eletroposto cadastrado.");
        }
        for (Eletroposto eletroposto : eletropostos  ) {
            System.out.println("\n-----------------------------------");
            System.out.println("ID: " + eletroposto.getId());
            System.out.println("Nome: " + eletroposto.getNome());
            System.out.println("Localização: " + eletroposto.getLocalizacao());
            System.out.println("ID da cidade: " + eletroposto.getCidadeId());
            System.out.println("Tipo de conectores: " + eletroposto.getTiposConectoresDisponiveis());
            System.out.println("Potência de carga: " + eletroposto.getPotenciaCargaKw() + "KW");
            System.out.println("Preço por Kwh: R$" + eletroposto.getPrecoPorKwh());
            System.out.println("Quantidade de vagas disponíveis: " + eletroposto.getVagasDisponiveis());

        }
        System.out.println("-----------------------------------");
    }

    public void exibirMenuAtualizarEletroposto() {
        System.out.println("\n======== Atualizar Eletroposto ========");
        System.out.println("\nDigite o ID do eletroposto que deseja atualizar: ");
        int id = sc.nextInt();
        sc.nextLine();

        Eletroposto eletropostoExiste = eletropostoController.buscarEletropostoPorIdController(id);

        if (eletropostoExiste == null) {
            System.out.println("Nenhum eletroposto encontrado. Verifique o ID.");
            return;
        }

        System.out.println("Novo nome: ");
        String novoNome = sc.nextLine();

        System.out.println("Nova localização: ");
        String novoLocalizacao = sc.nextLine();

        System.out.println("Novo ID da cidade: ");
        int novoCidadeId = sc.nextInt();
        sc.nextLine();

        System.out.println("Novos tipos de conectores: ");
        String novoTiposDeConectores = sc.nextLine();

        System.out.println("Nova potência de carga (kW): ");
        double novoPotenciaCarga = sc.nextDouble();

        System.out.println("Novo preço por kWh (R$): ");
        double novoPrecoPorKwh = sc.nextDouble();

        System.out.println("Novas vagas disponíveis: ");
        int novoVagasDisponiveis = sc.nextInt();
        sc.nextLine();

        Eletroposto eleropostoAtualizado = new Eletroposto(id, novoNome, novoLocalizacao, novoCidadeId, novoTiposDeConectores, novoPotenciaCarga, novoPrecoPorKwh, novoVagasDisponiveis);
        eletropostoController.atualizarEletropostoController(id, eleropostoAtualizado);
        System.out.println("Eletroposto atualizado com sucesso!");

    }

    public void exibirMenuApagarEletroposto() {
        System.out.println("\n======== AApasgar Eletroposto ========");
        System.out.println("\nDigite o ID do eletroposto que deseja apagar: ");
        int id = sc.nextInt();
        sc.nextLine();

        boolean sucesso = eletropostoController.apagarEletropostoController(id);
        if (sucesso) {
            System.out.println("Eletroposto apagado com sucesso!");
        }else {
            System.out.println("Nenhum eletroposto encontrado. Verifique o ID.");
        }
    }

    //CRUD: Menu Simular rota:
    public void exibirMenuSimularRota() {
        System.out.println("\n======== Simular Autonomia ========");
        System.out.println("Digite o ID do veículo: ");
        int veiculoId = sc.nextInt();

        if (veiculoController.buscarVeiculoPorId(veiculoId) == null) {
            System.out.println("Nenhuma cidade encontrada com o ID " + veiculoId + ". Verifique o ID.");
            return;
        }

        System.out.println("Digite o ID da cidade de destino: ");
        int cidadeId = sc.nextInt();
        sc.nextLine();

        if (cidadeController.buscarCidadePorIdController(cidadeId) == null) {
            System.out.println("Nenhuma cidade encontrada com o ID " + cidadeId + ". Verifique o ID." );
            return;
        }

        String resultado = veiculoController.simularRota(veiculoId, cidadeId);
        System.out.println(resultado);
    }


    //CRUD: Menu principal:

    public void exibirMenuPrincipal(){
        int opcao;
        while(true){
            System.out.println("======== Menu Principal GreenRoute ========" + "\n");
            System.out.println("Escolha uma das opções abaixo: " + "\n");
            System.out.println("1 - Gerenciar seu Veículo");
            System.out.println("2 - Gerenciar sua Cidade");
            System.out.println("3 - Gerenciar seu Eletroposto");
            System.out.println("4 - Teste de Autonomia");
            System.out.println("0 - Fechar o Programa");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao){
                case 1:
                    exibirMenuVeiculos();
                    break;

                case 2:
                    exibirMenuCidades();
                    break;

                case 3:
                    exibirMenuEletropostos();
                    break;

                case 4:
                    exibirMenuSimularRota();
                    break;

                case 0:
                    System.out.println("Encerrando o programa...");
                    System.exit(0);
            }
        }
    }
    
}
