/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.net.URL;

/**
 *
 * @author youss
 */
public class Attachement {
    private int id;
    private String Name;
    private String Path;

    public Attachement(int id, String Name, String Path) {
        this.id = id;
        this.Name = Name;
        this.Path = Path;
    }

    public Attachement() {
    }

    public Attachement(int id, String Path) {
        this.id = id;
       // this.Name = Name;
        this.Path = Path;//To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getPath() {
        return Path;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setPath(String Path) {
        this.Path = Path;
    }

    @Override
    public String toString() {
        return "Attachement{" + "id=" + id + ", Name=" + Name + ", Path=" + Path + '}';
    }
       
}