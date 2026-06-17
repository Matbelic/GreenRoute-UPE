package br.upe.greenroute.view;

import br.upe.greenroute.controller.ChargingStationController;
import br.upe.greenroute.controller.CityController;
import br.upe.greenroute.controller.TripController;
import br.upe.greenroute.controller.VehicleController;
import br.upe.greenroute.repository.ChargingStationRepository;
import br.upe.greenroute.repository.CityRepository;
import br.upe.greenroute.repository.VehicleRepository;

import java.util.Scanner;

public class MainMenu {
    private final Scanner scanner;
    private final VehicleController vehicleController;
    private final VehicleView vehicleView;
    private final VehicleMenu vehicleMenu;
    private final VehicleRepository vehicleRepository;
    private final CityController cityController;
    private final CityView cityView;
    private final CityMenu cityMenu;
    private final CityRepository cityRepository;
    private final ChargingStationController chargingStationController;
    private final ChargingStationView chargingStationView;
    private final ChargingStationMenu chargingStationMenu;
    private final ChargingStationRepository chargingStationRepository;
    private final TripView tripView;
    private final TripController tripController;

    public MainMenu(Scanner scanner, VehicleView vehicleView, VehicleRepository vehicleRepository, VehicleController vehicleController,
                    CityView cityView, CityRepository cityRepository, CityController cityController,
                    ChargingStationView chargingStationView, ChargingStationRepository chargingStationRepository, ChargingStationController chargingStationController,
                    TripView tripView, TripController tripController) {
        this.scanner = scanner;
        this.vehicleView = vehicleView;
        this.vehicleController = vehicleController;
        this.vehicleRepository = vehicleRepository;
        this.vehicleMenu = new VehicleMenu(scanner, vehicleView, vehicleController);
        this.cityView = cityView;
        this.cityController = cityController;
        this.cityRepository = cityRepository;
        this.cityMenu = new CityMenu(scanner,cityView, cityController);
        this.chargingStationView = chargingStationView;
        this.chargingStationRepository = chargingStationRepository;
        this.chargingStationController = chargingStationController;
        this.chargingStationMenu = new ChargingStationMenu(scanner, chargingStationView, chargingStationController);
        this.tripController = tripController;
        this.tripView = tripView;
    }

    public void showMenu() {
        String opcao;
        boolean executando = true;
        while (executando) {
            System.out.print("""
              \n
                 ________    ⠀⠀⠀ ⣀⣤⣶⠖⠛⠉⠉⠉⠉⣿⠉⠉⠙⠒⠦⣄⠀⠀⠀⠀⠀\s
                 |[____]|⠀⠀  ⠀⢲⣶⣿⣿⣿⣷⡶⠦⣦⣤⣤⣤⣽⣧⣤⣤⣤⣴⣿⣿⣶⣤⣤⣤⣀⣀⣀
                 |⣿    +|>---⣴⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣉⣂
                 |⣿_____|    ⢿⣿⣿⣿⣿⠏⠀⠈⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⠈⣿⣿⣿⣿
                 [⣿_____]    ⠀⠀ ⠈⠙⢧⣄⣠⠞⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠻⠦⣤⠴⠋⠉⠀⠀
              ================================================
              ____ ____ ____ ____ _  _ ____ ____ _  _ ___ ____\s
              | __ |__/ |___ |___ |\\ | |__/ |  | |  |  |  |___\s
              |__] |  \\ |___ |___ | \\| |  \\ |__| |__|  |  |___
              ================================================
              SISTEMA DE LOGÍSTICA INTELIGENTE""");
            System.out.println(" ");
            System.out.println("\nEscolha uma opção: ");
            System.out.println("\n1. Gerenciar veiculos");
            System.out.println("2. Gerenciar cidades");
            System.out.println("3. Gerenciar eletropostos");
            System.out.println("4. Simular viagem da capital");
           System.out.println("0. Sair");
           opcao = scanner.nextLine();
           switch (opcao) {
               case "1" -> vehicleMenu.showMenu();
               case "2" -> cityMenu.showMenu();
               case "3" -> chargingStationMenu.showMenu();
               case "4" -> tripController.TripSimulation();
               case "0" -> {
               System.out.println("Encerrando o programa . . .");
               executando = false;
               }
               default -> vehicleView.displayError("\nDigite uma opção válida!");
           }
        }
    }
}