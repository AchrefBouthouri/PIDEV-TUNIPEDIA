/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Enum.Gender;
import java.sql.Date;

/**
 *
 * @author Wassym
 */
public class Admin extends Person {
        public Admin(int id, String FullName, String Email, String Password, int Avatar, boolean Hasplaces, Date CreatedAt, Gender gender, String Nationalite) {
        super(id, FullName, Email, Password, Avatar, Hasplaces, CreatedAt, gender, Nationalite,false, "Admin");
    }
}
