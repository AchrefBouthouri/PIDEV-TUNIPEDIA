/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Attachement;
import Enum.Gender;
import Services.AttachementService;
import Services.PersonService;
import Tools.Md5;
import Tools.Session;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author Achref Bouthouri
 */
public class SettingProfileController implements Initializable {

    @FXML
    private TextField path;

    public String Filename;
    @FXML
    private TextField name;
    @FXML
    private TextField oldpassword;
    @FXML
    private TextField newpassword;
    @FXML
    private TextField confimepassword;
    @FXML
    private RadioButton rb1;
    @FXML
    private RadioButton rb2;
    @FXML
    private ComboBox<String> nationality;
    
     private Parent fxml;
    
    @FXML
    ToggleGroup gen = new ToggleGroup();
    String[] countries = Locale.getISOCountries();
    ObservableList<String> list = FXCollections.observableArrayList(countries);
    Gender Gend = Gender.Male;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Session.getFirstInstance(Session.getUser());
        //int ide = Session.getUser().getId();
        //System.out.println(Session.getUser().getId());
        nationality.setItems(list);
        rb1.setToggleGroup(gen);
        rb1.setSelected(true);
        rb2.setToggleGroup(gen);

    }

    @FXML
    private void upload(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        path.setText(filename);
        Filename = filename;

    }

    @FXML
    private void Onclick(ActionEvent event) throws IOException {
        PersonService ps = new PersonService();
        AttachementService as = new AttachementService();

        if (!name.getText().isEmpty()) {
            ps.UpdateFullName(name.getText(), Session.getUser().getId());
        }
        if (rb1.isSelected()) {

            ps.UpdateGender(Gend, Session.getUser().getId());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        }
        if (rb2.isSelected()) {

            ps.UpdateGender(Gend, Session.getUser().getId());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Gender modifier avec succes !!");
            alert.showAndWait();
        }
        if (nationality.getValue() != null) {
            ps.UpdateNationalite(nationality.getValue(), Session.getUser().getId());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Nationalite modifier avec succes !!");
            alert.showAndWait();
        }
        if (!oldpassword.getText().isEmpty() && !newpassword.getText().isEmpty() && !confimepassword.getText().isEmpty()) {
            if (newpassword.getText().equals(confimepassword.getText())) {
                Md5 var = new Md5(newpassword.getText());
                ps.UpdatePassword(var.codeGet(), Session.getUser().getId());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Mot de passe modifier avec succes !!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("les deux mot de passe ne sont pas identique !!");
                alert.showAndWait();
            }

        }
        if (Filename != null) {
            String name = Filename.substring(50);
            Attachement a = new Attachement(0, name, Filename);
            as.ajouterAttachement(a);
            Attachement a1 = as.findByPath(Filename);
            ps.UpdateAvatar(Session.getUser().getId(), a1.getId());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Error Upload Image !!");
            alert.showAndWait();
        }

        try {
            fxml = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void GetGender(ActionEvent event) {
        if (rb1.isSelected()) {
            Gend = Gender.Male;
        }
        if (rb2.isSelected()) {
            Gend = Gender.Female;
        }
    }

}
