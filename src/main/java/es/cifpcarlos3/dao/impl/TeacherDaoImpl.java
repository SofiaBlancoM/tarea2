package es.cifpcarlos3.dao.impl;

import es.cifpcarlos3.dao.TeacherDao;
import es.cifpcarlos3.model.Teacher;
import es.cifpcarlos3.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

            System.out.println("Líneas afectadas: " + affectedRows);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
