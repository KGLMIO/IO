package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controllers.CreateNewFormController;
import sample.controllers.MainController;

public class Main extends Application {


  private static Scene loginScene,registerScene,mainScene,createFormScene;

  private static MainController mainController;

  private static CreateNewFormController createNewFormController;


  private static Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception{

        this.primaryStage=primaryStage;
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent login_scene_layout = loginLoader.load();
        loginScene = new Scene(login_scene_layout,600,600);


        Parent register_scene_layout = FXMLLoader.load(getClass().getResource("sign_up.fxml"));
        registerScene = new Scene(register_scene_layout,600,600);


        FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("main_form.fxml"));
        Parent main_screen_layout = loaderMain.load();
        mainController = loaderMain.getController();
        mainScene = new Scene(main_screen_layout,670,600);



        FXMLLoader loaderCreateFormLoader = new FXMLLoader(getClass().getResource("create_new_form.fxml"));
        Parent create_form_layout = loaderCreateFormLoader.load();
        createNewFormController = loaderCreateFormLoader.getController();
        createFormScene = new Scene(create_form_layout,600,600);

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

    public static void goToMain(int user_ID) {
        mainController.setUser_ID(user_ID);
        primaryStage.setTitle("Panel Główny");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        mainController.hideDescription();
    }

    public static void goToCreateForm(int user_ID) {
        createNewFormController.setUser_ID(user_ID);
        primaryStage.setTitle("Utwórz szkode");
        primaryStage.setScene(createFormScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
