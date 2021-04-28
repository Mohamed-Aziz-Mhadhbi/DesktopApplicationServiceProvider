
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author Nour
 */
public class ServiceMainController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button btn1;
    @FXML
    private Button btn3;
    @FXML
    private Button btn2;
    
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void interFreelencer(ActionEvent event) {
        
        try {
            FXMLLoader loader
                    = new FXMLLoader(getClass().getResource("/servicepovidermain/ServiceShow.fxml"));
            Parent root = loader.load();
            //ServiceShowController irc = loader.getController();
            btn1.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ServiceShowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void interAdmin(ActionEvent event) {
         try {
            FXMLLoader loader
                    = new FXMLLoader(getClass().getResource("/servicepovidermain/ServiceBack.fxml"));
            Parent root = loader.load();
            //ServiceBackController irc = loader.getController();
            btn1.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ServiceBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void interUser(ActionEvent event) {
         try {
            FXMLLoader loader
                    = new FXMLLoader(getClass().getResource("/servicepovidermain/ServiceUserShow.fxml"));
            Parent root = loader.load();
            //ServiceUserShowController irc = loader.getController();
            btn1.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ServiceUserShowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}