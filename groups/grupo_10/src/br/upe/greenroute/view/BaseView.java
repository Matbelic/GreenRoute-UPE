package br.upe.greenroute.view;

import java.util.Scanner;

public abstract class BaseView {
    private final Scanner scanner;
    public BaseView(Scanner scanner) {
        this.scanner = scanner;
    }
    public void displayMessage(String message) {
        System.out.println("[INFO] "+ message);
    }
    public void displayError(String error) {
        System.out.println("[ERROR] "+ error);
    }
    public boolean askForDeleteConfirmation() {
        System.out.println("Você realmente deseja excluir este registro? (S/N): ");
        String answer = scanner.nextLine();
        return answer.equalsIgnoreCase("S");
    }
    public void Enter() {
        System.out.println("\nPressione ENTER para continuar. . .");
        scanner.nextLine();
    }
}
