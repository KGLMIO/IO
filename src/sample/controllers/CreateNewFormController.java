package sample.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import sample.DatabaseHelper;
import sample.Main;
import sample.Models.FormModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateNewFormController {


    public Button save_form_button;

    public enum FORM_STATUS {
        PRZETWARZANIE,ODMOWA,AKCEPTACJA,KONTAKT
    };


    private boolean edit;
    private FormModel currentModel;


    public TextArea detriment_description;
    public Label error_message;
    public TextField detriment_name;

    private int user_ID;

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;

    }

    public void setEditModel(FormModel currentModel) {
        edit=true;
        this.currentModel=currentModel;
        detriment_description.setText(currentModel.getDescripton());
        detriment_name.setText(currentModel.getName());
        save_form_button.setText("Modyfikuj");
    }


    public void GoToMainPage(ActionEvent actionEvent) {
        Main.goToMain(user_ID);
        edit=false;
        currentModel=null;
        save_form_button.setText("Utwórz szkode");
    }

    public void saveFormIntoDataBase(ActionEvent actionEvent) throws SQLException {


        Connection connection = DatabaseHelper.getConnection();
        PreparedStatement ps =null;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(edit) {
            ps = connection.prepareStatement("UPDATE form SET name = ? , description = ?  WHERE id = ? ");

            ps.setString(1, detriment_name.getText());
            ps.setString(2, detriment_description.getText());
            ps.setInt(3,currentModel.getId());
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Pomyslnie zmodyfikowano");
        }
        else
        {
            ps = connection.prepareStatement("INSERT INTO form"
                    + "(name, description, status, userID) VALUES" + "(?,?,?,?)");

            ps.setString(1, detriment_name.getText());
            ps.setString(2, detriment_description.getText());
            ps.setString(3, FORM_STATUS.PRZETWARZANIE.toString());
            ps.setInt(4, user_ID);
            alert.setTitle("Pomyslnie dodano");
            alert.setHeaderText(null);
            alert.setContentText("Zgłoszenie zostało przyjęte!");
        }
        ps .executeUpdate();

        clearFields();




        alert.showAndWait();
        Main.goToMain(user_ID);


    }

    private void clearFields(){
        detriment_description.clear();
        detriment_name.clear();
    }


}
