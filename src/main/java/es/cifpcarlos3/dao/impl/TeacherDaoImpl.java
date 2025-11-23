package es.cifpcarlos3.dao.impl;

import es.cifpcarlos3.dao.TeacherDao;
import es.cifpcarlos3.model.Teacher;
import es.cifpcarlos3.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Clase que se encarga de comunicarse con la base de datos para tratar con la entidad teacher
public class TeacherDaoImpl implements TeacherDao {

    //Clase que centraliza la creación de una nueva conexión a base de datos
    private final DatabaseConnection databaseConnection;

    public TeacherDaoImpl (DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    //Obtiene un profesor por su dni
    @Override
    public Teacher getByDni (String dni) {

        //Consulta a realizar en base de datos
        String query = "SELECT * FROM T_PROFESOR WHERE dni = ?";

        //Crea una nueva conexión a base de datos como autoclosable
        try (Connection connection = databaseConnection.getConnection()) {

            //Utilizamos PreparedStatement para añadir parámetros a la query de forma segura
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dni);
            ResultSet result = preparedStatement.executeQuery();

            //Si la query tiene resultado, mapeamos el teacher y lo devolvemos
            if(result.next()) {
                return new Teacher(result.getString("dni"),
                        result.getString("nombre"),
                        result.getString("apellidos"),
                        result.getString("telefono"));
            } else {
                return null;
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Crea un profesor en base de datos
    @Override
    public void add(Teacher teacher) {

        //Consulta a realizar en base de datos
        String query = "INSERT INTO T_PROFESOR VALUES (?, ?, ?, ?)" ;

        //Crea una nueva conexión a base de datos como autoclosable
        try (Connection connection = databaseConnection.getConnection()) {

            //Utilizamos PreparedStatement para añadir parámetros a la query de forma segura
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, teacher.getDni());
            preparedStatement.setString(2, teacher.getName());
            preparedStatement.setString(3, teacher.getSurnames());
            preparedStatement.setString(4, teacher.getPhone());

            //Ejecuta la query de tipo update
            int affectedRows = preparedStatement.executeUpdate();

            System.out.println("Se ha añadido " + affectedRows + " profesor correctamente");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Elimina un profesor por su dni si existe en la base de datos
    @Override
    public void delete(String dni) {

        //Consulta a realizar en base de datos
        String query = "DELETE FROM T_PROFESOR WHERE dni = ?";

        //Crea una nueva conexión a base de datos como autoclosable
        try (Connection connection = databaseConnection.getConnection()) {

            //Utilizamos PreparedStatement para añadir parámetros a la query de forma segura
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dni);
            int affectedRows = preparedStatement.executeUpdate();

            System.out.println("Se ha borrado " + affectedRows + " profesor correctamente");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Devuelve una lista con todos los profesores de la base de datos
    @Override
    public List<Teacher> getAll() {

        //Consulta a realizar en base de datos
        String query = "SELECT apellidos, nombre, dni, telefono FROM T_PROFESOR ORDER BY apellidos asc, nombre asc";

        //Crea una lista para meter a los profesores sacados de la consulta
        List<Teacher> teachers = new ArrayList<>();

        //Crea una nueva conexión a base de datos como autoclosable
        try (Connection connection = databaseConnection.getConnection()) {

            //Utilizamos PreparedStatement para añadir parámetros a la query de forma segura
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();

            //Si la query tiene resultado añadimos el profesor a la lista
            while (result.next()) {
                teachers.add(new Teacher(result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4)));
            }

            //Devuelve la lista
            return teachers;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
