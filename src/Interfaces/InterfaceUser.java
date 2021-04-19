package Interfaces;

import Entities.User;
import javafx.collections.ObservableList;

import javax.mail.MessagingException;
import java.sql.SQLException;


public interface InterfaceUser {
    public void addFreelancer(User user);
    public ObservableList<User> displayUsers() throws SQLException;
    public void updateUser(User user,int id);
    public void deleteUser(User user);
    public ObservableList<User> displayUserById(int id) throws SQLException;
    public ObservableList<User> searchUser(String input) throws SQLException;
    public Boolean checkEmail(String email) throws SQLException;
    public Boolean checkUsername(String username) throws SQLException;
    public String generateNewToken();
    public void changePassword(String email, String password);


}
