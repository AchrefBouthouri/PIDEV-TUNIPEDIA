/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Attachement;
import Tools.ConnexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author youss
 */
public class AttachementService {

    Connection conn;
    PreparedStatement ste;

    public AttachementService() {
        conn = ConnexionDB.getInstance().getCnx();
    }

    public void ajouterAttachement(Attachement a) {
        String sql = "insert into attachement(name,path) Values(?,?)";
        try {
            ste = conn.prepareStatement(sql);
            ste.setString(1, a.getName());
            ste.setString(2, a.getPath());
            ste.executeUpdate();
            System.out.println("Attachement Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
        public void SupprimerAttachement(Attachement a){
             String sql = "delete from attachement where Id=?";
        try {
           ste = conn.prepareStatement(sql);
           ste.setInt(1, a.getId());
           ste.executeUpdate(); 
            System.out.println("Attachement Supprimée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }
}
