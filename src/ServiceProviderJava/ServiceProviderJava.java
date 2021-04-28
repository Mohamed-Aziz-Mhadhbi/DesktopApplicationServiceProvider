/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceProviderJava;


import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import Entities.Offre;


/**
 *
 * @author asus
 */
public class ServiceProviderJava extends Application {
    private Stage primaryStage;
    private Parent parentPage;
    @Override
    public void start(Stage primaryStage)  {
        
        this.primaryStage = primaryStage;
        
        try { 
        parentPage = FXMLLoader.load(getClass().getResource("/views/FXMLDocument.fxml"));
       //parentPage = FXMLLoader.load(getClass().getResource("/views/postulationFXML.fxml"));
        Scene scene = new Scene(parentPage);
        scene.getStylesheets().add("/views/styleSheetFront.css");
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
    launch(args);
    }
}

