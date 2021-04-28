/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Offre;
import Services.IservicesOffre;
import Utils.Myconnexion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user16
 */
public class ServiceOffre implements IservicesOffre {
  
Connection cnx = Myconnexion.getInstance().getCnx();
private  ResultSet rst;
private Statement stn;
 
    
    /**
     *
     * @param O
     */
    
    public void AjouterOffre(Offre O) {
       
        try {
            String requete = "INSERT INTO `offre`(`id`, `domain_offer_id`, `title`, `description`, `creat_at`)  VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, O.getId());
            pst.setInt(2, O.getDomainOffre());
            //pst.setInt(3, O.getUser_id());
            pst.setString(3, O.getTitle());
            pst.setString(4, O.getDescription());
            pst.setDate(5,(Date) O.getCreatAt());
            pst.executeUpdate();
            System.out.println("offre ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    }

    @Override
    public List<Offre> AfficherOffre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  public void delete(Offre O) {
        String req = "delete from offre where id=" + O.getId();
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
 public void modifierOffre(Offre o) {
String req ="UPDATE Offre SET title='"+o.getTitle()+"',description='"+o.getDescription()+"',domain_offer_id='"+o.getDomainOffre()+"',creat_at='"+o.getCreatAt()+"' WHERE id="+o.getId() ;
    try {
        
             PreparedStatement st1 = cnx.prepareStatement(req);
             //String value1 = tf_instru.getText();
             st1.executeUpdate();
            System.out.println("offre modifié");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }    }
 
 
 
     public ObservableList<Offre> readAlldiscc() throws SQLException {
        ObservableList oblistdisc = FXCollections.observableArrayList();

        Statement st = Myconnexion.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery("select * from offre ;");
        while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String description = rs.getString("description");
            int DomainOffre = rs.getInt("DomainOffre");
            Date CreatAt = rs.getDate("CreatAt");

            Offre d = new Offre(id, title, description,DomainOffre,CreatAt);
            oblistdisc.add(d);

        }
        return oblistdisc;
    }
  }
   

        
       
       
    

