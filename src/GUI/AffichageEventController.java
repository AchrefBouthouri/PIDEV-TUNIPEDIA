/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import Entities.Pdf;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import GUI.MyQr;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import Entities.Attachement;
import Entities.Person;
import Entities.Reservation;
import Services.AttachementService;
import Services.EventService;
import Services.PayementService;
import Services.PersonService;
import Services.PlaceService;
import Services.ReservationService;
import Tools.Session;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * FXML Controller class
 *
 * @author 21627
 */
public class AffichageEventController implements Initializable {


   @FXML
    private ImageView ConnectedAvtr;

    @FXML
    private Label ConnectedUsr;

    @FXML
    private Label Name;

    @FXML
    private FontAwesomeIcon buttonQR;

    @FXML
    private Button buttonreserver;

    @FXML
    private Label ch_balance;

    @FXML
    private Label datedebut;

    @FXML
    private Label datefin;

    @FXML
    private Button delete;

    @FXML
    private Label description;

    @FXML
    private Label history;

    @FXML
    private ImageView image;

    @FXML
    private ImageView imageQR;

    @FXML
    private Line lign;

    @FXML
    private Label montant;

    @FXML
    private Label namecoming;

    @FXML
    private Label place;

    @FXML
    private FontAwesomeIcon qrcodereader;
    private static Stage primaryStage;

//////////////////////////////////////////////
   
private int id;
EventService ps = new EventService();
PayementService pse = new PayementService();

   
    ReservationService rss = new ReservationService();
    @FXML
    private ImageView imageParticipant;    
    @FXML
    private VBox Personne;
    private Label result;
    @FXML
    private Label labelresult;
    @FXML
    private Label revenue;
    @FXML
    private Label nbtickets;
    @FXML
    private VBox boxstats;
    @FXML
    private FontAwesomeIcon facture;
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
int ida;

  private final Background focusBackground = new Background( new BackgroundFill( Color.web( "#7FFF00" ), CornerRadii.EMPTY, Insets.EMPTY ) );
    private final Background unfocusBackground = new Background( new BackgroundFill( Color.web( "#A52A2A" ), CornerRadii.EMPTY, Insets.EMPTY ) );

    /**
     * 
     * 
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }   
     public void afficher() {
         
           String balance=Float.toString(pse.SelectBalance(Session.getUser()));
           ch_balance.setText(balance);
    AttachementService as = new AttachementService();
        //System.out.println((Session.getUser().getAvatar()));
        Attachement a = as.findById(ps.Selectevent(this.getId()).getIdattachement());
        File file1 = new File(a.getPath());
        Image image1 = new Image(file1.toURI().toString());
        image.setImage(image1);
        PayementService payementservice=new PayementService();
       revenue.setText(String.valueOf(payementservice.countRevenue(this.getId(),ps.Selectevent(this.getId()).getCreatedBy()))+" DT");
        nbtickets.setText(String.valueOf(payementservice.counttickets(this.getId(),ps.Selectevent(this.getId()).getCreatedBy())));
        Name.setText(ps.Selectevent(this.getId()).getNom());
     //   datedebut.setText(ps.Selectevent(this.getId()).getDate_debut().toString());
        datefin.setText(ps.Selectevent(this.getId()).getDate_fin().toString());
        datedebut.setText(ps.Selectevent(this.getId()).getDate_debut().toString());
        montant.setText(String.valueOf(ps.Selectevent(this.getId()).getMontant())+"DT");
        description.setText(ps.Selectevent(this.getId()).getDescription());
        buttonQR.setVisible(false);
        imageQR.setVisible(false);
        qrcodereader.setVisible(false);
        qrcodereader.setDisable(true);
        if (LocalDate.now().isBefore(ps.Selectevent(this.getId()).getDate_debut()))
        {
        if (Session.getUser().getId()==ps.Selectevent(this.getId()).getCreatedBy())
        {
            
            imageQR.setVisible(false);
            buttonreserver.setVisible(false);
buttonQR.setVisible(false);
montant.setVisible(false);
qrcodereader.setVisible(true);
qrcodereader.setDisable(false);
delete.setVisible(false); delete.setDisable(true);
lign.setVisible(false);
Personne.setVisible(false);
facture.setVisible(false);
facture.setDisable(true);
            
        }
        else {
          boxstats.setVisible(false);
          boxstats.setDisable(true);
           
          if (pse.getcount(Session.getUser().getId(),this.getId())>=1){
     
/*Reservation r = new Reservation(datereservation, true, Session.getUser().getId(), this.getId());
       ReservationService rs = new ReservationService();        
//rs.AjouterReservation(r);*/
buttonreserver.setDisable(true);
buttonreserver.setText("Participated");
buttonQR.setVisible(true);
montant.setVisible(false);
qrcodereader.setVisible(false);
qrcodereader.setDisable(true);
imageQR.setVisible(true);
delete.setVisible(true); delete.setDisable(false);
lign.setVisible(true);
facture.setVisible(true);
facture.setDisable(false);
Personne.setVisible(false);
//imageParticipant.setVisible(false);
//namecoming.setVisible(false);
//result.setVisible(false);
          }
        
          else{ 
delete.setVisible(false); delete.setDisable(true); lign.setVisible(false);
buttonreserver.setDisable(false);
buttonreserver.setText("Participer");
montant.setVisible(true);
Personne.setVisible(false);
imageParticipant.setVisible(false);
namecoming.setVisible(false);
facture.setVisible(false);
facture.setDisable(true);
//result.setVisible(false);
   
       
                  }
          
        }
        }
        else {
            
     }}
    @FXML
    void BackHome(MouseEvent event) {
   FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        try {
            Parent root = loader.load();
            HomeController hc = loader.getController();
           datedebut.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @FXML
    private void effectuerreservation(MouseEvent event) throws SQLException {
        LocalDate datereservation = LocalDate.now();
        Boolean Validation = false;
    
        
   
        
          if (ps.gettheprice(this.getId())>Session.getUser().getBalance()){
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("No Balance please");
                alert.showAndWait();
                Validation=false;
       }
       else
       { Validation = true;
  
             
                
    }
       if (ps.getcount(this.getId())<ps.Selectevent(this.getId()).getCapacite()){
       Validation = true;
/*Reservation r = new Reservation(datereservation, true, Session.getUser().getId(), this.getId());
       ReservationService rs = new ReservationService();        
rs.AjouterReservation(r);*/
       }
       else
       { Validation =false;
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Event Full");
                alert.showAndWait();
                
   //     Place p = new Place(0, PName, Desc, Add, Cit, PC, lon, lat, 0, 0, 0, true, 0, tp, idA);
          //PlaceService ps = new PlaceService(); 
     //    ps.AjouterPlace(p,Session.getUser()); 
        
    }
       if (pse.getcount(Session.getUser().getId(),this.getId())>=1){
       Validation = false;
/*Reservation r = new Reservation(datereservation, true, Session.getUser().getId(), this.getId());
       ReservationService rs = new ReservationService();        
//rs.AjouterReservation(r);*/


 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("You Already Participated in this Event!");
                alert.showAndWait();
       }
   
       
    
    if(Validation==true){
        
        ButtonType payer = new ButtonType("Payer", ButtonBar.ButtonData.OK_DONE);
ButtonType non = new ButtonType("Annuler", ButtonBar.ButtonData.OK_DONE);
Alert alert = new Alert(Alert.AlertType.WARNING,
        "Please Choose " + ".", non,payer);

alert.setTitle("Participation");
Optional<ButtonType> result = alert.showAndWait();

if (result.orElse(non) == payer) {
   
    System.out.println("payer");
    PayementService pse = new PayementService();
    pse.Payer(this.getId(),"Payement" ,this.getId(),1,ps.gettheprice(this.getId()),ps.gettheowner(this.getId()),Session.getUser().getId());
   this.afficher();
    Validation=false;
    System.out.println(ps.gettheprice(this.getId()));
    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setHeaderText("Paricipation avec succees");
                alert1.showAndWait();
}
else {      
    System.out.println("Quitter");
   

    Validation=false;
    }
    
       }
    
}
    @FXML
    private void Addevent(ActionEvent event) {
    }


    @FXML
    public void AddP() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPlace.fxml"));
        try {
            Parent root = loader.load();
            AddPlaceController ac = loader.getController();
            Name.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void YourPlaces(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("YourPlaces.fxml"));
        try {
            Parent root = loader.load();
            YourPlacesController ac = loader.getController();
            Name.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }



    void GoMaps(ActionEvent event) {
   FXMLLoader loader = new FXMLLoader(getClass().getResource("Map.fxml"));
        try {
            Parent root = loader.load();
            MapController mc = loader.getController();
           Name.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @FXML
    private void GoMaps(MouseEvent event) {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("Map.fxml"));
        try {
            Parent root = loader.load();
            MapController mc = loader.getController();
           Name.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }

 

   

    @FXML
    private void supprimerP(ActionEvent event) throws SQLException {
         ButtonType payer = new ButtonType("Supprimer", ButtonBar.ButtonData.OK_DONE);
ButtonType non = new ButtonType("Annuler", ButtonBar.ButtonData.OK_DONE);
Alert alert = new Alert(Alert.AlertType.WARNING,
        "Please Choose " + ".", non,payer);

alert.setTitle("Retirer Participation");
Optional<ButtonType> result = alert.showAndWait();

if (result.orElse(non) == payer) {
   
    System.out.println("Etes Vous Sur de Retirer votre Participation");                    
    PayementService pse = new PayementService();
    pse.AnnulerParticipation(Session.getUser().getId(),this.getId());
   pse.Payer(this.getId(),"Remboursement" ,this.getId(),1,ps.gettheprice(this.getId()),Session.getUser().getId(),ps.gettheowner(this.getId()));
    this.afficher(); 
       
    
  
   

 //   Validation=false;
    System.out.println(ps.gettheprice(this.getId()));
    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setHeaderText("Balanace Remboursé!");
                alert1.showAndWait();
        
    }
    }

    @FXML
    private void gohistory(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PayHistory.fxml"));
        try {
            Parent root = loader.load();
            PayHistoryController ph = loader.getController();
            ch_balance.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    private void generateQR(MouseEvent event)throws WriterException, IOException,
               NotFoundException {
        // The data that the QR code will contain
        String data = String.valueOf(Session.getUser().getId());
  
        // The path where the image will get saved
        String path = "ticket"+Session.getUser().getFullName()+".png";
 
        // Encoding charset
        String charset = "UTF-8";
 
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap
            = new HashMap<EncodeHintType,
                          ErrorCorrectionLevel>();
 
        hashMap.put(EncodeHintType.ERROR_CORRECTION,
                    ErrorCorrectionLevel.L);
 
        // Create the QR code and save
        // in the specified folder
        // as a jpg file
        
        MyQr.createQR(data, path, charset, hashMap, 300, 300);
        AttachementService as = new AttachementService();
        Attachement a1 =new Attachement(0,"ticket"+Session.getUser().getFullName()+".png",path);
        ida =  as.ajouterAttachement(a1);
       
        System.out.println(a1);
       
       
        PayementService pse=new PayementService();
        pse.ajouterattachmement( ida, Session.getUser().getId(), this.getId());
     //  System.out.println( as.ajouterAttachement(a1));
        System.out.println("QR Code Generated!!! ");
    int idQR = (ida);
                System.out.println(idQR);
        Attachement a2 = as.findById(idQR);
        File file2 = new File(a2.getPath());
        Image image2 = new Image(file2.toURI().toString());
        imageQR.setImage(image2);
    }

    @FXML
    private void facturepdf(MouseEvent event) {
                  Pdf pd=new Pdf();
          PayementService payement=new PayementService();
        try{
        pd.GeneratePdf("MaFacture",payement.selectidpayement(Session.getUser().getId(), this.getId()),Session.getUser(),this.getId());
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(PayementService.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void readqr(MouseEvent event) {
         Webcam webcam = Webcam.getDefault();   //Generate Webcam Object
        webcam.setViewSize(new Dimension(320,240));
        WebcamPanel webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setMirrored(false);
        JFrame jFrame = new JFrame();
        jFrame.add(webcamPanel);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

        do {
            try {
                BufferedImage image = webcam.getImage();
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                Result result = new MultiFormatReader().decode(bitmap);
                if(result.getText() != null) {
                 
                 PersonService vs = new PersonService();
                 if (pse.getcount(Integer.parseInt(result.getText()), this.getId())==1){
                 namecoming.setText(vs.findById(Integer.parseInt(result.getText())).getFullName());
                 Personne.setVisible(true);
                
imageParticipant.setVisible(true);
Person p1 = vs.findById(Integer.parseInt(result.getText()));
        AttachementService as = new AttachementService();
        Attachement a = as.findById(p1.getAvatar());
         File file = new File(a.getPath());
        Image image2 = new Image(file.toURI().toString());
        imageParticipant.setImage(image2);
namecoming.setVisible(true);
labelresult.setVisible(true);
labelresult.setText("Authorised");

                 Personne.setBackground(focusBackground);
                 }
                  if (pse.getcount(Integer.parseInt(result.getText()), this.getId())==0){
                 namecoming.setText("Unauthorized");
                 Personne.setVisible(true);
                 labelresult.setVisible(false);
                 imageParticipant.setVisible(false);
                Personne.setBackground(unfocusBackground);
                 
                 }
                    jFrame.setVisible(false);
                    jFrame.dispose();
                    webcam.close();
                    break;
                }

            }catch (NotFoundException e ) {
                //pass
            }

        } while(true);
    }
}
