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
    private String Img;
    //private int[] evaluations;

    public Place(int id, String name, String Description, String Adresse, String City, String PostalCode, String Latitude, String Longitude, int category_id, int evaluation, int Notice, boolean Status, int CreatedBy, Type type, int attachement_id) {
        this.id = id;
        this.name = name;
        this.Description = Description;
        this.Adresse = Adresse;
        this.City = City;
        this.PostalCode = PostalCode;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.category_id = category_id;
        this.evaluation = evaluation;
        this.Notice = Notice;
        this.Status = Status;
        this.CreatedBy = CreatedBy;
        this.type = type;
        this.attachement_id = attachement_id;
    }

    public Place() {

    }

    public int getId() {
        return id;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String Img) {
        this.Img = Img;
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

    @Override
    public String toString() {
        return "********************" + "\n id=" + id + ",\n name=" + name + ",\n Description=" + Description + ",\n Adresse=" + Adresse + ",\n City=" + City + ",\n PostalCode=" + PostalCode + ",\n Latitude=" + Latitude + ",\n Longitude=" + Longitude + ",\n category=" + category + ",\n evaluation=" + evaluation + ",\n Notice=" + Notice + ",\n Status=" + Status + ",\n CreatedBy=" + CreatedBy + ",\n type=" + type + "\n************************";
    }

}
