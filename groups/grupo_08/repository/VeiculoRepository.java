package repository;

import Model.Veiculo;
import Model.VeiculoEletrico;
import Model.VeiculoHibrido;

public class VeiculoRepository {
    private int capacity = 10;
    private Veiculo[] veiculos = new Veiculo[capacity];
    private int totalVeiculos = 0;

    public VeiculoRepository() {}

    public void RegisterElectricCar(int tempoRecargaRapida, int id, int tempoRecargaCompleta, String tipoConector, String modelo, double autonomiaMaxima, double cargaBateriaAtual, double consumoKwhPorKm, double capacidadeTotalBateria) {
        for (int i = 0; i < totalVeiculos; i++) {
            if (veiculos[i] != null && veiculos[i].getId() == id) {
                System.out.println("Id ja Registrado Porfavor coloque outro id!!\n");
                return;
            }
        }
        if (totalVeiculos == capacity) {
            this.capacity = this.capacity + 5;
            Veiculo[] novoArray = new Veiculo[this.capacity];
            for (int i = 0; i < veiculos.length; i++) {
                novoArray[i] = this.veiculos[i];
            }
            this.veiculos = novoArray;
        }
        this.veiculos[totalVeiculos] = new VeiculoEletrico(id, modelo, tipoConector, cargaBateriaAtual, autonomiaMaxima, consumoKwhPorKm, tempoRecargaCompleta, tempoRecargaRapida, capacidadeTotalBateria); 
        totalVeiculos++;
        System.out.println("Carro Cadastrado com Sucesso\n");
    }
    public void RegisterhybridCar(int id, int tempoRecargaCompleta, String modelo, String tipoConector, String tipoCombustivel, double consumoCombustivel, double capacityTanqueCombustivel, double autonomiaMaximaMotorEletrico, double autonomiaMaximaMotorCombustao, double cargaBateriaAtual, double consumoKwhPorKm, double capacidadeTotalBateria) {
        for (int i = 0; i < totalVeiculos; i++) {
            if (veiculos[i] != null && veiculos[i].getId() == id) {
                System.out.println("Id ja Registrado Porfavor coloque outro id!!\n");
                return;
            }
        }
        if (totalVeiculos == capacity) {
            this.capacity = this.capacity + 5;
            Veiculo[] novoArray = new Veiculo[this.capacity];
            for (int i = 0; i < veiculos.length; i++) {
                novoArray[i] = this.veiculos[i];
            }
            this.veiculos = novoArray;
        }
        this.veiculos[totalVeiculos] = new VeiculoHibrido(id, modelo, tipoConector, cargaBateriaAtual, autonomiaMaximaMotorEletrico, consumoKwhPorKm, tempoRecargaCompleta, tipoCombustivel, consumoCombustivel, capacityTanqueCombustivel, autonomiaMaximaMotorCombustao, capacidadeTotalBateria);
        totalVeiculos++;
        System.out.println("Carro Cadastrado com Sucesso\n");
    }
    public void ListCar() {
        if (totalVeiculos == 0) {
            System.out.println("Não tem nem um Veículos Cadastrados\n");
            return;
        }
        for (int i = 0; i < totalVeiculos; i++) {
            System.out.println(veiculos[i]);
        }
    }
    public String CalculateRouteListCar() {
        if (totalVeiculos == 0) {
            return "Não tem nem um Veículos Cadastrados\n";
        }
        for (int i = 0; i < totalVeiculos; i++) {
            System.out.println("ID: " + veiculos[i].getId() + " - " + veiculos[i].getModelo());
        }
        return (veiculos[0] instanceof VeiculoEletrico) ? "1" : "0";
    }
    public String SearchCars(int idProcurado) {
        for (int i = 0; i < totalVeiculos; i++) {
            Veiculo veiculo = veiculos[i];
            if (veiculo != null && veiculo.getId() == idProcurado) {
                if ((veiculo instanceof VeiculoEletrico)) {
                    System.out.println(veiculo);
                    return "  ";
                }
                if ((veiculo instanceof VeiculoHibrido)){
                    System.out.println(veiculo);
                    return " ";
                }
            }
        }
        return "Veículo não encontrado.\n" ;
    }
    public void UpdateCarelectric(int idProcurado, int novoTempo, String novoModelo, double novaAutonomia, double novaCarga, String tipoConector, int tempoRecargaRapida, double consumoKwhPorKm, double capacidadeTotalBateria) {
        for (int i = 0; i < totalVeiculos; i++) {
            Veiculo veiculo = veiculos[i];
            if (veiculo != null && veiculo.getId() == idProcurado && veiculo instanceof VeiculoEletrico) {
                VeiculoEletrico carEletrico = (VeiculoEletrico) veiculo;
                carEletrico.setModelo(novoModelo);
                carEletrico.setTipoConector(tipoConector);
                carEletrico.setCargaBateriaAtual(novaCarga);
                carEletrico.setAutonomiaMaxima(novaAutonomia);
                carEletrico.setConsumoKwhPorKm(consumoKwhPorKm);
                carEletrico.setTempoRecargaCompleta(novoTempo);
                carEletrico.setTempoRecargaRapida(tempoRecargaRapida);
                carEletrico.setCapacidadeTotalBateria(capacidadeTotalBateria);
                System.out.println("Veículo ID " + idProcurado + " atualizado com sucesso!\n");
                return;
            }
        }
        System.out.println("Veículo para atualização não encontrado.\n");
    }
    public void UpdateCarhybrid(int idProcurado, int novoTempo, String novoModelo, String tipoCombustivel, String tipoConector, double capacidadeTanqueCombustivel, double autonomiaMaximaMotorEletrico, double autonomiaMaximaMotorCombustao, double novaCarga, double consumoKwhPorKm, double consumoCombustivel, double capacidadeTotalBateria) {
        for (int i = 0; i < totalVeiculos; i++) {
            Veiculo veiculo = veiculos[i];
            if (veiculo != null && veiculo.getId() == idProcurado && veiculo instanceof VeiculoHibrido) {
                VeiculoHibrido CarHibrido = (VeiculoHibrido) veiculo;
                CarHibrido.setModelo(novoModelo);
                CarHibrido.setTipoConector(tipoConector);
                CarHibrido.setCargaBateriaAtual(novaCarga);
                CarHibrido.setAutonomiaMaxima(autonomiaMaximaMotorEletrico);
                CarHibrido.setConsumoKwhPorKm(consumoKwhPorKm);
                CarHibrido.setTempoRecargaCompleta(novoTempo);
                CarHibrido.setTipoCombustivel(tipoCombustivel);
                CarHibrido.setConsumoCombustivel(consumoCombustivel);
                CarHibrido.setCapacidadeTanqueCombustivel(capacidadeTanqueCombustivel);
                CarHibrido.setAutonomiaMotorCombustao(autonomiaMaximaMotorCombustao);
                CarHibrido.setCapacidadeTotalBateria(capacidadeTotalBateria);
                System.out.println("Veículo ID " + idProcurado + " atualizado com sucesso!\n");
                return;
            }
        }
        System.out.println("Veículo para atualização não encontrado.\n");
    }
    public void DeleteCar(int idProcurado, int searchCars) {
        for (int i = 0; i < totalVeiculos; i++) {
            Veiculo veiculo = veiculos[i];
            if (veiculo != null && veiculo.getId() == idProcurado) {
                if ((searchCars == 1 && veiculo instanceof VeiculoEletrico) || (searchCars == 2 && veiculo instanceof VeiculoHibrido)) {
                    for (int j = i; j < totalVeiculos - 1; j++) {
                        veiculos[j] = veiculos[j + 1];
                    }
                    veiculos[totalVeiculos - 1] = null;
                    totalVeiculos--;
                    System.out.println("Veículo ID " + idProcurado + " excluído com sucesso!\n");
                    return;
                }
            }
        }
        System.out.println("Veículo Para Ser Exclusão Não Encontrado.\n");
    }
    public Veiculo getVeiculos(int indice) {
        return this.veiculos[indice];
    }
    public int getTotalVeiculos() {
        return totalVeiculos;
    }
}