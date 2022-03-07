/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDate;

/**
 *
 * @author Achref Bouthouri
 */
public class Event {
    private int id;
     private String Nom;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private String Description;
    private int capacite;
    private float montant;
    private int CreatedBy;
    private int Location;
   
   
    private int idattachement;

   

    public Event(String Nom,LocalDate date_debut,LocalDate date_fin, String Description, int capacite, float montant, int CreatedBy, int Location,int idattachement) {
        this.Nom= Nom;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.Description = Description;
        this.capacite = capacite;
        this.montant = montant;
        this.CreatedBy = CreatedBy;
        this.Location = Location;       
        this.idattachement= idattachement;
    }

    public Event(int id,String Nom,LocalDate date_debut, LocalDate date_fin, String Description, int capacite, float montant, int CreatedBy, int Location, int idattachement) {
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.Description = Description;
        this.capacite = capacite;
        this.montant = montant;
        this.CreatedBy = CreatedBy;
        this.Location = Location;
        this.Nom= Nom;
        this.idattachement = idattachement;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setIdattachement(int idattachement) {
        this.idattachement = idattachement;
    }

    public int getIdattachement() {
        return idattachement;
    }

    public int getCapacite() {
        return capacite;
    }

    public float getMontant() {
        return montant;
    }

  

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setMontant(float montant) {
        this.montant = montant;
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

    public Event() {
    }

    public String getDescription() {
        return Description;
    }

    public int getCreatedBy() {
        return CreatedBy;
    }

    public int getLocation() {
        return Location;
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

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setCreatedBy(int CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public void setLocation(int Location) {
        this.Location = Location;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id +", name=" + Nom + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", Description=" + Description + ", CreatedBy=" + CreatedBy + ", Location=" + Location + '}';
    }

}
