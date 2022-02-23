/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Client;
import Enum.Gender;
import Entities.Person;
import Services.PersonService;
import java.net.URL;
import java.sql.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Wassym
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField FullName;
    @FXML
    private TextField Email;
    @FXML
    private PasswordField Password;
    @FXML
    private ComboBox<String> Nationality;
    Image logo = new Image(getClass().getResourceAsStream("logo.png"));
    ImageView Logo = new ImageView(logo);
    String[] countries = Locale.getISOCountries();
    ObservableList<String> list = FXCollections.observableArrayList(countries);
    @FXML
    ToggleGroup gen = new ToggleGroup();
RadioButton rb1 = new RadioButton("Male");
RadioButton rb2 = new RadioButton("Female");
String Gend;
       
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Nationality.setItems(list);
         rb1.setToggleGroup(gen);
         rb2.setToggleGroup(gen);
    }    
    public String getGender(ActionEvent event){
    if(rb1.isSelected()){
        Gend = rb1.getText();
    }
     if(rb2.isSelected()){
        Gend = rb2.getText();
    }
     return Gend;
    }
    @FXML
    private void Ajouter(ActionEvent event) {
        String Name = FullName.getText();
        String Mail = Email.getText();
        String pwd = Password.getText();
       // Gender GenderEnum = Gender.valueOf(Gend);
        String Nation = Nationality.getValue();
        Person p = new Client(0, Name, Mail, pwd, 0, false, new Date(122,2,2), Gender.Female, Nation);
         PersonService ps = new PersonService(); 
        ps.AjouterPersonne(p);
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherPersonne.fxml"));
        try {
            Parent root = loader.load();
            AfficherPersonneController ac = loader.getController();
            ac.setAfficher(ps.afficherPerson().toString());
            FullName.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }*/
    }
    
}
