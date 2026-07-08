import java.util.Scanner;

import br.com.upe.controller.RotaController;
import br.com.upe.repository.CidadeRepository;
import br.com.upe.repository.EletropostoRepository;
import br.com.upe.repository.VeiculoRepository;
import br.com.upe.controller.CidadeController;
import br.com.upe.controller.EletropostoController;
import br.com.upe.controller.VeiculoController;

public class Principal {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        int escolhaPrincipal;
        CRUDs chamar = new CRUDs();

        CidadeRepository cidadeRepo = new CidadeRepository();
        EletropostoRepository eletropostoRepo = new EletropostoRepository();
        VeiculoRepository veiculoRepo = new VeiculoRepository();

        CidadeController cidadeController = new CidadeController(cidadeRepo);
        EletropostoController eletropostoController = new EletropostoController(eletropostoRepo, cidadeRepo);
        VeiculoController veiculoController = new VeiculoController(veiculoRepo, cidadeRepo, eletropostoRepo);
        RotaController rotaController = new RotaController(veiculoRepo, cidadeRepo, eletropostoRepo);
        do {
            chamar.CRUDPrincipal();
            escolhaPrincipal = leitura.nextInt();

            switch (escolhaPrincipal) {
                case 1:
                    int escolhaVeiculo;
                    do {
                        chamar.CRUDVeículos();
                        escolhaVeiculo = leitura.nextInt();
                        switch (escolhaVeiculo) {
                            case 1:
                                System.out.println("Qual tipo de veículo? (1 - Elétrico / 2 - Híbrido): ");
                                int tipo = leitura.nextInt();
                                leitura.nextLine();

                                System.out.print("ID: "); int idV = leitura.nextInt(); leitura.nextLine();
                                System.out.print("Modelo: "); String modelo = leitura.nextLine();
                                System.out.print("Autonomia Máxima (km): "); double autoMax = leitura.nextDouble();
                                System.out.print("Carga Bateria Atual (%): "); double cargaAtual = leitura.nextDouble();
                                System.out.print("Consumo (kWh/km): "); double consKwh = leitura.nextDouble();
                                System.out.print("Tempo Recarga Completa (min): "); int tempoRecarga = leitura.nextInt();
                                leitura.nextLine();

                                if (tipo == 1) {
                                    System.out.print("Tipo do Conector: "); String conector = leitura.nextLine();
                                    System.out.print("Tempo Recarga Rápida (min): "); int recargaRapida = leitura.nextInt();
                                    veiculoController.cadastrarVeiculoEletrico(idV, modelo, autoMax, cargaAtual, consKwh, tempoRecarga, conector, recargaRapida);
                                } else if (tipo == 2) {
                                    System.out.print("Capacidade Tanque (L): "); double tanque = leitura.nextDouble();
                                    System.out.print("Consumo Combustível (km/l): "); double consComb = leitura.nextDouble();
                                    leitura.nextLine();
                                    System.out.print("Tipo Combustível: "); String tipoComb = leitura.nextLine();
                                    veiculoController.cadastrarVeiculoHibrido(idV, modelo, autoMax, cargaAtual, consKwh, tempoRecarga, tanque, consComb, tipoComb);
                                } else {
                                    System.out.println("Tipo inválido.");
                                }
                                break;
                            case 2:
                                System.out.print("Digite o ID do Veículo para busca: ");
                                int idBuscaV = leitura.nextInt();
                                br.com.upe.model.Veiculo v = veiculoController.buscarVeiculo(idBuscaV);
                                if (v != null) {
                                    System.out.println("=> Veículo Encontrado: " + v.getModelo() + " | Autonomia: " + v.getAutonomiaMaxima() + "km | Bateria: " + v.getCargaBateriaAtual() + "%");
                                }
                                break;
                            case 3:
                                System.out.println("Qual tipo de veículo deseja atualizar? (1 - Elétrico / 2 - Híbrido): ");
                                int tipoAt = leitura.nextInt();
                                leitura.nextLine();

                                System.out.print("ID do Veículo a atualizar: "); int idVAt = leitura.nextInt(); leitura.nextLine();
                                System.out.print("Novo Modelo: "); String modeloAt = leitura.nextLine();
                                System.out.print("Nova Autonomia Máxima (km): "); double autoMaxAt = leitura.nextDouble();
                                System.out.print("Nova Carga Bateria Atual (%): "); double cargaAtualAt = leitura.nextDouble();
                                System.out.print("Novo Consumo (kWh/km): "); double consKwhAt = leitura.nextDouble();
                                System.out.print("Novo Tempo Recarga Completa (min): "); int tempoRecargaAt = leitura.nextInt();
                                leitura.nextLine();

                                if (tipoAt == 1) {
                                    System.out.print("Novo Tipo do Conector: "); String conectorAt = leitura.nextLine();
                                    System.out.print("Novo Tempo Recarga Rápida (min): "); int recargaRapidaAt = leitura.nextInt();
                                    veiculoController.atualizarVeiculoEletrico(idVAt, modeloAt, autoMaxAt, cargaAtualAt, consKwhAt, tempoRecargaAt, conectorAt, recargaRapidaAt);
                                } else if (tipoAt == 2) {
                                    System.out.print("Nova Capacidade Tanque (L): "); double tanqueAt = leitura.nextDouble();
                                    System.out.print("Novo Consumo Combustível (km/l): "); double consCombAt = leitura.nextDouble();
                                    leitura.nextLine();
                                    System.out.print("Novo Tipo Combustível: "); String tipoCombAt = leitura.nextLine();
                                    veiculoController.atualizarVeiculoHibrido(idVAt, modeloAt, autoMaxAt, cargaAtualAt, consKwhAt, tempoRecargaAt, tanqueAt, consCombAt, tipoCombAt);
                                } else {
                                    System.out.println("Tipo inválido.");
                                }
                                break;
                            case 4:
                                System.out.print("Digite o ID do Veículo a excluir: ");
                                int idExcluirV = leitura.nextInt();
                                veiculoController.excluirVeiculo(idExcluirV);
                                break;
                            case 5:
                                System.out.println("Voltando...");
                                break;
                            default:
                                System.out.println("Opção inválida");
                        }
                    } while (escolhaVeiculo != 5);
                    break;

                case 2:
                    int escolhaEletroposto;
                    do {
                        chamar.CRUDEletropostos();
                        escolhaEletroposto = leitura.nextInt();
                        switch (escolhaEletroposto) {
                            case 1:
                                System.out.print("ID: "); int idEp = leitura.nextInt(); leitura.nextLine();
                                System.out.print("Nome: "); String nomeEp = leitura.nextLine();
                                System.out.print("Localização: "); String locEp = leitura.nextLine();
                                System.out.print("ID da Cidade onde está localizado: "); int idCidEp = leitura.nextInt(); leitura.nextLine();
                                System.out.print("Tipos de Conectores (Ex: CCS2, Tipo 2): "); String conectores = leitura.nextLine();
                                System.out.print("Potência (kW): "); double pot = leitura.nextDouble();
                                System.out.print("Preço por kWh: R$ "); double preco = leitura.nextDouble();
                                System.out.print("Vagas Disponíveis: "); int vagas = leitura.nextInt();

                                eletropostoController.cadastrarEletroposto(idEp, nomeEp, locEp, idCidEp, conectores, pot, preco, vagas);
                                break;
                            case 2:
                                System.out.print("Digite o ID do Eletroposto: ");
                                int idBuscaEp = leitura.nextInt();
                                br.com.upe.model.Eletroposto ep = eletropostoController.buscarEletroposto(idBuscaEp);
                                if (ep != null) {
                                    System.out.println("=> Eletroposto Encontrado: " + ep.getNome() + " | Local: " + ep.getLocalizacao() + " | Potência: " + ep.getPotenciaCargaKw() + "kW");
                                }
                                break;
                            case 3:
                                System.out.print("ID do Eletroposto a atualizar: "); int idEpAt = leitura.nextInt(); leitura.nextLine();
                                System.out.print("Novo Nome: "); String nomeEpAt = leitura.nextLine();
                                System.out.print("Nova Localização: "); String locEpAt = leitura.nextLine();
                                System.out.print("Novo ID da Cidade: "); int idCidEpAt = leitura.nextInt(); leitura.nextLine();
                                System.out.print("Novos Tipos de Conectores (Ex: CCS2, Tipo 2): "); String conectoresAt = leitura.nextLine();
                                System.out.print("Nova Potência (kW): "); double potAt = leitura.nextDouble();
                                System.out.print("Novo Preço por kWh: R$ "); double precoAt = leitura.nextDouble();
                                System.out.print("Novas Vagas Disponíveis: "); int vagasAt = leitura.nextInt();

                                eletropostoController.atualizarEletroposto(idEpAt, nomeEpAt, locEpAt, idCidEpAt, conectoresAt, potAt, precoAt, vagasAt);
                                break;
                            case 4:
                                System.out.print("Digite o ID do Eletroposto a excluir: ");
                                int idExcEp = leitura.nextInt();
                                eletropostoController.excluirEletroposto(idExcEp);
                                break;
                            case 5:
                                System.out.println("Voltando...");
                                break;
                            default:
                                System.out.println("Opção inválida");
                        }
                    } while (escolhaEletroposto != 5);
                    break;

                case 3:
                    int escolhaCidade;
                    do {
                        chamar.CRUDCidades();
                        escolhaCidade = leitura.nextInt();
                        switch (escolhaCidade) {
                            case 1:
                                System.out.print("ID da Cidade: "); int idCid = leitura.nextInt(); leitura.nextLine(); // limpar buffer
                                System.out.print("Nome da Cidade: "); String nomeCid = leitura.nextLine();
                                System.out.print("Estado (UF): "); String estadoCid = leitura.nextLine();
                                System.out.print("Distância da Capital (km): "); double distCid = leitura.nextDouble();

                                cidadeController.cadastrarCidade(idCid, nomeCid, estadoCid, distCid);
                                break;
                            case 2:
                                System.out.print("Digite o ID da Cidade: ");
                                int idBuscaCid = leitura.nextInt();
                                br.com.upe.model.Cidade cid = cidadeController.buscarCidade(idBuscaCid);
                                if (cid != null) {
                                    System.out.println("=> Cidade Encontrada: " + cid.getNome() + " - " + cid.getEstado() + " | Distância da Capital: " + cid.getDistanciaDaCapital() + "km");
                                }
                                break;
                            case 3:
                                System.out.print("ID da Cidade a atualizar: "); int idAtCid = leitura.nextInt(); leitura.nextLine();
                                System.out.print("Novo Nome: "); String nNome = leitura.nextLine();
                                System.out.print("Novo Estado (UF): "); String nEst = leitura.nextLine();
                                System.out.print("Nova Distância (km): "); double nDist = leitura.nextDouble();
                                cidadeController.atualizarCidade(idAtCid, nNome, nEst, nDist);
                                break;
                            case 4:
                                System.out.print("Digite o ID da Cidade a excluir: ");
                                int idExcCid = leitura.nextInt();
                                cidadeController.excluirCidade(idExcCid);
                                break;
                            case 5:
                                System.out.println("Voltando...");
                                break;
                            default:
                                System.out.println("Opção inválida");
                        }
                    } while (escolhaCidade != 5);
                    break;

                case 4:
                    System.out.println("--- SIMULADOR DE VIAGEM ---");
                    System.out.print("Digite o ID do Veículo: ");
                    int veiculoId = leitura.nextInt();
                    System.out.print("Digite o ID da Cidade de Destino: ");
                    int cidadeId = leitura.nextInt();
                    rotaController.simularViagem(veiculoId, cidadeId);
                    break;
                case 5:
                    System.out.println("Encerrando o sistema GreenRoute...");
                    break;

                default:
                    System.out.println("Opção inválida");
            }
        } while (escolhaPrincipal != 5);

        leitura.close();
    }
}
