package sample.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import sample.DatabaseHelper;
import sample.Main;
import sample.Models.FormModel;
import sample.Models.User;
import sample.controllers.CreateNewFormController.FORM_STATUS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static sample.controllers.CreateNewFormController.FORM_STATUS.PRZETWARZANIE;

public class MainController  {

    public AnchorPane description_layout;
    public ListView list_view;
    public Label statusLabel;
    public Label nazwaLabel;
    public Label opisLabel;
    public Label helloLabel;

    private User user;
    private FormModel currentModel;
    private ObservableList<FormModel> form_list;

    public void GoToCreateNewFormButton(ActionEvent actionEvent) {
        Main.goToCreateForm(user);
    }

    public void GoToLoginScene(ActionEvent actionEvent) {
        hideDescription();
        Main.goToLogin();
    }

    public void hideDescription(){
       description_layout.setVisible(false);
    }

    public void showDescription(){
        description_layout.setVisible(true);
    }

    public void setUser(User user) {
        this.user = user;
        helloLabel.setText("Witaj "+user.getName());
    }



    public void updateList(){
        initList();
        form_list = FXCollections.observableArrayList();

        Connection connection =null;
        PreparedStatement ps =null;
        ResultSet rs = null;


        try {
            connection= DatabaseHelper.getConnection();

            ps = connection.prepareStatement("SELECT * FROM form WHERE userID = ? ");
            ps.setInt(1, user.getId());

            rs = ps.executeQuery();

            while(rs.next()){

                FormModel formModel = new FormModel(rs.getString("name"),rs.getString("description"),
                        rs.getString("status"),rs.getInt("id"), rs.getInt("userID"));

                form_list.add(formModel);
            }

    list_view.setItems(form_list);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initList() {

        list_view.setCellFactory(param -> new ListCell<FormModel>() {
            @Override
            protected void updateItem(FormModel item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText("");
                } else {
                    setText(item.getName());
                }
            }
        });
        
        
        list_view.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<FormModel>() {

            @Override
            public void changed(ObservableValue<? extends FormModel> observable, FormModel oldValue, FormModel newValue) {
                // Your action here
                currentModel = newValue;
                insertValuesIntoFields();
            }
        });
    }

    private void insertValuesIntoFields() {
        
        if(!description_layout.isVisible())
            description_layout.setVisible(true);

       if(currentModel.getStatus().equals(FORM_STATUS.PRZETWARZANIE.toString()))
        statusLabel.setTextFill(Color.web("#6e6f72"));
       else if(currentModel.getStatus().equals(FORM_STATUS.AKCEPTACJA.toString()))
            statusLabel.setTextFill(Color.web("#1da200"));
       else if(currentModel.getStatus().equals(FORM_STATUS.ODMOWA.toString()))
            statusLabel.setTextFill(Color.web("#b52924"));
      else  if(currentModel.getStatus().equals(FORM_STATUS.KONTAKT.toString()))
            statusLabel.setTextFill(Color.web("#0076a3"));

        statusLabel.setText(currentModel.getStatus());
        opisLabel.setText(currentModel.getDescripton());
        nazwaLabel.setText(currentModel.getName());
    }

    public void modifyForm(ActionEvent actionEvent) {
        Main.goToCreateForm(user);
        Main.EditForm(currentModel);
    }

    public void reneawForm(ActionEvent actionEvent)  {

        if(currentModel.getStatus().equals(PRZETWARZANIE.toString()))
            return;

        currentModel.setStatus(FORM_STATUS.PRZETWARZANIE.toString());
        DatabaseHelper.updateFormStatus(currentModel);
        insertValuesIntoFields();
    }
}
