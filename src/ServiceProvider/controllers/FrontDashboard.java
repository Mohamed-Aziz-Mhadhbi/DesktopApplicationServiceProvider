package ServiceProvider.controllers;

import Entities.User;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class FrontDashboard implements Initializable {

    public Button btnMyProfile;

    User user;
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnStudents;
    @FXML
    private Button btn_Timetable;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnClasses;
    @FXML
    private Button btnLogout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void profilesList(MouseEvent mouseEvent) throws IOException {
        System.out.println(user);
        Parent root = FXMLLoader.load(getClass().getResource("/ServiceProvider/view/dashboard.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void setUser(User userlogin) {
        this.user = userlogin;
    }

    @FXML
    public void showProfile(MouseEvent mouseEvent) throws IOException {
        Node node = (Node) mouseEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/ServiceProvider/view/userProfile.fxml"
                )
        );
        stage.setScene(
                new Scene(loader.load())
        );
        UserProfile myProfile = loader.getController();
        myProfile.setUser(this.user);

        stage.show();

    }

    @FXML
    public void logout(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ServiceProvider/view/login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showForum(ActionEvent event) throws IOException, SQLException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ServiceProvider/view/Forum.fxml"));
        stage.setScene(new Scene(loader.load()));
        ForumController fc = loader.getController();
        fc.setUser(this.user);

        stage.show();
    }

    @FXML
    private void goToOffre(ActionEvent event) throws IOException, SQLException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ServiceProvider/view/FXMLDocument.fxml"));
        stage.setScene(new Scene(loader.load()));
        FXMLDocumentController fc = loader.getController();
        fc.setUser(this.user);

        stage.show();
    }
}
