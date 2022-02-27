/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Place;
import Services.PlaceService;
import Tools.Session;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Achref Bouthouri
 */
public class HomeController implements Initializable {

    @FXML
    private Label ConnectedUsr;
    private List<Place> places;
    @FXML
    private GridPane PlaceContainer;
    @FXML
    private ScrollPane Card;
    PlaceService pa = new PlaceService();
    @FXML
    private ImageView ConnectedAvtr;
    @FXML
    private Label ch_balance;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        ConnectedUsr.setText(Session.getUser().getFullName());
        String balance=Float.toString(Session.getUser().getBalance());
        ch_balance.setText(balance);
        System.out.println(pa.afficherPlace());
        places = new ArrayList<>(pa.afficherPlace());
        int column = 0;
        int row = 1;
         try {
        for ( Place p : places){
            p.setImg("Djem.jpg");
             FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("card.fxml"));
            VBox cardBox = loader.load();
            CardController cardController = loader.getController();
            cardController.SetData(p); 
            if (column == 5){
                column = 0; 
                ++row;
            }
            PlaceContainer.add(cardBox, column++, row);
            GridPane.setMargin(cardBox, new Insets(10));
        }
            } catch (IOException ex) {
             System.out.println(ex.getMessage());
            }
        }
    @FXML
    public void AddP(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPlace.fxml"));
        try {
            Parent root = loader.load();
            AddPlaceController ac = loader.getController();
           PlaceContainer.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
 /*   private List<Place> places(){
        List<Place> ps = new ArrayList<>();
        Place p = new Place();
        p.setName("El Djem Amphitheater");
        p.setDescription("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
       p.setImg("Djem.jpg");
       ps.add(p);
           Place p1 = new Place();
        p1.setName("El Djem Amphitheater");
        p1.setDescription("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
       p1.setImg("Djem.jpg");
       ps.add(p1);
            Place p6 = new Place();
        p6.setName("El Djem Amphitheater");
        p6.setDescription("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
       p6.setImg("Djem.jpg");
       ps.add(p6);
            Place p5 = new Place();
        p5.setName("El Djem Amphitheater");
        p5.setDescription("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
       p5.setImg("Djem.jpg");
       ps.add(p5);
            Place p4 = new Place();
        p4.setName("El Djem Amphitheater");
        p4.setDescription("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
       p4.setImg("Djem.jpg");
       ps.add(p4);
            Place p3 = new Place();
        p3.setName("El Djem Amphitheater");
        p3.setDescription("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
       p3.setImg("Djem.jpg");
       ps.add(p3);
            Place p2 = new Place();
        p2.setName("El Djem Amphitheater");
        p2.setDescription("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
       p2.setImg("Djem.jpg");
       ps.add(p2);
       
       return ps;
    }
*/
    @FXML
    private void Addevent(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEvent.fxml"));
        try {
            Parent root = loader.load();
            AddEventController ac = loader.getController();
           PlaceContainer.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
}

