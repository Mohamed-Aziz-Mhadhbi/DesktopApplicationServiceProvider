package ServiceProvider.controllers;

import Entities.User;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class ProfileCard {
    public ImageView profilePhoto;
    public Text username;
    public Text specialisation;
    public Button btnshow;
    private User user;
    public void showProfile(MouseEvent mouseEvent) {
    }

    public void setData(User user) {
        this.user = user;

        username.setText(user.getUsername());
        specialisation.setText(user.getSpecialisation());

    }
}
