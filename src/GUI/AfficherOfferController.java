/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Event;
import Entities.Offer;
import Entities.Person;
import Entities.Place;
import Services.EventService;
import Services.OfferService;
import Services.PersonService;
import Services.PlaceService;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Achref Bouthouri
 */
public class AfficherOfferController implements Initializable {

    @FXML
    private VBox list;
    @FXML
    private ImageView image;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
    @FXML
    private TextField montant;

    ListView<Offer> listView;
    PlaceService ps = new PlaceService();
    OfferService ev = new OfferService();
    PersonService pes = new PersonService();
    ObservableList<Offer> data = FXCollections.observableArrayList();
    Offer Goffer = new Offer();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        data.addAll(ev.afficherOffer());
        listView = new ListView<Offer>(data);
        System.out.println(data);
        list.getChildren().addAll(listView);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //listView.getSelectionModel().selectIndices(1, 2);
        //listView.getFocusModel().focus(1);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Offer>() {
            @Override
            public void changed(ObservableValue<? extends Offer> observable, Offer oldValue, Offer newValue) {
                //System.out.println(newValue.getId());

                //System.out.println("Gevent="+Gevent.getId());
                Goffer = newValue;
                datedebut.setValue(newValue.getDate_debut());
    
                datefin.setValue(newValue.getDate_fin());
                //Place p = ps.Selectplace(newValue.getLocation());
                //System.out.println(e.getLocation());
                montant.setText(String.valueOf(newValue.getMontant()));
                //Person per = pes.findById(Gevent.getCreatedBy());
                //System.out.println(per.toString());
                //createdby.setText(per.getFullName());
            }
        });
        // TODO
    }

    @FXML
    private void Delete(ActionEvent event) {
        datedebut.setValue(null);
        datefin.setValue(null);
        montant.clear();
        ev.SupprimerOffer(Goffer.getId());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Evénement supprimer avec succes !!");
        alert.showAndWait();
        listView.getItems().clear();;
        data.addAll(ev.afficherOffer());
        list.getChildren().addAll(listView);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    private void Cancel(ActionEvent event) {
        datedebut.setValue(null);
        datefin.setValue(null);
        montant.clear();
        File file = new File("C:\\Users\\Achref Bouthouri\\Documents\\NetBeansProjects\\TuniPedia\\src\\GUI\\Assets\\photo.png");
        Image Image = new Image(file.toURI().toString());
        image.setImage(Image);
    }

    @FXML
    private void Update(ActionEvent event) {
    }

}
