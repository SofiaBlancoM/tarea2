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

        //Obtiene del input del usuario una opción válida
        MenuOption selectedOption = MenuManager.getSelectedOption();

        //Mientras no haya elegido la opción de salirse
        while (selectedOption != MenuOption.EXIT) {

            switch (selectedOption) {
                //Lista los módulos y recupera los datos del profesor si existe
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

                //Crea un profesor si no existe en base de datos
                case ADD_TEACHER:
                    System.out.println("Introduce el DNI");
                    String dni = scanner.nextLine().trim();

                    Teacher teacher = teacherDao.getByDni(dni);
                    if (teacher != null) {
                        System.out.println("El profesor ya existe");
                        break;
                    }

                    System.out.println("Introduce el nombre");
                    String name = scanner.nextLine().trim();

                    System.out.println("Introduce los apellidos");
                    String surnames = scanner.nextLine().trim();

                    System.out.println("Introduce el número de teléfono");
                    String phone = scanner.nextLine().trim();

                    Teacher newTeacher = new Teacher(dni, name, surnames, phone);
                    teacherDao.add(newTeacher);
                    break;
                //Borra un profesor si existe en base de datos
                case DELETE_TEACHER:
                    System.out.println("Introduce el DNI del profesor");

                    String dniToDelete = scanner.nextLine().trim();

                    Teacher teacherToDelete = teacherDao.getByDni(dniToDelete);
                    if (teacherToDelete == null) {
                        System.out.println("El profesor no existe");
                        break;
                    }

                    teacherDao.delete(dniToDelete);
                    break;
                //Obtiene todos los profesores de base de datos
                case GET_ALL_TEACHERS:
                    System.out.println("Listando profesores...");
                    List<Teacher> teachers = teacherDao.getAll();
                    for (Teacher currentTeacher : teachers) {
                        System.out.println(currentTeacher);
                    }
                    break;

            }

            //Vuelve a pedir al usuario que seleccione una opción
            selectedOption = MenuManager.getSelectedOption();

        }

        //Finaliza el programa
        System.out.println("¡Gracias por utilizar nuestra aplicación, esperamos volver a verte pronto!");
        System.out.println("Cerrando...");

    }

}