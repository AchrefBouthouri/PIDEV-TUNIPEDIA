/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Attachement;
import java.util.Date;

import Entities.Person;
import Entities.Place;
import Enum.Type;
import Tools.ConnexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Achref Bouthouri
 */
public class PlaceService {

    Connection mc;
    PreparedStatement ste, ste1;

    public PlaceService() {
        mc = ConnexionDB.getInstance().getCnx();
    }
    

    public void AjouterPlace(Place p, Person p1) {
        String sql = "INSERT INTO Place(Name,Description,Adress,City,PostalCode,Latitude,Longitude,capacite,Category,Evaluation,Notice,Status,CreatedBy,Type,Attachement,prix) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String sql1 = "Update Person Set Role='Owner' where id=?";
        try {
            // Insert Place
            ste = mc.prepareStatement(sql);
            // ste.setInt(1, p.getId());
            ste.setString(1, p.getName());
            ste.setString(2, p.getDescription());
            ste.setString(3, p.getAdresse());
            ste.setString(4, p.getCity());
            ste.setString(5, p.getPostalCode());
            ste.setString(6, p.getLatitude());
            ste.setString(7, p.getLongitude());
            ste.setInt(8, p.getCapacite());
            ste.setInt(9, p.getCategory_id());
            ste.setInt(10, p.getEvaluation());
            ste.setInt(11, p.getNotice());
            ste.setBoolean(12, p.isStatus());
            ste.setInt(13, p1.getId());
            ste.setString(14, p.getType());
            ste.setInt(15, p.getAttachement());
            ste.setFloat(16, p.getPrix());
            mc.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("Place ajoute!");

            // Update Preson Role
            if ((p1.getRole() != "Owner") && (p.getType() == "Private")) {
                ste = mc.prepareStatement(sql1);
                ste.setString(1, p1.getFullName());
                ste.executeUpdate();
                System.out.println("Vous ete promu en Owner");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Place> afficherPlace() {
        List<Place> place = new ArrayList<>();
        String sql = "SELECT p.Id,p.Name,p.Description,p.Adress,p.City,p.PostalCode,p.Latitude,p.Longitude,p.Evaluation,p.Notice,p.Status,p.Createdby,p.Type,p.Attachement,c.Name from Place AS p INNER JOIN category AS c ON p.category=c.id";
        try {
            ste = mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                Place p = new Place();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setAdresse(rs.getString(4));
                p.setCity(rs.getString(5));
                p.setPostalCode(rs.getString(6));
                p.setLatitude(rs.getString(7));
                p.setLongitude(rs.getString(8));
                p.setEvaluation(rs.getInt(9));
                p.setNotice(rs.getInt(10));
                p.setStatus(rs.getBoolean(11));
                p.setCreatedBy(rs.getInt(12));
                String TypeStr = rs.getString(13);
                Type TypeEnum = Type.valueOf(TypeStr);
                p.setType(TypeEnum);
                p.setAttachement(rs.getInt(14));
                p.setCategory(rs.getString(15));
                place.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        // System.out.println(personnes);
        return place;

    }
    
    public void SupprimerPlace(int id){
             String sql = "delete from Place where Id=?";
        try {
           ste = mc.prepareStatement(sql);
           ste.setInt(1, id);
           ste.executeUpdate(); 
            System.out.println("Place SupprimÃ©e!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }
    
    public void UpdateNamePlace(String Name, int id){
             String sql = "UPDATE  Place set Name=? where id=?";
        try {
           ste = mc.prepareStatement(sql);
           ste.setString(1, Name);
           ste.setInt(2, id);
           ste.executeUpdate(); 
            System.out.println("le Nom est mis Ã  jour!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }
      public void UpdateAdressPlace(String Adress, Place p){
             String sql = "UPDATE  Place set Adress=? where id=?";
        try {
           ste = mc.prepareStatement(sql);
           ste.setString(1, Adress);
           ste.setInt(2, p.getId());
           ste.executeUpdate(); 
            System.out.println("Adresse mise Ã  jour!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }
      
      public void UpdatetypePlace(String type, Place p){
             String sql = "UPDATE  Place set Type=? where Id=?";
        try {
           ste = mc.prepareStatement(sql);
           ste.setString(1, type);
           ste.setInt(2, p.getId());
           ste.executeUpdate(); 
            System.out.println("Type mis Ã  jour!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }
     
      public Place Selectplace(int idplace) {
        Place pu = null;
        try {
            String req = "select  Id,Name,Description,Adress,City,PostalCode,Latitude,Longitude,capacite,Category,Evaluation,Notice,Status,CreatedBy,Type,Attachement,prix from place where Id=? ";
            PreparedStatement st = mc.prepareStatement(req);
            st.setInt(1, idplace);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
              String TypeStr = rs.getString(15);
                Type TypeEnum = Type.valueOf(TypeStr);
                pu = new Place(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getInt(10),rs.getInt(11),rs.getInt(12),rs.getBoolean(13),rs.getInt(14),TypeEnum,rs.getInt(16),rs.getFloat(17));
               //             1 id, 2String name, 3String Description, 4String Adresse, 5String City, 6String 7PostalCode, 8String Latitude, 9String Longitude, ,10int category_id, 11int evaluation, 12int Notice, 13boolean Status, 14int CreatedBy, 15Type type,16 int attachement_id)

            }
        } catch (SQLException a) {
        }
        return pu;
    }
      public int getcount(int idplace,LocalDate date) {
        int theCount = 0;
        try {
            String req = "select count(*) from reservation where Place_id=? and Date=?";
            PreparedStatement st = mc.prepareStatement(req);
            st.setInt(1, idplace);
            st.setDate(2,java.sql.Date.valueOf( date));
            ResultSet rs = st.executeQuery();
            if  (rs.next()) {
            theCount = rs.getInt(1);
          // System.out.println(theCount);
}
        } catch (SQLException a) {
        }
        return theCount;
    }
      public float gettheprice(int idplace) {
        float balance = 0;
        try {
            String req = "select  prix from Place where Id=?";
            PreparedStatement st = mc.prepareStatement(req);
            st.setInt(1, idplace);
           
            ResultSet rs = st.executeQuery();
            if  (rs.next()) {
            balance = rs.getInt(1);
          // System.out.println(theCount);
}
        } catch (SQLException a) {
        }
        return balance;
    }
 public Attachement geturl(Place p) {
       // List<Place> place = new ArrayList<>();
       Attachement   A = new Attachement();
        String sql = "SELECT a.Id,a.Path from place AS p INNER JOIN attachement AS a ON p.Attachement=a.Id where p.Attachement=?";
        try {
            ste = mc.prepareStatement(sql);
            ste.setInt(1, p.getAttachement());
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                A= new Attachement (rs.getInt(1),rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        // System.out.println(personnes);
        return A;

    }
 
 public int gettheowner(int idplace) {
        int idowner = 0;
        try {
            String req = "select  CreatedBy from Place where Id=?";
            PreparedStatement st = mc.prepareStatement(req);
            st.setInt(1, idplace);
           
            ResultSet rs = st.executeQuery();
            if  (rs.next()) {
            idowner = rs.getInt(1);
          // System.out.println(theCount);
}
        } catch (SQLException a) {
        }
        return idowner;
    }
}
