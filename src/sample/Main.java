package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Models.FormModel;
import sample.Models.User;
import sample.controllers.CreateNewFormController;
import sample.controllers.InsuranceNrController;
import sample.controllers.MainAdminController;
import sample.controllers.MainController;

public class Main extends Application {


  private static Scene loginScene,registerScene,mainScene,createFormScene,mainAdminScene, insuranceNrScene;

  private static MainController mainController;
private static MainAdminController mainAdminController;
  private static CreateNewFormController createNewFormController;
  private static InsuranceNrController insuranceNrController;


  private static Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception{

        this.primaryStage=primaryStage;
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("views/login.fxml"));
        Parent login_scene_layout = loginLoader.load();
        loginScene = new Scene(login_scene_layout,600,600);

        Parent register_scene_layout = FXMLLoader.load(getClass().getResource("views/sign_up.fxml"));
        registerScene = new Scene(register_scene_layout,600,600);

        FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("views/main_form.fxml"));
        Parent main_screen_layout = loaderMain.load();
        mainController = loaderMain.getController();
        mainScene = new Scene(main_screen_layout,670,600);

        FXMLLoader loaderMainAdmin = new FXMLLoader(getClass().getResource("views/main_admin_form.fxml"));
        Parent main_admin_screen_layout = loaderMainAdmin.load();
        mainAdminController = loaderMainAdmin.getController();
        mainAdminScene = new Scene(main_admin_screen_layout,900,600);

        FXMLLoader loaderCreateFormLoader = new FXMLLoader(getClass().getResource("views/create_new_form.fxml"));
        Parent create_form_layout = loaderCreateFormLoader.load();
        createNewFormController = loaderCreateFormLoader.getController();
        createFormScene = new Scene(create_form_layout,600,600);

        FXMLLoader loaderInsuranceNr = new FXMLLoader(getClass().getResource("views/insurance_nr.fxml"));
        Parent insurance_nr = loaderInsuranceNr.load();
        insuranceNrController = loaderInsuranceNr.getController();
        insuranceNrScene = new Scene(insurance_nr,600,600);

        goToLogin();
    }

    public static void goToRegister(){
        primaryStage.setTitle("Rejestracja");
        primaryStage.setScene(registerScene);
        primaryStage.show();
    }

    public static void goToLogin() {

        primaryStage.setTitle("Logowanie");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    public static void goToMain(User user) {
        primaryStage.setTitle("Panel Główny");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        mainController.hideDescription();
        mainController.setUser(user);
        mainController.updateList();
    }

    public static void goToMainAdmin(User user) {
        primaryStage.setTitle("Panel Główny - Admin");
        primaryStage.setScene(mainAdminScene);
        primaryStage.show();
        mainAdminController.hideDescription();
        mainAdminController.setUser(user);
        mainAdminController.updateList();
    }

    public static void goToCreateForm(User user) {
        createNewFormController.setUser(user);
        primaryStage.setTitle("Utwórz szkode");
        primaryStage.setScene(createFormScene);
        primaryStage.show();
    }

    public static void goToInsuranceNr(User user) {
        insuranceNrController.setUser(user);
        primaryStage.setTitle("Podaj numer polisy");
        primaryStage.setScene(insuranceNrScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void EditForm(FormModel currentModel) {
        createNewFormController.setEditModel(currentModel);
    }
}
