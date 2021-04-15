package ServiceProvider;

import Utils.dbConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Connection cnx = dbConnection.getInstance().getConnection();
        Parent root = FXMLLoader.load(getClass().getResource("view/main.fxml"));
        primaryStage.setTitle("Service Provider");
        primaryStage.setScene(new Scene(root, 1200, 800));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
