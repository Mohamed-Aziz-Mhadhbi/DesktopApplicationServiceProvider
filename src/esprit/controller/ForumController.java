/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import esprit.entities.Forum;
import esprit.entities.Post;
import esprit.services.CurseFilterService;
import esprit.services.ForumCRUD;
import esprit.services.PostCRUD;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ForumController implements Initializable {

    @FXML
    private TextField tfTitleForum;
    @FXML
    private HTMLEditor tfDescriptionForum;
    @FXML
    private TableView<Forum> tableFourm;
    @FXML
    private TableColumn<Forum, String> col_title;
    @FXML
    private Button btnDeleteForum;
    @FXML
    private Label controleTitle;
    @FXML
    private Label controleDescription;
    @FXML
    private Button btnDetails;
    @FXML
    private Label tfIdForum;
    
    private static int idf;
    
    ObservableList<Forum> oblistdisc = FXCollections.observableArrayList();
    ForumCRUD fc = new ForumCRUD();
    PostCRUD pc = new PostCRUD();
   
    @FXML
    private TextField cherche;
    Stage stage;
    @FXML
    private Button profile;
    @FXML
    private ImageView btnprofile;
    @FXML
    private Label userlabel;
    @FXML
    private ImageView logout;
    @FXML
    private Label titleF;
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnClear;
    @FXML
    private WebView webviewforum;
    private WebEngine engine;

    Set<String> possibleWordSet = new HashSet<>();

    private AutoCompletionBinding<String> autoCompletionBinding;

    String[] words;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  
        
        loadwebview();
        initTable();
        tfIdForum.setVisible(false);
        affForm();
        //search_formation();
        words = fc.getForumsTitle().toArray(new String[0]);

        Collections.addAll(possibleWordSet, words);
        autoCompletionBinding = TextFields.bindAutoCompletion(cherche, possibleWordSet);

        cherche.setOnKeyPressed((KeyEvent e)
                -> {
            switch (e.getCode()) {
                case ENTER:
                    learnWord(cherche.getText());
                    break;
                default:
                    break;
            }
        });
    }

    private void loadwebview()
    {
        engine = webviewforum.getEngine();
        engine.loadContent("<p>dfqdsfqsdffff</p>");
    }
    
    private void learnWord(String text) {
        possibleWordSet.add(text);
        if (autoCompletionBinding != null) {
            autoCompletionBinding.dispose();
        }
        autoCompletionBinding = TextFields.bindAutoCompletion(cherche, possibleWordSet);
    }

    public void setTfTitleForum(String tfTitleForum) {
        this.tfTitleForum.setText(tfTitleForum);
    }

    public void setTfDescriptionForum(String tfDescriptionForum) {
        this.tfDescriptionForum.setHtmlText(tfDescriptionForum);
    }

    public void setTfIdForum(String tfIdForum) {
        this.tfIdForum.setText(tfIdForum);
    }

    public String getTfIdForum() {
        return tfIdForum.getText();
    }

    public void setTitleF(String titleF) {
        this.titleF.setText(titleF);
    }

    private void clearAll() {
        tfTitleForum.setText("");
        tfDescriptionForum.setHtmlText("");
        tfIdForum.setText("");

    }

    private void initTable() {

        try {
            oblistdisc = (ObservableList<Forum>) fc.readAlldiscc();
            col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
            tableFourm.setItems(oblistdisc);
            
            FilteredList<Forum> filteredData = new FilteredList<>(oblistdisc, b -> true);

            cherche.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(Forum -> {
                    // If filter text is empty, display all persons.

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (Forum.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches first name.
                    } else if (Forum.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    } else {
                        return false; // Does not match.
                    }
                });
            });
            // 3. Wrap the FilteredList in a SortedList. 
            SortedList<Forum> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(tableFourm.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            tableFourm.setItems(sortedData);

        } catch (SQLException ex) {
            Logger.getLogger(ForumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouter(ActionEvent event) throws FileNotFoundException {
        if (tfTitleForum.getText().isEmpty() || tfDescriptionForum.getHtmlText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "please fill all the textfields");
        } else {

            controleDescription.setText("");
            controleTitle.setText("");

            // System.out.println(test("test ass"));
            ForumCRUD pc = new ForumCRUD();
            Forum f = new Forum();
            //Forum f = new Forum(CurseFilterService.cleanText(txftitre.getText()), CurseFilterService.cleanText(txfcontenu.getText()), txftheme.getValue());
            String tTitle = CurseFilterService.cleanText(tfTitleForum.getText());
            String tDescription = CurseFilterService.cleanText(tfDescriptionForum.getHtmlText());
            f.setTitle(tTitle);
            f.setDescription(tDescription);
            pc.addForum(f);
            clearAll();
            initTable();
            
            Image img = new Image("/esprit/img/ok.png");
            Notifications notifAdd = Notifications.create()
                    .title("add complet")
                    .text("saved avec sucees")
                    .graphic(new ImageView(img))
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);
            notifAdd.show();
        }
    }

   
    @FXML
    private void delete(ActionEvent event) throws SQLException {

        Forum dis = tableFourm.getSelectionModel().getSelectedItem();
        if (!dis.equals(null)) {
            fc.delete(dis.getId());
            initTable();
            clearAll();
        }
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {

        Forum F = tableFourm.getSelectionModel().getSelectedItem();
        if (F == null) {
            JOptionPane.showMessageDialog(null, "There is nothing selected !");
        } else {
            if (tfTitleForum.getText().isEmpty() || tfDescriptionForum.getHtmlText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "please fill all the textfields ");
            } else {
                controleDescription.setText("");
                controleTitle.setText("");
                fc.update(F.getId(), CurseFilterService.cleanText(tfTitleForum.getText()), CurseFilterService.cleanText(tfDescriptionForum.getHtmlText()));
                initTable();
                clearAll();
            }
        }
    }

    @FXML
    private void handleAction(MouseEvent event
    ) {
        Forum F = tableFourm.getSelectionModel().getSelectedItem();
        tfTitleForum.setText(F.getTitle());
        tfDescriptionForum.setHtmlText(F.getDescription());
        String id = Integer.toString(F.getId());
        titleF.setText(F.getTitle());
        tfIdForum.setText(id);
        engine = webviewforum.getEngine();
        engine.loadContent(F.getDescription());
    }

    @FXML
    private void detailsForum(ActionEvent event) {
        try {

            FXMLLoader loader
                    = new FXMLLoader(getClass().getResource("/esprit/view/DetailForum.fxml"));
            Parent root = loader.load();

            DetailForumController dc = loader.getController();
            dc.setRestDescriptionFourm(tfDescriptionForum.getHtmlText());
            dc.setRestTitleForum(tfTitleForum.getText());
            dc.setRestIdFourm(tfIdForum.getText());
            dc.setTforum(tfTitleForum.getText());
            try {

                dc.initTable((ObservableList<Post>) pc.readAllpost2(Integer.parseInt(tfIdForum.getText())));
            } catch (SQLException ex) {
                Logger.getLogger(ForumController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tfTitleForum.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(ForumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void exit(MouseEvent event) {

        stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void addForum(int id, String title, String description) {

        VBox v = new VBox();

        Label titreF = new Label(title);
        Label descriptionF = new Label(description);
        Forum f = new Forum(id, title, description);

        titreF.setStyle("-fx-font-weight: bold; -fx-underline: true;-fx-text-fill: black;");
        titreF.setFont(Font.font("Calibri", 35));
        // titreF.setStyle("-fx-font-weight: bold; -fx-underline: true;-fx-text-fill: black;");
        titreF.applyCss();
        titreF.setOnMousePressed((mouseEvent) -> {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/esprit/view/DetailForum.fxml"));
                Parent root = loader.load();

                DetailForumController dc = loader.getController();
                dc.setRestDescriptionFourm(tfDescriptionForum.getHtmlText());
                dc.setRestTitleForum(tfTitleForum.getText());
                dc.setRestIdFourm(tfIdForum.getText());
                dc.setTforum(tfTitleForum.getText());
                System.out.println(tfIdForum.getText());
                
                try {

                    dc.initTable((ObservableList<Post>) pc.readAllpost2(Integer.parseInt(tfIdForum.getText())));
                
                } catch (SQLException ex) {
                    Logger.getLogger(ForumController.class.getName()).log(Level.SEVERE, null, ex);
                }
                tfTitleForum.getScene().setRoot(root);

            } catch (IOException ex) {
                Logger.getLogger(ForumController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
       
    }

    public void affForm() {
        ForumCRUD fc = new ForumCRUD();
        fc.getForums().forEach(l
                -> {
            addForum(l.getId(), l.getTitle(), l.getDescription());
        });
    }

    @FXML
    private void Dashboard(ActionEvent event) {
        try {

            FXMLLoader loader
                    = new FXMLLoader(getClass().getResource("/esprit/view/BackForum.fxml"));
            Parent root = loader.load();

            BackForumController dc = loader.getController();

            tfTitleForum.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(ForumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Clear(ActionEvent event) {
        tfTitleForum.setText("");
        tfDescriptionForum.setHtmlText("");
        tfIdForum.setText("");
    }
     

}
