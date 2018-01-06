package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {

    private static final String DATABASE_PATH="jdbc:sqlite:@../../assets/database.sqlite";

    private static Connection instance ;


    public static Connection connectToDatabase() throws SQLException {

        Connection connection=null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection= DriverManager.getConnection(DATABASE_PATH);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return  connection;
    }

    public static Connection getConnection() throws SQLException {
        if (instance==null)
           instance= connectToDatabase();

        return instance;
    }
}
