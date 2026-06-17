package br.upe.greenroute;
import java.util.Scanner;

import br.upe.greenroute.controller.ChargingStationController;
import br.upe.greenroute.controller.TripController;
import br.upe.greenroute.controller.VehicleController;
import br.upe.greenroute.repository.ChargingStationRepository;
import br.upe.greenroute.view.*;
import br.upe.greenroute.controller.CityController;
import br.upe.greenroute.repository.CityRepository;
import br.upe.greenroute.repository.VehicleRepository;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CityRepository cityRepository = new CityRepository();
        CityView cityView = new CityView(scanner);
        VehicleRepository vehicleRepository = new VehicleRepository();
        VehicleView vehicleView = new VehicleView(scanner);
        VehicleController vehicleController = new VehicleController(vehicleRepository, vehicleView);
        ChargingStationView chargingStationView = new ChargingStationView(scanner);
        ChargingStationRepository chargingStationRepository = new ChargingStationRepository();
        ChargingStationController chargingStationController = new ChargingStationController(chargingStationRepository, cityRepository, chargingStationView);
        CityController cityController = new CityController(cityRepository, cityView, chargingStationRepository);
        TripView tripView = new TripView(scanner);
        TripController tripController = new TripController(tripView, vehicleView, vehicleRepository, cityView, cityRepository, chargingStationView, chargingStationRepository);

        MainMenu mainMenu = new MainMenu(scanner, vehicleView, vehicleRepository, vehicleController, cityView, cityRepository, cityController, chargingStationView, chargingStationRepository, chargingStationController,
                tripView, tripController);
        mainMenu.showMenu();
        scanner.close();
    }
}