/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceProvider.controllers;

import Entities.Comment;
import Entities.Forum;
import Entities.Post;
import Entities.User;
import Services.CommentCRUD;
import Services.CurseFilterService;
import Services.ForumCRUD;
import Services.PostCRUD;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.sql.Connection;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import Utils.dbConnection;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Mohamed Aziz Mhadhbi
 */
public class DetailForumController implements Initializable {

    @FXML
    private Label restTitleForum;
    @FXML
    private Label restDescriptionFourm;
    @FXML
    public TableView<Post> tablePost;
    @FXML
    private TableColumn<Post, String> col_title;
    @FXML
    private TableColumn<Post, String> col_description;
    @FXML
    private TableColumn<Post, Integer> col_views;
    @FXML
    private TableColumn<Post, Integer> col_noc;

    ObservableList<Post> oblistpost = FXCollections.observableArrayList();
    ForumCRUD pf = new ForumCRUD();
    PostCRUD pc = new PostCRUD();
    CommentCRUD cc = new CommentCRUD();
    @FXML
    private Label idF;

    @FXML
    private TextField tfTitlePost;
    @FXML
    private TextArea tfDescriptionPost;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnDetailsPost;
    @FXML
    private Label idP;
    Stage stage;

    Forum forumTest;
    @FXML
    private Button profile;
    @FXML
    private ImageView btnprofile;
    @FXML
    private Label userlabel;
    @FXML
    private ImageView logout;
    private Label entetForum;
    @FXML
    private Label Tforum;
    @FXML
    private Label tPost;
    private PieChart statPost;
    ObservableList<PieChart.Data> statPostdata;
    private Connection con;
    @FXML
    private Button btnClear;
    User user = null;
    @FXML
    private ImageView returnBack;
    @FXML
    private Label UserNameSession;

    /**
     * Initializes the controller class.
     */
    public DetailForumController() {
        dbConnection.getInstance().getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idF.setVisible(false);
        idP.setVisible(false);
    }

    public void setUser(User u) throws SQLException {
        this.user = u;
        UserNameSession.setText(cc.userName(user.getId()));
    }

    private void clearAll() {
        idP.setText("");
        tfTitlePost.setText("");
        tfDescriptionPost.setText("");
    }

    public void setTfTitlePost(String tfTitlePost) {
        this.tfTitlePost.setText(tfTitlePost);
    }

    public void setTfDescriptionPost(String tfDescriptionPost) {
        this.tfDescriptionPost.setText(tfDescriptionPost);
    }

    public void setIdP(String idP) {
        this.idP.setText(idP);
    }

    public void setRestDescriptionFourm(String restDescriptionFourm) {
        this.restDescriptionFourm.setText(restDescriptionFourm);
    }

    public void setTforum(String Tforum) {
        this.Tforum.setText(Tforum);
    }

    public void settPost(String tPost) {
        this.tPost.setText(tPost);
    }

    public void setRestTitleForum(String restTitleForum) {
        this.restTitleForum.setText(restTitleForum);
    }

    public void setRestIdFourm(String idF) {
        this.idF.setText(idF);
    }

    private void initTable(int id) {
        try {
            oblistpost = (ObservableList<Post>) pc.readAllpost2(id);
            col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
            col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
            col_noc.setCellValueFactory(new PropertyValueFactory<>("noc"));
            col_views.setCellValueFactory(new PropertyValueFactory<>("views"));

            tablePost.setItems(oblistpost);
        } catch (SQLException ex) {
            Logger.getLogger(DetailForumController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void initTable(ObservableList<Post> posts) {
        col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_noc.setCellValueFactory(new PropertyValueFactory<>("noc"));

        col_views.setCellValueFactory(new PropertyValueFactory<>("views"));
        tablePost.setItems(posts);

    }

    @FXML
    private void ajouter(ActionEvent event) {
        if (tfTitlePost.getText().isEmpty() || tfDescriptionPost.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "please fill all the textfields");
        } else {
            PostCRUD pc = new PostCRUD();
            Post p = new Post();
            String tTitle = CurseFilterService.cleanText(tfTitlePost.getText());
            String tDescription = CurseFilterService.cleanText(tfDescriptionPost.getText());
            p.setTitle(tTitle);
            p.setDescription(tDescription);
            p.setIdF(Integer.valueOf(idF.getText()));
            p.setUsr_id(user.getId());
           
            pc.addPost(p);

            clearAll();
            initTable(Integer.valueOf(idF.getText()));
            System.out.println(p.getDate());

            Image img = new Image("/ServiceProvider/view/image/ok.png");
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
    private void select(MouseEvent event) {
        Post P = tablePost.getSelectionModel().getSelectedItem();
        tfTitlePost.setText(P.getTitle());
        tfDescriptionPost.setText(P.getDescription());
        Integer idPost = P.getId();
        idP.setText(idPost.toString());
        String id = Integer.toString(P.getId());
        String tpost = tfTitlePost.getText();
        tPost.setText(">" + tpost);
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        Integer i = Integer.valueOf(idF.getText());
        Post dis = tablePost.getSelectionModel().getSelectedItem();
        if (!dis.equals(null)) {
            pc.delete(dis.getId());
            initTable(i);
            clearAll();
        }
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
        Integer i = Integer.valueOf(idF.getText());
        Post P = tablePost.getSelectionModel().getSelectedItem();
        if (P == null) {
            JOptionPane.showMessageDialog(null, "There is nothing selected !");
        } else if (tfTitlePost.getText().isEmpty() || tfDescriptionPost.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "please fill all the textfields");
        } else {
            pc.update(P.getId(), CurseFilterService.cleanText(tfTitlePost.getText()), CurseFilterService.cleanText(tfDescriptionPost.getText()));
            initTable(i);
            clearAll();
        }

    }

    @FXML
    private void DetailsPost(ActionEvent event) throws SQLException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ServiceProvider/view/DetailPost.fxml"));
            Parent root = loader.load();
            String v = idP.getText();
            int idPclicked = Integer.valueOf(v);
            DetailPostController dp = loader.getController();
            dp.setDescriptionP(tfDescriptionPost.getText());
            dp.setTitleP(tfTitlePost.getText());
            dp.setIdPc(idP.getText());
            dp.setIdForum(idF.getText());
            dp.setTitleForum(restTitleForum.getText());
            dp.setDescriptionFourm(restDescriptionFourm.getText());
            dp.setTF(restTitleForum.getText());
            dp.setTP(">" + tfTitlePost.getText());
            dp.setUser(this.user);
            pc.updatePostview(idPclicked);
            initTable(idPclicked);
            try {
                dp.initTable((ObservableList<Comment>) cc.readAllcomment2(Integer.parseInt(idP.getText())));

            } catch (SQLException ex) {
                Logger.getLogger(ForumController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tfTitlePost.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(DetailPostController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void exit(MouseEvent event) {
        stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Clear(ActionEvent event) {
        idP.setText("");
        tfTitlePost.setText("");
        tfDescriptionPost.setText("");
    }

    @FXML
    private void ReturnBack(MouseEvent event) throws SQLException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ServiceProvider/view/Forum.fxml"));
            Parent root = loader.load();

            ForumController df = loader.getController();
            df.setTfDescriptionForum(restDescriptionFourm.getText());
            df.setTfTitleForum(restTitleForum.getText());
            df.setTfIdForum(idF.getText());
            df.setTitleF(restTitleForum.getText());
            df.setUser(this.user);
            tfTitlePost.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(ForumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
