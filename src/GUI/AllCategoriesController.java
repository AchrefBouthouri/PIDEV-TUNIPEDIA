/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Achref Bouthouri
 */
public class AllCategoriesController implements Initializable {

    @FXML
    private Button ajouter;
    @FXML
    private Text username;
    @FXML
    private Button ret;
    @FXML
    private TextField nom;
    @FXML
    private Button img1;
    @FXML
    private ImageView imageview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterCategorie(ActionEvent event) {
    }

    @FXML
    private void home(MouseEvent event) {
    }

    @FXML
    private void monprofile(MouseEvent event) {
    }

    @FXML
    private void retour(ActionEvent event) {
    }

    @FXML
    private void image(ActionEvent event) {
    }
    
}
