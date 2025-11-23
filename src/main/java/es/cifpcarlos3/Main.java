package es.cifpcarlos3;

import es.cifpcarlos3.model.Module;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static final String MODULES_FILE_NAME = "datos_modulos.txt";

    public static void main(String[] args) {

        //Ruta por defecto del projecto
        Path rootProjectPath = Paths.get("").toAbsolutePath();

        //Lista con los módulos del fichero txt
        List<Module> modules = ModuleFileLoader.fromFile(rootProjectPath.resolve(MODULES_FILE_NAME));

        System.out.println(modules);
    }

}