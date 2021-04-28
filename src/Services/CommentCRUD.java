/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Comment;
import Entities.Forum;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import Utils.dbConnection;
import java.sql.Connection;

/**
 *
 * @author ASUS
 */
public class CommentCRUD {

    Connection cnx;

    public CommentCRUD() {

        cnx = dbConnection.getInstance().getConnection();
    }

    public ObservableList<Comment> readAllcomment2(int idd) throws SQLException {

        ObservableList oblistpost = FXCollections.observableArrayList();
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM `comment` WHERE pst_id = '" + idd + "';");

        // System.out.println(idd);
        while (rs.next()) {

            int id = rs.getInt("id");
            String content = rs.getString("content");
            int rating = rs.getInt("rating");

            Comment d = new Comment(id, content, rating);
            oblistpost.add(d);

        }
        return oblistpost;
    }

    public void addComment(Comment c, int i) {
        try {
            String requete = "INSERT INTO comment (usr_id,pst_id,content,rating,creat_at,likes,status_like)"
                    + "VALUES (1,?,?,?,sysdate(),0,1)";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.setInt(1, i);
            pst.setString(2, c.getContent());
            pst.setInt(3, c.getRating());

            pst.executeUpdate();
            System.out.println("Comment added!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public boolean delete(int dd) throws SQLException {

        PreparedStatement pre = cnx.prepareStatement("DELETE FROM comment WHERE id ='" + dd + "' ;");
        pre.executeUpdate();
        JOptionPane.showMessageDialog(null, "comment supprimé avec succées");
        return true;
    }

    public boolean updatePost(int id) throws SQLException {
        PreparedStatement pre = cnx.prepareStatement("UPDATE post SET noc= noc+1   WHERE id='" + id + "' ;");
        // JOptionPane.showMessageDialog(null, "Post update avec succées");
        pre.executeUpdate();
        return true;
    }

    public boolean updatePostnocDelete(int id) throws SQLException {
        PreparedStatement pre = cnx.prepareStatement("UPDATE post SET noc= noc-1   WHERE id='" + id + "' ;");
        // JOptionPane.showMessageDialog(null, "Post update avec succées");
        pre.executeUpdate();
        return true;
    }

    public boolean update(int id, String content, int rating) throws SQLException {
        PreparedStatement pre = cnx.prepareStatement("UPDATE comment SET content= '" + content + "' , rating='" + rating + "' WHERE id='" + id + "' ;");
        JOptionPane.showMessageDialog(null, "Comment updated successfully");
        System.out.println("Comment updated successfully");
        pre.executeUpdate();
        return true;
    }

    public String userName(int id) throws SQLException {
        String name = "";
        ResultSet rs = cnx.createStatement().executeQuery("select * from  user where id='" + id + "';");
        while (rs.next()) {
            name = rs.getString("username");
            return name;
        }
        return name;
    }

}
