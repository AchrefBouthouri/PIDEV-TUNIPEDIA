/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Attachement;
import Services.AttachementService;
import Services.PersonService;
import Tools.Session;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
    private RadioButton gender;
    @FXML
    private ComboBox<?> nationalite;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Session.getFirstInstance(Session.getUser());
        //int ide = Session.getUser().getId();
        System.out.println(Session.getUser().getId());

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
        if (name.getText() != null) {
            ps.UpdateFullName(name.getText(), Session.getUser().getId());
        }
        if (gender.getText() != null) {
            //ps.UpdateGender(gender.getText(),Session.getUser().getId());
        }
        if (nationalite.getValue() != null) {
            //ps.UpdateNationalite(nationalite.getValue(),Session.getUser().getId());
        }
        
        if (Filename != null){
        String name = Filename.substring(35);
        Attachement a = new Attachement(0, name, Filename);
        as.ajouterAttachement(a);
        Attachement a1 = as.findByPath(Filename);
        ps.UpdateAvatar(Session.getUser().getId(), a1.getId());
        }

        Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Scene home_page_scene = new Scene(root);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();

    }

}
