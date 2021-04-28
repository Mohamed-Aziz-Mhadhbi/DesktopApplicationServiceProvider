/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Order;
import Entities.Service;
import Utils.dbConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Nour
 */
public class ServicesDetailController implements Initializable {

    @FXML
    private TableView<Order> tableau;
    @FXML
    private TableColumn<Order, Integer> idcoll;
    @FXML
    private TableColumn<Order, Integer> idservicecol;
    @FXML
    private TableColumn<Order, String> messagecol;
    @FXML
    private TableColumn<Order, Date> datecol;
    @FXML
    private TableColumn<Order, Integer> satcol;
    @FXML
    private Button btn0;
    private dbConnection dc;
    private ObservableList<Order>data;
    @FXML
    private Label lbid;
    @FXML
    private Label lbtite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new dbConnection();
    }    

    @FXML
    private void backservice(ActionEvent event) {
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
    private void afficherOrder(){
    }
    public void setIdService(String lbid) {
        this.lbid.setText(lbid);
    }
    public void setTitreServ(String lbtite) {
        this.lbtite.setText(lbtite);
    }
}
