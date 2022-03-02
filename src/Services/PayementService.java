/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

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
        String sql = "INSERT INTO payment(Date,Description,Montant,Createdby,idoffre,sendtoid,idreservation) VALUES(NOW(),?,?,?,?,?,?)";
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

    public List<Payement> afficherPayement() {
        List<Payement> payement = new ArrayList<>();
        String sql = "select * from payment";
        try {
            ste = mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {

                Payement p;
                p = new Payement();

                p.setId(rs.getInt(1));
                p.setDate(rs.getDate(2));
                p.setDescription(rs.getString(3));
                p.setMontant(rs.getFloat(4));
                p.setCreatedBy(rs.getInt(5));
                p.setId_Offer(rs.getInt(6));
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

    public void Payer(Reservation r, String desc, int placeid, int offerid, float prix, int sendto, int sender) throws SQLException {
        Payement P = new Payement(desc, prix, sender, offerid, sendto, r.getId());

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
        rss.UpdateConfirmation(true, r.getId());

        try {

            String req = "INSERT INTO Payment (Date,Description,Montant,CreatedBy,idoffre,sendtoid,idreservation) VALUES (NOW(),?,?,?,?,?,?)";
            ste = mc.prepareStatement(req);
            ste.setString(1, desc);
            ste.setFloat(2, prix);
            ste.setInt(3, p1.getId());

            ste.setInt(4, offerid);
            ste.setInt(5, sendto);
            ste.setInt(6, r.getId());
            //System.out.println(ste);
            ste.executeUpdate();

            System.out.println("participation done");
        } catch (SQLException ex) {

        }
    }

    public boolean checkParticipation(int id_user, int id_event) {
        ResultSet rs = null;

        String req = "select * from Reservation where CreatedBy=? and Place_Id=?";
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

    public void AnnulerParticipation(int id_user, int id_res) throws SQLException {
        try {
            String req = "DELETE FROM Payement WHERE CreatedBy =? AND idreservation =?";
            ste = mc.prepareStatement(req);
            ste.setInt(1, id_user);
            ste.setInt(2, id_res);
            ste.executeUpdate();
            System.out.println(ste);
            System.out.println(" done");
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
