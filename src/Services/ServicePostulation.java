/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.postulation;
import Interfaces.IservicesPostulation;
import Utils.dbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user16
 */
public class ServicePostulation implements IservicesPostulation {

    Connection cnx = dbConnection.getInstance().getConnection();
    private ResultSet rst;
    private Statement stn;

    public void AjouterPostulation(postulation p) {

        try {
            String requete = " INSERT INTO `postulation`( `offre_id`, `postulation_user_id`, `motivation`, `price`, `duration`) VALUES (?,?,?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete);
            //pst.setInt(1, p.getId());
            pst.setInt(1, p.getOffre_id());
            pst.setInt(2, p.getPostulation_user_id());
            pst.setString(3, p.getMotivation());
            pst.setInt(4, p.getPrice());
            pst.setInt(5, p.getDuration());

            pst.executeUpdate();
            System.out.println("postulation ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<postulation> AfficherPostulation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(postulation p) {
        String req = "delete from postulation where id=" + p.getId();
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

    @Override
    public void modifierPostulation(postulation p) {
        String req = "UPDATE postulation SET motivation='" + p.getMotivation() + "',price='" + p.getPrice() + "',duration='" + p.getDuration() + "' WHERE id=" + p.getId();
        try {

            PreparedStatement st1 = cnx.prepareStatement(req);
            //String value1 = tf_instru.getText();
            st1.executeUpdate();
            System.out.println("postulation modifié");

        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());

        }
    }

    public ObservableList<postulation> readAllpost2(int idd) throws SQLException {

        ObservableList oblistpost = FXCollections.observableArrayList();
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM `postulation` WHERE offre_id = '" + idd + "';");

        //System.out.println(idd);
        while (rs.next()) {

            int id = rs.getInt("id");
            String motivation = rs.getString("motivation");
            int offre_id = rs.getInt("offre_id");
            int postulation_user_id = rs.getInt("postulation_user_id");
            int price = rs.getInt("price");
            int duration = rs.getInt("duration");
            

            postulation d = new postulation(id, offre_id, postulation_user_id,  motivation,  price, duration) ;
             
        
            oblistpost.add(d);

        }
        return oblistpost;
    }
}
