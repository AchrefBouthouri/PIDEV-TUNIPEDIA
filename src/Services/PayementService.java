/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Event;
import Entities.Payement;
import Entities.Person;
import Entities.Reservation;
import Tools.ConnexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Achref Bouthouri
 */
public class PayementService {

    Connection mc;
    PreparedStatement ste;

    public PayementService() {
        mc = ConnexionDB.getInstance().getCnx();
    }

    public void AjouterPayement(Payement p) {
        String sql = "INSERT INTO payment(Date,Description,Montant,Createdby,idoffre,sendtoid,idevent) VALUES(NOW(),?,?,?,?,?,?)";
        try {
            ste = mc.prepareStatement(sql);
            //   ste.setInt(1, p.getId());
            //ste.setDate(2, p.getDate());
            ste.setString(1, p.getDescription());
            ste.setFloat(2, p.getMontant());
            ste.setInt(3, p.getCreatedBy());
            ste.setInt(4, p.getId_Offer());
            ste.setInt(5, p.getSendtoid());
            ste.setInt(6, p.getIdreservation());

            // ste.setInt(5, p.getId_Offer());
            mc.prepareStatement(sql);
            ste.executeUpdate();

            System.out.println("Payement effectué!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Payement> afficherPayement(Person pe) {
        List<Payement> payement = new ArrayList<>();
        String sql = "select * from payment where sendtoid=? or CreatedBy=?";
        try {
            
            ste = mc.prepareStatement(sql);
            ste.setInt(1, pe.getId());
            ste.setInt(2, pe.getId());
            ResultSet rs = ste.executeQuery();
            
            //ste.setInt(2, id);
            while (rs.next()) {

                Payement p;
                p = new Payement();

                p.setId(rs.getInt(1));
                p.setDate(rs.getDate(2));
                p.setDescription(rs.getString(3));
                p.setMontant(rs.getFloat(4));
                p.setCreatedBy(rs.getInt(5));
                p.setId_Offer(rs.getInt(6));
                 p.setSendtoid(rs.getInt(7));
                 p.setIdreservation(rs.getInt(8));
                 p.setQR(rs.getInt(9));
                 
                
                //p.getAvatar(rs.getInt("Avatar"));
                //p.getHasplaces(rs.getBoolean(6));
                payement.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        // System.out.println(personnes);
        return payement;
    }

    public void SupprimerPayement(Payement p) {
        String sql = "DELETE FROM payment where Id =?";
        try {
            ste = mc.prepareStatement(sql);
            ste.setInt(1, p.getId());
            ste.executeUpdate();
            System.out.println("Payement Supprimée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void Payer(int idevent,String desc, int placeid, int offerid, float prix, int sendto, int sender) throws SQLException {
        Payement P = new Payement(desc, prix, sender, offerid, sendto,idevent);

        PersonService po = new PersonService();
        PersonService po1 = new PersonService();
        Person p = po.findById(sendto);
        Person p1 = po1.findById(sender);
        float modifplus = p1.getBalance() - prix;
        float modifmoins = p.getBalance() + prix;
        PersonService psss = new PersonService();
        PersonService pse = new PersonService();
        pse.UpdateBalance(modifplus, p1.getId());
        psss.UpdateBalance(modifmoins, p.getId());
        ReservationService rss = new ReservationService();
      

        try {

            String req = "INSERT INTO Payment (Date,Description,Montant,CreatedBy,idoffre,sendtoid,idevent,QR) VALUES (NOW(),?,?,?,?,?,?,?)";
            ste = mc.prepareStatement(req);
            ste.setString(1, desc);
            ste.setFloat(2, prix);
            ste.setInt(3, p1.getId());

            ste.setInt(4, offerid);
            ste.setInt(5, sendto);
            ste.setInt(6, idevent);
            ste.setInt(7, 0);
            //System.out.println(ste);
            ste.executeUpdate();

            System.out.println("participation done");
        } catch (SQLException ex) {

        }
    }

    public boolean checkParticipation(int id_user, int id_event) {
        ResultSet rs = null;

        String req = "select (count*) from payment where CreatedBy=? and idevent=?";
        try {
            ste = mc.prepareStatement(req);
            ste.setInt(1, id_user);
            ste.setInt(2, id_event);
            rs = ste.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;

    }

    public void AnnulerParticipation(int id_user, int id_ev)  {
        try {
            String req = "DELETE FROM payment WHERE CreatedBy =? AND idevent =?";
            ste = mc.prepareStatement(req);
            ste.setInt(1, id_user);
            ste.setInt(2, id_ev);
            ste.executeUpdate();
            System.out.println(ste);
            System.out.println(" done");
        } catch (SQLException ex) {
            
           
        }
    }
    public int getcount(int idreserveur , int eventid) {
        int theCount = 0;
        try {
            String req = "select count(*) from payment where CreatedBy=? and idevent=?";
            PreparedStatement st = mc.prepareStatement(req);
            st.setInt(1, idreserveur);
            st.setInt(2, eventid);
           

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                theCount = rs.getInt(1);
                // System.out.println(theCount);
            }
        } catch (SQLException a) {
        }
        return theCount;
    }
     public void ajouterattachmement(int qr, int id,int idevent) {
        String sql = "UPDATE  payment set QR=? where CreatedBy=? and idevent=?";
        
        try {
            ste = mc.prepareStatement(sql);
            ste.setInt(1, qr);
            ste.setInt(2, id);
            ste.setInt(3, idevent);
            ste.executeUpdate();
            System.out.println(" Ticket Generéé!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
     public Payement SelectidQR(int createdby,int idevent) {
         Payement p = new Payement();
        try {
            String req = "select QR from payment where CreatedBy=? and idevent=?";
            PreparedStatement st = mc.prepareStatement(req);
            st.setInt(1,createdby);
             st.setInt(2,idevent);
            ResultSet rs = st.executeQuery();
            
           
            while (rs.next()) {
              p = new Payement(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        return p;
    }
     public float SelectBalance(Person p) {
          float s =0;
        try {
           
            String req = "select balance from Person where Id=?";
            PreparedStatement st = mc.prepareStatement(req);
            st.setInt(1,p.getId());
            
            ResultSet rs = st.executeQuery();
            
           while (rs.next())
           {
          s =rs.getFloat(1);}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return s;
       
      
    
       
       
       
    }
      public List<Payement> afficherPayementMontantAsc(Person pe) {
        List<Payement> payement = new ArrayList<>();
        String sql = "select * from payment where sendtoid=? or CreatedBy=? Order By montant Asc";
        try {
            
            ste = mc.prepareStatement(sql);
            ste.setInt(1, pe.getId());
            ste.setInt(2, pe.getId());
            ResultSet rs = ste.executeQuery();
            
            //ste.setInt(2, id);
            while (rs.next()) {

                Payement p;
                p = new Payement();

                p.setId(rs.getInt(1));
                p.setDate(rs.getDate(2));
                p.setDescription(rs.getString(3));
                p.setMontant(rs.getFloat(4));
                p.setCreatedBy(rs.getInt(5));
                p.setId_Offer(rs.getInt(6));
                 p.setSendtoid(rs.getInt(7));
                 p.setIdreservation(rs.getInt(8));
                 p.setQR(rs.getInt(9));
                 
                
                //p.getAvatar(rs.getInt("Avatar"));
                //p.getHasplaces(rs.getBoolean(6));
                payement.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        // System.out.println(personnes);
        return payement;
    }
        public List<Payement> afficherPayementMontantDsc(Person pe) {
        List<Payement> payement = new ArrayList<>();
        String sql = "select * from payment where sendtoid=? or CreatedBy=? Order By montant Desc";
        try {
            
            ste = mc.prepareStatement(sql);
            ste.setInt(1, pe.getId());
            ste.setInt(2, pe.getId());
            ResultSet rs = ste.executeQuery();
            
            //ste.setInt(2, id);
            while (rs.next()) {

                Payement p;
                p = new Payement();

                p.setId(rs.getInt(1));
                p.setDate(rs.getDate(2));
                p.setDescription(rs.getString(3));
                p.setMontant(rs.getFloat(4));
                p.setCreatedBy(rs.getInt(5));
                p.setId_Offer(rs.getInt(6));
                 p.setSendtoid(rs.getInt(7));
                 p.setIdreservation(rs.getInt(8));
                 p.setQR(rs.getInt(9));
                 
                
                //p.getAvatar(rs.getInt("Avatar"));
                //p.getHasplaces(rs.getBoolean(6));
                payement.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        // System.out.println(personnes);
        return payement;
    }
        public List<Payement> afficherPayementDateAsc(Person pe) {
        List<Payement> payement = new ArrayList<>();
        String sql = "select * from payment where sendtoid=? or CreatedBy=? Order By Date Asc";
        try {
            
            ste = mc.prepareStatement(sql);
            ste.setInt(1, pe.getId());
            ste.setInt(2, pe.getId());
            ResultSet rs = ste.executeQuery();
            
            //ste.setInt(2, id);
            while (rs.next()) {

                Payement p;
                p = new Payement();

                p.setId(rs.getInt(1));
                p.setDate(rs.getDate(2));
                p.setDescription(rs.getString(3));
                p.setMontant(rs.getFloat(4));
                p.setCreatedBy(rs.getInt(5));
                p.setId_Offer(rs.getInt(6));
                 p.setSendtoid(rs.getInt(7));
                 p.setIdreservation(rs.getInt(8));
                 p.setQR(rs.getInt(9));
                 
                
                //p.getAvatar(rs.getInt("Avatar"));
                //p.getHasplaces(rs.getBoolean(6));
                payement.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        // System.out.println(personnes);
        return payement;
    }
         public List<Payement> afficherPayementDateDesc(Person pe) {
        List<Payement> payement = new ArrayList<>();
        String sql = "select * from payment where sendtoid=? or CreatedBy=? Order By Date Desc";
        try {
            
            ste = mc.prepareStatement(sql);
            ste.setInt(1, pe.getId());
            ste.setInt(2, pe.getId());
            ResultSet rs = ste.executeQuery();
            
            //ste.setInt(2, id);
            while (rs.next()) {

                Payement p;
                p = new Payement();

                p.setId(rs.getInt(1));
                p.setDate(rs.getDate(2));
                p.setDescription(rs.getString(3));
                p.setMontant(rs.getFloat(4));
                p.setCreatedBy(rs.getInt(5));
                p.setId_Offer(rs.getInt(6));
                 p.setSendtoid(rs.getInt(7));
                 p.setIdreservation(rs.getInt(8));
                 p.setQR(rs.getInt(9));
                 
                
                //p.getAvatar(rs.getInt("Avatar"));
                //p.getHasplaces(rs.getBoolean(6));
                payement.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        // System.out.println(personnes);
        return payement;
    }
          public float countRevenue(int idevent,int idowner){
              float nombrepayements=0;
          try {
            ResultSet rs, rs1, rs2, rs3, rs4;
            PreparedStatement pt1;
          
            pt1  = mc.prepareStatement("SELECT Montant FROM payment WHERE idevent=? and sendtoid=?");
            pt1.setInt(1, idevent);
              pt1.setInt(2, idowner);
            
             rs = pt1.executeQuery();
            while (rs.next()) {
                nombrepayements+=((rs.getFloat(1)));
               
                //System.out.println(countNonTraite);
            }} catch (SQLException ex) {
           System.out.println(ex);
        }
        return nombrepayements;
    }
public int counttickets(int idevent,int idowner){
              int nombretickets=0;
          try {
            ResultSet rs, rs1, rs2, rs3, rs4;
            PreparedStatement pt1;
          
            pt1  = mc.prepareStatement("SELECT Count(*) FROM payment WHERE idevent=? and sendtoid=?");
            pt1.setInt(1, idevent);
              pt1.setInt(2, idowner);
            
             rs = pt1.executeQuery();
            while (rs.next()) {
                nombretickets+=((rs.getInt(1)));
               
                //System.out.println(countNonTraite);
            }} catch (SQLException ex) {
           System.out.println(ex);
        }
        return nombretickets;
    }
        public Payement selectidpayement(int createdby,int idevent) {
         Payement p = new Payement();
        try {
            String req = "select Id,Date,Montant from payment where CreatedBy=? and idevent=?";
            PreparedStatement st = mc.prepareStatement(req);
            st.setInt(1,createdby);
             st.setInt(2,idevent);
            ResultSet rs = st.executeQuery();
            
           
            while (rs.next()) {
              p = new Payement(rs.getInt(1),rs.getDate(2),rs.getFloat(3));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        return p;
    }   
          
}
