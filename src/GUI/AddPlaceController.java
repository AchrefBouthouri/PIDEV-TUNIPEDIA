/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Attachement;
import Entities.Client;
import Entities.Person;
import Entities.Place;
import Enum.Gender;
import Enum.Type;
import Services.AttachementService;
import Services.CategoryService;
import Services.PersonService;
import Services.PlaceService;
import Tools.Session;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author Achref Bouthouri
 */
public class AddPlaceController implements Initializable {

    @FXML
    private TextField Name;
    @FXML
    private TextField PostalCode;
    @FXML
    private TextField Adress;
    @FXML
    private TextField Latitude;
    @FXML
    private TextField City;
    @FXML
    private TextField Longitude;
    @FXML
    private ComboBox<String> categorycombobox;
    @FXML
    private RadioButton Public;
    @FXML
    private RadioButton Private;
    @FXML
    private TextArea Description;

    Type tp = Type.Public;
    @FXML
    private ToggleGroup Typ;
    public String Filename;
    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategoryService cs = new CategoryService();
        ObservableList<String> list = FXCollections.observableArrayList(cs.getAllCategoryName());
        categorycombobox.setItems(list);
        Public.setToggleGroup(Typ);
        Public.setSelected(true);
        Private.setToggleGroup(Typ);
    }

    public void GetType(ActionEvent event) {
        if (Public.isSelected()) {
            tp = Type.Public;
        }
        if (Private.isSelected()) {
            tp = Type.Private;
        }
    }

    @FXML
    private void AddPlace(ActionEvent event) throws IOException {
        PlaceService ps = new PlaceService();
        PersonService pss = new PersonService();
        AttachementService as = new AttachementService();
        if (Filename != null) {
            String name = Filename.substring(78);
            Attachement a = new Attachement(0, name, Filename);
            as.ajouterAttachement(a);
            Attachement a1 = as.findByPath(Filename);
            Place p = new Place(0, Name.getText(), Description.getText(), Adress.getText(), City.getText(), PostalCode.getText(), Longitude.getText(), Latitude.getText(), 0, 0, 0, true, Session.getUser().getId(), tp, a1.getId());
            Person p1 = pss.getPerson(Session.getUser().getId());
            ps.AjouterPlace(p, p1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Place ajouter avec succes !!");
            alert.showAndWait();

            Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
            Scene home_page_scene = new Scene(root);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(home_page_scene);
            app_stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Upload Image !!");
            alert.showAndWait();
        }

    }

    @FXML
    private void Upload(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        Filename = filename;
        File file = new File(Filename);
        Image Image = new Image(file.toURI().toString());
        image.setImage(Image);

    }
}
