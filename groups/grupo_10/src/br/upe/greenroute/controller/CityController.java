package br.upe.greenroute.controller;

import br.upe.greenroute.model.CityModel;
import br.upe.greenroute.repository.ChargingStationRepository;
import br.upe.greenroute.repository.CityRepository;
import br.upe.greenroute.view.CityView;

public class CityController extends BaseController{
    private final CityRepository repository;
    private final CityView view;
    private final ChargingStationRepository stationRepository;
    public CityController (CityRepository repository, CityView view, ChargingStationRepository stationRepository) {
        this.repository = repository;
        this.view = view;
        this.stationRepository = stationRepository;
    }
    public void addCity () {
        String[] dates = view.requestDataForCreate();
        String name = dates[0];
        String state = dates[1];
        String capitalDistanceStr = dates[2];
        String error;
        error = isAnyBlank(name, state, capitalDistanceStr);
        if (error == null) {
            error = isDouble(capitalDistanceStr, "A distância deve ser um valor decimal");
        }
        if (error != null) {
            view.displayError(error);
            view.Enter();
            return;
        }
        double capitalDistance = Double.parseDouble(capitalDistanceStr);
        if (capitalDistance<0) {
            view.displayError("A distância da cidade para a capital não pode ser negativa!");
            view.Enter();
            return;
        }
        CityModel city = new CityModel(name, state, capitalDistance);
        repository.add(city);
        view.displayMessage("Cidade cadastrada!");
        view.displayCity(city);
        view.Enter();
    }
    public void searchCityById () {
        int id = view.requestId();
        CityModel cityFound = repository.searchById(id);
        if ( cityFound != null) {
            view.displayMessage("Cidade encontrada!");
            view.displayCity(cityFound);
        }else {
            view.displayError("Cidade não encontrada no sistema!");
        }
        view.Enter();
    }
    public void updateCity() {
        int id = view.requestId();
        String[] dates = view.requestDataForUpdate();
        String name = dates[0];
        String state = dates[1];
        String capitalDistanceStr = dates[2];
        CityModel cityFound = repository.searchById(id);
        if (cityFound != null) {
            view.displayCity(cityFound);
            if (name != null  && !name.isBlank()) {
                cityFound.setName(name);
            }
            if (state != null && !state.isBlank()) {
                cityFound.setState(state);
            }
            if (capitalDistanceStr != null && !capitalDistanceStr.isBlank()) {
                if (validDouble(capitalDistanceStr)) {
                    double capitalDistance = Double.parseDouble(capitalDistanceStr);
                    if(capitalDistance >= 0) {
                        cityFound.setCapitalDistance(capitalDistance);
                    }else {
                        view.displayError("A distância da capital deve ser maior que zero! (Será mantido o valor anterior)");
                    }
                }else {
                    view.displayError("O formato da nova distância é invalido! (Será mantido o valor anterior)");
                }
            }
            boolean result = repository.update(cityFound);
            if (result) {
                view.displayMessage("Cidade atualizada com sucesso!");
                view.displayCity(cityFound);
            }else {
                view.displayError("Cidade não pode ser atualizada!");
            }
        }else {
            view.displayError("Cidade não encontrada no sistema!");
        }
        view.Enter();
    }
    public void deleteCityById() {
        int id = view.requestId();
        CityModel cityFound = repository.searchById(id);
        if (cityFound == null) {
            view.displayError("Cidade não encontrada no sistema!");
            view.Enter();
            return;
        }
        view.displayMessage("Você está prestes a excluir a seguinte cidade: ");
        view.displayCity(cityFound);
        int sationsCount = stationRepository.countStationsByCityId(id);
        if (sationsCount > 0) {
            view.displayMessage("Esta cidade possui "+sationsCount+" eletroposto(s) vinculado(s).");
            view.displayMessage("Se você exclui-la, todos os eletropostos vinculados a ela serão apagados!");
        }
        boolean confirmation = view.askForDeleteConfirmation();
        if (confirmation) {
            if (sationsCount > 0) {
                if (stationRepository.deleteStationsByCityId(id)) {
                    view.displayMessage(sationsCount+" eletropostos deletados!");
                }else {
                    view.displayError("Não foi possivel deletar os eletropostos!");
                }
            }
            if (repository.deleteById(id)) {
                view.displayMessage("Cidade deletada com sucesso!");
            }
        }else {
            view.displayMessage("Ação cancelada, a cidade não foi removida!");
        }
        view.Enter();
    }
    public void listCities() {
        CityModel[] cities = repository.getCities();
        if (cities != null && cities.length > 0) {
            for (CityModel city : cities) {
                if (city != null) {
                    view.displayCity(city);
                }
            }
        }else {
            view.displayError("Nenhuma cidade cadastrada no sistema!");
        }
        view.Enter();
    }
}