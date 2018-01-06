package sample.controllers;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController  {
    public AnchorPane description_layout;
    private int user_ID;

    public void GoToCreateNewFormButton(ActionEvent actionEvent) {
        Main.goToCreateForm(user_ID);
    }

    public void GoToLoginScene(ActionEvent actionEvent) {
        Main.goToLogin();
    }

    public void hideDescription(){
        description_layout.setVisible(false);
    }

    public void showDescription(){
        description_layout.setVisible(true);
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }
}
