package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DB {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/lost_n_found";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Swapnil@28@";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}

  