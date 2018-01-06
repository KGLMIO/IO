package sample;

import sample.Models.FormModel;
import sample.controllers.CreateNewFormController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static sample.controllers.CreateNewFormController.FORM_STATUS.PRZETWARZANIE;

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



    public static void updateFormStatus(FormModel formModel){



        Connection connection =null;
        PreparedStatement ps =null;

        try {
            connection= DatabaseHelper.getConnection();

            ps = connection.prepareStatement("UPDATE form SET status = ? WHERE id = ? ");

            ps.setString(1, formModel.getStatus());
            ps.setInt(2, formModel.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
