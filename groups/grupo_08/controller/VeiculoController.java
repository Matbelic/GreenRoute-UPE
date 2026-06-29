package controller;

import Model.*;
import repository.VeiculoRepository;

public class VeiculoController {
    VeiculoRepository repository = new VeiculoRepository();
    public void NewElectricCar(int tempoRecargaRapida, int id, int tempoRecargaCompleta, String tipoConector, String modelo, double autonomiaMaxima, double cargaBateriaAtual, double capacidadeTotalBateria){
        if (id < 0){
            System.out.print("ID menor que 0, Digite um numero maior que 0!!!!");
            return;
        }
        Veiculo car = new Veiculo(id, tempoRecargaCompleta, modelo, autonomiaMaxima, cargaBateriaAtual, capacidadeTotalBateria);
        double consumoKwhPorKm = car.getConsumoKwhPorKm();
        repository.RegisterElectricCar(tempoRecargaRapida, id, tempoRecargaCompleta, tipoConector, modelo, autonomiaMaxima, cargaBateriaAtual, consumoKwhPorKm,capacidadeTotalBateria);
    }
    public void NewhybridCar(int id, int tempoRecargaCompleta, String modelo, String tipoConector, String tipoCombustivel, double consumoCombustivel, double capacidadeTanqueCombustivel, double autonomiaMaximaMotorEletrico, double autonomiaMaximaMotorCombustao, double cargaBateriaAtual, double capacidadeTotalBateria){
        if (id < 0){
            System.out.print("ID menor que 0, Digite um numero maior que 0!!!!");
            return;
        }
        Veiculo car = new Veiculo(id, tempoRecargaCompleta, modelo, autonomiaMaximaMotorEletrico, cargaBateriaAtual, capacidadeTotalBateria);
        double consumoKwhPorKm = car.getConsumoKwhPorKm();
        repository.RegisterhybridCar(id, tempoRecargaCompleta, modelo, tipoConector, tipoCombustivel, consumoCombustivel, capacidadeTanqueCombustivel, autonomiaMaximaMotorEletrico, autonomiaMaximaMotorCombustao, cargaBateriaAtual, consumoKwhPorKm, capacidadeTotalBateria);
    }
    public void ListCar(){
        repository.ListCar();
    }
    public String CalculateRouteListCar(){
        return repository.CalculateRouteListCar();
    }
    public void UpdateCarelectric(int idProcurado, int novoTempo, String novoModelo, double novaAutonomia, double novaCarga, String tipoConector, int tempoRecargaRapida, double capacidadeTotalBateria){
        // MUDANÇA AQUI: Recalculando o consumo antes de enviar a atualização para o repositório
        Veiculo car = new Veiculo(idProcurado, novoTempo, novoModelo, novaAutonomia, novaCarga, capacidadeTotalBateria);
        double consumoKwhPorKm = car.getConsumoKwhPorKm();
        
        repository.UpdateCarelectric(idProcurado, novoTempo, novoModelo, novaAutonomia, novaCarga, tipoConector, tempoRecargaRapida, consumoKwhPorKm, capacidadeTotalBateria);
    }
    public void UpdateCarhybrid(int idProcurado, int novoTempo, String novoModelo, String tipoCombustivel, String tipoConector, double capacidadeTanqueCombustivel, double autonomiaMaximaMotorEletrico, double autonomiaMaximaMotorCombustao, double novaCarga, double consumoCombustivel, double capacidadeTotalBateria){
        // MUDANÇA AQUI: Recalculando o consumo antes de enviar a atualização para o repositório
        Veiculo car = new Veiculo(idProcurado, novoTempo, novoModelo, autonomiaMaximaMotorEletrico, novaCarga, capacidadeTotalBateria);
        double consumoKwhPorKm = car.getConsumoKwhPorKm();
        
        repository.UpdateCarhybrid(idProcurado, novoTempo, novoModelo, tipoCombustivel, tipoConector, capacidadeTanqueCombustivel, autonomiaMaximaMotorEletrico, autonomiaMaximaMotorCombustao, novaCarga, consumoKwhPorKm, consumoCombustivel, capacidadeTotalBateria);
    }
    public void DeleteCar(int idProcurado, int searchCars){
        repository.DeleteCar(idProcurado, searchCars);
    }
    public String SearchCars(int idProcurado){
        return repository.SearchCars(idProcurado);
    }
    public Veiculo getVeiculos(int indice) {
        return repository.getVeiculos(indice); 
    }
    public int getTotalVeiculos() {
        return repository.getTotalVeiculos();
    }
}