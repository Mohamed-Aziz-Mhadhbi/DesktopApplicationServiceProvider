package Services;

import Entities.User;
import Interfaces.InterfaceUser;
import Utils.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceUser implements InterfaceUser {

    Connection cnx;

    public ServiceUser(){
        cnx = dbConnection.getInstance().getConnection();
    }

    @Override
    public void addUser(User user) {
        try {

            String requete = "INSERT INTO `user`"
                    + "(`username`, `nom`,"
                    + " `prenom`, `email`, `phone`,"
                    + " `password`, `role`) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getNom());
            pst.setString(3, user.getPrenom());
            pst.setString(4, user.getEmail());
            pst.setInt(5, user.getPhone());
            //String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(13));
            //pst.setString(6, hashedPassword);
            pst.setString(7, user.getRole());

            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<User> displayUsers() throws SQLException{
        ObservableList<User> users = FXCollections.observableArrayList();;

            Statement stm = cnx.createStatement();
            String query = "SELECT * FROM `user` ";
            ResultSet rst = stm.executeQuery(query);


            while (rst.next()){
                User user= new User();
                user.setId(rst.getInt("id"));
                user.setNom(rst.getString("nom"));
                user.setPrenom(rst.getString("prenom"));
                user.setEmail(rst.getString("email"));
                user.setRole(rst.getString("role"));
                user.setPhone(rst.getInt("phone"));
                user.setEnable(rst.getBoolean("enabled"));
                users.add(user);

            }


        return users;
    }
}
