/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Attachement;
import Entities.Person;
import Entities.Reclamation;
import Services.AttachementService;
import Services.PersonService;
import Services.PlaceService;
import Services.ReclamationService;
import Tools.Session;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author Achref Bouthouri
 */
public class AddRecController implements Initializable {


    @FXML
    private TextArea Description;
    int idA,idP; 
    @FXML
    private Label ConnectedUsr;
    @FXML
    private ImageView ConnectedAvtr;
    PersonService ps1 = new PersonService();
    @FXML
    private TextField imagepath;
    
    PlaceService ps = new PlaceService();
    @FXML
    private ImageView img;
//    private ComboBox<?> Place;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idP  = 0;
      ConnectedUsr.setText(Session.getUser().getFullName());
              Person p1 = ps1.findById((Session.getUser().getId()));
        AttachementService as = new AttachementService();
        Attachement a = as.findById(p1.getAvatar());
        //System.out.println((Session.getUser().getAvatar()));
        File file = new File(a.getPath());
        Image image = new Image(file.toURI().toString());
        ConnectedAvtr.setImage(image);
         ObservableList list = FXCollections.observableArrayList();
//         ps.afficherPlace().forEach((p) -> { if (p.isStatus()==true) {
//             list.add(p.getName());
//         }
//        });
//         Place.setItems(list);
    }    

//    private void Select(ActionEvent event) {
//        String s = Place.getSelectionModel().getSelectedItem().toString();
//        ps.afficherPlace().stream().filter((p) -> (s.equals(p.getName()))).forEachOrdered((p) -> {
//            idP = p.getId();
//        });       
//    }
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
       idA =  as.ajouterAttachement(a1);
       Image image = new Image(f.toURI().toString());
        img.setImage(image);
       imagepath.setText(filename);
        
    }

    @FXML
    void BackHome(MouseEvent event) {
   FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        try {
            Parent root = loader.load();
            HomeController hc = loader.getController();
           Description.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }


    @FXML
    void GoMaps(MouseEvent event) {
   FXMLLoader loader = new FXMLLoader(getClass().getResource("Map.fxml"));
        try {
            Parent root = loader.load();
            MapController mc = loader.getController();
           Description.getScene().setRoot(root);
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
            Description.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AddRec(ActionEvent event) {
         String Desc = Description.getText();
             if ( Description.getText().isEmpty()
               ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        }
           else{
                 PlaceService ps = new PlaceService();
                 Reclamation r = new Reclamation(0, Desc,Session.getUser().getId(), idP, idA);
                 ReclamationService rs = new ReclamationService();
                 rs.ajouterReclamation(r);
         Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
            alert3.setContentText("Reclamation en cours de traitement");
            alert3.showAndWait();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        try {
            Parent root = loader.load();
            HomeController hc = loader.getController();
           Description.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
           }
    }
}

