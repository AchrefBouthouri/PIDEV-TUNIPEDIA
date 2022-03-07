/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import com.itextpdf.text.Document;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.List;
import Entities.Attachement;
import Entities.Event;
import Entities.Payement;
import Services.AttachementService;
import Services.EventService;
import Services.PayementService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author 21627
 */
public class PayementcardController implements Initializable {

    @FXML
    private VBox Box;
    @FXML
    private Label datepay;
    @FXML
    private Label montantpay;
    @FXML
    private Label eventpay;
    @FXML
    private Label paystatus;
    PayementService Ps= new PayementService();
    @FXML
    private Label id;

    /**
     * Initializes the controller class.
     */
   
    public void initialize(URL url, ResourceBundle rb) {
        id.setVisible(false);
        // TODO
    }    

    void SetData1(Payement p) {
      
       EventService Es = new EventService();
        //System.out.println((Session.getUser().getAvatar()));
//        File file = new File(a.getPath());
      //  Image image = new Image(file.toURI().toString());
      //  Attachement.setImage(image);
       Event e1 = Es.Selectevent(p.getIdreservation());
       // n.setText(e1.getNom());
        datepay.setText(p.getDate().toString());
        
        eventpay.setText(e1.getNom());
        paystatus.setText(p.getDescription());
        montantpay.setText(String.valueOf(p.getMontant()));
        id.setText(String.valueOf(p.getId()));
        
        // int cardid = (p.getId());
        //  ps.Selectplace(p.getId());
        //  System.out.println(cardid);
     
     
    }
    @FXML
    private void select_one(MouseEvent event) throws FileNotFoundException, DocumentException, IOException {
       // if (Ps.){ System.out.println("effect");
       
       // }
}}
