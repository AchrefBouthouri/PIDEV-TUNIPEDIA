/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Reclamation;
import Tools.ConnexionDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Achref Bouthouri
 */
public class ReclamationService {

    Connection mc;
    PreparedStatement ste;
    
    public ReclamationService() {
        mc = ConnexionDB.getInstance().getCnx();
    }
    
    public void ajouterReclamation(Reclamation r) {
        String sql = "insert into Reclamation(Text,CreatedBy,Location) Values(?,?,?) ";
        try {
            ste = mc.prepareStatement(sql);
            
            ste.setString(1, r.getText());
            ste.setInt(2, r.getCreatedBy());
            ste.setInt(3, r.getLocation());
            //ste.setInt(4, o.getId_Event());
            
            ste.executeUpdate();
            System.out.println("Reclamation envoyéé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    public List<Reclamation> afficheReclamation() {
        List<Reclamation> Reclam = new ArrayList<>();
        String sql = "SELECT * from Reclamation";
        try {
            ste = mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                Reclamation o = new Reclamation();
                o.setId(rs.getInt(1));
                o.setText(rs.getString(2));
                o.setCreatedBy(rs.getInt(3));
                o.setLocation(rs.getInt(4));
                
                Reclam.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        // System.out.println(personnes);
        return Reclam;
    }

    public void SupprimerReclamation(int id) {
        String sql = "DELETE  FROM Reclamation where Id=? ";
        try {
            ste = mc.prepareStatement(sql);
            ste.setInt(1, id);
            ste.executeUpdate();            
            System.out.println("Reclamation  Supprimée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());            
        }
    }

   public Reclamation SelectReclam(int idreclam) {
        Reclamation ru = null;
        try {
            String req = "select  Id,Text,CreatedBy,Place_id,Attachement from reclamation where Id=? ";
            PreparedStatement st = mc.prepareStatement(req);
            st.setInt(1, idreclam);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
              
                ru= new Reclamation(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),rs.getInt(4),rs.getInt(5)
                        );
               //             1 id, 2String name, 3String Description, 4String Adresse, 5String City, 6String 7PostalCode, 8String Latitude, 9String Longitude, ,10int category_id, 11int evaluation, 12int Notice, 13boolean Status, 14int CreatedBy, 15Type type,16 int attachement_id)

            }
        } catch (SQLException a) {
        }
        return ru;
    }
    
}
