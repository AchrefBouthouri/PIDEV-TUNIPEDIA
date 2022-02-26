/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author youss
 */
public class Category {
    private int id;
    private String Name;
    private int attachement;

    public Category(int id, String Name, int attachement) {
        this.id = id;
        this.Name = Name;
        this.attachement = attachement;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public int getAttachement() {
        return attachement;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setAttachement(int attachement) {
        this.attachement = attachement;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", Name=" + Name + ", attachement=" + attachement + '}';
    }
    
}

