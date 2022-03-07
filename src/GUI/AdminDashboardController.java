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
import Services.PlaceService;
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
public class AdminDashboardController implements Initializable {

    @FXML
    private ImageView avatar;
    @FXML
    private Label name;
    @FXML
    private VBox vbox;
    private Parent fxml;

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
        try {
            fxml = FXMLLoader.load(getClass().getResource("AllUsers.fxml"));
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(fxml);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void AllUsers(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("AllUsers.fxml"));
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(fxml);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AllPlaces(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("AllPlaces.fxml"));
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(fxml);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AllCategories(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("AllCategories.fxml"));
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(fxml);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AllReclamation(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("AllReclamation.fxml"));
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(fxml);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AjouterPlace(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("AddPlace.fxml"));
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(fxml);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AjouterCategorie(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("AddCategory.fxml"));
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(fxml);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AllEvents(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("AllEvents.fxml"));
            vbox.getChildren().removeAll();
            vbox.getChildren().setAll(fxml);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
