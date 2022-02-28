/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Attachement;
import Entities.Person;
import Enum.Gender;
import Tools.ConnexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        
        
   public Attachement findById(int id) {
        Attachement a = null;
        try {
            String req = "select * from Attachement where id=? ";
            ste = conn.prepareStatement(req);
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                a = new Attachement(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return a;
    }

      public Attachement findByPath(String Path) {
        Attachement a = null;
        try {
            String req = "select * from Attachement where Path=? ";
            ste = conn.prepareStatement(req);
            ste.setString(1, Path);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                a = new Attachement(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return a;
    }
}
