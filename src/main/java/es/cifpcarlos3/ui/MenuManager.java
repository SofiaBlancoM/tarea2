package es.cifpcarlos3.ui;

import java.util.Scanner;

//Clase que se encarga de gestionar el menú
public class MenuManager {

    //Saca por consola todas las opciones a elegir
    public static void printMenu () {

        System.out.println("Introduce el número correspondiente para seleccionar una de las siguientes opciones:");
        System.out.println("1) Listar módulos con profesor");
        System.out.println("2) Añadir profesor");
        System.out.println("3) Eliminar profesor por DNI");
        System.out.println("4) Listar todos los profesores");
        System.out.println("5) Salir");

    }

    //Obtiene la opción seleccionada por el usuario
    public static MenuOption getSelectedOption() {

        Scanner scanner = new Scanner(System.in);

        MenuOption selectedOption = null;

        //Mientras no seleccione una opción válida
        while (selectedOption == null || selectedOption == MenuOption.NONE) {

            //Muestra las opciones al usuario
            printMenu();

            //Procesa el input del usuario
            selectedOption = parseMenuOption(scanner.nextLine().trim());

            //Si no es una opción válida vuelve a preguntar
            if (selectedOption == MenuOption.NONE) {
                System.out.println("La opción seleccionada no exite, por favor inténtelo de nuevo");
            }

        }

        //Devuelve la opción seleccionada
        return selectedOption;
    }

    //Procesa el input del usuario para ver si es una opción válida
    private static MenuOption parseMenuOption(String input) {

        //Si no es un número o es un 0, lo marca como opción no válida
        if (!input.matches("\\d") || input.equalsIgnoreCase("0")) {
            return MenuOption.NONE;
        }

        int menuOptionIndex = Integer.parseInt(input);

        //Si el número no corresponde con ninguna opción existente, lo marca como no válida
        if (menuOptionIndex >= MenuOption.values().length) {
            return MenuOption.NONE;
        }

        //Devuelve la opción seleccionada
        return MenuOption.values()[menuOptionIndex];

    }

}
