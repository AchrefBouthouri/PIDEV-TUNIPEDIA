/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Person;
import Services.PersonService;
import Tools.Session;
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
import javafx.stage.Stage;

/**
 *
 * @author Achref Bouthouri
 */
public class SignInController implements Initializable {

    @FXML
    private TextField Email;
    @FXML
    private TextField Password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private void SignIn1(ActionEvent event) {

        String email = Email.getText();
        String pwd = Password.getText();
        PersonService ps = new PersonService();
        Person p = ps.Connexion(email, pwd);
        System.out.println(p.toString());
        FXMLLoader loder = new FXMLLoader(getClass().getResource("Profile.fxml"));
        try {
            Parent root = loder.load();
            ProfileController pc = loder.getController();
            //ac.setList(ps.afficherPersonne().toString());
            Email.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void SignIn(ActionEvent event) throws IOException {
        PersonService us = new PersonService();
        Person u = new Person();
        u.setEmail(Email.getText());
        u.setPassword(Password.getText());
        int attempt = 1;
        if (us.Authentification(u)) {
            Parent home_page_parent;
            //System.err.println(us.checkRole(Email.getText()));
            if (us.checkRole(Email.getText()).equals("Admin") && attempt < 4) {
                Session.getFirstInstance(Session.getUser());
                home_page_parent = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();

            } else if (us.checkRole(Email.getText()).equals("Client") && attempt < 4) {
                home_page_parent = FXMLLoader.load(getClass().getResource("Home.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();

            } else if (us.checkRole(Email.getText()).equals("Owner") && attempt < 4) {
                Session.getFirstInstance(Session.getUser());
                int ide = Session.getUser().getId();
                System.out.println(ide);
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
