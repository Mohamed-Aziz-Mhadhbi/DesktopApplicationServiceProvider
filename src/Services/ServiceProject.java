/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.project;
import Utils.dbConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author marwe
 */
public class ServiceProject {
     Connection cnx = dbConnection.getInstance().getConnection();
private  ResultSet rst;
private Statement stn;
    private PreparedStatement pre;

public void AjouterD(project O) {
       
        try {
            String requete = "INSERT INTO `project`(`id`, `project_user_id`, `title`,`description`, `creat_at`, `status`,`domain_id_id`)  VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, O.getId());
            pst.setInt(2, O.getProject_user_id());
            pst.setString(3, O.getTitle());
            pst.setString(4, O.getDescription());
            pst.setDate(5, O.getCreat_at());
            pst.setInt(6, O.getStatus());
            pst.setInt(7, O.getDomain_id_id());
            pst.executeUpdate();
            System.out.println("projet ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    }
public ObservableList<project> readAll() throws SQLException {
        ObservableList oblistdisc = FXCollections.observableArrayList();

        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery("select * from project ;");
        while (rs.next()) {
            int id = rs.getInt("id");
            int project_user_id = rs.getInt("project_user_id");
            String title = rs.getString("title");
            String description = rs.getString("description");
            Date creat_at = rs.getDate("creat_at");
            int status = rs.getInt("status");
            int domain_id_id = rs.getInt("domain_id_id");
//project(int id, int project_user_id, String title, String description, Date creat_at, int status, int domain_id_id)

            project d = new project(id,project_user_id,title,description,creat_at,status,domain_id_id );
            oblistdisc.add(d);

        }
        return oblistdisc;
    }
public void delete(project O) {
        String req = "delete from project where id=" + O.getId();
        //if (c != null) {
            try {
            PreparedStatement st1 = cnx.prepareStatement(req);
             //String value1 = tf_instru.getText();
            st1.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
            }
        /*} else {
            System.out.println("Service n'existe pas");
        }*/

    }
public void modif(int id, int Status ) {
        try {
            String request="UPDATE project SET status = ? WHERE id = ?";
            PreparedStatement pre=cnx.prepareStatement(request);
            pre.setInt(1, Status);
            pre.setInt(2, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
 public List<project> displayClause(String cl) throws SQLException {
        String requeteInsert = "Select c.id,c.project_user_id,c.title,c.description,c.creat_at,c.status,c.domain_id_id FROM project c  "+cl+";";
        List<project> emp=new ArrayList<>();
        pre = cnx.prepareStatement(requeteInsert);
        ResultSet rs=pre.executeQuery(requeteInsert);
        while(rs.next())
        {
            int id=rs.getInt("c.id");
             int project_user_id=rs.getInt("c.project_user_id");
                String title=rs.getString("c.title");
                 String description=rs.getString("c.description");
                 Date creat_at=rs.getDate("c.creat_at");
                 int domain_id_id=rs.getInt("c.domain_id_id");
                 int status=rs.getInt("c.status");
               
                   //public project(int id, int project_user_id, String title, String description, Date creat_at, int status, int domain_id_id) {

               project p=new project ( id,project_user_id,title,description,creat_at,status,domain_id_id);
                emp.add(p);
                               System.out.println(p);
            System.out.println(emp.isEmpty());
        }
        return emp;
        
    }
}
