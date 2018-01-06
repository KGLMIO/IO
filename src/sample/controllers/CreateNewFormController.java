package sample.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.DatabaseHelper;
import sample.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateNewFormController {

    private static final String PRZETWARZANIE= "NIE ROZSTRZYGNIETO";

    public TextArea detriment_description;
    public Label error_message;
    public TextField detriment_name;

    private int user_ID;

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public void GoToMainPage(ActionEvent actionEvent) {
        Main.goToMain(user_ID);
    }

    public void saveFormIntoDataBase(ActionEvent actionEvent) throws SQLException {

        Connection connection = DatabaseHelper.getConnection();

        PreparedStatement ps =null;
        ResultSet rs = null;

        ps = connection.prepareStatement( "INSERT INTO form"
                + "(name, description, status, userID) VALUES"+ "(?,?,?,?)");

        ps.setString(1, detriment_name.getText());
        ps.setString(2, detriment_description.getText());
        ps.setString(3,PRZETWARZANIE );
        ps.setInt(4, user_ID);

        ps .executeUpdate();

        clearFields();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pomyslnie dodano");
        alert.setHeaderText(null);
        alert.setContentText("Zgłoszenie zostało przyjęte!");

        alert.showAndWait();
        Main.goToMain(user_ID);


    }

    private void clearFields(){
        detriment_description.clear();
        detriment_name.clear();
    }
}
