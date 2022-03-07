/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Event;
import Entities.Person;
import Entities.Place;
import Entities.Reclamation;
import Services.EventService;
import Services.PersonService;
import Services.PlaceService;
import Services.ReclamationService;
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
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Achref Bouthouri
 */
public class AllReclamationController implements Initializable {

    @FXML
    private VBox list;
    @FXML
    private TextField id;
    @FXML
    private TextField location;
    @FXML
    private TextField createdby;

    ListView<Reclamation> listView;
    PlaceService ps = new PlaceService();
    ReclamationService rs = new ReclamationService();
    PersonService pes = new PersonService();
    ObservableList<Reclamation> data = FXCollections.observableArrayList();
    Reclamation GReacl = new Reclamation();
    @FXML
    private TextArea desc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data.addAll(rs.afficheReclamation());
        listView = new ListView<Reclamation>(data);
        list.getChildren().addAll(listView);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //listView.getSelectionModel().selectIndices(1, 2);
        //listView.getFocusModel().focus(1);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Reclamation>() {
            @Override
            public void changed(ObservableValue<? extends Reclamation> observable, Reclamation oldValue, Reclamation newValue) {
                //System.out.println(newValue.getId());

                //System.out.println("Gevent="+Gevent.getId());
                GReacl = newValue;
                reload(newValue);
            }
        });
    }

    @FXML
    private void Delete(ActionEvent event) {
        desc.clear();
        id.clear();
        location.clear();
        createdby.clear();
        rs.SupprimerReclamation(GReacl.getId());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Reclamation supprimer avec succes !!");
        alert.showAndWait();
        listView.getItems().clear();;
        data.addAll(rs.afficheReclamation());
        list.getChildren().addAll(listView);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    private void Cancel(ActionEvent event) {
        id.clear();
        location.clear();
        createdby.clear();
        //File file = new File("C:\\Users\\Achref Bouthouri\\Documents\\NetBeansProjects\\TuniPedia\\src\\GUI\\Assets\\photo.png");
        //Image Image = new Image(file.toURI().toString());
        //image.setImage(Image);
        //reload();
    }

    private void reload(Reclamation r) {

        id.setText(String.valueOf(r.getId()));
        Place p = ps.Selectplace(r.getLocation());
        //System.out.println(e.getLocation());
        location.setText(p.getName());
        Person per = pes.findById(GReacl.getCreatedBy());
        //System.out.println(per.toString());
        createdby.setText(per.getFullName());
        desc.setText(r.getText());

    }

}
