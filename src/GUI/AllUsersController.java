/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Attachement;
import Entities.Person;
import Enum.Gender;
import Services.AttachementService;
import Services.PersonService;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;

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
public class AllUsersController implements Initializable {

    public String Filename;
    ObservableList<Person> data = FXCollections.observableArrayList();

    @FXML
    private TableView<Person> tabu;
    @FXML
    private TableColumn<Person, String> name;
    @FXML
    private TableColumn<Person, String> email;
    @FXML
    private TableColumn<Person, Gender> gender;
    @FXML
    private TableColumn<Person, String> nationalite;
    @FXML
    private TableColumn<Person, Float> balance;
    @FXML
    private TableColumn<Person, String> role;
    @FXML
    private TableColumn<Person, String> createdat;
    @FXML
    private TableColumn<Person, Boolean> ispartner;
    @FXML
    private ImageView image;
    @FXML
    private TextField id;
    @FXML
    private TextField namep;
    @FXML
    private TextField emailp;

    PersonService ps = new PersonService();
    AttachementService as = new AttachementService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data.addAll(ps.afficherPerson());
        //System.out.println(ps.afficherPerson().size());
        name.setCellValueFactory(new PropertyValueFactory<>("FullName"));
        email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
        balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        createdat.setCellValueFactory(new PropertyValueFactory<>("createdat"));
        // System.out.println("_________"+new PropertyValueFactory<>("createdat"));
        ispartner.setCellValueFactory(new PropertyValueFactory<>("ispartner"));
        //System.out.println(data);
        tabu.setItems(data);
        // TODO
    }

    @FXML
    private void getCategorie(MouseEvent event) {
        id.setText(String.valueOf(tabu.getSelectionModel().getSelectedItem().getId()));
        namep.setText(tabu.getSelectionModel().getSelectedItem().getFullName());
        emailp.setText(tabu.getSelectionModel().getSelectedItem().getEmail());
        Attachement a = as.findById(tabu.getSelectionModel().getSelectedItem().getAvatar());
        File file = new File(a.getPath());
        Image Image = new Image(file.toURI().toString());
        image.setImage(Image);

    }

    @FXML
    private void Delete(ActionEvent event) {
        ps.SupprimerPerson(tabu.getSelectionModel().getSelectedItem().getId());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Personne supprimer avec succes !!");
        alert.showAndWait();
        data.clear();
        data.addAll(ps.afficherPerson());
    }


    @FXML
    private void Cancel(ActionEvent event) {
        id.clear();
        namep.clear();
        emailp.clear();
        File file = new File("C:\\Users\\Achref Bouthouri\\Documents\\NetBeansProjects\\TuniPedia\\src\\GUI\\Assets\\photo.png");
        Image Image = new Image(file.toURI().toString());
        image.setImage(Image);
    }

}
