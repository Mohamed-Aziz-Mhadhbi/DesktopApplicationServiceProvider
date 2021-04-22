package ServiceProvider.controllers;

import Entities.User;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class UserProfile implements Initializable {
    public Button btnReturn;
    public ImageView profilePhoto;
    public Text username;
    public Text email;
    public Button btnEdit;
    public Button btnChangePwd;
    public Text name;
    public Text aboutMe;
    public Text specialisation;
    public Text salary;
    public Text phone;
    User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void goBack(MouseEvent mouseEvent) {
    }

    public void editProfile(MouseEvent mouseEvent) {
    }

    public void changePassword(MouseEvent mouseEvent) {
    }

    public void setUser(User u) {
        this.user = u;
    }
}
