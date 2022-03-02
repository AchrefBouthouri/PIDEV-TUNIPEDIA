/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Attachement;
import Entities.Category;
import Tools.ConnexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author youss
 */
public class CategoryService {

    Connection mc;
    PreparedStatement ste;

    public CategoryService() {
        mc = ConnexionDB.getInstance().getCnx();
    }

    public void AjouterCategory(Category c, int id) {
        String sql = "INSERT INTO Category(Name,Attachement) VALUES(?,?)";
        try {
            ste = mc.prepareStatement(sql);
            ste.setString(1, c.getName());
            ste.setInt(2, id);
            mc.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("Category ajoutée!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void SupprimerCategory(int id) {
        String sql = "delete from Category where id=?";
        try {
            ste = mc.prepareStatement(sql);
            ste.setInt(1, id);
            ste.executeUpdate();
            System.out.println("Category Supprimée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void UpdateName(String Name, int id) {
        String sql = "UPDATE  Category set name=? where id=?";
        try {
            ste = mc.prepareStatement(sql);
            ste.setString(1, Name);
            ste.setInt(2, id);
            ste.executeUpdate();
            System.out.println("Category mis à jour!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public void UpdateAttachement(Attachement a, Category c) {
        String sql = "UPDATE  Category set attachement=? where id=?";
        try {
            ste = mc.prepareStatement(sql);
            ste.setInt(1, a.getId());
            ste.setInt(2, c.getId());
            ste.executeUpdate();
            System.out.println("Category mis à jour!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Category> afficherCategory(){
        List<Category> categorys = new ArrayList<>();
        String sql = "SELECT * from Category";
        try {
            ste =mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
            Category r = new Category();
            r.setId(rs.getInt(1));
            r.setName(rs.getString(2));
            r.setAttachement(rs.getInt(3));
            categorys.add(r);
            }
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
         return categorys;
    }
    
    public List<String> getAllCategoryName() {
        List<String> categoryName = new ArrayList<>();
        String sql = "select Name from Category";
        try {
            ste = mc.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                categoryName.add(rs.getString("Name"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return categoryName;
    }


}
