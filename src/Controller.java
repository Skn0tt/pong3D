//package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.security.auth.login.Configuration;

public class Controller extends Application{
    private Stage primaryStage;
    //Configuration configuration;
    public Button btnVerbinden;

    public Controller(){
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        this.primaryStage.setTitle("Hello World");
        this.primaryStage.setScene(new Scene(root, 850, 640));
        this.primaryStage.show();
    }

    void show(){
        launch();
    }

    public void btnStartenActionPerformed() {
        System.out.println("Hallo Welt");
    }

    public void btnVerbindenActionPerformed() {
        System.out.println("Hallo Welt");
    }
}
