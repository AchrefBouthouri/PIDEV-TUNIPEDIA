/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Person;
import Enum.Gender;
import Tools.ConnexionDB;
import Tools.Md5;
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
        mc= ConnexionDB.getInstance().getCnx();
    }
    public void AjouterPersonne(Person p){
            String sql = "INSERT INTO Person(FullName,Email,Password,Avatar,HasPlaces,Gender,CreatedAt,Nationalite,isPartner,Role) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, p.getFullName());
            ste.setString(2, p.getEmail());
            Md5 var = new Md5(p.getPassword());
                //System.out.println(var.codeGet());
            ste.setString(3, var.codeGet());
            ste.setInt(4, p.getAvatar());
            ste.setBoolean(5, p.getHasplaces());
            ste.setString(6, p.getGender());
            ste.setDate(7, p.getCreatedAt());
            ste.setString(8, p.getNationalite());
            ste.setBoolean(9, p.GetIsPartner());
            ste.setString(10, p.getRole());
            mc.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("Personne ajoutée!");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }
    public List<Person> afficherPerson(){
        List<Person> personnes = new ArrayList<>();
        String sql = "select * from Person";
        try {
            ste =mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
            Person p = new Person();
            p.setId(rs.getInt(1));
            p.setFullName(rs.getString(2));
            p.setEmail(rs.getString(3));
             //Md5 var = new Md5(rs.getString(4));
            //p.setPassword(Md5.decryptAES(var.codeGet(),"key"));
                //System.out.println(var.codeGet());
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
    
      public void SupprimerPerson(int id){
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
         public void UpdateFullName(String FullName, int id){
             String sql = "UPDATE  Person set FullName=? where id=?";
        try {
           ste = mc.prepareStatement(sql);
           ste.setString(1, FullName);
           ste.setInt(2, id);
           ste.executeUpdate(); 
            System.out.println("FullName mis Ã  jour!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }
      public void UpdateEmail(String Email, int id){
             String sql = "UPDATE  Person set Email=? where id=?";
        try {
           ste = mc.prepareStatement(sql);
           ste.setString(1, Email);
           ste.setInt(2, id);
           ste.executeUpdate(); 
            System.out.println("Email mis Ã  jour!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }
      
      public void UpdatePassword(String Password, int id){
             String sql = "UPDATE  Person set Password=? where id=?";
        try {
           ste = mc.prepareStatement(sql);
           ste.setString(1, Password);
           ste.setInt(2, id);
           ste.executeUpdate(); 
            System.out.println("Mot de passe mis Ã  jour!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }
        public void UpdateGender(Gender gender, int id){
             String sql = "UPDATE  Person set Gender=? where id=?";
             String GenderStr = gender.name();
        try {
           ste = mc.prepareStatement(sql);
           ste.setString(1, GenderStr);
           ste.setInt(2, id);
           ste.executeUpdate(); 
            System.out.println("Sexe mis Ã  jour!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());  
        }
    }
}

