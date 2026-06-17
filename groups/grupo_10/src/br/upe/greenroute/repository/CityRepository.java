package br.upe.greenroute.repository;

import br.upe.greenroute.model.CityModel;

public class CityRepository {
    private CityModel[] cities;
    private int count;
    private int id = 1;

    public CityRepository() {
        cities = new CityModel[5];
        count = 0;
    }

    private void expandArray() {
        CityModel[] newArray = new CityModel[cities.length * 2];
        for (int i = 0; i < cities.length; i++) {
            newArray[i] = cities[i];
        }
        cities = newArray;
    }

    public void add(CityModel city) {
        if (this.count == cities.length) {
            expandArray();
        }
        city.setId(this.id);
        cities[this.count++] = city;
        this.id++;
    }

    public CityModel searchById(int id) {
        for (int i = 0; i < this.count; i++) {
            if (cities[i].getId() == id) {
                return cities[i];
            }
        }
        return null;
    }
    public CityModel[] searchByState(String state) {
        CityModel[] citiesByState = new CityModel[this.count];
        int count = 0;
        for (int i = 0; i < this.count; i++) {
            if (cities[i].getState().equalsIgnoreCase(state)) {
                citiesByState[count++] = cities[i];
            }
        }
        CityModel[] finalCitiesByState = new CityModel[count];
        for (int i = 0; i < count; i++) {
            finalCitiesByState[i] = citiesByState[i];
        }
        return finalCitiesByState;
    }
    public boolean update(CityModel updateCity) {
        for (int i = 0; i < this.count; i++) {
            if (cities[i].getId()==updateCity.getId()) {
                cities[i] = updateCity;
                return true;
            }
        }
        return false;
    }
    public boolean deleteById(int id) {
        for (int i = 0; i < this.count; i++) {
            if (cities[i].getId() == id) {
                for (int j = i; j < this.count - 1; j++) {
                    cities[j] = cities[j + 1];
                }
                cities[this.count - 1] = null;
                this.count--;
                return true;
            }
        }
        return false;
    }
    public CityModel[] getCities() {
        return cities;
    }
    public boolean isEmpty() {
        return this.count == 0;
    }
}