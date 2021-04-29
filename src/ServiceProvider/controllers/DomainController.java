/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceProvider.controllers;

import Entities.domain;
import Services.ServiceDomain;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author marwe
 */
public class DomainController implements Initializable {

    @FXML
    private TableView<domain> tableDomain;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> title;
    @FXML
    private TableColumn<?, ?> color;
    @FXML
    private PieChart statPostviews;
    @FXML
    private PieChart statPostnoc;
    @FXML
    private Button profile;
    @FXML
    private ImageView btnprofile;
    @FXML
    private Button btnforum;
    @FXML
    private Button btnDashboard;
    @FXML
    private Label Forum;
    @FXML
    private ImageView exit;
    @FXML
    private Button add;
    @FXML
    private Button edit;
    @FXML
    private Button delete;
    @FXML
    private TextField ti;

    @FXML
    private TextField cl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try
    {
            ServiceDomain Servcom = new ServiceDomain();
            List<domain> list = Servcom.readAll();
            System.out.println(list);
            ObservableList<domain> cls = FXCollections.observableArrayList();
            for (domain aux : list) {

          
                cls.add(new domain(aux.getId(),aux.getTitle(),aux.getColor()));
            }
             id.setCellValueFactory(new PropertyValueFactory<>("id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
                color.setCellValueFactory(new PropertyValueFactory<>("color"));

       

            

            tableDomain.setItems(cls);
            
           
            }catch(Exception ex){
                    System.out.println(ex);
                   
             }
        // TODO
    }    

    @FXML
    private void handleAction(MouseEvent event) {
    }

    @FXML
    private void ForumFront(ActionEvent event) {
    }

    @FXML
    private void exit(MouseEvent event) {
    }

    @FXML
    private void ajout(ActionEvent event) throws SQLException {
           if (ti.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ajout impossible");
            alert.setHeaderText("Remplir les champs");
            alert.showAndWait();
        }
        else {
              //  public reclamation(int id, String sujet, String description, int id_user, String etat, String reponse, int avis, String fichier, Date date_reclamation) {

            domain ca  =  new domain();
            ServiceDomain sca = new ServiceDomain();
            ca.setTitle(ti.getText());
                    
            sca.AjouterD(ca);
            TrayNotification tray =new TrayNotification();
            tray.setTitle("Succès");
        tray.setMessage("Ajout avec succès !");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
                    

    }                       ServiceDomain Servcom = new ServiceDomain();

        List<domain> list = Servcom.readAll();

        System.out.println(list);
            ObservableList<domain> cls = FXCollections.observableArrayList();
            for (domain aux : list) {

          
                cls.add(new domain(aux.getId(),aux.getTitle(),aux.getColor()));
            }
             id.setCellValueFactory(new PropertyValueFactory<>("id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
                color.setCellValueFactory(new PropertyValueFactory<>("color"));

       

            tableDomain.setItems(cls);
            
           
            
    }

    @FXML
    private void ed(ActionEvent event) throws SQLException {
        domain tmp=new domain(); 
        if(!tmp.equals(tableDomain.getSelectionModel().getSelectedItem())){
        domain p = tableDomain.getSelectionModel().getSelectedItem();
        
          
        if (p!=null){
                 ServiceDomain ser = new ServiceDomain();
                           
                             
                 p.setTitle(ti.getText());
            p.setColor(cl.getText());


           
                //public void updateReclamation (int id,String  sujet ,String description ,int  id_user,String etat,String reponse, String avis , String fichier , Date date_reclamation){

            ser.modif(p.getId(),p.getTitle(),p.getColor());
        
    }}
     ServiceDomain Servcom = new ServiceDomain();

        List<domain> list = Servcom.readAll();

        System.out.println(list);
            ObservableList<domain> cls = FXCollections.observableArrayList();
            for (domain aux : list) {

          
                cls.add(new domain(aux.getId(),aux.getTitle(),aux.getColor()));
            }
             id.setCellValueFactory(new PropertyValueFactory<>("id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
                color.setCellValueFactory(new PropertyValueFactory<>("color"));

       

            tableDomain.setItems(cls);}

    @FXML
    private void del(ActionEvent event) throws SQLException {
          domain tmp=new domain(); 
        
if(!tmp.equals(tableDomain.getSelectionModel().getSelectedItem())){
    
         ServiceDomain ser = new ServiceDomain();
         
            ObservableList<domain> SingleDemandes ;
            SingleDemandes=tableDomain.getSelectionModel().getSelectedItems();
            domain C1=SingleDemandes.get(0);
             ser.delete(C1);
                TrayNotification tray =new TrayNotification();
            tray.setTitle("Succès");
        tray.setMessage("Suppression avec succès !");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
    }
    ServiceDomain Servcom = new ServiceDomain();

        List<domain> list = Servcom.readAll();

        System.out.println(list);
            ObservableList<domain> cls = FXCollections.observableArrayList();
            for (domain aux : list) {

          
                cls.add(new domain(aux.getId(),aux.getTitle(),aux.getColor()));
            }
             id.setCellValueFactory(new PropertyValueFactory<>("id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
                color.setCellValueFactory(new PropertyValueFactory<>("color"));

       

            tableDomain.setItems(cls);
}}
