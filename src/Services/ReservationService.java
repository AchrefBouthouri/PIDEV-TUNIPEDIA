/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Reservation;
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
            ste.setDate(1, r.getDate());
            ste.setBoolean(2, r.isValidation());
            ste.setString(3, r.getCreatedBy());
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
        String sql = "SELECT r.Date,r.Validation,r.Createdby,p.Name from Reservation AS r INNER JOIN Place AS p ON r.Place_id=p.id";
        try {
            ste =mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
            Reservation r = new Reservation();
           r.setDate(rs.getDate(1));
            r.setValidation(rs.getBoolean(2));
            r.setCreatedBy(rs.getString(3));
            r.setLocation(rs.getString(4));
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
           ste.setDate(1, Date);
           ste.setInt(2, id);
           ste.executeUpdate(); 
            System.out.println("Date mis à jour!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }
       
      public void UpdateLocation(String Location, int id){
             String sql = "UPDATE  Reservation set Location=? where id=?";
        try {
           ste = mc.prepareStatement(sql);
           ste.setObject(1, Location);
           ste.setInt(1, id);
           ste.executeUpdate(); 
            System.out.println("Location mis à jour!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }   
}
