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
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;


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
    private void SignUp(ActionEvent event) {
        String name = FullName.getText();
        String email = Email.getText();
        String pwd = Password.getText();
        PersonService ps = new PersonService();
        Person p = ps.SignUp(name, email, pwd);
        System.out.println(p.toString());
        
//            FXMLLoader loder = new FXMLLoader(getClass().getResource("Profile.fxml"));
//        try {
//            Parent root = loder.load();
//            //AfficherPersonneController ac = loder.getController();
//            //ac.setList(ps.afficherPersonne().toString());
//            Email.getScene().setRoot(root);
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
    }

}
