
package Services;

import java.sql.*;
import java.util.*;
import java.sql.Date;
import Tools.ConnexionDB;
//import java.time.LocalDate;

import Entities.Event;
public class EventService {
    Connection mc;
    PreparedStatement ste; 

    public EventService() {
        mc= ConnexionDB.getInstance().getCnx();
    }
    public void AjouterEvent(Event e){
            String sql = "INSERT INTO event(Date_Debut,Date_Fin,Description,capacite,montant,CreatedBy,Location,idoffre,idattachement) VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            
           ste.setDate(1,Date.valueOf(e.getDate_debut()));
            
        ste.setDate(2,Date.valueOf(e.getDate_fin()));
            ste.setString(3, e.getDescription());
            ste.setInt(4, e.getCapacite());
            ste.setFloat(5, e.getMontant());
            ste.setInt(6, e.getCreatedBy());
            ste.setInt(7, e.getLocation());
            ste.setInt(8, e.getIdoffre());
            ste.setInt(9, e.getIdattachement());
           
            mc.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("Evenement ajoutée!");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }
public List<Event> afficherEvent(){
        List<Event> event = new ArrayList<>();
        String sql = "select * from Event";
        try {
            ste =mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
            Event e;
                e = new Event();
            
            e.setId(rs.getInt(1));
            e.setDate_debut(rs.getDate(2).toLocalDate());
            e.setDate_fin(rs.getDate(3).toLocalDate());
            e.setDescription(rs.getString(4));
             e.setCreatedBy(rs.getInt(5));
            e.setLocation(rs.getInt(6));
            
           
            event.add(e);
                
            }
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
       // System.out.println(personnes);
         return event;
    }

    public void SupprimerEvent(Event e){
             String sql = "delete from Event where Id=?";
        try {
           ste = mc.prepareStatement(sql);
           ste.setInt(1, e.getId());
           ste.executeUpdate(); 
            System.out.println("Event Supprimée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }
    
  
    public void Updatedate_debut(String Date_Debut, Event e){
             String sql = "UPDATE  Event set Date_Debut=? where id=?";
        try {
           ste = mc.prepareStatement(sql);
           ste.setString(1, Date_Debut);
           ste.setInt(2, e.getId());
           ste.executeUpdate(); 
            System.out.println("la date debut est mise à jour!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }
      public void UpdateDate_Fin(String Date_Fin, Event e){
             String sql = "UPDATE  Event set Date_Fin=? where id=?";
        try {
           ste = mc.prepareStatement(sql);
           ste.setString(1, Date_Fin);
           ste.setInt(2, e.getId());
           ste.executeUpdate(); 
            System.out.println("Date Fun mise à jour!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }
      
      public void UpdatetypePlace(int location, Event e){
             String sql = "UPDATE  Event set Location=? where Id=?";
        try {
           ste = mc.prepareStatement(sql);
           ste.setInt(1, location);
           ste.setInt(2, e.getId());
           ste.executeUpdate(); 
            System.out.println("Location mise à jour!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }
    
}
