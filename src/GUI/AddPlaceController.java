/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Client;
import Entities.Person;
import Entities.Place;
import Enum.Gender;
import Enum.Type;
import Services.PlaceService;
import Tools.Session;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Achref Bouthouri
 */
public class AddPlaceController implements Initializable {

    private TextField Name;
    private TextArea Description;
    private TextField Adress;

    private TextField City;

    private TextField PostalCode;

    private TextField Longitude;

    private TextField Latitude;
    private RadioButton Public;
    private ToggleGroup Typ;
    private RadioButton Private;
    Type tp = Type.Public;
    private Label connecteduser;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
    @FXML
    private TextField capacite;
    @FXML
    private TextField prix;
    @FXML
    private TextField description;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      connecteduser.setText(Session.getUser().getFullName());
         Public.setToggleGroup(Typ);
         Public.setSelected(true);
         Private.setToggleGroup(Typ);
    }    
      public void GetType(ActionEvent event){
          if(Public.isSelected()){
              tp = Type.Public;
          }
          if(Private.isSelected()){
              tp = Type.Private;
          }
      }
    private void AddPlace(ActionEvent event) {
        String PName = Name.getText();
        String Desc = Description.getText();
        String Add = Adress.getText();
        String Cit = City.getText();
        String PC = PostalCode.getText();
        String lon = Longitude.getText();
        String lat = Latitude.getText();
        Place p = new Place(0, PName, Desc, Add, Cit, PC, lon, lat, 0, 0, 0, true, 0, tp, 0);
          PlaceService ps = new PlaceService(); 
          Person p1 = new Client(0, PName, lat, Add, 0, true, new Date(122,12,1), Gender.Female, PName,2f);
         //System.out.println(p);
         ps.AjouterPlace(p,p1);

    }

    @FXML
    private void AddEvent(ActionEvent event) {
    }
}

