/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Attachement;
import Entities.Evaluation;
import Entities.Person;
import Entities.Place;
import Entities.Reservation;
import Services.AttachementService;
import Services.EvaluationService;
import Services.PayementService;
import Services.PersonService;
import Services.PlaceService;
import Services.ReservationService;
import Tools.Session;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Achref Bouthouri
 */
public class AffichagePlaceController implements Initializable {

    @FXML
    private Label placename;
    @FXML
    private Label placedescription;
    @FXML
    private Label adresseid;
    @FXML
    private Label cityid;
    @FXML
    private Label postalid;
    private int id;
    PlaceService ps = new PlaceService();
    @FXML
    private ImageView image;
    @FXML
    private Label ConnectedUsr;
    @FXML
    private ImageView ConnectedAvtr;
    int row,column;
     private List<Evaluation> evaluations;
    @FXML
    private GridPane Comments;
    @FXML
    private ScrollPane EvContainer;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    PlaceService p = new PlaceService();
    CardController c = new CardController();
    ReservationService rss = new ReservationService();
    PersonService ps1 = new PersonService();
    EvaluationService es = new EvaluationService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Attachement.setImage(image);
        // TODO
        Person p1 = ps1.findById((Session.getUser().getId()));
             AttachementService as = new AttachementService();
        Attachement a = as.findById(p1.getAvatar());
        //System.out.println((Session.getUser().getAvatar()));
        File file = new File(a.getPath());
        Image image = new Image(file.toURI().toString());
        ConnectedAvtr.setImage(image);
        ConnectedUsr.setText(Session.getUser().getFullName());
         evaluations = new ArrayList<>(es.afficherEvaluation());
        column = 0;
        row = 1;
    }

    public String getPlacename() {
        return placename.toString();
    }

    public void setPlacename(Label placename) {
        this.placename = placename;
    }
    

    public void afficher() {
        AttachementService as = new AttachementService();
        //System.out.println((Session.getUser().getAvatar()));
        Attachement a = as.findById(ps.Selectplace(this.getId()).getAttachement());
        File file = new File(a.getPath());
        Image image1 = new Image(file.toURI().toString());
        image.setImage(image1);
        placename.setText(ps.Selectplace(this.getId()).getName());
        adresseid.setText(ps.Selectplace(this.getId()).getAdresse());
        cityid.setText(ps.Selectplace(this.getId()).getCity());
        placedescription.setText(ps.Selectplace(this.getId()).getDescription());
        postalid.setText(ps.Selectplace(this.getId()).getPostalCode());
        //System.out.println(this.getId());
                try {
            for (Evaluation e : evaluations) {
                if(e.getLocation().equals(placename.getText())){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("CommentCard.fxml"));
                VBox cardBox = loader.load();
                CommentCardController commentCardController = loader.getController();
                commentCardController.SetData(e);
                if (column == 1) {
                    column = 0;
                    ++row;
                }
                Comments.add(cardBox, column++, row);
                GridPane.setMargin(cardBox, new Insets(10));
            }
                }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

//    @FXML
//    private void effectuerreservation(MouseEvent event) throws SQLException {
//        Boolean Validation = false;
//        if (ps.gettheprice(this.getId()) > Session.getUser().getBalance()) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setHeaderText("No Balance please");
//            alert.showAndWait();
//            Validation = false;
//        } else {
//            Validation = true;
//
//        }
//        if (ps.getcount(this.getId(), datereservation) < ps.Selectplace(this.getId()).getCapacite()) {
//            Validation = true;
//            /*Reservation r = new Reservation(datereservation, true, Session.getUser().getId(), this.getId());
//              ReservationService rs = new ReservationService();        
//              rs.AjouterReservation(r);*/
//        } else {
//            Validation = false;
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setHeaderText("Place Full");
//            alert.showAndWait();
//
//            //Place p = new Place(0, PName, Desc, Add, Cit, PC, lon, lat, 0, 0, 0, true, 0, tp, idA);
//            //PlaceService ps = new PlaceService(); 
//            //ps.AjouterPlace(p,Session.getUser()); 
//        }
//        if (rss.getcount(Session.getUser().getId(), this.getId(), datereservation) > 1) {
//            Validation = false;
//            /*Reservation r = new Reservation(datereservation, true, Session.getUser().getId(), this.getId());
//            ReservationService rs = new ReservationService();        
//            rs.AjouterReservation(r);*/
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setHeaderText("You Already Reserved in this Place on this date!");
//            alert.showAndWait();
//        }
//
//        while (Validation == true) {
//
//            ButtonType reserver = new ButtonType("reserver", ButtonBar.ButtonData.OK_DONE);
//            ButtonType payer = new ButtonType("payer", ButtonBar.ButtonData.OK_DONE);
//            Alert alert = new Alert(Alert.AlertType.WARNING,
//                    "Please Choose " + ".", reserver, payer);
//
//            alert.setTitle("Date format warning");
//            Optional<ButtonType> result = alert.showAndWait();
//
//            if (result.orElse(reserver) == payer) {
//                Reservation r = new Reservation(datereservation, false, Session.getUser().getId(), this.getId());
//                ReservationService rs = new ReservationService();
//                rs.AjouterReservation(r);
//                System.out.println("payer");
//                PayementService pse = new PayementService();
//                pse.Payer(r, "Effectué", this.getId(), 1, ps.gettheprice(this.getId()), ps.gettheowner(this.getId()), Session.getUser().getId());
//                Validation = false;
//                System.out.println(ps.gettheprice(this.getId()));
//            } else {
//                System.out.println("reserver");
//                Reservation r = new Reservation(datereservation, false, Session.getUser().getId(), this.getId());
//                ReservationService rs = new ReservationService();
//                rs.AjouterReservation(r);
//                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
//                alert1.setHeaderText("You Have Reserved ! Please Pay Later");
//                alert1.showAndWait();
//                Validation = false;
//            }
//        }
//    }
    @FXML
    void BackHome(MouseEvent event) {
   FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        try {
            Parent root = loader.load();
            HomeController hc = loader.getController();
           adresseid.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @FXML
    private void effectuerreservation(MouseEvent event) {
    }

    @FXML
    private void Addevent(ActionEvent event) {
    }


    @FXML
    public void AddP() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPlace.fxml"));
        try {
            Parent root = loader.load();
            AddPlaceController ac = loader.getController();
            placename.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void YourPlaces(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("YourPlaces.fxml"));
        try {
            Parent root = loader.load();
            YourPlacesController ac = loader.getController();
            placename.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }



    @FXML
    void GoMaps(ActionEvent event) {
   FXMLLoader loader = new FXMLLoader(getClass().getResource("Map.fxml"));
        try {
            Parent root = loader.load();
            MapController mc = loader.getController();
           placename.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }

}
