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

public class TeacherDaoImpl implements TeacherDao {

    private final DatabaseConnection databaseConnection;

    public TeacherDaoImpl (DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public Teacher getByDni (String dni) {

        String query = "SELECT * FROM T_PROFESOR WHERE dni = ?";

        try (Connection connection = databaseConnection.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dni);
            ResultSet result = preparedStatement.executeQuery();

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

    @Override
    public void add(Teacher teacher) {

        String query = "INSERT INTO T_PROFESOR VALUES (?, ?, ?, ?)" ;

        try (Connection connection = databaseConnection.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, teacher.getDni());
            preparedStatement.setString(2, teacher.getName());
            preparedStatement.setString(3, teacher.getSurnames());
            preparedStatement.setString(4, teacher.getPhone());
            int affectedRows = preparedStatement.executeUpdate();

            System.out.println("Se ha añadido " + affectedRows + " profesor correctamente");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(String dni) {

        String query = "DELETE FROM T_PROFESOR WHERE dni = ?";

        try (Connection connection = databaseConnection.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dni);
            int affectedRows = preparedStatement.executeUpdate();

            System.out.println("Se ha borrado " + affectedRows + " profesor correctamente");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Teacher> getAll() {

        String query = "SELECT apellidos, nombre, dni, telefono FROM T_PROFESOR ORDER BY apellidos asc, nombre asc";

        List<Teacher> teachers = new ArrayList<>();

        try (Connection connection = databaseConnection.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                teachers.add(new Teacher(result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4)));
            }

            return teachers;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
