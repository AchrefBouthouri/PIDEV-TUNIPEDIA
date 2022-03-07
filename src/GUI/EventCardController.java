/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Attachement;
import Entities.Event;
import Entities.Place;
import Services.AttachementService;
import Services.EventService;
import Services.PlaceService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author 21627
 */
public class EventCardController implements Initializable {

    @FXML
    private VBox Box;
    @FXML
    private ImageView Attachement;
    @FXML
    private Label Name;
    @FXML
    private Label Description;
    @FXML
    private FontAwesomeIcon star1;
    @FXML
    private FontAwesomeIcon star2;
    @FXML
    private FontAwesomeIcon star3;
    @FXML
    private FontAwesomeIcon star4;
    @FXML
    private FontAwesomeIcon star5;
    @FXML
    private Label views;
    @FXML
    private Label datedebut;
    @FXML
    private Label capacite;
int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddStar1(MouseEvent event) {
    }

    @FXML
    private void AddStar2(MouseEvent event) {
    }

    @FXML
    private void AddStar3(MouseEvent event) {
    }

    @FXML
    private void AddStar4(MouseEvent event) {
    }

    @FXML
    private void AddStar5(MouseEvent event) {
    }
  void SetData1(Event p) {
      
         AttachementService as = new AttachementService();
        Attachement a = as.findById(p.getIdattachement());
        //System.out.println((Session.getUser().getAvatar()));
//        File file = new File(a.getPath());
      //  Image image = new Image(file.toURI().toString());
      //  Attachement.setImage(image);
        id = p.getId();
        Name.setText(p.getNom());
        // int cardid = (p.getId());
        //  ps.Selectplace(p.getId());
        //  System.out.println(cardid);
       datedebut.setText(p.getDate_debut().toString());
        Description.setText(p.getDescription());
     
    }
    @FXML
    private void select_one(MouseEvent event) {
        AffichageEventController hc;
        EventService pse = new EventService();
        Event a = pse.Selectevent(id);
       
        System.out.println("ID" + id);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageEvent.fxml"));
        try {
            Parent root = loader.load();
            hc = loader.getController();
            hc.setId(a.getId());
            hc.afficher();
            Name.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
}
