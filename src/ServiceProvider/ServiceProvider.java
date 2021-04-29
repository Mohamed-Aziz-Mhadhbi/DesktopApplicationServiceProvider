package serviceprovider;


import Utils.dbConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;

public class ServiceProvider extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        
        Parent root = FXMLLoader.load(getClass().getResource("view/Project.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("view/SiteWeb.fxml"));
        primaryStage.setTitle("Service Provider");
        primaryStage.setScene(new Scene(root, 900, 550));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
