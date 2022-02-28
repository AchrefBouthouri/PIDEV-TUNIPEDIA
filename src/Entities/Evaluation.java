/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author user
 */
public class Evaluation {
    private int id;
    private Date CreatedAt;
    private int Notice;
    private String Comment;
    private int CreatedBy;
    private String Location;
    private int place_id;

    public Evaluation(int id, Date CreatedAt, int Notice, String Comment,int CreatedBy,int place_id) {
        this.id = id;
        this.CreatedAt = CreatedAt;
        this.Notice = Notice;
        this.Comment = Comment;
        this.CreatedBy = CreatedBy;
        this.place_id=place_id;
        
    }
   
    
    public Evaluation(){}
    

    public int getId() {
        return id;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public int getNotice() {
        return Notice;
    }

    public String getComment() {
        return Comment;
    }

    public  int getCreatedBy() {
        return CreatedBy;
    }

    public String getLocation() {
        return Location;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreatedAt(Date CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public void setNotice(int Notice) {
        this.Notice = Notice;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    public void setCreatedBy(int CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public int getPlace_id() {
        return place_id;
    }

    public void setPlace_id(int place_id) {
        this.place_id = place_id;
    }

    @Override
    public String toString() {
        return "Evaluation{" + "id=" + id + ", CreatedAt=" + CreatedAt + ", Notice=" + Notice + ", Comment=" + Comment + ", CreatedBy=" + CreatedBy + ", Location=" + Location + '}';
    }
    
    
    
    
    
    
}
