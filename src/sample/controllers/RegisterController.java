package sample.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.DatabaseHelper;
import sample.Main;

import java.sql.*;

public class RegisterController {


    public TextField register_name_input_text_view;
    public PasswordField register_password_input_text_view;
    public TextField register_surname_input_text_view;
    public TextField register_login_input_text_view;
    public Label register_error_label;

    public void GoToLoginPage(ActionEvent actionEvent) {

        Main.goToLogin();

    }


    public void registerUser(ActionEvent actionEvent) throws SQLException {

        if(register_login_input_text_view.getText().isEmpty() ||
                register_name_input_text_view.getText().isEmpty() ||
                register_password_input_text_view.getText().isEmpty() ||
                register_surname_input_text_view.getText().isEmpty())
            register_error_label.setText("Wypełnij wszystkie pola");
        else
        {

            Connection connection = DatabaseHelper.getConnection();
            PreparedStatement ps =null;
            ResultSet rs = null;

                ps = connection.prepareStatement( "INSERT INTO User"
                        + "(name, surname, password, login) VALUES"+ "(?,?,?,?)");

                ps.setString(1, register_name_input_text_view.getText());
                ps.setString(2, register_surname_input_text_view.getText());
                ps.setString(3, register_password_input_text_view.getText());
                ps.setString(4, register_login_input_text_view.getText());


                register_surname_input_text_view.clear();
                register_password_input_text_view.clear();
                register_name_input_text_view.clear();
                register_login_input_text_view.clear();
                register_error_label.setText("");

        // execute insert SQL stetement
                ps .executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Zarejestrowano");
            alert.setHeaderText(null);
            alert.setContentText("Gratulacje! Teraz możesz się zalogować do systemu");

            alert.showAndWait();
                Main.goToLogin();
        }
    }
}
