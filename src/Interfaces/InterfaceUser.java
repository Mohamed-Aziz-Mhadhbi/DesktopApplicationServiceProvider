package Interfaces;

import Entities.User;
import javafx.collections.ObservableList;

import java.sql.SQLException;


public interface InterfaceUser {
    public void addUser(User user);
    public ObservableList<User> displayUsers() throws SQLException;
    public void updateUser(User user);
    public void deleteUser(User user);
    public ObservableList<User> displayUserById(int id) throws SQLException;
    public ObservableList<User> searchUser(String input) throws SQLException;
    public Boolean checkEmail(String email) throws SQLException;

}
