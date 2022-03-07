/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author amine
 */
public class Offer {
      private int id;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private float Montant; 
    private int id_Event;
    private String Location;
    
  

    public Offer(int id, LocalDate date_debut, LocalDate date_fin, float Montant, int id_Event) {
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

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public LocalDate getDate_fin() {
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

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(LocalDate date_fin) {
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
        return  date_debut + "          " + date_fin + "          "+ Location+ "          " + Montant + "          " + id_Event  ;
    }

}
