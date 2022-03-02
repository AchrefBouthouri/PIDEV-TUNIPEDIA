/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Attachement;
import Entities.Reservation;
import Services.AttachementService;
import Services.PayementService;
import Services.PlaceService;
import Services.ReservationService;
import Tools.Session;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Achref Bouthouri
 */
public class AffichagePlaceController implements Initializable {

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
    private int id;
    PlaceService ps = new PlaceService();
    @FXML
    private DatePicker datepicker;
    private Label prixlabel;
    @FXML
    private Label ch_balance;
    @FXML
    private Label money;
    @FXML
    private ImageView image;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    PlaceService p = new PlaceService();
    CardController c = new CardController();
    ReservationService rss = new ReservationService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Attachement.setImage(image);
        // TODO
        connecteduser.setText(Session.getUser().getFullName());
        String balance = Float.toString(Session.getUser().getBalance());
        ch_balance.setText(balance);
    }

    public String getPlacename() {
        return placename.toString();
    }

    public void setPlacename(TextField placename) {
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
        money.setText(Float.toString(ps.Selectplace(this.getId()).getPrix()) + "DT");
        adresseid.setText(ps.Selectplace(this.getId()).getAdresse());
        cityid.setText(ps.Selectplace(this.getId()).getCity());
        latid.setText(ps.Selectplace(this.getId()).getLatitude());
        longid.setText(ps.Selectplace(this.getId()).getLongitude());
        placedescription.setText(ps.Selectplace(this.getId()).getDescription());
        postalid.setText(ps.Selectplace(this.getId()).getPostalCode());

        //System.out.println(this.getId());
    }

    @FXML
    private void effectuerreservation(MouseEvent event) throws SQLException {
        LocalDate datereservation = datepicker.getValue();
        Boolean Validation = false;
        if (ps.gettheprice(this.getId()) > Session.getUser().getBalance()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("No Balance please");
            alert.showAndWait();
            Validation = false;
        } else {
            Validation = true;

        }
        if (ps.getcount(this.getId(), datereservation) < ps.Selectplace(this.getId()).getCapacite()) {
            Validation = true;
            /*Reservation r = new Reservation(datereservation, true, Session.getUser().getId(), this.getId());
              ReservationService rs = new ReservationService();        
              rs.AjouterReservation(r);*/
        } else {
            Validation = false;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Place Full");
            alert.showAndWait();

            //Place p = new Place(0, PName, Desc, Add, Cit, PC, lon, lat, 0, 0, 0, true, 0, tp, idA);
            //PlaceService ps = new PlaceService(); 
            //ps.AjouterPlace(p,Session.getUser()); 
        }
        if (rss.getcount(Session.getUser().getId(), this.getId(), datereservation) > 1) {
            Validation = false;
            /*Reservation r = new Reservation(datereservation, true, Session.getUser().getId(), this.getId());
            ReservationService rs = new ReservationService();        
            rs.AjouterReservation(r);*/
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("You Already Reserved in this Place on this date!");
            alert.showAndWait();
        }

        while (Validation == true) {

            ButtonType reserver = new ButtonType("reserver", ButtonBar.ButtonData.OK_DONE);
            ButtonType payer = new ButtonType("payer", ButtonBar.ButtonData.OK_DONE);
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Please Choose " + ".", reserver, payer);

            alert.setTitle("Date format warning");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.orElse(reserver) == payer) {
                Reservation r = new Reservation(datereservation, false, Session.getUser().getId(), this.getId());
                ReservationService rs = new ReservationService();
                rs.AjouterReservation(r);
                System.out.println("payer");
                PayementService pse = new PayementService();
                pse.Payer(r, "Effectué", this.getId(), 1, ps.gettheprice(this.getId()), ps.gettheowner(this.getId()), Session.getUser().getId());
                Validation = false;
                System.out.println(ps.gettheprice(this.getId()));
            } else {
                System.out.println("reserver");
                Reservation r = new Reservation(datereservation, false, Session.getUser().getId(), this.getId());
                ReservationService rs = new ReservationService();
                rs.AjouterReservation(r);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setHeaderText("You Have Reserved ! Please Pay Later");
                alert1.showAndWait();
                Validation = false;
            }
        }
    }

}
