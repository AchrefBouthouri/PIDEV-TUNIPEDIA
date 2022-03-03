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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Achref Bouthouri
 */
public class AllUsersController implements Initializable {

    @FXML
    private TableView<?> tabc;
    @FXML
    private TableColumn<?, ?> namecat;
    @FXML
    private TableColumn<?, ?> attachementcat;
    @FXML
    private TableColumn<?, ?> attachementcat1;
    @FXML
    private TableColumn<?, ?> attachementcat2;
    @FXML
    private TableColumn<?, ?> attachementcat3;
    @FXML
    private TableColumn<?, ?> attachementcat4;
    @FXML
    private TableColumn<?, ?> attachementcat41;
    @FXML
    private TableColumn<?, ?> attachementcat42;
    @FXML
    private ImageView image;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void getCategorie(MouseEvent event) {
    }

    @FXML
    private void Upload(ActionEvent event) {
    }

    @FXML
    private void Delete(ActionEvent event) {
    }

    @FXML
    private void verify(ActionEvent event) {
    }

    @FXML
    private void Cancel(ActionEvent event) {
    }
    
}
