/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Attachement;
import Entities.Person;
import Entities.Place;
import Services.AttachementService;
import Services.PersonService;
import Services.PlaceService;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Achref Bouthouri
 */
public class AllPlacesController implements Initializable {

    @FXML
    private TableView<Place> tabu;
    @FXML
    private TableColumn<Place, String> name;
    @FXML
    private TableColumn<Place, String> city;
    @FXML
    private TableColumn<Place, String> postalcode;
    @FXML
    private TableColumn<Place, Integer> notice;
    @FXML
    private TableColumn<Place, String> type;
    @FXML
    private TableColumn<Place, Integer> createdby;
    @FXML
    private TableColumn<Place, Boolean> Status;
    @FXML
    private ImageView image;
    @FXML
    private TextField id;
    @FXML
    private TextField namep;
    @FXML
    private TextField CreatedBy;
    @FXML
    private TableColumn<Place, String> adresse;
    PlaceService ps = new PlaceService();
    PersonService ppc = new PersonService();
    AttachementService as = new AttachementService();
    public String Filename;
    ObservableList<Place> data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data.addAll(ps.getAllPlaces());
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("Adress"));
        city.setCellValueFactory(new PropertyValueFactory<>("City"));
        postalcode.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));
        notice.setCellValueFactory(new PropertyValueFactory<>("Notice"));
        type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        createdby.setCellValueFactory(new PropertyValueFactory<>("CreatedBy"));
        Status.setCellValueFactory(new PropertyValueFactory<>("Status"));
        System.out.println(data);
        tabu.setItems(data);
    }

    @FXML
    private void getCategorie(MouseEvent event) {
        id.setText(String.valueOf(tabu.getSelectionModel().getSelectedItem().getId()));
        namep.setText(tabu.getSelectionModel().getSelectedItem().getName());
        Person p = ppc.findById(tabu.getSelectionModel().getSelectedItem().getCreatedBy());
        CreatedBy.setText(p.getFullName());
        Attachement a = as.findById(tabu.getSelectionModel().getSelectedItem().getAttachement());
        File file = new File(a.getPath());
        Image Image = new Image(file.toURI().toString());
        image.setImage(Image);
    }

    @FXML
    private void Delete(ActionEvent event) {
        ps.SupprimerPlace(tabu.getSelectionModel().getSelectedItem().getId());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Place supprimer avec succes !!");
        alert.showAndWait();
        data.clear();
        data.addAll(ps.afficherPlace());
    }

    @FXML
    private void verify(ActionEvent event) {
        ps.VerifPlace(tabu.getSelectionModel().getSelectedItem().getId());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Place Verifier avec succes !!");
        alert.showAndWait();
        data.clear();
        data.addAll(ps.afficherPlace());
    }

    @FXML
    private void Cancel(ActionEvent event) {
        id.clear();
        namep.clear();
        CreatedBy.clear();
        File file = new File("C:\\Users\\Achref Bouthouri\\Documents\\NetBeansProjects\\TuniPedia\\src\\GUI\\Assets\\photo.png");
        Image Image = new Image(file.toURI().toString());
        image.setImage(Image);
    }

}
