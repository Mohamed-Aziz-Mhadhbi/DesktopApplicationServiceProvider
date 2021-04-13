package Interfaces;

import Entities.User;
import javafx.collections.ObservableList;

import java.sql.SQLException;


public interface InterfaceUser {
    public void addUser(User user);
    public ObservableList<User> displayUsers() throws SQLException;

}
