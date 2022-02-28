/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Place;
import Services.PlaceService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author 21627
 */
public class Affichageplace implements Initializable {

    @FXML
    private Label connecteduser;
       @FXML
    private TextField placename;
    @FXML
    private Label placedescription;
    @FXML
    private Label adresseid;
    @FXML
    private Label cityid;
    @FXML
    private Label postalid;
    @FXML
    private Label longid;
    @FXML
    private Label latid;
    PlaceService p = new PlaceService();
    CardController c = new CardController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       // Attachement.setImage(image);
        placename.setText("Hammamet");
        // TODO
    }    
    
}
