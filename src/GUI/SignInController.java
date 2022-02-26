/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Person;
import Services.PersonService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

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

    @FXML
    private void SignIn(ActionEvent event) {

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

}
