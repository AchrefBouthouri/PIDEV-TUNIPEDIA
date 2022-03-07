/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Event;
import Services.EventService;
import Tools.Session;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Achref Bouthouri
 */
public class AddEventController implements Initializable {

    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
    @FXML
    private TextField capacite;
    @FXML
    private TextField prix;
    @FXML
    private TextField description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddEvent(ActionEvent event) {
        
        LocalDate  DPCurrentDate = datedebut.getValue();
    // pst.setDate(5, ((datedebut)DPCurrentDate.getEditor()).getText());
        LocalDate Datefin = datefin.getValue();
        String capacitestring = capacite.getText();
        int Capacite = Integer.parseInt(capacitestring);
        
        String prixx = prix.getText();
      Float Prix =  Float.parseFloat(prixx);

        String Description = description.getText();
        
      //  Event e = new Event(DPCurrentDate, Datefin, Description, Capacite, Prix,Session.getUser().getId(),55,55,55);
        EventService ps = new EventService(); 
        //ps.AjouterEvent(e);
    }
    
    
}

