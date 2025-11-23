package es.cifpcarlos3;

import es.cifpcarlos3.model.Module;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ModuleFileLoader {

    public static final String SPLIT_REGEX = ",";

    public static List<Module> fromFile(Path filePath) {

        System.out.println("Leyendo fichero: " + filePath.getFileName() + "...");

        List<Module> modules = new ArrayList<>();

        //Abre el fichero para leerlo
        try(var reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)){

            String currentLine;

            //Mientras haya texto
            while((currentLine = reader.readLine()) !=null){

                //Coge el contenido de la línea y la limpia de caracteres extraños
                String cleanLine = cleanLine(currentLine);

                //Separa la línea
                String[] moduleParts = cleanLine.split(SPLIT_REGEX);

                //Obtiene todos los datos por separado de la línea para crear un módulo
                String name = moduleParts[0].trim();
                int hours = Integer.parseInt(moduleParts[1].trim());
                String dni = moduleParts[2].trim();

                //Crea el módulo
                Module module = new Module(name, hours, dni);

                //Añado el módulo a la lista
                modules.add(module);

            }

            //Devuelve los módulos
            return modules;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Limpia un String de caracteres invisibles
    private static String cleanLine (String currentLine) {

        if (!currentLine.isEmpty() && currentLine.charAt(0) == '\uFEFF') {
            return currentLine.substring(1).trim();
        }
        return currentLine.trim();
    }

}
