/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offres;

import Entities.Offre;
import Entities.postulation;
import Service.ServiceOffre;
import Service.ServicePostulation;
import Utils.Myconnexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user16
 */
public class PostulationFXMLController implements Initializable {
    ObservableList<postulation> oblist = FXCollections.observableArrayList();

    @FXML
    private TextField tfmotivation;
    @FXML
    private TextField tfprice;
    @FXML
    private TextField tfduration;
    private TableColumn<Offre, Offre> cloffre_id;
    @FXML
    private TableColumn<postulation,String> clmotivation;
    @FXML
    private TableColumn<postulation,Integer> clprice;
    @FXML
    private TableColumn<postulation, Integer> clduration;
    @FXML
    private TableView<postulation> tv_postulation;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_modifier;
    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfDomaine;
    @FXML
    private TextField tfId;
    
     ObservableList<postulation> oblistpost = FXCollections.observableArrayList();
       ServiceOffre pf = new ServiceOffre();
    ServicePostulation pc = new ServicePostulation();

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTable();
        // TODO
    }    

    @FXML
    private void AjouterPostulation(ActionEvent event) {
                String motivation=tfmotivation.getText();
        
      int price = Integer.parseInt(tfprice.getText());
       int duration = Integer.parseInt(tfduration.getText());
        
       
        if ((motivation.isEmpty())) {
            JOptionPane.showMessageDialog(null, "il faut remplir tous les champs  ");
        }
        else if (motivation.equals("")) {
            JOptionPane.showMessageDialog(null, "saisir motivation est obligatoire ");
        }
        
        else if ((motivation.equals("")) ) {
            JOptionPane.showMessageDialog(null, "il faut remplir tous les champs  ");
        }  
        else {
            
         postulation p = new postulation(0 ,motivation, price, duration );
        ServicePostulation sc = new ServicePostulation();
               sc.AjouterPostulation(p);
               loadTable();
            
        }
        
    }

    private void loadTable() {
        
        try {
            
            oblist.clear();
            Connection cnx = Myconnexion.getInstance().getCnx();
            
            ResultSet rs = cnx.createStatement().executeQuery("select * from `postulation`");
            
            while(rs.next()){
                
                oblist.add(new postulation(rs.getInt("id"),rs.getString("motivation"),rs.getInt("price"),rs.getInt("duration")));
            
            }
            
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(PostulationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //cloffre_id.setCellValueFactory(new PropertyValueFactory<>("offre_id"));
        clmotivation.setCellValueFactory(new PropertyValueFactory<>("motivation"));
        clprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        clduration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        
        
       
        
        tv_postulation.setItems(oblist);  
    }
    
    
  

    @FXML
    private void delete(ActionEvent event) {
                postulation p=tv_postulation.getSelectionModel().getSelectedItem();
       tv_postulation.getItems().removeAll(tv_postulation.getSelectionModel().getSelectedItem());
        ServicePostulation sc = new ServicePostulation();
               sc.delete(p);
               loadTable();
    }

    @FXML
    private void modifierPostulation(ActionEvent event) {
                postulation cl=tv_postulation.getSelectionModel().getSelectedItem();
          int id =cl.getId();
           String motivation=tfmotivation.getText();
        int price = Integer.parseInt(tfprice.getText());
        int duration = Integer.parseInt(tfduration.getText());
          
           
         
           
         
            ServicePostulation sc = new ServicePostulation();
            postulation p = new postulation(id,motivation,price,duration);
           //Cours cc = new Cours(id, instru , niveau, vid);
            sc.modifierPostulation(p);
            
            loadTable();

    }

    @FXML
    private void handleMouseButton(MouseEvent event) {
    
        postulation  p= tv_postulation.getSelectionModel().getSelectedItem();
         
            
            tfmotivation.setText(p.getMotivation());
            tfprice.setText(p.getPrice()+"");
            tfduration.setText(p.getDuration()+"");
            //tfCreateAt.setDate(O.getCreatAt());
            
    
    }
    public void setTfmotivation(String tfTitlePost) {
        this.tfmotivation.setText(tfTitlePost);
    }

    public void setTfprice(String tfDescriptionPost) {
        this.tfprice.setText(tfDescriptionPost);
    }

    public void setduration(String id) {
        this.tfduration.setText(id);
    }

    public void setDescription(String restDescriptionFourm) {
        this.tfDescription.setText(restDescriptionFourm);
    }

    public void setTitle(String Tforum) {
        this.tfTitle.setText(Tforum);
    }

   /* public void settPost(String tPost) {
        this.tPost.setText(tPost);
    }*/

    public void setDomainOffre(String restTitleForum) {
        this.tfDomaine.setText(restTitleForum);
    }

    public void setid(String id) {
        this.tfId.setText(id);
    }
        private void initTable(int id) {
        try {
            /*String idfs = idtest.getText();
            Integer idf = Integer.valueOf(idfs);*/

            oblistpost = (ObservableList<postulation>) pc.readAllpost2(id);
            clmotivation.setCellValueFactory(new PropertyValueFactory<>("motivation"));
            clprice.setCellValueFactory(new PropertyValueFactory<>("price"));
            clduration.setCellValueFactory(new PropertyValueFactory<>("duration"));
            
            
            tv_postulation.setItems(oblistpost);
        } catch (SQLException ex) {
            Logger.getLogger(PostulationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
            public void initTable(ObservableList<postulation> posts) {
        // String idfs = idtest.getText();
        // Integer idf = Integer.valueOf(idfs);
        //oblistpost = (ObservableList<Post>) pc.readAllpost2(idf);
        clmotivation.setCellValueFactory(new PropertyValueFactory<>("motivation"));
        clprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        clduration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        
        
        tv_postulation.setItems(posts);

    }
    
    
}
