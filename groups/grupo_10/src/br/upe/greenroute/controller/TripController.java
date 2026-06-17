package br.upe.greenroute.controller;

import br.upe.greenroute.model.*;
import br.upe.greenroute.repository.ChargingStationRepository;
import br.upe.greenroute.repository.CityRepository;
import br.upe.greenroute.repository.VehicleRepository;
import br.upe.greenroute.view.ChargingStationView;
import br.upe.greenroute.view.CityView;
import br.upe.greenroute.view.TripView;
import br.upe.greenroute.view.VehicleView;

public class TripController {
    private final TripView view;
    private final VehicleView vehicleView;
    private final VehicleRepository vehicleRepository;
    private final CityView cityView;
    private final CityRepository cityRepository;
    private final ChargingStationView chargingStationView;
    private final ChargingStationRepository chargingStationRepository;
    public TripController (TripView tripView,VehicleView vehicleView, VehicleRepository vehicleRepository, CityView cityView, CityRepository cityRepository, ChargingStationView chargingStationView, ChargingStationRepository chargingStationRepository) {
        this.view = tripView;
        this.vehicleView = vehicleView;
        this.cityView = cityView;
        this.chargingStationView = chargingStationView;
        this.chargingStationRepository = chargingStationRepository;
        this.cityRepository = cityRepository;
        this.vehicleRepository = vehicleRepository;
    }
    public void TripSimulation () {
        if (vehicleRepository.isEmpty() || cityRepository.isEmpty()) {
            view.displayError("É necessário ter pelo menos um veículo e uma cidade cadastrados para simular uma viagem!");
            view.Enter();
            return;
        }
        int vehicleId = view.requestVehicleId();
        int cityId = view.requestCityId();
        VehicleModel vehicle = vehicleRepository.searchById(vehicleId);
        CityModel destination = cityRepository.searchById(cityId);
        if (vehicle != null &&  destination!= null) {
            view.displayMessage("Veiculo informado: ");
            if (vehicle instanceof ElectricVehicleModel) {
                vehicleView.displayVehicleData((ElectricVehicleModel) vehicle);
            }else {
                vehicleView.displayVehicleData((HybridVehicleModel) vehicle);
            }
            view.displayMessage("Cidade informada: ");
            cityView.displayCity(destination);
            double currentAutonnomy = vehicle.calculateCurrentAutonomy();
            double destinyDistance = destination.getCapitalDistance();
            if (currentAutonnomy >= destinyDistance) {
                view.displayMessage("A autonomia atual do carro é suficiente para concluir a viagem! autonomia restante: "+(currentAutonnomy - destinyDistance));
                view.Enter();
                return;
            }
            view.displayMessage("A autonomia atual do carro é insuficiente para concluir a viagem!");
            CityModel stopCity = requestValidStop(currentAutonnomy);
            if (stopCity != null) {
                view.displayMessage("Você pode reabastecer nesses postos: ");
                ChargingStationModel[] stations = chargingStationRepository.searchByCityId(stopCity.getId());
                for (ChargingStationModel station : stations) {
                    chargingStationView.displayChargingStation(station);
                }
            }else {
                view.displayMessage("Viagem cancelada pelo usuário!");
            }
        }else {
            view.displayError("Veiculo ou cidade não encontrado no sistema!");
        }
        view.Enter();
    }
    private CityModel requestValidStop(double currentAutonnomy) {
        boolean wantToStop = view.askWantToMakeAStop();
        while (wantToStop) {
            int stopCityId = view.requestCityId();
            CityModel stopCity = cityRepository.searchById(stopCityId);
            if (stopCity == null) {
                view.displayError("Cidade de parada não encontrada no sistema!");
            }else if (stopCity.getCapitalDistance() > currentAutonnomy) {
                view.displayError("a autonomia atual do carro é insuficiente para chegar até a parada!");
            }else if (chargingStationRepository.countStationsByCityId(stopCity.getId()) == 0) {
                view.displayError("A cidade de parada não possui eletropostos cadastrados!");
                view.displayMessage("Selecione outra cidade de parada");
            } else {
                view.displayMessage("Parada válida! nome: "+stopCity.getName());
                return stopCity;
            }
            wantToStop = view.askWantToMakeAStop();
        }
        return null;
    }
}
