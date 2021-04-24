/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author ASUS
 */
public class MyConnection {
    public String url = "jdbc:mysql://localhost:3306/serviceprovider";
    public String user = "root";
    public String pwd = "";
    public static MyConnection instance;
 
    
    Connection cnx;

    private MyConnection() {

        try {
            cnx = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   

    public Connection getCnx() {
        return cnx;
    }
    public static MyConnection getInstance() {
        if(instance == null){
            instance = new MyConnection();
        }
        return instance;
    }
}
