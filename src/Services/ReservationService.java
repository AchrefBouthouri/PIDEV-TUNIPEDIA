/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
//import java.time.LocalDate;
import Entities.Reservation;
import Tools.ConnexionDB;
import java.sql.Connection;
import java.sql.Date;
//import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Wassym
 */
public class ReservationService {
      Connection mc;
    PreparedStatement ste; 

    public ReservationService() {
        mc= ConnexionDB.getInstance().getCnx();
    }
    public void AjouterReservation(Reservation r){
            String sql = "INSERT INTO Reservation(date,Validation,CreatedBy,Place_id) VALUES(?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setDate(1,Date.valueOf( r.getDate()));
            ste.setBoolean(2, r.isValidation());
            ste.setInt(3, r.getCreatedBy());
            ste.setInt(4, r.getPlace_id());
            mc.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("Reservation ajoutée!");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }
public List<Reservation> afficherReservation(){
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT r.Id,r.Date,r.Validation,pe.FullName,p.Name FROM Reservation AS r LEFT JOIN Place as p on r.Place_id=p.id LEFT JOIN Person as pe ON r.CreatedBy=pe.Id";
        try {
            ste =mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
            Reservation r = new Reservation();
            r.setId(rs.getInt(1));
           r.setDate(rs.getDate(2).toLocalDate());
            r.setValidation(rs.getBoolean(3));
            r.setFName(rs.getString(4));
            //r.setCreatedBy(rs.getInt(4));
            r.setLocation(rs.getString(5));
            
            
            reservations.add(r);
            }
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
       // System.out.println(personnes);
         return reservations;
    }

    public void SupprimerReservation(int id){
             String sql = "delete from Reservation where id=?";
        try {
           ste = mc.prepareStatement(sql);
           ste.setInt(1, id);
           ste.executeUpdate(); 
            System.out.println("Reservation Supprimée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }

    public void UpdateDate(Date Date, int id){
             String sql = "UPDATE  Reservation set Date=? where id=?";
        try {
           ste = mc.prepareStatement(sql);
           ste.setDate(1,Date);
           ste.setInt(2, id);
           ste.executeUpdate(); 
            System.out.println("Date mis à jour!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }
     
    
       
      public void UpdateConfirmation(Boolean confirm, int id){
             String sql = "UPDATE  Reservation set Validation=? where Id=?";
             confirm =true;
        try {
           ste = mc.prepareStatement(sql);
           ste.setBoolean(1, confirm);
           ste.setInt(2, id);
           ste.executeUpdate(); 
            System.out.println(" Reservation Confirmed!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
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
      public int getcount(int idreserveur,int placeid,LocalDate jourchoisi) {
        int theCount = 0;
        try {
            String req = "select count(*) from reservation where Place_id=? and CreatedBy=? and Date=?";
            PreparedStatement st = mc.prepareStatement(req);
            st.setInt(1, placeid);
            st.setInt(2,idreserveur);
            st.setDate(3,Date.valueOf(jourchoisi));
            
            ResultSet rs = st.executeQuery();
            if  (rs.next()) {
            theCount = rs.getInt(1);
          // System.out.println(theCount);
}
        } catch (SQLException a) {
        }
        return theCount;
    }
      
      
}
