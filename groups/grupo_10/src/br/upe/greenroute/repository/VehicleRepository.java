package br.upe.greenroute.repository;

import br.upe.greenroute.model.VehicleModel;

public class VehicleRepository {

    private VehicleModel[] vehicles;
    private int count;
    private int id;

    public VehicleRepository() {
        vehicles = new VehicleModel[5];
        count = 0;
        id = 1;
    }

    private void expandArray() {
        VehicleModel[] newArray = new VehicleModel[vehicles.length * 2];
        for (int i = 0; i < vehicles.length; i++) {
            newArray[i] = vehicles[i];
        }
        vehicles = newArray;
    }

    public void add(VehicleModel vehicle) {
        if (this.count == vehicles.length) {
            expandArray();
        }
        vehicle.setId(this.id);
        vehicles[this.count++] = vehicle;
        this.id++;
    }
    public VehicleModel searchById(int id) {
        for (int i = 0; i < this.count; i++) {
            if (vehicles[i].getId() == id) {
                return vehicles[i];
            }
        }
        return null;
    }

    public boolean update(VehicleModel updateVehicle) {
        for (int i = 0; i < this.count; i++) {
            if (vehicles[i].getId() == updateVehicle.getId()) {
                vehicles[i] = updateVehicle;
                return true;
            }
        }
        return false;
    }
    public boolean deleteById(int id) {
        for (int i = 0; i < this.count; i++) {
            if (vehicles[i].getId() == id) {
                for (int j = i; j < this.count - 1; j++) {
                    vehicles[j] = vehicles[j + 1];
                }
                vehicles[this.count - 1] = null;
                this.count--;
                return true;
            }
        }
        return false;
    }
    public VehicleModel[] getVehicles() {
        return vehicles;
    }
    public boolean isEmpty() {
        return this.count == 0;
    }
}