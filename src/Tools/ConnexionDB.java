/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Achref Bouthouri
 */
public class ConnexionDB {
     public   String url="jdbc:mysql://localhost:3306/tunipedia-1";
    public   String user="root";
    public   String pwd="";
    public   Statement ste; 
    public static ConnexionDB cn; 
    private Connection cnx; 
    private ConnexionDB(){
        try {
            cnx=DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etablie! ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static ConnexionDB getInstance(){
    if (cn==null)
        cn = new ConnexionDB();
        return cn;
        }

    public Connection getCnx() {
        return cnx;
    }
    
    
}

