package es.cifpcarlos3;

import es.cifpcarlos3.model.Module;
import es.cifpcarlos3.ui.MenuManager;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final String MODULES_FILE_NAME = "datos_modulos.txt";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //Ruta por defecto del projecto
        Path rootProjectPath = Paths.get("").toAbsolutePath();

        //Lee el fichero txt y recupera la lista de módulos
        List<Module> modules = ModuleFileLoader.fromFile(rootProjectPath.resolve(MODULES_FILE_NAME));

        System.out.println("Bienvenido/a a la segunda tarea de Acceso a Datos");

        MenuManager.getSelectedOption();


    }

}