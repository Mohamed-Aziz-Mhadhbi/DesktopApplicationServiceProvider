/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author aissa
 */
public class Myconnexion {

    private static Myconnexion instance;
    private Connection cnx;

    private final String URL = "jdbc:mysql://localhost:3306/serviceprovider";
    private final String LOGIN = "root";
    private final String PASSWORD = "";

    private Myconnexion() {
        try {
            cnx = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Connecting !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static Myconnexion getInstance() {
        if (instance == null) {
            instance = new Myconnexion();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}
