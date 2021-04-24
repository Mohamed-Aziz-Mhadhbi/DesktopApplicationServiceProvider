/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import esprit.entities.Forum;
import esprit.tools.MyConnection;

/**
 *
 * @author ASUS
 */
public class ForumCRUD {

    public void addForum(Forum f) {
        try {
            String requete = "INSERT INTO forum (us_id,title,description,creat_at)"
                    + "VALUES (1,?,?,sysdate())";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);

            pst.setString(1, f.getTitle());
            pst.setString(2, f.getDescription());
            pst.executeUpdate();
            System.out.println("Forum added!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public ObservableList<Forum> readAlldiscc() throws SQLException {
        ObservableList oblistdisc = FXCollections.observableArrayList();

        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery("select * from forum ;");
        while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String description = rs.getString("description");

            Forum d = new Forum(id, title, description);
            oblistdisc.add(d);

        }
        return oblistdisc;
    }

    public List<Forum> getForums() {
        List<Forum> myList = new ArrayList();
        try {
            String requete = "SELECT * FROM forum";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Forum f = new Forum();
                f.setId(rs.getInt(1));
                f.setTitle(rs.getString("title"));
                f.setDescription(rs.getString("description"));
                myList.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

     public boolean delete(int id) throws SQLException {
        int retour = JOptionPane.showConfirmDialog(null,
                "OK - Annuler",
                "titre",
                JOptionPane.OK_CANCEL_OPTION);
        System.out.println("retour :" + retour);
        if (retour == 0) {
            PreparedStatement pre = MyConnection.getInstance().getCnx().prepareStatement("DELETE FROM forum WHERE id ='" + id + "' ;");
            pre.executeUpdate();
        }
        return true;
    }
   

    public boolean update(int id, String title, String description) throws SQLException {
        PreparedStatement pre = MyConnection.getInstance().getCnx().prepareStatement("UPDATE forum SET title= '" + title + "' , description='" + description + "' WHERE id='" + id + "' ;");
        JOptionPane.showMessageDialog(null, "Forum update avec succées");
        pre.executeUpdate();
        return true;
    }

    public ObservableList<Forum> saerchTitle(String titlee) throws SQLException {
        ObservableList<Forum> arr = FXCollections.observableArrayList();
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery("select * from forum where title='" + titlee + "';");
        while (rs.next()) {
            int id = rs.getInt(1);

            String title = rs.getString("title");
            String description = rs.getString("description");

            Forum f = new Forum(id, title, description);
            arr.add(f);

        }
        return arr;

    }

    public Forum findById(int id) {

        try {
            String request = "SELECT * FROM forum where id = " + id;
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(request);
            ResultSet rs = pst.executeQuery(request);
            Forum forum = new Forum();
            while (rs.next()) {

                forum.setId(rs.getInt("id"));
                forum.setTitle(rs.getString("title"));
                forum.setDescription(rs.getString("description"));

            }
            return forum;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public List<String> getForumsTitle() {
        List<String> myList = new ArrayList();
        try {

            ResultSet rs = MyConnection.getInstance().getCnx().createStatement().executeQuery("select * from  forum ;");
            while (rs.next()) {
                String title = rs.getString("title");
                myList.add(title);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
}
