package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
    final static String URL = "jdbc:mysql://127.0.0.1:3306/ServiceProvider";
    final static String LOGIN = "root";
    final static String PWD = "";
    static dbConnection instance = null;
    private Connection cnx;

    private dbConnection(){
        try{
            cnx = DriverManager.getConnection(URL,LOGIN,PWD);
            System.out.println("connection success");
        }catch (SQLException ex){
            System.out.println("connection failed");
        }
    }

    public static dbConnection getInstance(){
        if (instance == null){
            instance = new dbConnection();
        }

        return instance;
    }

    public Connection getConnection(){
        return cnx;
    }
}
