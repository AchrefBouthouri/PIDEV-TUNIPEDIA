/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Attachement;
import Entities.Person;
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
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Achref Bouthouri
 */
public class ProfileController implements Initializable {

    @FXML
    private ImageView avatar;
    @FXML
    private Label name;
    private Parent fxml;
    @FXML
    private VBox vbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PersonService ps = new PersonService();
        Person p = ps.findById((Session.getUser().getId()));
        AttachementService as = new AttachementService();
        Attachement a = as.findById(p.getAvatar());
        name.setText(p.getFullName());
        //System.out.println((Session.getUser().getAvatar()));
        File file = new File(a.getPath());
        Image image = new Image(file.toURI().toString());
        avatar.setImage(image);

    }

    @FXML
    private void setting(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("SettingProfile.fxml"));
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(fxml);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
