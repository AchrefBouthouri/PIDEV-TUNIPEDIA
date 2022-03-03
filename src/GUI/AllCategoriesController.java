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
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

/**
 * FXML Controller class
 *
 * @author Achref Bouthouri
 */
public class AllCategoriesController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField attachement;
    @FXML
    private TableColumn<Category, String> namecat;
    @FXML
    private TableColumn<Category, String> attachementcat;
    CategoryService cs = new CategoryService();
    AttachementService as = new AttachementService();
    @FXML
    private TableView<Category> tabc;
    public String Filename;
    ObservableList<Category> data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ObservableList<Category> data1 = FXCollections.observableArrayList();

        data.addAll(cs.afficherCategory());
//        for (Category cat : data){
//            Attachement a = as.findById(cat.getAttachement());
//            cat.setPath(a.getPath());
//            System.err.println(cat.toString());
//            data1.add(cat);
//        }
        //System.out.println(data.size());
        //System.out.println(data);
        namecat.setCellValueFactory(new PropertyValueFactory<>("name"));
        attachementcat.setCellValueFactory(new PropertyValueFactory<>("attachement"));
        tabc.setItems(data);
    }

    @FXML
    private void Upload(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        Filename = filename;
        File file = new File(filename);
        Image Image = new Image(file.toURI().toString());
        image.setImage(Image);
    }

    @FXML
    private void Cancel(ActionEvent event) {
        id.clear();
        name.clear();
        attachement.clear();
        File file = new File("C:\\Users\\Achref Bouthouri\\Documents\\NetBeansProjects\\TuniPedia\\src\\GUI\\Assets\\photo.png");
        Image Image = new Image(file.toURI().toString());
        image.setImage(Image);
    }

    @FXML
    private void Delete(ActionEvent event) throws IOException {
        cs.SupprimerCategory(tabc.getSelectionModel().getSelectedItem().getId());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Categorie supprimer avec succes !!");
        alert.showAndWait();
        data.clear();
        data.addAll(cs.afficherCategory());

    }

    @FXML
    private void Update(ActionEvent event) throws IOException {
        Attachement a = as.findById(tabc.getSelectionModel().getSelectedItem().getAttachement());
        if (!name.getText().equals(tabc.getSelectionModel().getSelectedItem().getName())) {
            cs.UpdateName(name.getText(), tabc.getSelectionModel().getSelectedItem().getId());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Nom modifier avec succes !!");
            alert.showAndWait();
            data.clear();
            data.addAll(cs.afficherCategory());

        } else if (!attachement.getText().equals(a.getPath())) {
            String name = Filename.substring(78);
            Attachement a1 = new Attachement(0, name, Filename);
            as.ajouterAttachement(a1);
            Attachement a2 = as.findByPath(Filename);
            cs.UpdateAttachement(a2, tabc.getSelectionModel().getSelectedItem().getId());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Attachement modifier avec succes !!");
            alert.showAndWait();
            data.clear();
            data.addAll(cs.afficherCategory());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Rien n'est pas modifier !!");
            alert.showAndWait();

        }

    }

    @FXML
    private void getCategorie(MouseEvent event) {
        id.setText(String.valueOf(tabc.getSelectionModel().getSelectedItem().getId()));
        name.setText(tabc.getSelectionModel().getSelectedItem().getName());
        Attachement a = as.findById(tabc.getSelectionModel().getSelectedItem().getAttachement());
        attachement.setText(a.getPath());
        File file = new File(a.getPath());
        Image Image = new Image(file.toURI().toString());
        image.setImage(Image);
    }

}
