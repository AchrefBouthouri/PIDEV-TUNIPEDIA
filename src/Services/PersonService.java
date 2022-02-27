/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Person;
import Enum.*;
import Tools.ConnexionDB;
import Tools.Md5;
import Tools.Session;
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
public class PersonService {

    Connection mc;
    PreparedStatement ste;

    public PersonService() {
        mc = ConnexionDB.getInstance().getCnx();
    }

    public Person Connexion(String email, String password) {
        String sql = "select * from Person where Email=? AND Password=?";
        Person p = new Person();
        Md5 var = new Md5(password);
        try {
            //System.out.println(email);
            ste = mc.prepareStatement(sql);
            ste.setString(1, email);
            //System.out.println(var.codeGet());
            ste.setString(2, var.codeGet());
            mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                //if (var.codeGet().equals(rs.getString(4))) {
                p.setId(rs.getInt(1));
                p.setFullName(rs.getString(2));
                p.setEmail(rs.getString(3));
                p.setPassword(rs.getString(4));
                p.setAvatar(rs.getInt(5));
                p.setHasplaces(rs.getBoolean(6));
                //String GenderStr = rs.getString(7);
                //Gender GenderEnum = Gender.valueOf(GenderStr);
                //p.setGender(GenderEnum);
                p.setCreatedAt(rs.getDate(8));
                p.setNationalite(rs.getString(9));
                p.setIsPartner(rs.getBoolean(10));
                p.setRole(rs.getString(11));
            }
            System.out.println("Personne Trouve!");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;

    }

    public Person SignUp(String name, String email, String password) {
        String sqlAP = "INSERT INTO Person(FullName,Email,Password,Avatar,HasPlaces,CreatedAt,isPartner,Role) VALUES(?,?,?,?,?,NOW(),?,?)";
        String sqlRP = "select * from Person where Email=?";
        Person p = new Person();
        try {
            ste = mc.prepareStatement(sqlAP);
            ste.setString(1, name);
            ste.setString(2, email);
            Md5 var = new Md5(password);;
            ste.setString(3, var.codeGet());
            //System.out.println(var.codeGet())
            ste.setInt(4, 0);
            ste.setBoolean(5, false);
            ste.setBoolean(7, false);
            ste.setString(8, "Client");
            mc.prepareStatement(sqlAP);
            ste.executeUpdate();

            ste = mc.prepareStatement(sqlRP);
            ste.setString(1, email);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                //p.setId(rs.getInt(1));
                //if (var.codeGet().equals(rs.getString(4))) {
                p.setId(rs.getInt(1));
                p.setFullName(rs.getString(2));
                p.setEmail(rs.getString(3));
                p.setPassword(rs.getString(4));
                p.setAvatar(rs.getInt(5));
                p.setHasplaces(rs.getBoolean(6));
                //String GenderStr = rs.getString(7);
                //Gender GenderEnum = Gender.valueOf(GenderStr);
                // p.setGender(GenderEnum);
                System.out.println(rs.getDate(7));
                p.setCreatedAt(rs.getDate(7));
                // p.setNationalite(rs.getString(9));
                p.setIsPartner(rs.getBoolean(8));
                p.setRole(rs.getString(9));
            }
            System.out.println("Session Ouverte!");
            // }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;

    }

    public void AjouterPersonne(Person p) {
        String sql = "INSERT INTO Person(FullName,Email,Password,Avatar,HasPlaces,Gender,CreatedAt,Nationalite,isPartner,Role) VALUES(?,?,?,?,?,?,NOW(),?,?,?)";
        try {
            ste = mc.prepareStatement(sql);
            ste.setString(1, p.getFullName());
            ste.setString(2, p.getEmail());
            Md5 var = new Md5(p.getPassword());
            //System.out.println(var.codeGet());
            ste.setString(3, var.codeGet());
            ste.setInt(4, p.getAvatar());
            ste.setBoolean(5, p.getHasplaces());
            ste.setString(6, p.getGender());
            // ste.setDate(7, p.getCreatedAt());
            ste.setString(7, p.getNationalite());
            ste.setBoolean(8, p.GetIsPartner());
            ste.setString(9, p.getRole());
            mc.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("Personne ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void getPersonById(int id) {
        String sql = "select¨* from Person where id=?";
        Person p = new Person();
        try {
            ste = mc.prepareStatement(sql);
            ste.setInt(1, id);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                //p.setId(rs.getInt(1));
                //if (var.codeGet().equals(rs.getString(4))) {
                p.setId(rs.getInt(1));
                p.setFullName(rs.getString(2));
                p.setEmail(rs.getString(3));
                p.setPassword(rs.getString(4));
                p.setAvatar(rs.getInt(5));
                p.setHasplaces(rs.getBoolean(6));
                //String GenderStr = rs.getString(7);
                //Gender GenderEnum = Gender.valueOf(GenderStr);
                // p.setGender(GenderEnum);
                System.out.println(rs.getDate(7));
                p.setCreatedAt(rs.getDate(7));
                // p.setNationalite(rs.getString(9));
                p.setIsPartner(rs.getBoolean(8));
                p.setRole(rs.getString(9));
            }
            System.out.println("Personne Recuperer!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Person> afficherPerson() {
        List<Person> personnes = new ArrayList<>();
        String sql = "select * from Person";
        try {
            ste = mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                Person p = new Person();
                p.setId(rs.getInt(1));
                p.setFullName(rs.getString(2));
                p.setEmail(rs.getString(3));
                p.setPassword(rs.getString(4));
                p.setAvatar(rs.getInt(5));
                p.setHasplaces(rs.getBoolean(6));
                String GenderStr = rs.getString(7);
                Gender GenderEnum = Gender.valueOf(GenderStr);
                p.setGender(GenderEnum);
                p.setCreatedAt(rs.getDate(8));
                p.setNationalite(rs.getString(9));
                p.setIsPartner(rs.getBoolean(10));
                p.setRole(rs.getString(11));

                personnes.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        // System.out.println(personnes);
        return personnes;
    }

    public void SupprimerPerson(int id) {
        String sql = "delete from Person where id=?";
        try {
            ste = mc.prepareStatement(sql);
            ste.setInt(1, id);
            ste.executeUpdate();
            System.out.println("Personne Supprimée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void UpdateFullName(String FullName, int id) {
        String sql = "UPDATE  Person set FullName=? where id=?";
        try {
            ste = mc.prepareStatement(sql);
            ste.setString(1, FullName);
            ste.setInt(2, id);
            ste.executeUpdate();
            System.out.println("FullName mis à jour!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void UpdateEmail(String Email, int id) {
        String sql = "UPDATE  Person set Email=? where id=?";
        try {
            ste = mc.prepareStatement(sql);
            ste.setString(1, Email);
            ste.setInt(2, id);
            ste.executeUpdate();
            System.out.println("Email mis à jour!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void UpdatePassword(String Password, int id) {
        String sql = "UPDATE  Person set Password=? where id=?";
        try {
            ste = mc.prepareStatement(sql);
            Md5 var = new Md5(Password);
            //System.out.println(var.codeGet());
            ste.setString(1, var.codeGet());
            ste.setInt(2, id);
            ste.executeUpdate();
            System.out.println("Mot de passe mis à jour!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void UpdateGender(Gender gender, int id) {
        String sql = "UPDATE  Person set Gender=? where id=?";
        String GenderStr = gender.name();
        try {
            ste = mc.prepareStatement(sql);
            ste.setString(1, GenderStr);
            ste.setInt(2, id);
            ste.executeUpdate();
            System.out.println("Sexe mis à jour!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
        public void UpdateNationalite(String Nationalite, int id) {
        String sql = "UPDATE Person set Nationalite=? where id=?";
        try {
            ste = mc.prepareStatement(sql);
            ste.setString(1, Nationalite);
            ste.setInt(2, id);
            ste.executeUpdate();
            System.out.println("Nationalite mis à jour!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
         public boolean Authentification(Person u) {
        boolean status = false;
        try {
            String req = "select * from Person where Email=? ";
            PreparedStatement st;
            st = mc.prepareStatement(req);
            st.setString(1, u.getEmail());

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                if (u.getPassword().equals(rs.getString("Password"))) {

                    status = true;
                    u = this.findById(rs.getInt("Id"));
                    
                    
                    Session.setUser(u);
                    
                  

                } else {
                    status = false;
                }

            }
        } catch (Exception ex) {
        }
        return status;
    }
         
         public Person findById(int idconnected) {
        Person p = null;
        try {
            String req = "select  Id,Fullname,Email,Password,Avatar,Hasplaces,balance from Person where Id=? ";
            PreparedStatement st = mc.prepareStatement(req);
            st.setInt(1, idconnected);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p = new Person(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getInt(5),rs.getBoolean(6), rs.getFloat(7));
            }
        } catch (SQLException a) {
        }
        return p;
    }
  public String checkRole(String email) {
        String default_return = "ND";
        try {
            String req;
            req = "select Role from person where Email=?";
            PreparedStatement st = mc.prepareStatement(req);
            st.setString(1, email);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                if (rs.getString(1).equals("Admin")) {

                    return "Admin";
                } else if (rs.getString(1).equals("Owner")) {
                    return "Owner";
                } else if (rs.getString(1).equals("Owner")) {
                    return "Owner";
                } else if (rs.getString(1).equals("Client")) {
                    return "Client";
              }

            }

        } catch (SQLException ex) {
        }
        return default_return;
    }
}
