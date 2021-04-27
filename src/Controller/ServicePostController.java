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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Nour
 */
public class ServicePostController implements Initializable {

    @FXML
    private Button btn0;
    @FXML
    private TextField tftitle;
    @FXML
    private TextField tfdec;
    @FXML
    private TextField tfpricee;
    @FXML
    private Button btn1;
    private dbConnection dc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new dbConnection();
       
    }    

    @FXML
    private void Back(ActionEvent event) {
        try {
            FXMLLoader loader
                    = new FXMLLoader(getClass().getResource("/servicepovidermain/ServiceShow.fxml"));
            Parent root = loader.load();
            //ServiceShowController irc = loader.getController();
            btn0.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ServiceShowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouterService(ActionEvent event) {
        if (tftitle.getText().equals("") || tfdec.getText().equals("")  || tfpricee.getText() == null) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir tous les champs !!!");
            alert.showAndWait();
     }
       else try {
            Connection conn =dc.getConnection();
            String requete="INSERT INTO service (title,description,price,creat_at) VALUES (?,?,?,?)";
         
            PreparedStatement pst;
            pst = conn.prepareStatement(requete);//instruction sql; envoyer la requete et l'executer coté base de donné.
           
            pst.setString(1, tftitle.getText());
            pst.setString(2, tfdec.getText());
            pst.setInt(3, Integer.valueOf(tfpricee.getText()));
            pst.setDate(4, new Date(System.currentTimeMillis()));
            
            
            pst.executeUpdate();//méthodes appropriées à l'exécution d'une instruction menant à la modification de la BD (INSERT, UPDATE, DELETE, CREATE, etc.)
           
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("********Ajout Service*********");
            alert.setHeaderText("INFORMATION");
            alert.setContentText(" votre livraison a été ajouté");
            alert.showAndWait();
            
            System.out.println("bien");
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }
    
}
