/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Attachement;
import Entities.Category;
import Entities.Person;
import Entities.Place;
import Enum.Type;
import Services.AttachementService;
import Services.CategoryService;
import Services.PersonService;
import Services.PlaceService;
import Tools.Session;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.swing.JFileChooser;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Wassym
 */
public class AddPlaceController implements Initializable {

    @FXML
    private TextField Name;
    @FXML
    private TextArea Description;
    @FXML
    private TextField Adress;

    @FXML
    private TextField City;

    @FXML
    private TextField PostalCode;

    @FXML
    private TextField Longitude;

    @FXML
    private TextField Latitude;
    @FXML
    private RadioButton Public;
    @FXML
    private ToggleGroup Typ;
    @FXML
    private RadioButton Private;
    Type tp;
    int idA, idC;
    @FXML
    private Label ConnectedUsr;
    @FXML
    private ImageView ConnectedAvtr;
    PersonService ps1 = new PersonService();
    @FXML
    private ComboBox<?> Category;
    @FXML
    private TextField imagepath;

    CategoryService cs = new CategoryService();
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idC = 0;
        tp = Type.Public;
        ConnectedUsr.setText(Session.getUser().getFullName());
        Person p1 = ps1.findById((Session.getUser().getId()));
        AttachementService as = new AttachementService();
        Attachement a = as.findById(p1.getAvatar());
        //System.out.println((Session.getUser().getAvatar()));
        File file = new File(a.getPath());
        Image image = new Image(file.toURI().toString());
        ConnectedAvtr.setImage(image);
        Public.setToggleGroup(Typ);
        Public.setSelected(true);
        Private.setToggleGroup(Typ);
        ObservableList list = FXCollections.observableArrayList();
        cs.afficherCategory().forEach((c) -> {
            list.add(c.getName());
        });
        Category.setItems(list);
    }

    @FXML
    public void GetType(ActionEvent event) {
        if (Public.isSelected()) {
            tp = Type.Public;
        }
        if (Private.isSelected()) {
            tp = Type.Private;
        }
    }

    @FXML
    private void Select(ActionEvent event) {
        String s = Category.getSelectionModel().getSelectedItem().toString();
        cs.afficherCategory().stream().filter((c) -> (s.equals(c.getName()))).forEachOrdered((c) -> {
            idC = c.getId();
        });
    }

    @FXML
    void Upload(MouseEvent event) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        Attachement a1 = new Attachement();
        AttachementService as = new AttachementService();
        a1.setName("");
        a1.setPath(filename);
        idA = as.ajouterAttachement(a1);
        Image image = new Image(f.toURI().toString());
        img.setImage(image);
        imagepath.setText(filename);

    }

    @FXML
    void AddPlace(ActionEvent event) throws MalformedURLException, IOException, ParseException {
        String PName = Name.getText();
        String Desc = Description.getText();
        String Add = Adress.getText();
        String Cit = City.getText();
        String PC = PostalCode.getText();
        String lon = Longitude.getText();
        String lat = Latitude.getText();
        if (Name.getText().isEmpty()
                || Description.getText().isEmpty()
                || Adress.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        } else if (Longitude.getText().isEmpty() && Latitude.getText().isEmpty()) {
            URL url = new URL("https://nominatim.openstreetmap.org/search?format=json&q=$" + Adress.getText()
            );
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                //System.out.println(String.valueOf(informationString));

                JSONParser parse = new JSONParser();
                JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));
                //System.out.println(dataObject.get(0));

                JSONObject countryData = (JSONObject) dataObject.get(0);
                lon = countryData.get("lon").toString();
                lat = countryData.get("lat").toString();

                Latitude.setText(lon);
                Longitude.setText(lat);
                Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                alert3.setContentText("Longtitude et Latitude Inseré!");
                alert3.showAndWait();
            }
        } else if (idC == 0) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setContentText("Veuillez choisir une catégorie");
            alert1.showAndWait();
        } else if (imagepath.getText().isEmpty()) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText("Veuillez saisir un attachement");
            alert2.showAndWait();
        } else {
            Place p = new Place(0, PName, Desc, Add, Cit, PC, lon, lat, idC, 0, 0, false, Session.getUser().getId(), tp, idA);
            PlaceService ps = new PlaceService();
            ps.AjouterPlace(p, Session.getUser());
            Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
            alert3.setContentText("Votre demande est en cours de traitement");
            alert3.showAndWait();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            try {
                Parent root = loader.load();
                HomeController hc = loader.getController();
                Name.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            Notifications notificationBuild = Notifications.create()
                    .title("Traitement Offer")
                    .text("la Place a été ajouté avec succes")
                    .graphic(null)
                    //.hideAfter(Duration.Hours(5))
                    .position(Pos.BOTTOM_RIGHT)
                    .onAction((ActionEvent event1) -> {
                        System.out.println("click here");
                    });
            notificationBuild.show();
        }

    }

    @FXML
    void BackHome(MouseEvent event) {
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
    void GoMaps(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Map.fxml"));
        try {
            Parent root = loader.load();
            MapController mc = loader.getController();
            Name.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Addevent(ActionEvent event) {
    }

    @FXML
    void YourPlaces(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("YourPlaces.fxml"));
        try {
            Parent root = loader.load();
            YourPlacesController ac = loader.getController();
            Name.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
