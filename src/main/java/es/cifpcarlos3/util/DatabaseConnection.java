package es.cifpcarlos3.util;

import es.cifpcarlos3.Constants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    //Crea una nueva conexión a base de datos
    public Connection getConnection () throws SQLException {
        return DriverManager.getConnection(Constants.CONNECTION_STRING, Constants.USER, Constants.PASSWORD);
    }

}
