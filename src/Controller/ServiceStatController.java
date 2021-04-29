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
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Nour
 */
public class ServiceStatController implements Initializable {

    @FXML
    private Button btn00;
    @FXML
    private Button btn01;
    @FXML
    private BarChart<String, Integer> barchart;
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
    private void backback(ActionEvent event) {
        try {
            FXMLLoader loader
                    = new FXMLLoader(getClass().getResource("/servicepovidermain/ServiceBack.fxml"));
            Parent root = loader.load();
            //Controller irc = loader.getController();
            btn00.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ServiceBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void statview(ActionEvent event) {
        String query="select title,price from service ORDER By price asc";
        XYChart.Series<String,Integer> series=new XYChart.Series<>();
        try{
            Connection conn =dc.getConnection();
            ResultSet rs=conn.createStatement().executeQuery(query);
            while (rs.next()){
            series.getData().add(new XYChart.Data<> (rs.getString(1), rs.getInt(2)));
            } 
            barchart.getData().addAll(series);
            
        }catch(Exception e){
            System.out.println("erreurrrrr");
        }
    }
    
}
