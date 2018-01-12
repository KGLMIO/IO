package sample.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import sample.DatabaseHelper;
import sample.Main;
import sample.Models.FormModel;
import sample.Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static sample.controllers.CreateNewFormController.FORM_STATUS.*;
import static sample.controllers.CreateNewFormController.FORM_STATUS.PRZETWARZANIE;

public class MainAdminController {


    public AnchorPane description_layout;
    public ListView list_view;
    public Label nazwaLabel;
    public Label opisLabel;
    public ComboBox status_combo_box;
    public Label imieLabel;
    public Label nazwiskoLabel;
    public Label loginLabel;
    public Label hasloLabel;
    public Label helloLabel;
    public Label adresLabel;
    public Label dataUrodzeniaLabel;
    public Label peselLabel;
    public TextField detriment_cost;
    public Label amountLabel;

    private FormModel currentModel;
    private ObservableList<FormModel> form_list;
    private ArrayList<User> users;
    private User user;

    public void setUser(User user) {
        this.user = user;
        helloLabel.setText("Witaj "+user.getName());
    }


    public void AcceptChanges(ActionEvent actionEvent) {

        currentModel.setStatus(status_combo_box.getSelectionModel().getSelectedItem().toString());
        currentModel.setAmount(Integer.parseInt(detriment_cost.getText()));
        if(currentModel==null )
            return;

        DatabaseHelper.updateFormStatus(currentModel);
        hideDescription();
    }


    public void hideDescription() {
        description_layout.setVisible(false);
    }

    public void updateList(){
        initList();
        form_list = FXCollections.observableArrayList();

        Connection connection =null;
        PreparedStatement ps =null;
        ResultSet rs = null;

        users = new ArrayList<>();

        try {
            connection= DatabaseHelper.getConnection();

            ps =connection.prepareStatement("SELECT * FROM User WHERE admin = ? ");
            ps.setBoolean(1,false);
            rs = ps.executeQuery();
            while(rs.next()){

                User user = new User(rs.getString("name"),rs.getString("surname"),
                        rs.getString("login"),rs.getString("password"), rs.getInt("id"),
                        rs.getBoolean("admin"), rs.getInt("pesel"), rs.getString("data_urodzenia"),
                        rs.getString("adres_zamieszkania"));

                users.add(user);
            }

            ps = connection.prepareStatement("SELECT * FROM form WHERE status = ? ");
            ps.setString(1,PRZETWARZANIE.toString());
            rs = ps.executeQuery();


            while(rs.next()){

                FormModel formModel = new FormModel(rs.getString("name"),rs.getString("description"),
                        rs.getString("status"),rs.getInt("id"), rs.getInt("userID"), rs.getInt("amount"));

                for(User u : users){
                    if(u.getId()== formModel.getUserID()){
                        formModel.setUser(u);
                        break;
                    }
                }

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
                    System.out.println();
                    setText(item.getName());
                }
            }
        });


        list_view.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<FormModel>() {

            @Override
            public void changed(ObservableValue<? extends FormModel> observable, FormModel oldValue, FormModel newValue) {

                currentModel =newValue;
                insertValuesIntoFields();
            }
        });

    }

    private void insertValuesIntoFields() {


        initComboBox();


        if(!description_layout.isVisible())
            description_layout.setVisible(true);


        status_combo_box.setValue(currentModel.getStatus());
        opisLabel.setText(currentModel.getDescripton());
        nazwaLabel.setText(currentModel.getName());

        imieLabel.setText(currentModel.getUser().getName());
        nazwiskoLabel.setText(currentModel.getUser().getSurname());
        loginLabel.setText(currentModel.getUser().getLogin());
        hasloLabel.setText(currentModel.getUser().getPassword());
        adresLabel.setText(currentModel.getUser().getAdres());
        dataUrodzeniaLabel.setText(currentModel.getUser().getData_ur());
        peselLabel.setText(Integer.toString(currentModel.getUser().getPesel()));
        amountLabel.setText(Integer.toString(currentModel.getAmount()));

    }

    private void initComboBox() {

       status_combo_box.getItems().clear();

            status_combo_box.getItems().addAll(
                    PRZETWARZANIE.toString(),
                    AKCEPTACJA.toString(),
                    ODMOWA.toString(),
                    KONTAKT.toString()
        );

    }


    public void GoToLoginScene(ActionEvent actionEvent) {
        hideDescription();
        Main.goToLogin();
    }

}
