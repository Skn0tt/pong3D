//package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.security.auth.login.Configuration;

public class Controller extends Application{
    private Stage primaryStage;



    //Configuration configuration;

    public JFXButton btnVerbinden;
    public JFXButton btnStarten;
    public JFXTextField txtIp;
    public JFXTextField txtPort;
    public JFXRadioButton radioHost;
    public JFXRadioButton radioClient;
    public Label lblVerbunden;

    Main main;


    public Controller(){
        this.main = main;
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


    //Events

    public void btnStartenActionPerformed() {
        if(main.server == null) main.createServer(main.STANDARD_PORT);
        main.startGame();
    }

    public void btnVerbindenActionPerformed() {
        if (radioHost.isSelected()){
            main.createServer(Integer.parseInt(txtPort.getText()));
        }
        if(radioClient.isSelected()) {
            main.createClient(txtIp.getText(), Integer.parseInt(txtPort.getText()));
        }
    }

    public void radioHostStateChanged() {
        if(radioHost.isSelected()) modeHost();
        else modeClient();
    }
    public void radioClientStateChanged() {
        if (radioHost.isSelected()) modeHost();
        else modeClient();
    }


    //Methoden

    void modeClient(){
        txtIp.setEditable(true);
        txtIp.setDisable(false);
        txtIp.setText("");
        btnVerbinden.setText("Verbinden");
        btnStarten.setDisable(true);
    }

    void modeHost(){
        txtIp.setEditable(false);
        txtIp.setDisable(true);
        txtIp.setText(getIp());
        btnVerbinden.setText("Server Start");
        btnStarten.setDisable(false);
    }
    protected String getIp(){
        String ip = "Error: No IP Found";
        try {
            ip = InetAddress.getLocalHost().toString();
        } catch (UnknownHostException ex){}

        ip = ip.split("/")[1];
        return ip;
    }

    void setVerbunden(boolean b, String ip){
        if (b) lblVerbunden.setText("Verbunden mit " + ip);
        else lblVerbunden.setText("Nicht Verbunden");
    }

    public void setConnection(boolean verbunden){
        if(verbunden) lblVerbunden.setText("Verbunden");
        else lblVerbunden.setText("Nicht verbunden");
    }
}
