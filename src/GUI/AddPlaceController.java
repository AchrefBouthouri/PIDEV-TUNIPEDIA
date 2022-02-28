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
import Services.PersonService;
import Services.PlaceService;
import Tools.Session;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javax.swing.JFileChooser;
import Tools.Session;

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
    Type tp = Type.Public;
    @FXML
    private Label connecteduser;
    int idA; 
    @FXML
    private TextField capacite;
    @FXML
    private ComboBox<?> categorycombobox;
    @FXML
    private TextField price;
    @FXML
    private Label ch_balance;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      connecteduser.setText(Session.getUser().getFullName());
       String balance=Float.toString(Session.getUser().getBalance());
        ch_balance.setText(balance); 
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
      
    @FXML
            
    void Upload(ActionEvent event) {
JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getPath();
        Attachement a1 = new Attachement();
        AttachementService as = new AttachementService();
        a1.setName("");
        a1.setPath(filename);
        as.ajouterAttachement(a1);
        
    }
        @FXML
          void AddPlace(ActionEvent event) {
        String PName = Name.getText();
        String Desc = Description.getText();
        String Add = Adress.getText();
        String Cit = City.getText();
        String PC = PostalCode.getText();
        String lon = Longitude.getText();
        String lat = Latitude.getText();
//        int capaciteplace = Integer.getInteger(capacite.getText());
        float prix;
        prix = Float.parseFloat(price.getText());
        
        
      Place p = new Place(PName, Desc, Add, Cit, PC, lon, lat, 20,1, 1, 12, true,Session.getUser().getId(), tp,1,prix);
          PlaceService ps = new PlaceService(); 
        ps.AjouterPlace(p,Session.getUser());
    }

  
   

}

