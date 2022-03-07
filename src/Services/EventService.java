/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Event;
import Entities.Place;
import Enum.Type;
import Tools.ConnexionDB;
import java.sql.Connection;
import java.sql.Date;
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
public class EventService {

    Connection mc;
    PreparedStatement ste;

    public EventService() {
        mc = ConnexionDB.getInstance().getCnx();
    }

    public void AjouterEvent(Event e) {
        String sql = "INSERT INTO event(Date_Debut,Date_Fin,Description,capacite,montant,CreatedBy,Location,idoffre,idattachement) VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            ste = mc.prepareStatement(sql);

            ste.setDate(1, Date.valueOf(e.getDate_debut()));

            ste.setDate(2, Date.valueOf(e.getDate_fin()));
            ste.setString(3, e.getDescription());
            ste.setInt(4, e.getCapacite());
            ste.setFloat(5, e.getMontant());
            ste.setInt(6, e.getCreatedBy());
            ste.setInt(7, e.getLocation());
//            ste.setInt(8, e.getIdoffre());
            ste.setInt(9, e.getIdattachement());

            mc.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("Evenement ajoutée!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Event> afficherEvent() {
        List<Event> event = new ArrayList<>();
        String sql = "select * from Event";
        try {
            ste = mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                Event e;
                e = new Event();

                e.setId(rs.getInt(1));
                e.setNom(rs.getString(2));
                e.setDate_debut(rs.getDate(3).toLocalDate());
                e.setDate_fin(rs.getDate(4).toLocalDate());
                e.setDescription(rs.getString(5));
                e.setCapacite(rs.getInt(6));
                e.setMontant(rs.getInt(7));
                e.setCreatedBy(rs.getInt(8));
                e.setLocation(rs.getInt(9));
                e.setIdattachement(rs.getInt(10));

                event.add(e);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        // System.out.println(personnes);
        return event;
    }

    public void SupprimerEvent(Event e) {
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

    public void Updatedate_debut(String Date_Debut, Event e) {
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

    public void UpdateDate_Fin(String Date_Fin, Event e) {
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

    public void UpdatetypePlace(int location, Event e) {
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
    public Event Selectevent(int idevent) {
        Event pu = null;
        try {
            String req = "select  * from event where Id=?";
            PreparedStatement st = mc.prepareStatement(req);
            st.setInt(1, idevent);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
               
                pu = new Event(rs.getInt(1),rs.getString(2),rs.getDate(3).toLocalDate(), rs.getDate(4).toLocalDate(), rs.getString(5), rs.getInt(6), rs.getFloat(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return pu;
    }
     public float gettheprice(int idevent) {
        float balance = 0;
        try {
            String req = "select  montant from event where Id=?";
            PreparedStatement st = mc.prepareStatement(req);
            st.setInt(1, idevent);
           
            ResultSet rs = st.executeQuery();
            if  (rs.next()) {
            balance = rs.getInt(1);
          // System.out.println(theCount);
}
        } catch (SQLException a) {
        }
        return balance;
    }
     public int gettheowner(int idevent) {
        int idowner = 0;
        try {
            String req = "select  CreatedBy from event where Id=?";
            PreparedStatement st = mc.prepareStatement(req);
            st.setInt(1, idevent);
           
            ResultSet rs = st.executeQuery();
            if  (rs.next()) {
            idowner = rs.getInt(1);
          // System.out.println(theCount);
}
        } catch (SQLException a) {
        }
        return idowner;
    }
      public int getcount(int idevent) {
        int theCount = 0;
        try {
            String req = "select count(*) from reservation where Place_id=? and Validation = 1";
            PreparedStatement st = mc.prepareStatement(req);
            st.setInt(1, idevent);
           
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                theCount = rs.getInt(1);
                // System.out.println(theCount);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return theCount;
    }

}
