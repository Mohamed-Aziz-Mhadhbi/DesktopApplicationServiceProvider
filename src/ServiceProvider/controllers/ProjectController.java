/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceProvider.controllers;

import Entities.domain;
import Entities.project;
import Services.ServiceDomain;
import Services.ServiceProject;
import Utils.SendMail;
import Utils.SmsSender;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.mail.MessagingException;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author marwe
 */
public class ProjectController implements Initializable {

    @FXML
    private TableView<project> tableDomain;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> project_user;
    @FXML
    private TableColumn<?, ?> tit;
    @FXML
    private TableColumn<?, ?> desc;
    @FXML
    private TableColumn<?, ?> creat;
    @FXML
    private TableColumn<?, ?> status;
    @FXML
    private TableColumn<?, ?> domain;
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
    private TextField ti;
    @FXML
    private TextField des;
    @FXML
    private Button add;
    @FXML
    private Button edit;
    @FXML
    private Button delete;
    @FXML
    private TextField dom;
    @FXML
    private ComboBox<?> dd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try
    {
            ServiceProject Servcom = new ServiceProject();
            List<project> list = Servcom.readAll();
            System.out.println(list);
            ObservableList<project> cls = FXCollections.observableArrayList();
            for (project aux : list) {

          //project(int id, int project_user_id, String title, String description, Date creat_at, int status, int domain_id_id)
                cls.add(new project(aux.getId(),aux.getProject_user_id(),aux.getTitle(),aux.getDescription(),aux.getCreat_at(),aux.getStatus(),aux.getDomain_id_id()));
            }
             id.setCellValueFactory(new PropertyValueFactory<>("id"));
        project_user.setCellValueFactory(new PropertyValueFactory<>("project_user_id"));
                tit.setCellValueFactory(new PropertyValueFactory<>("title"));
                 desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        creat.setCellValueFactory(new PropertyValueFactory<>("creat_at"));
                status.setCellValueFactory(new PropertyValueFactory<>("status"));
                domain.setCellValueFactory(new PropertyValueFactory<>("domain_id_id"));


       

            

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
    private void ajout(ActionEvent event) {
         if (ti.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ajout impossible");
            alert.setHeaderText("Remplir les champs");
            alert.showAndWait();
        }
        else {
//    public project(int id, int project_user_id, String title, String description, Date creat_at, int status, int domain_id_id) {

            project ca  =  new project();
            ServiceProject sca = new ServiceProject();
            ca.setTitle(ti.getText());
            ca.setDescription(des.getText());
            int co = Integer.parseInt(dom.getText());
            ca.setDomain_id_id(co);
            long millis=System.currentTimeMillis();  
            java.sql.Date date=new java.sql.Date(millis);  
            System.out.println(date); 
            ca.setCreat_at(date);
            ca.setStatus(0);
                   
            sca.AjouterD(ca);
            TrayNotification tray =new TrayNotification();
            tray.setTitle("Succès");
        tray.setMessage("Ajout avec succès !");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
                    
    }
          try
    {
            ServiceProject Servcom = new ServiceProject();
            List<project> list = Servcom.readAll();
            System.out.println(list);
            ObservableList<project> cls = FXCollections.observableArrayList();
            for (project aux : list) {

          //project(int id, int project_user_id, String title, String description, Date creat_at, int status, int domain_id_id)
                cls.add(new project(aux.getId(),aux.getProject_user_id(),aux.getTitle(),aux.getDescription(),aux.getCreat_at(),aux.getStatus(),aux.getDomain_id_id()));
            }
             id.setCellValueFactory(new PropertyValueFactory<>("id"));
        project_user.setCellValueFactory(new PropertyValueFactory<>("project_user_id"));
                tit.setCellValueFactory(new PropertyValueFactory<>("title"));
                 desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        creat.setCellValueFactory(new PropertyValueFactory<>("creat_at"));
                status.setCellValueFactory(new PropertyValueFactory<>("status"));
                domain.setCellValueFactory(new PropertyValueFactory<>("domain_id_id"));


       

            

            tableDomain.setItems(cls);
            
           
            }catch(Exception ex){
                    System.out.println(ex);
                   
             }
    }
    @FXML
    private void ed(ActionEvent event) throws MessagingException {
         project tmp=new project(); 
        if(!tmp.equals(tableDomain.getSelectionModel().getSelectedItem())){
        project p = tableDomain.getSelectionModel().getSelectedItem();
        
          
        if (p!=null){
                 ServiceProject ser = new ServiceProject();

                //public void updateReclamation (int id,String  sujet ,String description ,int  id_user,String etat,String reponse, String avis , String fichier , Date date_reclamation){

            ser.modif(p.getId(),1);
             String email = "zakaria.houas@esprit.tn";
        String cn="Thank you for choosing our service";
        String sb="Thank you";
        SendMail.sendMail(email,sb, cn);
        SmsSender s= new SmsSender() ;
                   s.send(" Thank you for validating the project ", "b");
    }}
    try
    {
            ServiceProject Servcom = new ServiceProject();
            List<project> list = Servcom.readAll();
            System.out.println(list);
            ObservableList<project> cls = FXCollections.observableArrayList();
            for (project aux : list) {

          //project(int id, int project_user_id, String title, String description, Date creat_at, int status, int domain_id_id)
                cls.add(new project(aux.getId(),aux.getProject_user_id(),aux.getTitle(),aux.getDescription(),aux.getCreat_at(),aux.getStatus(),aux.getDomain_id_id()));
            }
             id.setCellValueFactory(new PropertyValueFactory<>("id"));
        project_user.setCellValueFactory(new PropertyValueFactory<>("project_user_id"));
                tit.setCellValueFactory(new PropertyValueFactory<>("title"));
                 desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        creat.setCellValueFactory(new PropertyValueFactory<>("creat_at"));
                status.setCellValueFactory(new PropertyValueFactory<>("status"));
                domain.setCellValueFactory(new PropertyValueFactory<>("domain_id_id"));


       

            

            tableDomain.setItems(cls);
            
           
            }catch(Exception ex){
                    System.out.println(ex);
                   
             }}

    @FXML
    private void del(ActionEvent event) {
         project tmp=new project(); 
        
if(!tmp.equals(tableDomain.getSelectionModel().getSelectedItem())){
    
         ServiceProject ser = new ServiceProject();
         
            ObservableList<project> SingleDemandes ;
            SingleDemandes=tableDomain.getSelectionModel().getSelectedItems();
            project C1=SingleDemandes.get(0);
             ser.delete(C1);
                TrayNotification tray =new TrayNotification();
            tray.setTitle("Succès");
        tray.setMessage("Suppression avec succès !");
        tray.setAnimationType(AnimationType.POPUP);
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndWait();
    }
try
    {
            ServiceProject Servcom = new ServiceProject();
            List<project> list = Servcom.readAll();
            System.out.println(list);
            ObservableList<project> cls = FXCollections.observableArrayList();
            for (project aux : list) {

          //project(int id, int project_user_id, String title, String description, Date creat_at, int status, int domain_id_id)
                cls.add(new project(aux.getId(),aux.getProject_user_id(),aux.getTitle(),aux.getDescription(),aux.getCreat_at(),aux.getStatus(),aux.getDomain_id_id()));
            }
             id.setCellValueFactory(new PropertyValueFactory<>("id"));
        project_user.setCellValueFactory(new PropertyValueFactory<>("project_user_id"));
                tit.setCellValueFactory(new PropertyValueFactory<>("title"));
                 desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        creat.setCellValueFactory(new PropertyValueFactory<>("creat_at"));
                status.setCellValueFactory(new PropertyValueFactory<>("status"));
                domain.setCellValueFactory(new PropertyValueFactory<>("domain_id_id"));


            tableDomain.setItems(cls);
            
           
            }catch(Exception ex){
                    System.out.println(ex);
                   
             }
    }
    
}
