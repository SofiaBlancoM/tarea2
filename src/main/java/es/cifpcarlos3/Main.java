package es.cifpcarlos3;

import es.cifpcarlos3.dao.TeacherDao;
import es.cifpcarlos3.dao.impl.TeacherDaoImpl;
import es.cifpcarlos3.model.Module;
import es.cifpcarlos3.model.Teacher;
import es.cifpcarlos3.ui.MenuManager;
import es.cifpcarlos3.ui.MenuOption;
import es.cifpcarlos3.util.DatabaseConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        DatabaseConnection databaseConnection = new DatabaseConnection();

        TeacherDao teacherDao = new TeacherDaoImpl(databaseConnection);

        //Ruta por defecto del projecto
        Path rootProjectPath = Paths.get(Constants.ROOT_BASE_PATH).toAbsolutePath();

        //Lee el fichero txt y recupera la lista de módulos
        List<Module> modules = ModuleFileLoader.fromFile(rootProjectPath.resolve(Constants.MODULES_FILE_NAME));

        System.out.println("Bienvenido/a a la segunda tarea de Acceso a Datos");

        MenuOption selectedOption = MenuManager.getSelectedOption();

        while (selectedOption != MenuOption.EXIT) {

            switch (selectedOption) {
                case GET_MODULES:
                    System.out.println("Listando módulos...");
                    for (Module module : modules) {
                        String teacherMessage = "Falta en BD";
                        Teacher teacher = teacherDao.getByDni(module.getDni());
                        if (teacher != null) {
                            teacherMessage = teacher.getName() + " " + teacher.getSurnames();
                        }
                        System.out.println(module + " " + teacherMessage);
                    }
                    break;
                case ADD_PROFESSOR:
                    System.out.println("Introduce el DNI");
                    System.out.println("Introduce el nombre");
                    System.out.println("Introduce los apellidos");
                    System.out.println("Introduce el número de teléfono");
                    break;
                case DELETE_PROFESSOR:
                    System.out.println("Introduce el DNI del profesor");
                    break;
                case GET_ALL_PROFESSORS:
                    System.out.println("Listando profesores");
                    break;

            }

            selectedOption = MenuManager.getSelectedOption();

        }

        System.out.println("¡Gracias por utilizar nuestra aplicación, esperamos volver a verte pronto!");
        System.out.println("Cerrando...");

    }

}