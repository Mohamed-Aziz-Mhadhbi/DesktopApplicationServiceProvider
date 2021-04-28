/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceProvider.controllers;

import Entities.Offre;
import Entities.postulation;
import Services.ServiceOffre;
import Services.ServicePostulation;
import Utils.dbConnection;
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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;



/**
 * FXML Controller class
 *
 * @author user16
 */
public class DashboardOffresController implements Initializable {

    @FXML
    private Button profile;
    @FXML
    private ImageView btnprofile;
    @FXML
    private Button btnforum;
    @FXML
    private Button btnDashboard;
    @FXML
    private TableView<Offre> tv_offre;
    @FXML
    private TableColumn<Offre, String> cl_Title;
    @FXML
    private TableColumn<Offre, String> cl_Description;
    @FXML
    private TableColumn<Offre, Integer> cl_DomainOffre;
    @FXML
    private TableColumn<Offre, Date> cl_CreatAt;
    @FXML
    private TableView<Offre> tv_postulation;
    @FXML
    private TableColumn<postulation, String> clmotivation;
    @FXML
    private TableColumn<postulation, Integer> clprice;
    @FXML
    private TableColumn<postulation, Integer> clduration;
    
    
    @FXML
    private TextField search;
     
    @FXML
    private TextField tfId;
    ObservableList<Offre> oblist = FXCollections.observableArrayList();
    ObservableList<postulation> oblistpost = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTable();
        loadTablePost();
        // TODO
    }    

    @FXML
    private void ForumFront(ActionEvent event) {
    }

    @FXML
    private void handleMouseButton(MouseEvent event) {
        Offre O = tv_offre.getSelectionModel().getSelectedItem();

        //tfCreateAt.setDate(O.getCreatAt());

    }
     public void loadTable() {

        try {

            oblist.clear();
            Connection cnx = dbConnection.getInstance().getConnection();

            ResultSet rs = cnx.createStatement().executeQuery("select * from `offre`");

            while (rs.next()) {

                oblist.add(new Offre(rs.getInt("id"), rs.getString("Title"), rs.getString("Description"), rs.getInt("domain_offer_id"), rs.getDate("creat_at")));

            }

            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        cl_Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        cl_Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        cl_DomainOffre.setCellValueFactory(new PropertyValueFactory<>("DomainOffre"));
        cl_CreatAt.setCellValueFactory(new PropertyValueFactory<>("CreatAt"));

        tv_offre.setItems(oblist);

        FilteredList<Offre> filteredData = new FilteredList<>(oblist, b -> true);

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Offre -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Offre.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (Offre.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } //tf_datedeb.setValue(o.getDatedeb().toLocalDate());
                else if (String.valueOf(Offre.getCreatAt()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(Offre.getDomainOffre()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Offre> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tv_offre.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tv_offre.setItems(sortedData);

    }
     
     
     
    public void setid(String id) {
        this.tfId.setText(id);
    }
     public void loadTablePost(){
         
                  try {
            System.out.println("id offre :" + tfId.getText());
            int id = Integer.valueOf(tfId.getText());
            oblistpost.clear();
            Connection cnx = dbConnection.getInstance().getConnection();

            ResultSet rs = cnx.createStatement().executeQuery("select * from `postulation`WHERE offre_id ='" + id + "' ; ;");

            while (rs.next()) {

                oblistpost.add(new postulation(rs.getInt("id"), rs.getString("motivation"), rs.getInt("price"), rs.getInt("duration")));

            }

            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(PostulationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //cloffre_id.setCellValueFactory(new PropertyValueFactory<>("offre_id"));
        clmotivation.setCellValueFactory(new PropertyValueFactory<>("motivation"));
        clprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        clduration.setCellValueFactory(new PropertyValueFactory<>("duration"));

        tv_postulation.setItems(oblistpost);

}
}
