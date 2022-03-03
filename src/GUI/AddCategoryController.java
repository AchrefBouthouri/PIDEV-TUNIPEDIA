/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Attachement;
import Entities.Category;
import Services.AttachementService;
import Services.CategoryService;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author Achref Bouthouri
 */
public class AddCategoryController implements Initializable {

    @FXML
    private Button img1;
    @FXML
    private Button ret;
    @FXML
    private Button ajouter;
    @FXML
    private TextField name;
    @FXML
    private TextField path;
    public String Filename;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void image(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        path.setText(filename);
        Filename = filename;
        File file = new File(Filename);
        Image image = new Image(file.toURI().toString());
        img.setImage(image);
    }

    @FXML
    private void AjouterCategorie(ActionEvent event) {
        CategoryService cs = new CategoryService();
        AttachementService as = new AttachementService();
        if (Filename != null) {
            String Name = Filename.substring(78);
            Attachement a = new Attachement(0, Name, Filename);
            as.ajouterAttachement(a);
            Attachement a1 = as.findByPath(Filename);
            Category c = new Category(0, name.getText(), a1.getId());
            cs.AjouterCategory(c, a1.getId());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Category ajouter avec succes !!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Upload Image !!");
            alert.showAndWait();
        }

    }

}
