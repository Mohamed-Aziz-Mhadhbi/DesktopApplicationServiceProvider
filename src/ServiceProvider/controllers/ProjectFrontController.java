/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceProvider.controllers;

import Entities.project;
import Services.ServiceProject;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author marwe
 */
public class ProjectFrontController implements Initializable {

    @FXML
    private TableView<project> tableDomain;
    @FXML
    private TextField rech;
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
        // TODO
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
    }

    @FXML
    private void ed(ActionEvent event) {
    }

    @FXML
    private void del(ActionEvent event) {
    }
     @FXML
    public void Search() throws SQLException{
       
        ServiceProject se = new ServiceProject();
        List<project> list = se.displayClause(" WHERE title LIKE '%"+rech.getText()+"%' or id LIKE '%"+rech.getText()+"%' ");
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
            
    }
}
