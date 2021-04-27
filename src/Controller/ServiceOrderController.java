/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Utils.dbConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Nour
 */
public class ServiceOrderController implements Initializable {

    @FXML
    private TextField tforder;
    @FXML
    private CheckBox tfcheck;
    @FXML
    private Button btn1;
    @FXML
    private Button tn8;
    private dbConnection dc;
    @FXML
    private Label tfidd;
    @FXML
    private Label tftitl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new dbConnection();
    }    

    @FXML
    private void Orderbtn(ActionEvent event) {
        if (tforder.getText().equals("")  || tfcheck.getText() == null) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir tous les champs !!!");
            alert.showAndWait();
     }
       else try {
            Connection conn =dc.getConnection();
            String requete="INSERT INTO order (service_id,message,creat_at,status) VALUES (?,?,?,?)";
         
            PreparedStatement pst;
            pst = conn.prepareStatement(requete);//instruction sql; envoyer la requete et l'executer coté base de donné.
            
            pst.setInt(1, Integer.valueOf(tfidd.getText()));
            pst.setString(2, tforder.getText());
            pst.setDate(3, new Date(System.currentTimeMillis()));
            pst.setString(4, tfcheck.getText());
            
            
            
            
            
            pst.executeUpdate();//méthodes appropriées à l'exécution d'une instruction menant à la modification de la BD (INSERT, UPDATE, DELETE, CREATE, etc.)
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("********Ajout Order*********");
            alert.setHeaderText("INFORMATION");
            alert.setContentText(" votre Order a été ajouté");
            alert.showAndWait();
            
            System.out.println("bien");
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @FXML
    private void backhh(ActionEvent event) {
        try {
            FXMLLoader loader
                    = new FXMLLoader(getClass().getResource("/servicepovidermain/ServiceUserShow.fxml"));
            Parent root = loader.load();
            //ServiceUserShowController irc = loader.getController();
            tn8.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ServiceUserShowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public void setIdOrder(String tfidd) {
        this.tfidd.setText(tfidd);
    }
    public void setTitre(String tftitl) {
        this.tftitl.setText(tftitl);
    }
    
}
