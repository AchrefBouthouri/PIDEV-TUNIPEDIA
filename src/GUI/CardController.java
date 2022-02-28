/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Attachement;
import Entities.Evaluation;
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
import GUI.HomeController;
import Services.AttachementService;
import Services.EvaluationService;
import Tools.Session;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
//import java.awt.Color;
import javafx.scene.paint.Color;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Wassym
 */
public class CardController implements Initializable {
     @FXML
    private VBox Box;

    @FXML
    private ImageView Attachement;

    @FXML
    private Label Name;
    @FXML
    private Label Type;

    @FXML
    private Label DeleteLabel;
    @FXML
    private TextArea Description;
    @FXML
     Button Deletebtn;
    @FXML
     Button DeletebtnNO;
    @FXML
     Button DeletebtnYES;
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
        int id; 
     AffichageplaceController hc;
    PlaceService ps = new PlaceService();
    EvaluationService es = new EvaluationService();
   // private final String [] colors = {"FFD20A","FF4F30","FFABBE","FADEB4"};
    

    @FXML
    void DELETENO(ActionEvent event) {
  FXMLLoader loader = new FXMLLoader(getClass().getResource("YourPlaces.fxml"));
        try {
            Parent root = loader.load();
            YourPlacesController ypc = loader.getController();
           Name.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
@FXML
    void AddStar1(MouseEvent event) {
     star1.setFill(Color.YELLOW);
      star2.setFill(Color.BLACK);
      star3.setFill(Color.BLACK);
      star4.setFill(Color.BLACK);
      star5.setFill(Color.BLACK);
     Evaluation E = new Evaluation(0, new Date(122,2,2), 1 , "", Session.getUser().getId(), id);
     es.AjouterEvaluation(E);
    }

    @FXML
    void AddStar2(MouseEvent event) {
        Evaluation E = new Evaluation(0, new Date(122,2,2), 2 , "", Session.getUser().getId(), id);
         star1.setFill(Color.YELLOW);
         star2.setFill(Color.YELLOW);
         star3.setFill(Color.BLACK);
         star4.setFill(Color.BLACK);
         star5.setFill(Color.BLACK);
         es.AjouterEvaluation(E);
    }

    @FXML
    void AddStar3(MouseEvent event) {
        Evaluation E = new Evaluation(0, new Date(122,2,2), 3 , "", Session.getUser().getId(), id);
         star1.setFill(Color.YELLOW);
         star2.setFill(Color.YELLOW);
         star3.setFill(Color.YELLOW);
         star4.setFill(Color.BLACK);
         star5.setFill(Color.BLACK);
         es.AjouterEvaluation(E);
    }

    @FXML
    void AddStar4(MouseEvent event) {
        Evaluation E = new Evaluation(0, new Date(122,2,2), 4 , "", Session.getUser().getId(), id);
         star1.setFill(Color.YELLOW);
         star2.setFill(Color.YELLOW);
         star3.setFill(Color.YELLOW);
         star4.setFill(Color.YELLOW);
         star5.setFill(Color.BLACK);
         es.AjouterEvaluation(E);
    }

    @FXML
    void AddStar5(MouseEvent event) {
        Evaluation E = new Evaluation(0, new Date(122,2,2), 5 , "", Session.getUser().getId(), id);
         star1.setFill(Color.YELLOW);
         star2.setFill(Color.YELLOW);
         star3.setFill(Color.YELLOW);
         star4.setFill(Color.YELLOW);
         star5.setFill(Color.YELLOW);
         es.AjouterEvaluation(E);
    }
    @FXML
    void DELETEYES(ActionEvent event) {
    ps.SupprimerPlace(id);
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        try {
            Parent root = loader.load();
            HomeController hc = loader.getController();
           Name.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    void DELETE(ActionEvent event) {
        Deletebtn.setVisible(false);
       DeletebtnNO.setVisible(true);
       DeletebtnYES.setVisible(true);
       DeleteLabel.setVisible(true);
    
    }
   
void SetBtnVisibility(){
    Deletebtn.setVisible(true);
}
    
    public void SetData(Place p){
        AttachementService as = new AttachementService();
        Attachement a = as.findById(p.getAttachement());
        Name.setText(p.getName());
        File file = new File(a.getPath());
        Image image = new Image(file.toURI().toString());
        Attachement.setImage(image);
//   Image image = new Image(getClass().getResourceAsStream(p.getImg()));
        id = p.getId();
       // Attachement.setImage(image);
        Name.setText(p.getName());
        Type.setText(p.getType());
        Description.setText(p.getDescription());
        if("Public".equals(Type.getText())){
        Box.setStyle("-fx-background-color: #FFD20A;" +
                     "-fx-background-radius: 15;" +
                     "-fx-effect: dropShadown(three-pass-box,rgba(0,0,0,0),10,0,0,10);") ;
        }
        else
        Box.setStyle("-fx-background-color: #FADEB4;" +
                     "-fx-background-radius: 15;" +
                     "-fx-effect: dropShadown(three-pass-box,rgba(0,0,0,0),10,0,0,10);") ;
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Deletebtn.setVisible(false);
        DeletebtnYES.setVisible(false);
         DeleteLabel.setVisible(false);
        DeletebtnNO.setVisible(false);
    } 
    @FXML
    public void select_one(MouseEvent event) {
        
      
       PlaceService pse= new PlaceService();
       
       Place a =  ps.Selectplace(id);
 //System.out.println(id);
 // FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichageplace.fxml"));
 FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichageplace.fxml"));
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
