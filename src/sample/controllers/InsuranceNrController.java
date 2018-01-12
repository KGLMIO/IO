package sample.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.DatabaseHelper;
import sample.Main;
import sample.Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsuranceNrController {

    public Label insurance_error_label;
    public TextField insurance_input_text_view;
    private User user;
    public void setUser(User user) {
        this.user = user;
    }

    public void GoToMainPage(ActionEvent actionEvent) throws SQLException {

        Connection connection =null;
        PreparedStatement ps =null;
        ResultSet rs = null;

        connection= DatabaseHelper.getConnection();

        ps = connection.prepareStatement("SELECT * FROM user WHERE nr_polisy = ?");

        ps.setString(1,insurance_input_text_view.getText());


        rs = ps.executeQuery();

        if(rs.next()){
            insurance_input_text_view.clear();

            Main.goToCreateForm(user);

        }
        else{
            insurance_error_label.setText("Nie ma takiego numeru polisy");
        }
    }

    public void GoToLoginPage(ActionEvent actionEvent) {
        Main.goToMain(user);
    }
}
