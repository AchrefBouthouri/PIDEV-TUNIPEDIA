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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Wassym
 */
public class MapController implements Initializable {

    @FXML
    private Label ConnectedUsr;
    @FXML
    private ImageView ConnectedAvtr;
    @FXML
    private WebView webview = new WebView();
    private WebEngine e;
    PersonService ps1 = new PersonService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ConnectedUsr.setText(Session.getUser().getFullName());
        Person p1 = ps1.findById((Session.getUser().getId()));
        AttachementService as = new AttachementService();
        Attachement a = as.findById(p1.getAvatar());
        //System.out.println((Session.getUser().getAvatar()));
        File file = new File(a.getPath());
        Image image = new Image(file.toURI().toString());
        ConnectedAvtr.setImage(image);
       
        e = webview.getEngine();
		final URL urlLeafletMaps = getClass().getResource("index.html");
		e.load(urlLeafletMaps.toExternalForm());
    }    
    
    

    @FXML
    private void Addevent(ActionEvent event) {
    }

    @FXML
    private void YourPlaces(MouseEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("YourPlaces.fxml"));
        try {
            Parent root = loader.load();
            YourPlacesController ac = loader.getController();
            ConnectedAvtr.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AddP(ActionEvent event) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPlace.fxml"));
        try {
            Parent root = loader.load();
            AddPlaceController ac = loader.getController();
            ConnectedAvtr.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
      @FXML
    void BackHome(MouseEvent event) {
   FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        try {
            Parent root = loader.load();
            HomeController hc = loader.getController();
           ConnectedUsr.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }
        @FXML
    private void GoCategories(MouseEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Categories.fxml"));
        try {
            Parent root = loader.load();
            CategoriesController cc = loader.getController();
            ConnectedUsr.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
}
