package es.cifpcarlos3.ui;

import java.util.Scanner;

public class MenuManager {

    public static void printMenu () {

        System.out.println("Introduce el número correspondiente para seleccionar una de las siguientes opciones:");
        System.out.println("1. Listar módulos");
        System.out.println("2. Añadir profesor");
        System.out.println("3. Eliminar profesor por DNI");
        System.out.println("4. Listar todos los profesores");
        System.out.println("5. Salir");

    }

    public static MenuOption getSelectedOption() {

        Scanner scanner = new Scanner(System.in);

        MenuOption selectedOption = null;

        while (selectedOption == null) {

            printMenu();

            selectedOption = parseMenuOption(scanner.nextLine().trim());

            if (selectedOption == null) {
                System.out.println("La opción seleccionada no exite, por favor inténtelo de nuevo");
            }

        }

        return selectedOption;
    }

    private static MenuOption parseMenuOption(String input) {

        if (!input.matches("\\d") || input.equalsIgnoreCase("0")) {
            return null;
        }

        int menuOptionIndex = Integer.parseInt(input);

        if (menuOptionIndex >= MenuOption.values().length) {
            return null;
        }

        return MenuOption.values()[menuOptionIndex];

    }

}
