
package Entities;

import java.sql.Date;

public class Payement {
    private int id;
    private Date date;
    private String Description;
    private float Montant;
    private int CreatedBy;
    private int id_Offer;
    private int sendtoid;
    private int idreservation;

    public Payement() {
    }

    public Payement(String Description, float Montant, int CreatedBy, int id_Offer,int sendtoid,int idreservation) {
       
        this.Description = Description;
        this.Montant = Montant;
        this.CreatedBy = CreatedBy;   
        this.id_Offer = id_Offer;
         this.sendtoid = sendtoid;
        this.idreservation = idreservation;
    }

    public int getSendtoid() {
        return sendtoid;
    }

    public int getIdreservation() {
        return idreservation;
    }

    public void setSendtoid(int sendtoid) {
        this.sendtoid = sendtoid;
    }

    public void setIdreservation(int idreservation) {
        this.idreservation = idreservation;
    }

   
    
    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return Description;
    }

    public float getMontant() {
        return Montant;
    }

   
    public int getCreatedBy() {
        return CreatedBy;
    }

    public int getId_Offer() {
        return id_Offer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setMontant(float Montant) {
        this.Montant = Montant;
    }

    public void setCreatedBy(int CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public void setId_Offer(int id_Offer) {
        this.id_Offer = id_Offer;
    }

    @Override
    public String toString() {
        return "Payment{" + "id=" + id + ", date=" + date + ", Description=" + Description + ", Montant=" + Montant + ", CreatedBy=" + CreatedBy + ", id_Offer=" + id_Offer + '}';
    }
    
    
    
    
}

