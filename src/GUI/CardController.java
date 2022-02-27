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
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Achref Bouthouri
 */
public class CardController implements Initializable {

    @FXML
    private VBox Box;

    @FXML
    private ImageView Attachement;

    @FXML
    //  public Place p;
    private Label Name;
    @FXML
    private Label Type;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private TextArea Description;

    private final String[] colors = {"FFD20A", "FF4F30", "FFABBE", "FADEB4"};
    PlaceService ps = new PlaceService();

    public int SetData(Place p) {
        Image image = new Image(getClass().getResourceAsStream(p.getImg()));
        Attachement.setImage(image);
        Name.setText(p.getName());
        // int cardid = (p.getId());
        //  ps.Selectplace(p.getId());
        //  System.out.println(cardid);
        Type.setText("Public");
        Description.setText(p.getDescription());
        Box.setStyle("-fx-background-color: #" + colors[(int) (Math.random() * colors.length)] + ";"
                + "-fx-background-radius: 15;"
                + "-fx-effect: dropShadown(three-pass-box,rgba(0,0,0,0),10,0,0,10);");
        return p.getId();

    }
    
        @FXML
    public void select_one(MouseEvent event) {
        
       //SetData(p);
      //  PlaceService ps= new PlaceService();
     //   ps.Selectplace(p.getId());
//        System.out.println(cardid);
    }

}
