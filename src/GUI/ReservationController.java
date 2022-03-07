/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Payement;
import Entities.Reservation;
import Services.PayementService;
import Services.ReservationService;
import Tools.Session;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Achref Bouthouri
 */
public class ReservationController implements Initializable {

    @FXML
    private Label ch_balance;

    @FXML
    private Label connecteduser;

   
    @FXML
    private Button Participer;
    @FXML
    private Button Annuler;
    @FXML
    private TextField tfSearch;
    @FXML
    private ComboBox<?> cbSearch;
    @FXML
    private TableView<Reservation> tvEvent;
    @FXML
    private TableColumn<Reservation, String> tcdate;
    @FXML
    private TableColumn<Reservation, String> tcstatus;
    @FXML
    private TableColumn<Reservation, String> tcowner;
    @FXML
    private TableColumn<Reservation, String> tcplace;
    @FXML
    private TableColumn<Reservation, String> tcPrice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         List<Reservation> le = new ArrayList<>();
        ReservationService res = new ReservationService();
        PayementService ps = new PayementService();
        
         connecteduser.setText(Session.getUser().getFullName());
        String balance=Float.toString(Session.getUser().getBalance());
        ch_balance.setText(balance);

        tvEvent.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            Reservation r=tvEvent.getSelectionModel().getSelectedItem();

            if (!ps.checkParticipation(Session.getUser().getId(),r.getId()))
            {
                Annuler.setDisable(true);
                Participer.setDisable(false);


            }
            else {
                Annuler.setDisable(false);
                Participer.setDisable(true);


            }

        });
        le = (ArrayList<Reservation>) res.afficherReservation();

        if ( !le.isEmpty()){

            ObservableList<Reservation> data = FXCollections.observableArrayList(le);
            FilteredList<Reservation> fle = new FilteredList(data, e -> true);
            
  
            tcdate.setCellValueFactory(new PropertyValueFactory<>("Date"));
            tcstatus.setCellValueFactory(new PropertyValueFactory<>("Confirmation"));
            tcowner.setCellValueFactory(new PropertyValueFactory<>("PlaceOwner"));
            tcplace.setCellValueFactory(new PropertyValueFactory<>("Lieu_event"));
          //  tcPrice.setCellValueFactory(new PropertyValueFactory<>("Prix"));

            tvEvent.setItems(fle);
            int nbe=tvEvent.getItems().size();
            
        cbSearch.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
        {//reset table and textfield when new choice is selected
            if (newVal != null)
            {
                tfSearch.setText("");
                fle.setPredicate(null);//This is same as saying flPerson.setPredicate(p->true);
            }
        });
           
        }else
       {
           System.out.println("il n'y a pas des reservations valider !!!!");
       }
  
    } 
    
        private void TableEvent(ActionEvent event) {

        
        tvEvent.getSelectionModel().getSelectedItem();   
     }
        
             public void loadData() throws SQLException{
    ObservableList<Reservation> dataa = null;

    dataa = FXCollections.observableArrayList(new ReservationService().afficherReservation());
    }
    
     void refresh() throws SQLException {
         ReservationService rs = new ReservationService();
         ArrayList<Reservation> le;
         le = (ArrayList<Reservation>) rs.afficherReservation();
         ObservableList<Reservation> data = FXCollections.observableArrayList(le);
         tcdate.setCellValueFactory(new PropertyValueFactory<>("Date"));
            tcstatus.setCellValueFactory(new PropertyValueFactory<>("Confirmation"));
            tcowner.setCellValueFactory(new PropertyValueFactory<>("PlaceOwner"));
            tcplace.setCellValueFactory(new PropertyValueFactory<>("Lieu_event"));
         tvEvent.setItems(data);
    }
        // TODO

    @FXML
    private void ParticiperEventAction(ActionEvent event) throws SQLException { Reservation r=tvEvent.getSelectionModel().getSelectedItem();
             Payement p = new Payement();
//             System.out.println(r.toString());

        if(r==null){
            System.out.println("Aucun événement séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun événement séléctionné");
            alert.showAndWait();
        }
        else {
            PayementService ps = new PayementService();

            if (ps.checkParticipation(Session.getUser().getId(),r.getId()))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("not allowed ");
                alert.setHeaderText(null);
                alert.setContentText("already done");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    return;
                }

            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Participer à l'Evenement");
            alert.setHeaderText(null);
            alert.setContentText("Etes-vous sur de vouloir de payer la reservation " +" "+ r.getId());
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
//                    ps.Payer(r.getId(),r.getPlace_id(),1,p.getMontant(),p.getCreatedBy(),Session.getUser().getId());
System.out.println(p.toString());
Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
alert1.setTitle("Succés!");
alert1.showAndWait();
            }
        
         loadData();
//         refresh();
        }
    
            
       
    }

      @FXML
        private void AnnulerParticipation(ActionEvent event) throws SQLException {
            Reservation r=tvEvent.getSelectionModel().getSelectedItem();
            Payement p =new Payement();

           
        if(r==null){
        
           System.out.println("Aucun Reservation séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun Reservation séléctionné");

            alert.showAndWait();
     
        }else {
            PayementService ps = new PayementService();

            System.out.println(p.toString());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Annuler le paiement");
            alert.setHeaderText(null);
            alert.setContentText("Etes-vous sur de vouloir Annuler la participation dans l'evenement " +" "+ r.getLocation());
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                
                ps.AnnulerParticipation(Session.getUser().getId(), r.getId());
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Succés!");
                alert1.setHeaderText(null);
                alert1.setContentText("Anuulation done!");
                
                alert1.showAndWait();
            }
        
         loadData();
         refresh();
        }
        
        }
       

}

