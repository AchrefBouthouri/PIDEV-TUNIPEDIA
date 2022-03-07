/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Wassym
 */
public class Reclamation {
    private int id;
    private String Text;
    private int CreatedBy;
    private int Location;
    private int Attachement;

 

    public Reclamation(int id, String Text, int CreatedBy,int Location) {
        this.id = id;
        this.Text = Text;
        this.CreatedBy = CreatedBy;
        this.Location = Location;

    }
    
    
    public Reclamation(int id, String Text, int CreatedBy,int Location,int Attachement) {
        this.id = id;
        this.Text = Text;
        this.CreatedBy = CreatedBy;
        this.Location = Location;
        this.Attachement = Attachement;
    }


    public Reclamation() {
       
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    public void setCreatedBy(int CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public void setPlace_id(int Location) {
        this.Location = Location;
    }

    public void setLocation(int Location) {
        this.Location = Location;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return Text;
    }

    public int getCreatedBy() {
        return CreatedBy;
    }

    public int getPlace_id() {
        return Location;
    }

    public int getLocation() {
        return Location;
    }

    @Override
    public String toString() {
        return Text;
    }
    
    
    
}