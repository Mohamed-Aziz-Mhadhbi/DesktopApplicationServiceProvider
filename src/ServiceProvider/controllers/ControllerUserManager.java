package ServiceProvider.controllers;

import Entities.User;
import Services.ServiceUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerUserManager implements Initializable {

    @FXML
    private TableView<User> usersTable;
    @FXML
    private TableColumn<User, String> idCol;
    @FXML
    private TableColumn<User, String> usernameCol;
    @FXML
    private TableColumn<User, String> nomCol;
    @FXML
    private TableColumn<User, String> prenomCol;
    @FXML
    private TableColumn<User, String> emailCol;
    @FXML
    private TableColumn<User, String> roleCol;
    @FXML
    private TableColumn<User, String> phoneCol;
    @FXML
    private TableColumn<User, String> enabledCol;
    @FXML
    private TableColumn<User, String> actionCol;

    User user = null;

    ObservableList<User> userList = FXCollections.observableArrayList();;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getAddView(MouseEvent mouseEvent) {
    }

    public void refreshTable(MouseEvent mouseEvent) throws SQLException {
        ServiceUser serviceUser = new ServiceUser();
        userList.clear();
        userList= serviceUser.displayUsers();
        usersTable.setItems(userList);
    }

    public void print(MouseEvent mouseEvent) {
    }

    private void loadData() throws SQLException {
        ServiceUser serviceUser = new ServiceUser();
        userList.clear();
        userList= serviceUser.displayUsers();
        usersTable.setItems(userList);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        enabledCol.setCellValueFactory(new PropertyValueFactory<>("enabled"));

    }
}
