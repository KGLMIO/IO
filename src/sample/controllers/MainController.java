package sample.controllers;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController  {
    public AnchorPane description_layout;


    public void GoToCreateNewFormButton(ActionEvent actionEvent) {
        Main.goToCreateForm();
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

}
