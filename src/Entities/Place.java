/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Enum.Type;

/**
 *
 * @author Achref Bouthouri
 */
public class Place {
    private int id;
    private String name;
    private String Description;
    private String Adresse;
    private String City;
    private String PostalCode;
    private String Latitude;
    private String Longitude;
    private int category_id;
    private int evaluation;
    private int Notice;
    private boolean Status;
    private int CreatedBy;
    private Type type;
    private int attachement_id;
    private String category; 
    private int capacite;
    private float prix;
   //private String Img;
    //private int[] evaluations;

    public Place(int id, String name, String Description, String Adresse, String City, String PostalCode, String Latitude, String Longitude,int capacite ,int category_id, int evaluation, int Notice, boolean Status, int CreatedBy, Type type, int attachement_id,float prix) {
        this.id = id;
        this.name = name;
        this.Description = Description;
        this.Adresse = Adresse;
        this.City = City;
        this.PostalCode = PostalCode;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.capacite = capacite;
        this.category_id = category_id;
        this.evaluation = evaluation;
        this.Notice = Notice;
        this.Status = Status;
        this.CreatedBy = CreatedBy;
        this.type = type;
        this.attachement_id = attachement_id;
        this.prix= prix;
    }

    public Place() {
       
    }

    public Place(String name, String Description, String Adresse, String City, String PostalCode, String Latitude, String Longitude,int capacite ,int category_id, int evaluation, int Notice, boolean Status, int CreatedBy, Type type, int attachement_id,float prix)
    {
    this.name = name;
        this.Description = Description;
        this.Adresse = Adresse;
        this.City = City;
        this.PostalCode = PostalCode;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.capacite = capacite;
        this.category_id = category_id;
        this.evaluation = evaluation;
        this.Notice = Notice;
        this.Status = Status;
        this.CreatedBy = CreatedBy;
        this.type = type;
        this.attachement_id = attachement_id;
        this.prix= prix;
   //private String Img;
    //private int[] evaluations;
}
    

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public float getPrix() {
        return prix;
    }

    public Place(int id, String name, String Description) {
         this.id = id;
        this.name = name;
        this.Description = Description;
    }

    public int getId() {
        return id;
    }

    

  

    public String getName() {
        return name;
    }

    public String getDescription() {
        return Description;
    }

    public String getAdresse() {
        return Adresse;
    }

    public String getCity() {
        return City;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public String getLatitude() {
        return Latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public int getCategory_id() {
        return category_id;
    }

    public int getEvaluation() {
        return evaluation;
    }


    public int getNotice() {
        return Notice;
    }

    public boolean isStatus() {
        return Status;
    }

    public int getCreatedBy() {
        return CreatedBy;
    }

    public String getType() {
        return type.name();
    }

    public int getAttachement() {
        return attachement_id;
    }

    public int getCapacite() {
        return capacite;
    }

    public String getCategory() {
        return category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public void setPostalCode(String PostalCode) {
        this.PostalCode = PostalCode;
    }

    public void setLatitude(String Latitude) {
        this.Latitude = Latitude;
    }

    public void setLongitude(String Longitude) {
        this.Longitude = Longitude;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    public void setNotice(int Notice) {
        this.Notice = Notice;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public void setCreatedBy(int CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setAttachement(int attachement_id) {
        this.attachement_id = attachement_id;
    }

    public void setAttachement_id(int attachement_id) {
        this.attachement_id = attachement_id;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    @Override


    public String toString() {
        return "Place{" + "id=" + id + ", name=" + name + ", Description=" + Description + ", Adresse=" + Adresse + ", City=" + City + ", PostalCode=" + PostalCode + ", Latitude=" + Latitude + ", Longitude=" + Longitude + ", category_id=" + category_id + ", evaluation=" + evaluation + ", Notice=" + Notice + ", Status=" + Status + ", CreatedBy=" + CreatedBy + ", type=" + type + ", attachement_id=" + attachement_id + ", category=" + category + ", capacite=" + capacite + '}';
    }
   


     
}

