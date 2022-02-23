/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Person;
import Entities.Place;
import Enum.Type;
import Tools.ConnexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Achref Bouthouri
 */
public class PlaceService {
    Connection mc;
    PreparedStatement ste,ste1; 


    public PlaceService() {
        mc= ConnexionDB.getInstance().getCnx();


    }
    public void AjouterPlace(Place p, Person p1){
            String sql1 = "Update Person Set Role='Owner' where FullName=?";
            String sql = "INSERT INTO Place(Name,Description,Adress,City,PostalCode,Latitude,Longitude,Category,Evaluation,Notice,Status,CreatedBy,Type,Attachement) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
           // ste.setInt(1, p.getId());
            ste.setString(1, p.getName());
            ste.setString(2, p.getDescription());
            ste.setString(3, p.getAdresse());
            ste.setString(4, p.getCity());
            ste.setString(5, p.getPostalCode());
            ste.setString(6, p.getLatitude());
            ste.setString(7, p.getLongitude());
            ste.setInt(8, p.getCategory_id());
            ste.setInt(9, p.getEvaluation());
            ste.setInt(10, p.getNotice());
            ste.setBoolean(11, p.isStatus());
            ste.setInt(12, p.getCreatedBy());
            ste.setString(13, p.getType());
            ste.setInt(14, p.getAttachement());
            
            mc.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("Place ajoutÃ©e!");
            if ( (p1.getRole() != "Owner") && (p.getType() == "Private" ))
            {
               ste = mc.prepareStatement(sql1);
           ste.setString(1, p1.getFullName());
           ste.executeUpdate(); 
            System.out.println("Vous ete promu en Owner");
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }
    
public List<Place> afficherPlace(){
        List<Place> place = new ArrayList<>();
        String sql = "SELECT p.Id,p.Name,p.Description,p.Adress,p.City,p.PostalCode,p.Latitude,p.Longitude,p.Evaluation,p.Notice,p.Status,p.Createdby,p.Type,p.Attachement,c.Name from Place AS p INNER JOIN category AS c ON p.category=c.id";
        try {
            ste =mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
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
/*
    public void SupprimerPlace(Place p){
             String sql = "delete from Place where Id=?";
        try {
           ste = mc.prepareStatement(sql);
           ste.setInt(1, p.getId());
           ste.executeUpdate(); 
            System.out.println("Place SupprimÃ©e!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }
    
    public void UpdateNamePlace(String Name, Place p){
             String sql = "UPDATE  Place set Name=? where id=?";
        try {
           ste = mc.prepareStatement(sql);
           ste.setString(1, Name);
           ste.setInt(2, p.getId());
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
  */  

}
