package sample.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;

import java.sql.*;

public class LoginController {

    public Label login_error_label;
    public TextField login_input_text_view;
    public PasswordField password_input_text_view;
    private Scene register_scene,login_scene,main_scene,create_form_scene;
private Stage primaryStage;

    public void moveToRegisterScene(ActionEvent actionEvent) {
       System.out.println("CLICK");
      Main.goToRegister();

    }




    public void Login(ActionEvent actionEvent) throws SQLException {
        Connection connection =null;
        PreparedStatement ps =null;
        ResultSet rs = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection= DriverManager.getConnection("jdbc:sqlite:@../../assets/database.sqlite");


        ps = connection.prepareStatement("SELECT * FROM User WHERE login = ? and password = ?");
        ps.setString(1,login_input_text_view.getText());
        ps.setString(2,password_input_text_view.getText());

            rs = ps.executeQuery();

            if(rs.next()){
                password_input_text_view.clear();
                Main.goToMain();
            }
            else{
                login_error_label.setText("Zły login i/lub hasło");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
