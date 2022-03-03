/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Place;
import Services.PlaceService;
import Tools.Session;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Achref Bouthouri
 */
public class YourPlacesController implements Initializable {

    @FXML
    private Label ConnectedUsr;
    private Label ch_balance;
    private List<Place> places;
    @FXML
    private GridPane PlaceContainer;
    @FXML
    private ScrollPane Card;
    PlaceService ps = new PlaceService();
    @FXML
    private ImageView ConnectedAvtr;
    @FXML
    private TextField SearchBar;
    int column,row;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ConnectedUsr.setText(Session.getUser().getFullName());
        //String balance = Float.toString(Session.getUser().getBalance());
        //ch_balance.setText(balance);
        places = new ArrayList<>(ps.afficherPlace());
        column = 0;
        row = 1;
        try {
            for (Place p : places) {
                if (p.getCreatedBy() == Session.getUser().getId()) {
                    // p.setAttachement_id("Djem.jpg");
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("card.fxml"));
                    VBox cardBox = loader.load();
                    CardController cardController = loader.getController();
                    cardController.SetBtnVisibility();
                    cardController.SetData(p);
                    if (column == 5) {
                        column = 0;
                        ++row;
                    }
                    PlaceContainer.add(cardBox, column++, row);
                    GridPane.setMargin(cardBox, new Insets(10));
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

@FXML
    public void AddP() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPlace.fxml"));
        try {
            Parent root = loader.load();
            AddPlaceController ac = loader.getController();
            PlaceContainer.getScene().setRoot(root);
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
            PlaceContainer.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Addevent(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EventHome.fxml"));
        try {
            Parent root = loader.load();
            AddEventController ac = loader.getController();
            PlaceContainer.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void reservations(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reservations.fxml"));
        try {
            Parent root = loader.load();
            ReservationController rc = loader.getController();
            PlaceContainer.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private List<Place> searchList(String searchWords) {
        List<Place> searchedplaces = new ArrayList<>();
//       List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(""));
        places.stream().filter((p) -> (p.getName().toLowerCase().equals(searchWords.toLowerCase()))).forEachOrdered((p) -> {
            searchedplaces.add(p);
        });
        return searchedplaces;
    }

    @FXML
    private void search(MouseEvent event) {
        PlaceContainer.getChildren().clear();
        try {
            for (Place p : searchList(SearchBar.getText())) {
                if (p.getCreatedBy() == Session.getUser().getId()) {
                //  p.setImg("Djem.jpg");
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Card.fxml"));
                VBox cardBox = loader.load();
                CardController cardController = loader.getController();
                cardController.SetData(p);
                if (column == 5) {
                    column = 0;
                    ++row;
                }
                PlaceContainer.add(cardBox, column++, row);
                GridPane.setMargin(cardBox, new Insets(10));
            }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }        @FXML
    void BackHome(MouseEvent event) {
   FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        try {
            Parent root = loader.load();
            HomeController hc = loader.getController();
           PlaceContainer.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }

}
