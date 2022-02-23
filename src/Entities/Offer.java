/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author amine
 */
public class Offer {
      private int id;
    private Date date_debut;
    private Date date_fin;
    private float Montant; 
    private int id_Event;
    private String Location;
    
  

    public Offer(int id, Date date_debut, Date date_fin, float Montant, int id_Event) {
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.Montant = Montant;
        this.id_Event = id_Event;
      
    }

    public Offer() {
      
    }

   
    


   

    public int getId() {
        return id;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public float getMontant() {
        return Montant;
    }

    public int getId_Event() {
        return id_Event;
    }

   
   

   

    public void setId(int id) {
        this.id = id;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public void setMontant(float Montant) {
        this.Montant = Montant;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

  
 



   

    @Override
    public String toString() {
        return "**********\n" + "\nid=" + id + ",\n date_debut=" + date_debut + ",\n date_fin=" + date_fin + ",\n Montant=" + Montant + ",\n Location=" + Location +  "\n******************";
    }

}
