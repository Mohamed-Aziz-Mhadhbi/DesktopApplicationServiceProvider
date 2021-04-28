/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceProvider.controllers;

import Entities.Comment;
import Entities.Post;
import Entities.User;
import Services.CommentCRUD;
import Services.CurseFilterService;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class DetailPostController implements Initializable {

    @FXML
    private Label TitleP;
    @FXML
    private Label DescriptionP;
    @FXML
    private TableView<Comment> tableComment;
    @FXML
    private TableColumn<Comment, String> col_content;
    @FXML
    private TableColumn<Comment, Integer> col_rating;
    private ComboBox<Post> ComboP;
    PostCRUD pc = new PostCRUD();
    CommentCRUD cc = new CommentCRUD();
    Post p = new Post();
    ObservableList<Comment> oblistcomment = FXCollections.observableArrayList();
    private Label idtestp;
    private Label testtitlep;
    private Label testdescriptionp;
    @FXML
    private TextArea contentC;
    @FXML
    private TextField rating;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Label idC;
    Stage stage;
    @FXML
    private Label idPc;
    @FXML
    private Label idForum;
    @FXML
    private Label titleForum;
    @FXML
    private Label descriptionFourm;
    @FXML
    private Button profile;
    @FXML
    private ImageView btnprofile;
    @FXML
    private Label userlabel;
    @FXML
    private ImageView logout;
    @FXML
    private Label TF;
    @FXML
    private Label TP;
    @FXML
    private Button btnClear;
    @FXML
    private Rating RatingPost;
    @FXML
    private Rating RatingCommet;
    User user = null;
    @FXML
    private ImageView returnBack;
    @FXML
    private Label UserNameSession;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        check(rating.getText());
        idC.setVisible(false);
        idPc.setVisible(false);
        idForum.setVisible(false);
        // initTable();
    }

    public void setUser(User u) throws SQLException {
        this.user = u;
        UserNameSession.setText(pc.userName(user.getId()));
    }

    private void clearAll() {
        idC.setText("");
        contentC.setText("");
        rating.setText("");
        RatingCommet.setRating(0);
    }

    private void rating() throws SQLException {
        RatingPost.setRating(pc.getAvgRating(Integer.parseInt(idPc.getText())));
    }

    /**
     * ******forum***********
     */
    public void setIdForum(String idForum) {
        this.idForum.setText(idForum);
    }

    public void setTitleForum(String titleForum) {
        this.titleForum.setText(titleForum);
    }

    public void setDescriptionFourm(String descriptionFourm) {
        this.descriptionFourm.setText(descriptionFourm);
    }

    public void setTF(String TF) {
        this.TF.setText(TF);
    }

    /**
     * ******post***********
     */
    public void setDescriptionP(String DescriptionP) {
        this.DescriptionP.setText(DescriptionP);
    }

    public void setTitleP(String TitleP) {
        this.TitleP.setText(TitleP);
    }

    public void setIdPc(String idPc) {
        this.idPc.setText(idPc);
    }

    public void setTP(String TP) {
        this.TP.setText(TP);
    }

    public void initTable(ObservableList<Comment> comments) {
        col_content.setCellValueFactory(new PropertyValueFactory<>("content"));
        col_rating.setCellValueFactory(new PropertyValueFactory<>("rating"));

        tableComment.setItems(comments);
    }

    public void initTable(int i) {
        try {
            oblistcomment = (ObservableList<Comment>) cc.readAllcomment2(i);
            col_content.setCellValueFactory(new PropertyValueFactory<>("content"));
            col_rating.setCellValueFactory(new PropertyValueFactory<>("rating"));

            tableComment.setItems(oblistcomment);
        } catch (SQLException ex) {
            Logger.getLogger(DetailPostController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        if (contentC.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "please fill all the textfields");
        } else {
            CommentCRUD cc = new CommentCRUD();
            Comment c = new Comment();
            String content = CurseFilterService.cleanText(contentC.getText());
            c.setContent(content);
            c.setRating((int) RatingCommet.getRating());
            Integer i = Integer.valueOf(idPc.getText());
            c.setIdP(i);
            cc.updatePost(i);
            cc.addComment(c, i);
            System.out.println(RatingCommet.getRating());
            clearAll();
            initTable(i);
            rating();

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
        Comment C = tableComment.getSelectionModel().getSelectedItem();
        contentC.setText(C.getContent());
        Integer r = C.getRating();
        RatingCommet.setRating(r);
        rating.setText(r.toString());
        Integer id = C.getId();
        idC.setText(id.toString());
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
        Comment C = tableComment.getSelectionModel().getSelectedItem();
        if (C == null) {
            JOptionPane.showMessageDialog(null, "There is nothing selected !");
        } else if (contentC.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "please fill all the textfields");
        } else {
            Integer i = Integer.valueOf(idPc.getText());
            cc.update(C.getId(), CurseFilterService.cleanText(contentC.getText()), (int) RatingCommet.getRating());
            initTable(i);
            clearAll();
            rating();
        }
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        Comment dis = tableComment.getSelectionModel().getSelectedItem();
        if (!dis.equals(null)) {
            cc.delete(dis.getId());
            Integer i = Integer.valueOf(idPc.getText());
            cc.updatePostnocDelete(i);
            initTable(i);
            clearAll();
            rating();
        }
    }

    @FXML
    private void exit(MouseEvent event) {
        stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Clear(ActionEvent event) {
        idC.setText("");
        contentC.setText("");
        rating.setText("");
    }

    private boolean check(String text) {
        if (text.matches("^[0-9]{0,5}$")) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    private void ReturnBack(MouseEvent event) throws SQLException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ServiceProvider/view/DetailForum.fxml"));
            Parent root = loader.load();

            DetailForumController dc = loader.getController();
            dc.setTfDescriptionPost(DescriptionP.getText());
            dc.setTfTitlePost(TitleP.getText());
            dc.setIdP(idPc.getText());
            dc.setRestIdFourm(idForum.getText());
            dc.setRestTitleForum(titleForum.getText());
            dc.setRestDescriptionFourm(descriptionFourm.getText());
            dc.setTforum(titleForum.getText());
            dc.settPost(">" + TitleP.getText());
            dc.setUser(this.user);
            try {
                dc.initTable((ObservableList<Post>) pc.readAllpost2(Integer.parseInt(idForum.getText())));
            } catch (SQLException ex) {
                Logger.getLogger(ForumController.class.getName()).log(Level.SEVERE, null, ex);
            }
            TitleP.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(ForumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
