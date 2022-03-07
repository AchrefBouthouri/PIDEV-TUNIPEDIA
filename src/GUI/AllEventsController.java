/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Event;
import Entities.Person;
import Entities.Place;
import Services.CategoryService;
import Services.EventService;
import Services.PersonService;
import Services.PlaceService;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * FXML Controller class
 *
 * @author Achref Bouthouri
 */
public class AllEventsController implements Initializable {

    @FXML
    public VBox list;
    @FXML
    private ImageView image;
    @FXML
    private TextField id;
    @FXML
    private TextField location;
    @FXML
    private TextField createdby;

    ListView<Event> listView;
    PlaceService ps = new PlaceService();
    EventService ev = new EventService();
    PersonService pes = new PersonService();
    ObservableList<Event> data = FXCollections.observableArrayList();
    Event Gevent = new Event();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data.addAll(ev.afficherEvent());
        listView = new ListView<Event>(data);
        list.getChildren().addAll(listView);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //listView.getSelectionModel().selectIndices(1, 2);
        //listView.getFocusModel().focus(1);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Event>() {
            @Override
            public void changed(ObservableValue<? extends Event> observable, Event oldValue, Event newValue) {
                //System.out.println(newValue.getId());

                //System.out.println("Gevent="+Gevent.getId());
                Gevent = newValue;
                reload(newValue);
            }
        });

    }

    @FXML
    private void Delete(ActionEvent event) {
        id.clear();
        location.clear();
        createdby.clear();
        ev.SupprimerEvent(Gevent);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Evénement supprimer avec succes !!");
        alert.showAndWait();
        listView.getItems().clear();;
        data.addAll(ev.afficherEvent());
        list.getChildren().addAll(listView);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    @FXML
    private void Cancel(ActionEvent event) {
        id.clear();
        location.clear();
        createdby.clear();
        File file = new File("C:\\Users\\Achref Bouthouri\\Documents\\NetBeansProjects\\TuniPedia\\src\\GUI\\Assets\\photo.png");
        Image Image = new Image(file.toURI().toString());
        image.setImage(Image);
        //reload();
    }

    private void reload(Event e) {

        id.setText(String.valueOf(e.getId()));
        Place p = ps.Selectplace(e.getLocation());
        //System.out.println(e.getLocation());
        location.setText(p.getName());
        Person per = pes.findById(Gevent.getCreatedBy());
        //System.out.println(per.toString());
        createdby.setText(per.getFullName());

    }

}
