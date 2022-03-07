/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Attachement;
import Entities.Category;
import Entities.Place;
import Services.AttachementService;
import Services.CategoryService;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Achref Bouthouri
 */
public class CardCategorieController implements Initializable {

    @FXML
    private VBox Box;
    int id;
    @FXML
    private Label Name;
    @FXML
    private ImageView Attachement;
    AffichageParCategorieController hc;
    CategoryService cse = new CategoryService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    private final String[] colors = {"000000"};
    PlaceService ps = new PlaceService();

    public int SetData(Category c) {
        AttachementService as = new AttachementService();
        Attachement a = as.findById(c.getAttachement());
        //System.out.println((Session.getUser().getAvatar()));
        File file = new File(a.getPath());
        Image image = new Image(file.toURI().toString());
        Attachement.setImage(image);
        Name.setText(c.getName());
        id = c.getId();

        return c.getId();

    }

    @FXML
    public void select_one(MouseEvent event) {
        Category a = cse.SelectCategory(id);
        //System.out.println("ID" + id);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageParCategorie.fxml"));
        try {
            Parent root = loader.load();
            hc = loader.getController();
            hc.setIdC(a.getId());
            hc.afficher();
            Name.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }




}

