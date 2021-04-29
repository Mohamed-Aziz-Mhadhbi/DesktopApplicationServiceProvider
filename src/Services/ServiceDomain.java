/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.domain;
import Utils.dbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author marwe
 */
public class ServiceDomain {
    Connection cnx = dbConnection.getInstance().getConnection();
private  ResultSet rst;
private Statement stn;

public void AjouterD(domain O) {
       
        try {
            String requete = "INSERT INTO `domain`(`id`, `title`, `color`)  VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, O.getId());
            pst.setString(2, O.getTitle());
            pst.setString(3, O.getColor());
            pst.executeUpdate();
            System.out.println("domain ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    }
 public ObservableList<domain> readAll() throws SQLException {
        ObservableList oblistdisc = FXCollections.observableArrayList();

        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery("select * from domain ;");
        while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String color = rs.getString("color");

            domain d = new domain(id, title, color);
            oblistdisc.add(d);

        }
        return oblistdisc;
    }
 public void delete(domain O) {
        String req = "delete from domain where id=" + O.getId();
        //if (c != null) {
            try {
            PreparedStatement st1 = cnx.prepareStatement(req);
             //String value1 = tf_instru.getText();
            st1.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
            }
        /*} else {
            System.out.println("Service n'existe pas");
        }*/

    }
   public void modif(int id, String title , String color ) {
        try {
            String request="UPDATE domain SET title = ? , color = ?  WHERE id = ?";
            PreparedStatement pre=cnx.prepareStatement(request);
            pre.setString(1, title);
            pre.setString(2, color);
            pre.setInt(3, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
