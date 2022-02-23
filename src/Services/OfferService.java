/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Offer;

import Tools.ConnexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amine
 */
public class OfferService{
    Connection mc;
    PreparedStatement ste;

    public OfferService() {
        mc=ConnexionDB.getInstance().getCnx();
    }

    
    
     public void ajouterOffer(Offer o){
        String sql ="insert into offer(Date_Debut,Date_Fin,Montant,Id_Event) Values(?,?,?,?) ";
        try {
            ste=mc.prepareStatement(sql);
           
            ste.setDate(1, o.getDate_debut());
            ste.setDate(2, o.getDate_fin());
            ste.setFloat(3, o.getMontant());
            ste.setInt(4, o.getId_Event());
            
            
            ste.executeUpdate();
            System.out.println("offer ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   
    
   

     }
     public List<Offer> afficherOffer(){
        List<Offer> offers = new ArrayList<>();
        String sql = "SELECT o.Id,o.Date_debut,o.Date_fin,o.Montant,e.Location from Offer AS o INNER JOIN Event AS e ON o.Id_Event=e.id";
        try {
            ste =mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
            Offer o = new Offer();
            o.setId(rs.getInt(1));
           o.setDate_debut(rs.getDate(2));
            o.setDate_fin(rs.getDate(3));
            o.setMontant(rs.getFloat(4));
            o.setLocation(rs.getString(5));
           
            offers.add(o);
            }
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
       // System.out.println(personnes);
         return offers;
    }


      
   public void UpdateMontant(float Montant, int id){
             String sql = "UPDATE  offer set Montant=? where Id=?";
        try {
           ste = mc.prepareStatement(sql);
           ste.setFloat(1, Montant);
           ste.setInt(2,id);
           
           ste.executeUpdate(); 
            System.out.println("Montant mise ajour effectue");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }
    

 public void UpdateDateDebut(Date date_debut, int id){
             String sql = "UPDATE  Offer set Date_Debut=? where Id=?";
        try {
           ste = mc.prepareStatement(sql);
           ste.setDate(1,date_debut);
           ste.setInt(2, id);
           ste.executeUpdate(); 
            System.out.println("Date_debut mise a jour");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }
 
      
      public void Updatedatefin(Date date_fin ,int id  ){
             String sql = "UPDATE  Offer set Date_Fin=? where Id=?";
        try {
           ste = mc.prepareStatement(sql);
           ste.setDate(1,date_fin);
           ste.setInt(2,id);
           ste.executeUpdate(); 
            System.out.println("Date_Fin mise a jour");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }
    

   public void SupprimerOffer(int id){
             String sql = "DELETE  FROM Offer where Id=? ";
        try {
           ste = mc.prepareStatement(sql);
           ste.setInt(1, id);
           ste.executeUpdate(); 
            System.out.println("Offer  Supprimée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }
 
}

    
 
