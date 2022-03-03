/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Attachement;
import Entities.Place;
import Services.AttachementService;
import Services.PlaceService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Achref Bouthouri
 */
public class CardHomeController implements Initializable {

    @FXML
    private VBox Box;
    @FXML
    private ImageView Attachement;
    @FXML
    private Label Description;
    @FXML
    private Label Name;
    @FXML
    private Label Type;
    AffichagePlaceController hc;
    public int notice;
    int id;
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


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    private final String[] colors = {"e6d2b5","9f7866"};
    PlaceService ps = new PlaceService();

    public int SetData(Place p) {
        AttachementService as = new AttachementService();
        Attachement a = as.findById(p.getAttachement());
        //System.out.println((Session.getUser().getAvatar()));
        File file = new File(a.getPath());
        Image image = new Image(file.toURI().toString());
        Attachement.setImage(image);
        id = p.getId();
        Name.setText(p.getName());
        // int cardid = (p.getId());
        //  ps.Selectplace(p.getId());
        //  System.out.println(cardid);
        Type.setText(p.getType());
        Description.setText(p.getDescription());
        switch (p.getNotice()) {
            case 1:
                star1.setFill(Color.rgb(253, 193, 2));
                star2.setFill(Color.BLACK);
                star3.setFill(Color.BLACK);
                star4.setFill(Color.BLACK);
                star5.setFill(Color.BLACK);
                break;
            case 2:
                star1.setFill(Color.rgb(253, 193, 2));
                star2.setFill(Color.rgb(253, 193, 2));
                star3.setFill(Color.BLACK);
                star4.setFill(Color.BLACK);
                star5.setFill(Color.BLACK);
                break;
            case 3:
                star1.setFill(Color.rgb(253, 193, 2));
                star2.setFill(Color.rgb(253, 193, 2));
                star3.setFill(Color.rgb(253, 193, 2));
                star4.setFill(Color.BLACK);
                star5.setFill(Color.BLACK);
                break;
            case 4:
                star1.setFill(Color.rgb(253, 193, 2));
                star2.setFill(Color.rgb(253, 193, 2));
                star3.setFill(Color.rgb(253, 193, 2));
                star4.setFill(Color.rgb(253, 193, 2));
                star5.setFill(Color.BLACK);
                break;
            case 5:
                star1.setFill(Color.rgb(253, 193, 2));
                star2.setFill(Color.rgb(253, 193, 2));
                star3.setFill(Color.rgb(253, 193, 2));
                star4.setFill(Color.rgb(253, 193, 2));
                star5.setFill(Color.rgb(253, 193, 2));
                break;
            default:
                star1.setFill(Color.BLACK);
                star2.setFill(Color.BLACK);
                star3.setFill(Color.BLACK);
                star4.setFill(Color.BLACK);
                star5.setFill(Color.BLACK);
                break;
                
        }
        Box.setStyle("-fx-background-color: #" + colors[(int) (Math.random() * colors.length)] + ";" + "-fx-background-radius: 15;" + "-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0),10,0,0,10);");
        return p.getId();

    }

    @FXML
    public void select_one(MouseEvent event) {
        PlaceService pse = new PlaceService();
        Place a = ps.Selectplace(id);
        System.out.println("ID" + id);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichagePlace.fxml"));
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



}

