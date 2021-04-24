package ServiceProvider.controllers;

import Entities.User;
import Services.ServiceUser;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    public Button btnReturn;
    public ScrollPane scroll;
    public GridPane grid;

    private List<User> users = new ArrayList<>();


    private List<User> getData() {
        List<User> users = new ArrayList<>();
        ServiceUser se = new ServiceUser();
        try {
            users.addAll(se.displayFreelancer());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return users;
    }
    public void goBack(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ServiceProvider/view/frontDashboard.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        users.addAll(getData());
        
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < users.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/ServiceProvider/view/profileCard.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ProfileCard profileCard = fxmlLoader.getController();
                profileCard.setData(users.get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }
