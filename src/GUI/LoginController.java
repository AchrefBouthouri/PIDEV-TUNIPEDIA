/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
//import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Tools.Session;
import Entities.Person;
import Services.PersonService;
//import utils.Session

/**
 * FXML Controller class
 *
 * @author 21627
 */
public class LoginController implements Initializable {

    @FXML
    private TextField login;
    @FXML
    private TextField pass;

    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

  

    @FXML
    private void Verifiercompte(ActionEvent event) throws IOException {
        PersonService us = new PersonService();
        Person u = new Person();

        u.setEmail(login.getText());
        u.setPassword(pass.getText());
        int attempt = 1;
        if (us.Authentification(u)) {

            /* if (txtUsername.getText().equals("admin")) {
                Parent home_page_parent = FXMLLoader.load(getClass().getResource("Dashbord.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
            }*/
            Parent home_page_parent;
             
            if (us.checkRole(login.getText()).equals("Admin") && attempt < 4) {
               Session.getFirstInstance(Session.getUser());
               // int ide = Session.getUser().getId();
                    System.out.println(Session.getUser().getId());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Welcome Admin");
                alert.showAndWait();
                home_page_parent = FXMLLoader.load(getClass().getResource("Admin.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();

            } 
        else    if (us.checkRole(login.getText()).equals("Client") && attempt < 4) {
                 //Session.getFirstInstance(Session.getUser());
               //  System.out.println(Session.getUser().id);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Accessed! as client");
                alert.showAndWait();
                home_page_parent = FXMLLoader.load(getClass().getResource("Client.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();

            }
            else if (us.checkRole(login.getText()).equals("Owner") && attempt < 4) {
                Session.getFirstInstance(Session.getUser());
                int ide = Session.getUser().getId();
                    System.out.println(ide);
 

  
              
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Welcome Owner "+Session.getUser().getFullName());
                alert.showAndWait();
                home_page_parent = FXMLLoader.load(getClass().getResource("Home.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();

            }
            else if (attempt != 4) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Denied!" + attempt);
                alert.showAndWait();
                attempt--;

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("attempt exceed!" + attempt);
                alert.showAndWait();

                pass.setDisable(true);
                login.setEditable(false);
            }

            attempt++;

        }

    }
    
    
    
}
