/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Enum.Gender;
import java.sql.Date;

/**
 *
 * @author Achref Bouthouri
 */
public class Person {
     public int id; 
    private String  FullName;
    private String Email;
    private String Password;
    private int Avatar;
    private boolean Hasplaces;
    private Date CreatedAt;
    private Gender gender;
    private String Nationalite;
    private boolean isPartner;
    private String Role;
    private float balance;

    public Person() {
    }

    public Person(int id, String FullName, String Email, String Password, int Avatar, boolean Hasplaces, Date CreatedAt, Gender gender, String Nationalite, boolean isPartner, String Role, float balance) {
        this.id = id;
        this.FullName = FullName;
        this.Email = Email;
        this.Password = Password;
        this.Avatar = Avatar;
        this.Hasplaces = Hasplaces;
        this.CreatedAt = CreatedAt;
        this.gender = gender;
        this.Nationalite = Nationalite;
        this.isPartner= isPartner;
        this.Role = Role;
        this.balance = balance;
    }
    public Person(int id,String FullName, String Email, String Password, int Avatar, boolean Hasplaces,float balance) {
        this.id= id;
        this.FullName = FullName;
        this.Email = Email;
        this.Password = Password;
        this.Avatar = Avatar;
        this.Hasplaces = Hasplaces;
        this.balance = balance;
    }

    public Person(String FullName, String Email, String Password, int Avatar, boolean Hasplaces, float balance) {
        this.FullName = FullName;
        this.Email = Email;
        this.Password = Password;
        this.Avatar = Avatar;
        this.Hasplaces = Hasplaces;
        this.balance = balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
    public int getId() {
        return id;
    }

    public boolean isIsPartner() {
        return isPartner;
    }

    public boolean isHasplaces() {
        return Hasplaces;
    }

    public float getBalance() {
        return balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public int getAvatar() {
        return Avatar;
    }

    public boolean getHasplaces() {
        return Hasplaces;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setAvatar(int Avatar) {
        this.Avatar = Avatar;
    }

    public void setHasplaces(boolean Hasplaces) {
        this.Hasplaces = Hasplaces;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }
    public Date getCreatedAt() {
        return CreatedAt;
    }

    public String getGender() {
        return gender.name();
    }

    public String getNationalite() {
        return Nationalite;
    }
   public boolean GetIsPartner() {
        return isPartner;
    }

    public void setIsPartner(boolean isPartner) {
        this.isPartner = isPartner;
    }
    
    public void setCreatedAt(Date CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setNationalite(String Nationalite) {
        this.Nationalite = Nationalite;
    }

    @Override
    public String toString() {
        return "\n*******************" + "\nid=" + id + "\nFullName=" + FullName + "\nEmail=" + Email + "\nPassword=" + Password + "\nAvatar=" + Avatar + "\nHasplaces=" + Hasplaces + "\nCreatedAt=" + CreatedAt + "\nGender=" + gender + "\nNationalite=" + Nationalite + "\nRole=" + Role + "\nBalance="+ balance+"\n*******************";
    }

   
    
   
}
