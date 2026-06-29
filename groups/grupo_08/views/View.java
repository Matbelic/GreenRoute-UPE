package views;

import Model.Cidade;
import Model.Eletroposto;
import Model.Veiculo;
import Model.VeiculoEletrico;
import Model.VeiculoHibrido;
import controller.*;
import java.util.Scanner;

public class View {
    public void exibirMenus(){
        String skip;
        int opcao =-1, opcao2, opcao3;
        int idProcurado;
        VeiculoController car = new VeiculoController();
        CidadeController city = new CidadeController();
        EletropostoController post = new EletropostoController();
        Scanner scanner = new Scanner(System.in);
        while (opcao != 0){
            System.out.print("=====================!!!!Seja Bem-Vindo ao Green Route!!!!=====================\n\nDigite o numero da sua escolha\n \n1-Calcular Rota\n2-Editar Itens\n \n0-Sair\n=====================================================\n: ");
            opcao = Integer.parseInt(scanner.nextLine());
            if(opcao == 0){break;}
            if (opcao == 1){
                System.out.println("Cidades disponíveis:");
                if("Não tem nenhuma cidade cadastrada.".equals(city.ListCity())){
                    System.out.println("Não tem nenhuma cidade cadastrada.");
                    continue;
                }
                System.out.print("ID da cidade de origem ou 0 para sair: ");
                int idOrigem = Integer.parseInt(scanner.nextLine());
                System.out.print("ID da cidade de destino ou 0 para sair: ");
                int idDestino = Integer.parseInt(scanner.nextLine());
                if (idOrigem == idDestino){
                    System.out.print("Digite IDs Diferentes");
                    continue;
                }
                double distanciaViagem = city.Calculatedist(idOrigem, idDestino);
                System.out.println("\n===Veiculos===");
                car.ListCar();
                System.out.print("Escolha o ID do veículo ou 0 para sair: : ");
                int idVeiculo = Integer.parseInt(scanner.nextLine());
                if (idVeiculo == 0) continue;
                if ("  ".equals(car.SearchCars(idVeiculo))) {
                    Veiculo veiculoEncontrado = null;
                    for (int i = 0; i < car.getTotalVeiculos(); i++) {
                        Veiculo veiculo = car.getVeiculos(i);
                        if (veiculo != null && veiculo.getId() == idVeiculo && veiculo instanceof VeiculoEletrico) {
                            veiculoEncontrado = veiculo;
                            break;
                        }
                    }
                    if (veiculoEncontrado == null) {
                        System.out.println("Veículo elétrico não encontrado ou ID inválido!\n");
                        continue; 
                    }
                    double autonomiaMaxima = veiculoEncontrado.getAutonomiaMaxima();
                    double cargaBateriaAtual = veiculoEncontrado.getCargaBateriaAtual();
                    double resultado = autonomiaMaxima * (cargaBateriaAtual / 100.0);
                    if (resultado == 0.0) {continue;}
                    if (resultado >= distanciaViagem) {
                        System.out.println("Viagem possível!");
                        System.out.println("Distância: " + distanciaViagem + " km");
                        System.out.print("Digite 0 para Voltar a menu\n: ");
                        skip = scanner.nextLine();
                    } else if (cargaBateriaAtual != 100.0 && post.getTotalpost() != 0) { 
                        System.out.println("Não é possível fazer essa viagem.");
                        System.out.println("Postos disponíveis na sua cidade de partida");
                        if(post.SearchpostCity(idOrigem)){
                            System.out.print("Escolha o ID do Posto: ");
                            int IdPosto = Integer.parseInt(scanner.nextLine());
                            Eletroposto postoEncontrado = null;
                            for (int i = 0; i < post.getTotalpost(); i++) {
                                Eletroposto p = post.getPostos(i);
                                if (p != null && p.getId() == IdPosto) {
                                    postoEncontrado = p;
                                    break;
                                }
                            }
                            if (postoEncontrado != null) {
                                double preco = postoEncontrado.getPrecoPorKwh(); 
                                double vagas = postoEncontrado.getVagasDisponiveis();
                                if (vagas >0){
                                    System.out.println("O preço por kWh neste posto é: R$ " + preco);
                                    double valor = ((cargaBateriaAtual*(-1))+100)*preco;
                                    System.out.print("Valor a ser pago: "+valor +" para completar a bateria \n\nDigite sua Escolha\n1-Recarregar baterria\n2-Nao Recarregar\n\n: ");
                                    int escolha = Integer.parseInt(scanner.nextLine());
                                    if (escolha == 1){
                                        veiculoEncontrado.setCargaBateriaAtual(100);
                                        System.out.println("Reabastecido!!!");
                                        postoEncontrado.setVagasDisponiveis((int)vagas - 1);
                                    }
                                }else{
                                    System.out.println("Nao tem vagas disponiveis!!!");
                                }
                            }
                        }else {
                            System.out.println("Posto não encontrado.\n");
                        }
                    } else {
                        System.out.println("Não é possível fazer essa viagem.");
                        System.out.println("Distância necessária: " + distanciaViagem + " km");
                        System.out.println("Autonomia do veículo: " + resultado + " km");
                    }
                }else if (" ".equals(car.SearchCars(idVeiculo))){
                    Veiculo veiculoEncontrado = null;
                    for (int i = 0; i < car.getTotalVeiculos(); i++) {
                        Veiculo veiculo = car.getVeiculos(i);
                        if (veiculo != null && veiculo.getId() == idVeiculo && veiculo instanceof VeiculoHibrido) {
                            veiculoEncontrado = veiculo;
                            break;
                        }
                    }
                    if (veiculoEncontrado == null) {
                        System.out.println("Veículo Hibrido não encontrado ou ID inválido!\n");
                        continue; 
                    }
                    VeiculoHibrido hibrido = (VeiculoHibrido) veiculoEncontrado;
                    double autonomiaEletricoMaxima = hibrido.getAutonomiaMaxima();
                    double cargaBateriaAtual = hibrido.getCargaBateriaAtual();
                    double autonomiaEletricoReal = autonomiaEletricoMaxima * (cargaBateriaAtual / 100.0);
                    double autonomiaCombustao = hibrido.calcularAutonomiaTotal() - autonomiaEletricoMaxima;
                    double resultado = autonomiaEletricoReal + autonomiaCombustao;
                    if (resultado == 0.0) {
                        System.out.println("Veículo totalmente sem combustível ou carga!\n");
                        continue;
                    }
                    if (resultado >= distanciaViagem) {
                        System.out.println("Viagem possível!");
                        System.out.println("Distância: " + distanciaViagem + " km");
                        System.out.print("Digite 0 para Voltar a menu\n: ");
                        skip = scanner.nextLine();
                    } else if (cargaBateriaAtual != 100.0 && post.getTotalpost() != 0) {
                        System.out.println("Não é possível fazer essa viagem.");
                        System.out.println("Postos disponíveis na sua cidade de partida");
                        if(post.SearchpostCity(idOrigem)){
                            System.out.print("Escolha o ID do Posto: ");
                            int IdPosto = Integer.parseInt(scanner.nextLine());
                            Eletroposto postoEncontrado = null;
                            for (int i = 0; i < post.getTotalpost(); i++) {
                                Eletroposto p = post.getPostos(i);
                                if (p != null && p.getId() == IdPosto) {
                                    postoEncontrado = p;
                                    break;
                                }
                            }
                            if (postoEncontrado != null) {
                                double preco = postoEncontrado.getPrecoPorKwh(); 
                                double vagas = postoEncontrado.getVagasDisponiveis();
                                if (vagas >0){
                                    System.out.println("O preço por kWh neste posto é: R$ " + preco);
                                    double valor = ((cargaBateriaAtual*(-1))+100)*preco;
                                    System.out.print("Valor a ser pago: "+valor +" para completar a bateria \n\nDigite sua Escolha\n1-Recarregar baterria\n2-Nao Recarregar\n\n: ");
                                    int escolha = Integer.parseInt(scanner.nextLine());
                                    if (escolha == 1){
                                        veiculoEncontrado.setCargaBateriaAtual(100);
                                        System.out.println("Reabastecido!!!");
                                        postoEncontrado.setVagasDisponiveis((int)vagas - 1);
                                    }
                                }else{
                                    System.out.println("Nao tem vagas disponiveis!!!");
                                }
                            }
                        }else {
                            System.out.println("Posto não encontrado.\n");
                        }
                    } else {
                        System.out.println("Não é possível fazer essa viagem.");
                        System.out.println("Distância necessária: " + distanciaViagem + " km");
                        System.out.println("Autonomia do veículo: " + resultado + " km");
                    }
                }
            }else if (opcao == 2){
                System.out.print("\nDigite o numero da sua escolha\n \n1-Cidades\n2-Eletropostos/Postos\n3-Veiculos\n \n0-Voltar\n\n: ");
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1 :
                        while (true) { 
                            System.out.print("Digite o numero da sua escolha \n\n1-Cadastrar Cidade\n2-Listar Cidades\n3-Editar Cidades\n4-Buscar Cidade por id\n \n0-Voltar a menu\n: ");
                            opcao3 = Integer.parseInt(scanner.nextLine());
                            if (opcao3 == 0) {break;}
                            switch(opcao3){
                                case 1:
                                    System.out.println("Digite o Id da Cidade (Numero de identificação maior que 0):");
                                    int id = Integer.parseInt(scanner.nextLine());
                                    System.out.println("Digite o Nome da Cidade:");
                                    String nameCity = scanner.nextLine();
                                    System.out.println("Digite o Estado da Cidade(Ex: PE, PB, MA):");
                                    String stateCity = scanner.nextLine();
                                    System.out.println("Digite a Distancia Da Capital (Distancia Da Cidade ate Capital ):");
                                    double distanciaDaCapital = Double.parseDouble(scanner.nextLine());
                                    Cidade novaCidade = new Cidade(id, nameCity, stateCity, distanciaDaCapital);
                                    city.RegisterCity(novaCidade);
                                    break;
                                case 2:
                                    city.ListCity();
                                    System.out.print("Digite 0 para Voltar a menu\n: ");
                                    skip = scanner.nextLine();
                                    break;
                                case 3:
                                    System.out.print("Digite o numero da sua escolha \n\n1-Atualizar Cidade\n2-Excluir Cidade\n\n0-Voltar menu\n\n: ");
                                    opcao3 =  Integer.parseInt(scanner.nextLine());
                                    if (opcao3 == 0){break;}
                                    if (opcao3 == 1){
                                        System.out.print("Digite o id que quer Atualizar ou digite 0 para Voltar a menu\n: ");
                                        idProcurado = Integer.parseInt(scanner.nextLine());
                                        if (idProcurado == 0){break;}
                                        System.out.println("Digite o Nome da Cidade:");
                                        nameCity = scanner.nextLine();
                                        System.out.println("Digite o Estado da Cidade:");
                                        stateCity = scanner.nextLine();
                                        System.out.println("Digite a Distancia Da Capital (Distancia Da Cidade ate Capital ):");
                                        distanciaDaCapital = Double.parseDouble(scanner.nextLine());
                                        Cidade cidadeAtualizada = new Cidade(idProcurado, nameCity, stateCity, distanciaDaCapital);
                                        city.UpdateCity(cidadeAtualizada);
                                    }else if (opcao3 == 2){
                                        System.out.print("Digite o id que quer Exclir ou digite 0 para Voltar a menu\n: ");
                                        idProcurado = Integer.parseInt(scanner.nextLine());
                                        if (idProcurado == 0){break;}
                                        city.DeleteCity(idProcurado);
                                    }
                                    break;
                                case 4:
                                    System.out.print("Digite o id que quer procurar ou digite 0 para Voltar a menu\n: ");
                                    idProcurado = Integer.parseInt(scanner.nextLine());
                                    if(idProcurado == 0){break;}
                                    city.SearchCity(idProcurado);
                                    System.out.print("Digite 0 para Voltar a menu\n: ");
                                    skip = scanner.nextLine();
                                    break;
                            }
                            if (opcao3 != 0 && opcao3 != 1 && opcao3 != 2 && opcao3 != 3 && opcao3 != 4){
                                System.out.println("Opção invalida!!!");
                            }
                        }
                        break;
                    case 2 :
                        while (true) { 
                            System.out.print("Digite o numero da sua escolha \n\n1-Cadastrar Posto\n2-Listar Postos\n3-Editar Postos\n4-Buscar Posto por id\n \n0-Voltar a menu\n: ");
                            opcao3 = Integer.parseInt(scanner.nextLine());
                            if (opcao3 == 0){break;}
                            switch(opcao3){
                                case 1:
                                    System.out.println("Cidades disponíveis:");
                                    if ("Não tem nem um Cidadade Cadastrada\n".equals(city.ListCity())){
                                        System.out.println("Não tem nem um Cidadade Cadastrada");
                                        break;
                                    }
                                    System.out.print("ID da cidade onde vai estar o Posto: ");
                                    int cidadeIdPosto = Integer.parseInt(scanner.nextLine());
                                    String localCidade = city.Local(cidadeIdPosto);
                                    if ("Cidade não encontrado.\n".equals(localCidade)){
                                        System.out.println("Cidade não encontrado.\n");
                                        break;
                                    }
                                    System.out.print("ID do Posto: ");
                                    int IdPosto = Integer.parseInt(scanner.nextLine());
                                    System.out.println("Digite o Nome do Posto:");
                                    String namePost = scanner.nextLine();
                                    System.out.println("Digite a localizacao do Posto (Endereço/Rodovia):");
                                    String localizacao = scanner.nextLine();
                                    System.out.println("Digite os tipos Conectores Disponiveis do Posto(Ex: CCS2, Tipo 2, aceita múltiplos):");
                                    String tiposConectoresDisponiveis = scanner.nextLine();
                                    System.out.println("Digite a potencia de Carga em kWh Do Posto (Potência do carregador, ex: 50.0 Kw, 150.0 Kw):");
                                    double potenciaCargaKw = Double.parseDouble(scanner.nextLine());
                                    System.out.println("Digite O Preço Do Carga (Preço Por Kwh):");
                                    double precoPorKwh = Double.parseDouble(scanner.nextLine());
                                    System.out.println("Digite Quantas vagas Tem Disponiveis no Posto (Numero de Vagas maior que 0):");
                                    int vagasDisponiveis = Integer.parseInt(scanner.nextLine());
                                    post.Registerpost(cidadeIdPosto, IdPosto, namePost, localCidade, localizacao, tiposConectoresDisponiveis, cidadeIdPosto, potenciaCargaKw, precoPorKwh, vagasDisponiveis);
                                    break;
                                case 2:
                                    post.Listpost();
                                    System.out.print("Digite 0 para Voltar a menu\n: ");
                                    skip = scanner.nextLine();
                                    break;
                                case 3:
                                    System.out.print("Digite o numero da sua escolha \n\n1-Atualizar Posto\n2-Excluir Posto\n\n0-Voltar menu\n\n: ");
                                    opcao3 =  Integer.parseInt(scanner.nextLine());
                                    if (opcao3 == 0){break;}
                                    if (opcao3 == 1){
                                        System.out.print("Digite o id que quer Atualizar ou digite 0 para Voltar a menu\n: ");
                                        idProcurado = Integer.parseInt(scanner.nextLine());
                                        if (idProcurado == 0){break;}
                                        System.out.println("Digite o Nome do Posto:");
                                        namePost = scanner.nextLine();
                                        System.out.println("Digite a localizacao do Posto (Endereço/Rodovia):");
                                        localizacao = scanner.nextLine();
                                        System.out.println("Digite os tipos Conectores Disponiveis do Posto(Ex: CCS2, Tipo 2, aceita múltiplos):");
                                        tiposConectoresDisponiveis = scanner.nextLine();
                                        System.out.println("Digite a potencia de Carga em kWh Do Posto (Potência do carregador, ex: 50.0 Kw, 150.0 Kw):");
                                        potenciaCargaKw = Double.parseDouble(scanner.nextLine());
                                        System.out.println("Digite O Preço Do Carga (Preço Por Kwh):");
                                        precoPorKwh = Double.parseDouble(scanner.nextLine());
                                        System.out.println("Digite Quantas vagas Tem Disponiveis no Posto (Numero de Vagas maior que 0):");
                                        vagasDisponiveis = Integer.parseInt(scanner.nextLine());
                                        post.Updatepost(idProcurado, namePost, localizacao, tiposConectoresDisponiveis, idProcurado, potenciaCargaKw, precoPorKwh, vagasDisponiveis);
                                    }else if (opcao3 == 2){
                                        System.out.print("Digite o id que quer Exclir ou digite 0 para Voltar a menu\n: ");
                                        idProcurado = Integer.parseInt(scanner.nextLine());
                                        if (idProcurado == 0){break;}
                                        city.DeleteCity(idProcurado);
                                    }
                                    break;
                                case 4:
                                    System.out.print("Digite o id que quer procurar ou digite 0 para Voltar a menu\n: ");
                                    idProcurado = Integer.parseInt(scanner.nextLine());
                                    if(idProcurado == 0){break;}
                                    post.Searchpost(idProcurado);
                                    System.out.print("Digite 0 para Voltar a menu\n: ");
                                    skip = scanner.nextLine();
                                    break;
                            }
                            if (opcao3 != 0 && opcao3 != 1 && opcao3 != 2 && opcao3 != 3 && opcao3 != 4){
                                System.out.println("Opção invalida!!!");
                            }
                        }
                        break;
                    case 3:
                        while (true) { 
                            System.out.print("Digite o numero da sua escolha \n\n1-Veiculo Eletrico \n2-Veiculo Hibrido\n \n0-Voltar a menu\n: ");
                            opcao2 = Integer.parseInt(scanner.nextLine());
                            if(opcao2 == 1){
                                while (true) { 
                                    System.out.print("Digite o numero da sua escolha \n\n1-Cadastrar Veiculos\n2-Listar Veiculos\n3-Editar Veiculos\n4-Buscar Veiculos por id\n \n0-Voltar a menu\n: ");
                                    opcao3 = Integer.parseInt(scanner.nextLine());
                                    if (opcao3 == 0){break;}
                                    switch (opcao3) {
                                        case 1:
                                            System.out.println("Digite o Id do Veiculo (Numero de identificação maior que 0):");
                                            int id = Integer.parseInt(scanner.nextLine());
                                            System.out.println("Digite o Modelo do Veiculo (Nome do Modelo):");
                                            String modelo = scanner.nextLine();
                                            System.out.println("Digite o Tempo da Recarga Completa do Veiculo (Tempo estimado em minutos para recarga de 0% a 100%):");
                                            int tempoRecargaCompleta = Integer.parseInt(scanner.nextLine());
                                            System.out.println("Digite o tipo do conector do Veiculo (Ex: Tipo 2, CCS2, CHAdeMO):");
                                            String tipoConector = scanner.nextLine();
                                            System.out.println("Digite a Capacidade do tanque em Kwh do Veiculo (em Kwh):");
                                            double capacidadeTotalBateria = Integer.parseInt(scanner.nextLine());
                                            System.out.println("Digite o Tempo da Recarga com Carregador mais forte (em minutos em carregadores de alta potência):");
                                            int tempoRecargaRapida = Integer.parseInt(scanner.nextLine());
                                            System.out.println("Digite a Autonomia Maxima do Veiculo (em quilômetros com carga completa):");
                                            double autonomiaMaxima = Double.parseDouble(scanner.nextLine());
                                            System.out.println("Digite a Carga da Bateria Atual do Veiculo (em porcentagem, de 0.0 a 100.0):");
                                            double cargaBateriaAtual = Double.parseDouble(scanner.nextLine());
                                            car.NewElectricCar(tempoRecargaRapida, id, tempoRecargaCompleta, tipoConector, modelo, autonomiaMaxima, cargaBateriaAtual, capacidadeTotalBateria);
                                            break;
                                        case 2:
                                            car.ListCar();
                                            System.out.print("Digite 0 para Voltar a menu\n: ");
                                            skip = scanner.nextLine();
                                            break;
                                        case 3:
                                            System.out.print("Digite o numero da sua escolha \n\n1-Atualizar Veiculo\n2-Excluir Veiculo\n: ");
                                            opcao3 =  Integer.parseInt(scanner.nextLine());
                                            System.out.print("Digite o id que quer Editar ou digite 0 para Voltar a menu\n: ");
                                            idProcurado = Integer.parseInt(scanner.nextLine());
                                            if (idProcurado == 0){break;}
                                            if(opcao3 == 1){
                                                    System.out.println("Digite o Modelo do Veiculo (Nome do Modelo):");
                                                    modelo = scanner.nextLine();
                                                    System.out.println("Digite o Tempo da Recarga Completa do Veiculo (Tempo estimado em minutos para recarga de 0% a 100%):");
                                                    tempoRecargaCompleta = Integer.parseInt(scanner.nextLine());
                                                    System.out.println("Digite o tipo do conector do Veiculo (Ex: Tipo 2, CCS2, CHAdeMO):");
                                                    tipoConector = scanner.nextLine();
                                                    System.out.println("Digite a Capacidade do tanque em Kwh do Veiculo (em Kwh):");
                                                    capacidadeTotalBateria = Integer.parseInt(scanner.nextLine());
                                                    System.out.println("Digite o Tempo da Recarga com Carregador mais forte (em minutos em carregadores de alta potência):");
                                                    tempoRecargaRapida = Integer.parseInt(scanner.nextLine());
                                                    System.out.println("Digite a Autonomia Maxima do Veiculo (em quilômetros com carga completa):");
                                                    autonomiaMaxima = Double.parseDouble(scanner.nextLine());
                                                    System.out.println("Digite a Carga da Bateria Atual do Veiculo (em porcentagem, de 0.0 a 100.0):");
                                                    cargaBateriaAtual = Double.parseDouble(scanner.nextLine());
                                                    car.UpdateCarelectric(idProcurado, tempoRecargaCompleta, modelo, autonomiaMaxima, cargaBateriaAtual, tipoConector, tempoRecargaRapida, capacidadeTotalBateria);  
                                            }else if (opcao3 == 2){
                                                car.DeleteCar(idProcurado, opcao2);
                                            }
                                            break;
                                        case 4:
                                            System.out.print("Digite o id que quer procurar ou digite 0 para Voltar a menu\n: ");
                                            idProcurado = Integer.parseInt(scanner.nextLine());
                                            if(idProcurado == 0){break;}
                                            car.SearchCars(idProcurado);
                                            System.out.print("Digite 0 para Voltar a menu\n: ");
                                            skip = scanner.nextLine();
                                            break;
                                    }
                                    if (opcao3 != 0 && opcao3 != 1 && opcao3 != 2 && opcao3 != 3 && opcao3 != 4){
                                        System.out.println("Opção invalida!!!");
                                    }
                                }
                                break;
                            }else if(opcao2 == 2){
                                while (true) { 
                                    System.out.print("Digite o numero da sua escolha \n\n1-Cadastrar Veiculos\n2-Listar Veiculos\n3-Editar Veiculos\n4-Buscar Veiculos por id\n \n0-Voltar a menu\n: ");
                                    opcao3 = Integer.parseInt(scanner.nextLine());
                                    if (opcao3 == 0){break;}
                                    switch (opcao3) {
                                        case 1:
                                            System.out.println("Digite o Id do Veiculo (Numero de identificação maior que 0):");
                                            int id = Integer.parseInt(scanner.nextLine());
                                            System.out.println("Digite o Modelo do Veiculo (Nome do Modelo):");
                                            String modelo = scanner.nextLine();
                                            System.out.println("Digite o Tempo da Recarga Completa do Veiculo (Tempo estimado em minutos para recarga de 0% a 100%):");
                                            int tempoRecargaCompleta = Integer.parseInt(scanner.nextLine());
                                            System.out.println("Digite o tipo do conector do Veiculo (Ex: Tipo 2, CCS2, CHAdeMO):");
                                            String tipoConector = scanner.nextLine();
                                            System.out.println("Digite o Tipo do Combustive do Veiculo (Ex: Gasolina, Etanol)");
                                            String tipoCombustivel = scanner.nextLine();
                                            System.out.println("Digite a Capacidade do Tanque de Combustivel do Veiculo (em litros):");
                                            double capacidadeTanqueCombustivel = Double.parseDouble(scanner.nextLine());
                                            System.out.println("Digite a Capacidade do tanque em Kwh do Veiculo (em Kwh):");
                                            int capacidadeTotalBateria = Integer.parseInt(scanner.nextLine());
                                            System.out.println("Digite a Autonomia Maxima do Veiculo (em quilômetros com carga completa):");
                                            double autonomiaMaximaMotorEletrico = Double.parseDouble(scanner.nextLine());
                                            System.out.println("Digite a Autonomia Maxima do Veiculo (em km no motor a combustão):");
                                            double autonomiaMaximaMotorCombustão = Double.parseDouble(scanner.nextLine());
                                            System.out.println("Digite a Consumo de Combustivel do Veiculo (em km/l no motor a combustão):");
                                            double consumoCombustivel = Double.parseDouble(scanner.nextLine());
                                            System.out.println("Digite a Carga da Bateria Atual do Veiculo (em porcentagem, de 0.0 a 100.0):");
                                            double cargaBateriaAtual = Double.parseDouble(scanner.nextLine());
                                            car.NewhybridCar(id, tempoRecargaCompleta, modelo, tipoConector, tipoCombustivel, consumoCombustivel, capacidadeTanqueCombustivel, autonomiaMaximaMotorEletrico, autonomiaMaximaMotorCombustão, cargaBateriaAtual, capacidadeTotalBateria);
                                            break;
                                        case 2:
                                            car.ListCar();
                                            System.out.print("Digite 0 para Voltar a menu\n: ");
                                            skip = scanner.nextLine();
                                            break;
                                        case 3:
                                            System.out.print("Digite o numero da sua escolha \n\n1-Atualizar Veiculo\n2-Excluir Veiculo\n: ");
                                            opcao3 =  Integer.parseInt(scanner.nextLine());
                                            System.out.print("Digite o id que quer Editar ou digite 0 para Voltar a menu\n: ");
                                            idProcurado = Integer.parseInt(scanner.nextLine());
                                            if (idProcurado == 0){break;}
                                            if(opcao3 == 1){
                                                System.out.println("Digite o Modelo do Veiculo (Nome do Modelo):");
                                                modelo = scanner.nextLine();
                                                System.out.println("Digite o Tempo da Recarga Completa do Veiculo (Tempo estimado em minutos para recarga de 0% a 100%):");
                                                tempoRecargaCompleta = Integer.parseInt(scanner.nextLine());
                                                System.out.println("Digite o tipo do conector do Veiculo (Ex: Tipo 2, CCS2, CHAdeMO):");
                                                tipoConector = scanner.nextLine();
                                                System.out.println("Digite o Tipo do Combustive do Veiculo (Ex: Gasolina, Etanol)");
                                                tipoCombustivel = scanner.nextLine();
                                                System.out.println("Digite a Capacidade do Tanque de Combustivel do Veiculo (em litros):");
                                                capacidadeTanqueCombustivel = Double.parseDouble(scanner.nextLine());
                                                System.out.println("Digite a Capacidade do tanque em Kwh do Veiculo (em Kwh):");
                                                capacidadeTotalBateria = Integer.parseInt(scanner.nextLine());
                                                System.out.println("Digite a Autonomia Maxima do Veiculo (em quilômetros com carga completa):");
                                                autonomiaMaximaMotorEletrico = Double.parseDouble(scanner.nextLine());
                                                System.out.println("Digite a Autonomia Maxima do Veiculo (em km no motor a combustão):");
                                                autonomiaMaximaMotorCombustão = Double.parseDouble(scanner.nextLine());
                                                System.out.println("Digite a Consumo de Combustivel do Veiculo (em km/l no motor a combustão):");
                                                consumoCombustivel = Double.parseDouble(scanner.nextLine());
                                                System.out.println("Digite a Carga da Bateria Atual do Veiculo (em porcentagem, de 0.0 a 100.0):");
                                                cargaBateriaAtual = Double.parseDouble(scanner.nextLine());
                                                car.UpdateCarhybrid (idProcurado, tempoRecargaCompleta, modelo, tipoCombustivel, tipoConector, capacidadeTanqueCombustivel, autonomiaMaximaMotorEletrico, autonomiaMaximaMotorCombustão, cargaBateriaAtual, consumoCombustivel, capacidadeTotalBateria);
                                            }else if (opcao3 == 2){
                                                car.DeleteCar(idProcurado, opcao2);
                                            }
                                            break;
                                        case 4:
                                            System.out.print("Digite o id que quer procurar ou digite 0 para Voltar a menu\n: ");
                                            idProcurado = Integer.parseInt(scanner.nextLine());
                                            if(idProcurado == 0){break;}
                                            car.SearchCars(idProcurado);
                                            System.out.print("Digite 0 para Voltar a menu\n: ");
                                            skip = scanner.nextLine();
                                            break;
                                    }
                                    if (opcao3 != 0 && opcao3 != 1 && opcao3 != 2 && opcao3 != 3 && opcao3 != 4){
                                        System.out.println("Opção invalida!!!");
                                    }
                                }
                                break;
                            }else if (opcao2 == 0){break;}
                            else{
                                System.out.println("Opção invalida!!!");
                            }
                        }
                        break;
                    case 0:
                        opcao = 0;
                        break;
                }
            }else{
                System.out.println("Opção invalida!!!");
            }
        }
    }
}