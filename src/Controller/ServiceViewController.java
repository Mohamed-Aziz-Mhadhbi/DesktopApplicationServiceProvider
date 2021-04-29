/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javax.swing.JOptionPane;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Nour
 */
public class ServiceViewController implements Initializable {

    @FXML
    private Label ltatle;
    @FXML
    private Label ldesc;
    @FXML
    private Label lprix;
    @FXML
    private Label ldate;
    @FXML
    private Button btn0;
    @FXML
    private Button btnRatID;
    @FXML
    private Rating RatId;
    @FXML
    private Label lnote;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void backq(ActionEvent event) {
        try {
            FXMLLoader loader
                    = new FXMLLoader(getClass().getResource("/servicepovidermain/ServiceUserShow.fxml"));
            Parent root = loader.load();
            //ServiceUserShowController irc = loader.getController();
            btn0.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ServiceUserShowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setTitleV(String ltatle) {
        this.ltatle.setText(ltatle);
    }
    public void setDescV(String ldesc) {
        this.ldesc.setText(ldesc);
    }
    public void setPriceV(String lprix) {
        this.lprix.setText(lprix);
    }
    public void setDateV(Date ldate) {
        this.ldate.setText(ldate.toString());
    }

    @FXML
    private void RatAction(ActionEvent event) {
        JOptionPane.showMessageDialog(null,"votre note =" +RatId.getRating());
         lnote.setText (Integer.toString((int) RatId.getRating()));
    }
}
