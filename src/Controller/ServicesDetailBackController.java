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
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Nour
 */
public class ServicesDetailBackController implements Initializable {

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
    private Label lbidser;
    @FXML
    private Label lbservtet;
    @FXML
    private Button btnshow;
    @FXML
    private Button bntstat;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new dbConnection();
        
    }    

    @FXML
    private void backq(ActionEvent event) {
        try {
            FXMLLoader loader
                    = new FXMLLoader(getClass().getResource("/servicepovidermain/ServiceBack.fxml"));
            Parent root = loader.load();
            //Controller irc = loader.getController();
            btn0.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ServiceBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setIdService(String lbidser) {
        this.lbidser.setText(lbidser);
    }
    public void setTitreServ(String lbservtet) {
        this.lbservtet.setText(lbservtet);
    }
    private void afficherorder(){
        try {
            Connection conn =dc.getConnection();
            data=FXCollections.observableArrayList();
            ResultSet rs=conn.createStatement().executeQuery("Select * From order");
           
            
            while (rs.next()){
            data.add(new Order (rs.getInt(1),rs.getInt(2) , rs.getString(4), rs.getDate(5), rs.getInt(6)));
            } 
            
        } catch (SQLException ex) {
          System.err.println("errwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwweur"+ex);
        
           }
        
        idcoll.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        idservicecol.setCellValueFactory(new PropertyValueFactory<>("service_id"));
        messagecol.setCellValueFactory(new PropertyValueFactory<>("message"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("creat_at"));
         
        satcol.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        
       
        tableau.setItems(null);
         
        tableau.setItems(data);
    }

    @FXML
    private void showAction(ActionEvent event) {
        afficherorder();
    }

    @FXML
    private void satbutt(ActionEvent event) {
        try {
            FXMLLoader loader
                    = new FXMLLoader(getClass().getResource("/servicepovidermain/ServiceStat.fxml"));
            Parent root = loader.load();
            ServiceStatController irc = loader.getController();
            btn0.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ServiceStatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
