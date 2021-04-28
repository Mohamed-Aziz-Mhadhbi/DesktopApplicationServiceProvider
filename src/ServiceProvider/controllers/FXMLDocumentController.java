/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceProvider.controllers;

import Entities.Offre;
import Entities.User;
import Entities.postulation;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import Utils.dbConnection;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import Services.ServiceOffre;
import Services.ServicePostulation;
import static Utils.print.printNode;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.sql.Date;
import java.sql.PreparedStatement;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user16
 */
public class FXMLDocumentController implements Initializable {

    ObservableList<Offre> oblist = FXCollections.observableArrayList();
    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfDomaine;
    @FXML
    private TableColumn<Offre, String> cl_Title;
    @FXML
    private TableColumn<Offre, String> cl_Description;
    @FXML
    private TableColumn<Offre, Integer> cl_DomainOffre;
    @FXML
    private TableColumn<Offre, Date> cl_CreatAt;
    @FXML
    private TableView<Offre> tv_offre;
    @FXML
    private DatePicker tfCreateAt;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_modifier;
    @FXML
    private PieChart piechart;
    private String query;
    private PreparedStatement preparedStatement;
    @FXML
    private TextField search;
    @FXML
    private TextField tfTitleOffre;
    @FXML
    private Button print;
    @FXML
    private AnchorPane AnO;
    @FXML
    private TextField tfId;
    User user = null;
    ObservableList<Offre> oblistdisc = FXCollections.observableArrayList();
    ServiceOffre fc = new ServiceOffre();
    ServicePostulation pc = new ServicePostulation();
    private static int id;
    @FXML
    private TextField tduser_id;
    @FXML
    private Button profile;
    @FXML
    private ImageView btnprofile;
    @FXML
    private Button home;
    @FXML
    private Button btnDashboard;
    @FXML
    private Label userlabel;
    @FXML
    private ImageView logout;
    @FXML
    private ImageView returnBack;
    @FXML
    private Label UserNameSession;
    @FXML
    private Button tfEcouter;
    private static final String VOICENAME="kevin16";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTable();
        stat();
        tduser_id.setVisible(false);

        //tfId.setVisible(false);
        //affForm();
        //search_formation();
        // TODO
    }

    public void setUser(User u) throws SQLException {
        this.user = u;
        //UserNameSession.setText(cc.userName(user.getId()));
    }

    @FXML
    private void AjouterOffre(ActionEvent event) {
        String Title = tfTitle.getText();
        String Description = tfDescription.getText();
        //int user_id = Integer.parseInt(tduser_id.getText());
        int DomainOffre = Integer.parseInt(tfDomaine.getText());
        Date CreatAt = java.sql.Date.valueOf(tfCreateAt.getValue());

        if ((Title.isEmpty()) && (Description.isEmpty())) {
            JOptionPane.showMessageDialog(null, "il faut remplir tous les champs  ");
        } else if (Title.equals("")) {
            JOptionPane.showMessageDialog(null, "saisir Title est obligatoire ");
        } else if (Description.equals("")) {
            JOptionPane.showMessageDialog(null, "saisir Description est obligatoire ");
        } else if ((Title.equals("")) && (Description.equals(""))) {
            JOptionPane.showMessageDialog(null, "il faut remplir tous les champs  ");
        } else {

            Offre O = new Offre(0, Title, Description, DomainOffre, CreatAt);
            ServiceOffre sc = new ServiceOffre();
            sc.AjouterOffre(O);
            loadTable();

        }

    }

    @FXML
    private void handleMouseButton(MouseEvent event) {
        Offre O = tv_offre.getSelectionModel().getSelectedItem();

        tfId.setText(String.valueOf(O.getId()));
        tfTitle.setText(O.getTitle());
        tfDescription.setText(O.getDescription());
        tfDomaine.setText(O.getDomainOffre() + "");
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

    @FXML
    private void delete(ActionEvent event) {
        Offre O = tv_offre.getSelectionModel().getSelectedItem();
        //tv_offre.getItems().removeAll(tv_offre.getSelectionModel().getSelectedItem());
        ServiceOffre sc = new ServiceOffre();
        sc.delete(O);
        loadTable();
    }

    @FXML
    private void modifierOffre(ActionEvent event) {
        Offre cl = tv_offre.getSelectionModel().getSelectedItem();
        int id = cl.getId();
        String Title = tfTitle.getText();
        String Description = tfDescription.getText();
        Date CreatAt = java.sql.Date.valueOf(tfCreateAt.getValue());

        int DomainOffre = Integer.parseInt(tfDomaine.getText());

        ServiceOffre sc = new ServiceOffre();
        Offre o = new Offre(id, Title, Description, DomainOffre, CreatAt);
        //Cours cc = new Cours(id, instru , niveau, vid);
        sc.modifierOffre(o);

        loadTable();

    }

    private void stat() {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        data = FXCollections.observableArrayList();
        try {
            Connection cnx = dbConnection.getInstance().getConnection();
            query = "SELECT COUNT(*),domain_offer_id FROM offre GROUP BY domain_offer_id";
            preparedStatement = cnx.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String d = rs.getString(2) + " : " + rs.getString(1);

                data.add(new PieChart.Data(d, rs.getInt(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        piechart.setTitle("Statistques des Offres");
        //piechart.setLegendSide(Side.LEFT);
        piechart.setData(data);

    }

    /*   private void initTable() {

        try {
            oblistdisc = (ObservableList<Offre>) fc.readAlldiscc();
            cl_Title.setCellValueFactory(new PropertyValueFactory<>("title"));
            tv_offre.setItems(oblistdisc);
            
            FilteredList<Offre> filteredData = new FilteredList<>(oblistdisc, b -> true);

            search.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(Offre -> {
                    // If filter text is empty, display all persons.

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter;
                    lowerCaseFilter = newValue.toLowerCase();

                    if (Offre.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches first name.
                    } else if (Offre.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
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

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

    @FXML
    private void Postuler(ActionEvent event) {

        /* try {
            FXMLLoader loader
                    = new FXMLLoader(getClass().getResource("/views/FXMLDocument.fxml"));
            Parent root = loader.load();

           /* PostulationFXMLController dc = loader.getController();
            dc.setRestDescriptionFourm(tfDescriptionForum.getText());
            dc.setRestTitleForum(tfTitleForum.getText());
            dc.setRestIdFourm(tfIdForum.getText());
            String test = tfIdForum.getText();
            try {
               // pc.readAllpost(test);
                // System.out.println(idF.getText());

            } catch (SQLException ex) {
                Logger.getLogger(PostulationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }*/
 /*tfTitleOffre.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(PostulationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        try {

            FXMLLoader loader
                    = new FXMLLoader(getClass().getResource("/views/postulationFXML.fxml"));
            Parent root = loader.load();

            PostulationFXMLController dc = loader.getController();
            dc.setDescription(tfDescription.getText());
            dc.setTitle(tfTitle.getText());
            dc.setDomainOffre(tfDomaine.getText());
            dc.setid(tfId.getText());

            try {
                dc.initTable((ObservableList<postulation>) pc.readAllpost2(Integer.valueOf(tfId.getText())));
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tfTitle.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void print(ActionEvent event) {
        try {
            printNode(AnO);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
    }

    @FXML
    private void Dashboard(ActionEvent event) {
    }

    @FXML
    private void exit(MouseEvent event) {
    }

    @FXML
    private void ReturnBack(MouseEvent event) {
    }

    @FXML
    private void jButtonSpeak(ActionEvent event) {
                Voice voice;
        VoiceManager vm=VoiceManager.getInstance();
        voice =vm.getVoice(VOICENAME);
        
        voice.allocate();
        try{
           
            //voice.speak(tf_name.getText());
              // voice.speak(tf_nombre.getText());
     
             //voice.speak(tf_lieu.getText());
             voice.speak(tfTitle.getText());
             voice.speak(tfDescription.getText());
           
        }catch(Exception e){ 
            
        }
    }

}
