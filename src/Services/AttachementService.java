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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        int id = 0;
        String sql = "insert into attachement(name,path) Values(?,?)";
        try {
            ste = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ste.setString(1, a.getName());
            ste.setString(2, a.getPath());
            ste.executeUpdate();
            System.out.println("Attachement Ajoutée");
            ResultSet rs=ste.getGeneratedKeys();
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
      public List<Attachement> afficherAttachement(){
        List<Attachement> Attachement = new ArrayList<>();
        String sql = "SELECT * from Attachement";
        try {
            ste =conn.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
            Attachement a = new Attachement();
            a.setId(rs.getInt(1));
           a.setName(rs.getString(2));
            a.setPath(rs.getString(3));
           
            Attachement.add(a);
            }
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
       // System.out.println(personnes);
         return Attachement;
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
}