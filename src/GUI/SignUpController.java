/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Person;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import Services.PersonService;
import Tools.Session;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Wassym
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField FullName;
    @FXML
    private TextField Email;
    @FXML
    private TextField Password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void SignUp(ActionEvent event) throws IOException {
        PersonService us = new PersonService();
//        Person u = new Person();
//        u.setEmail(Email.getText());
//        u.setPassword(Password.getText());
        String name = FullName.getText();
        String email = Email.getText();
        String pwd = Password.getText();
        Person u = us.SignUp(name, email, pwd);
        int attempt = 1;
        if (us.Authentificationn(u)) {
            Parent home_page_parent;
            if (us.checkRole(Email.getText()).equals("Admin") && attempt < 4) {
                Session.getFirstInstance(Session.getUser());
                int ide = Session.getUser().getId();
                System.out.println(Session.getUser().getId());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Welcome Admin");
                alert.showAndWait();
                home_page_parent = FXMLLoader.load(getClass().getResource("Profile.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();

            } else if (us.checkRole(Email.getText()).equals("Client") && attempt < 4) {
                //Session.getFirstInstance(Session.getUser());
                //  System.out.println(Session.getUser().id);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Accessed! as client");
                alert.showAndWait();
                home_page_parent = FXMLLoader.load(getClass().getResource("Profile.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();

            } else if (us.checkRole(Email.getText()).equals("Owner") && attempt < 4) {
                Session.getFirstInstance(Session.getUser());
                int ide = Session.getUser().getId();
                System.out.println(ide);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Welcome Owner " + Session.getUser().getFullName());
                alert.showAndWait();
                home_page_parent = FXMLLoader.load(getClass().getResource("Profile.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();

            } else if (attempt != 4) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Denied!" + attempt);
                alert.showAndWait();
                attempt--;

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("attempt exceed!" + attempt);
                alert.showAndWait();

                Password.setDisable(true);
                Email.setEditable(false);
            }

            attempt++;

        }

    }

}
