package Utils;

import Entities.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class dbConnection {
    final static String URL = "jdbc:mysql://localhost/serviceprovider";
    final static String LOGIN = "root";
    final static String PWD = "";
    static dbConnection instance = null;
    private Connection cnx;

    public dbConnection(){
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
    
     public static void supprimerLivraison(Service s) {
       Connection conn = dbConnection.getInstance().getConnection();
      
       //Id =Selected.getId().get();
     
        try {
                    String requete="DELETE from service where id=?"; //executer une requette parametrer.
                    PreparedStatement st=conn.prepareStatement(requete);
                    st.setInt(1, s.getId());
                   
// Statement st = conn.createStatement(); //instruction sql; envoyer la requete et l'executer coté base de donné.
                   int res = st.executeUpdate();
                    System.out.println("bien");
                 //   return true;
            //return true;
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
   // return false;
    }
}
