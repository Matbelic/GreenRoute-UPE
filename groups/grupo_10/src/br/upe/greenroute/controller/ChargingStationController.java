package br.upe.greenroute.controller;

import br.upe.greenroute.model.ChargingStationModel;
import br.upe.greenroute.repository.ChargingStationRepository;
import br.upe.greenroute.repository.CityRepository;
import br.upe.greenroute.view.ChargingStationView;

public class ChargingStationController extends BaseController {
    private final ChargingStationRepository repository;
    private final ChargingStationView view;
    private final CityRepository cityRepository;
    public ChargingStationController (ChargingStationRepository repository, CityRepository cityRepository, ChargingStationView view) {
        this.repository = repository;
        this.view = view;
        this.cityRepository = cityRepository;
    }
    private String validateChargingStation(String name, String location,String cityIdStr, String availableConnectorsType, String chargingPowerKWStr, String pricePerKWhStr, String availableVacanciesStr) {
        String error;
        error = isAnyBlank(name, location, cityIdStr, availableConnectorsType, chargingPowerKWStr, pricePerKWhStr, availableVacanciesStr);
        if (error != null) {
            return error;
        }
        error = isInt(cityIdStr, "Id da cidade deve ser um valor inteiro!");

        if (error == null) {
            error = isInt(availableVacanciesStr, "Quantidade de vagas inválida!");
        }
        if (error == null) {
                error = isDouble(chargingPowerKWStr, "Potência de carregamento inválida!");
            }
        if (error == null) {
            error = isDouble(pricePerKWhStr, "Preço por kWh inválido!");
        }
        return error;
    }
    public void addChargingStation() {
        String[] dates = view.requestDataForCreate();
        String name = dates[0];
        String location = dates[1];
        String cityIdStr = dates[2];
        String availableConnectorsTypeStr = dates[3];
        String chargingPowerKWStr = dates[4];
        String pricePerKWhStr = dates[5];
        String availableVacanciesStr  = dates[6];
        String error = validateChargingStation(name, location, cityIdStr, availableConnectorsTypeStr, chargingPowerKWStr, pricePerKWhStr, availableVacanciesStr);
        if (error != null) {
            view.displayError(error);
            view.Enter();
            return;
        }
        int cityId = Integer.parseInt(cityIdStr);
        if (cityRepository.searchById(cityId) == null) {
            view.displayError("Não existe nenhuma cidade com o id informado! id: " + cityId);
            view.Enter();
            return;
        }
        String[] availableConnectorsType = availableConnectorsTypeStr.trim().split(",");
        double chargingPowerKW = Double.parseDouble(chargingPowerKWStr);
        double pricePerKWh = Double.parseDouble(pricePerKWhStr);
        int availableVacancies = Integer.parseInt(availableVacanciesStr);
        if (chargingPowerKW <= 0) {
            error= ("A potência do carregador deve ser um valor positivo!");
        }else if (pricePerKWh < 0) {
            error = ("O preço por quilowatt-hora deve ser no mínimo 0!");
        }else if (availableVacancies < 0) {
            error = ("A quantidade de vagas disponíveis deve ser no mínimo 0!");
        }
        if (error != null) {
            view.displayError(error);
            view.Enter();
            return;
        }
        ChargingStationModel chargingStation = new ChargingStationModel(name, location, cityId, availableConnectorsType, chargingPowerKW, pricePerKWh, availableVacancies);
        repository.add(chargingStation);
        view.displayMessage("Eletroposto cadastrado no sistema!");
        view.displayChargingStation(chargingStation);
        view.Enter();
    }
    public void searchChargingStationById() {
        int id = view.requestId();
        ChargingStationModel chargingStationFound = repository.searchById(id);
        if ( chargingStationFound != null) {
            view.displayMessage("Eletroposto encontrado!");
            view.displayChargingStation(chargingStationFound);
        }else {
            view.displayError("Eletroposto não encontrado no sistema!");
        }
        view.Enter();
    }
    public void updateChargingStation() {
        int id = view.requestId();
        String[] dates = view.requestDataForUpdate();
        String name = dates[0];
        String location = dates[1];
        String availableConnectorsTypeStr = dates[2];
        String chargingPowerKWStr = dates[3];
        String pricePerKWhStr = dates[4];
        String availableVacanciesStr  = dates[5];
        ChargingStationModel stationFound = repository.searchById(id);
        if (stationFound != null) {
            if (name != null && !name.isBlank()) {
                stationFound.setName(name);
            }
            if (location != null && !location.isBlank()) {
                stationFound.setLocation(location);
            }
            if (availableConnectorsTypeStr != null && !availableConnectorsTypeStr.isBlank()) {
                String[] availableConnectorsType = availableConnectorsTypeStr.trim().split(",");
                if (availableConnectorsType.length != 0) {
                    stationFound.setAvailableConnectorsType(availableConnectorsType);
                }
            }
            if (chargingPowerKWStr != null && !chargingPowerKWStr.isBlank()) {
                if(validDouble(chargingPowerKWStr)) {
                    double chargingPowerKW = Double.parseDouble(chargingPowerKWStr);
                    if (chargingPowerKW > 0) {
                        stationFound.setChargingPowerKW(chargingPowerKW);
                    }else {
                        view.displayError("A potência do carregador deve ser um valor positivo! (Será mantido o valor anterior)");
                    }
                }else {
                    view.displayError("Potência de carregamento inválida! (Será mantido o valor anterior)");
                }
            }
            if (pricePerKWhStr != null && !pricePerKWhStr.isBlank()) {
                if(validDouble(pricePerKWhStr)) {
                    double pricePerKWh = Double.parseDouble(pricePerKWhStr);
                    if (pricePerKWh >= 0) {
                        stationFound.setPricePerKWh(pricePerKWh);
                    }else {
                        view.displayError("O preço por quilowatt-hora deve ser no mínimo 0! (Será mantido o valor anterior)");
                    }
                }else {
                    view.displayError("Preço por kWh inválido! (Será mantido o valor anterior)");
                }
            }
            if (availableVacanciesStr != null && !availableVacanciesStr.isBlank()) {
                if (validInt(availableVacanciesStr)) {
                    int availableVacancies = Integer.parseInt(availableVacanciesStr);
                    if (availableVacancies >= 0) {
                        stationFound.setAvailableVacancies(availableVacancies);
                    }else {
                        view.displayError("A quantidade de vagas disponíveis deve ser no mínimo 0! (Será mantido o valor anterior)");
                    }
                }else {
                    view.displayError("Quantidade de vagas inválida! (Será mantido o valor anterior)");
                }
            }
            boolean result = repository.update(stationFound);
            if (result) {
                view.displayMessage("Eletroposto atualizado com sucesso!");
                view.displayChargingStation(stationFound);
            }else {
                view.displayError("Eletroposto não pode ser atualizado!");
            }
        }else {
            view.displayError("Eletroposto não encontrado no sistema!");
        }
        view.Enter();
    }
    public void deleteChargingStationById() {
        int id = view.requestId();
        ChargingStationModel chargingStationFound = repository.searchById(id);
        if (chargingStationFound == null) {
            view.displayError("Eletroposto não encontrado no sistema!");
            view.Enter();
            return;
        }
        view.displayMessage("Você está prestes a excluir o seguinte eletroposto: ");
        view.displayChargingStation(chargingStationFound);
        boolean confirmation = view.askForDeleteConfirmation();
        if (confirmation) {
            boolean result = repository.deleteById(id);
            if (result) {
                view.displayMessage("Eletroposto deletado com sucesso!");
            }
        }else {
            view.displayMessage("Ação cancelada, o eletroposto não foi removido!");
        }
        view.Enter();
    }
    public void listChargingStations() {
        ChargingStationModel[] stations = repository.getStations();
        if (stations != null && stations.length > 0) {
            for (ChargingStationModel station : stations) {
                if (station != null) {
                    view.displayChargingStation(station);
                }
            }
        }else {
            view.displayError("Nenhum eletroposto cadastrado no sistema!");
        }
        view.Enter();
    }
}