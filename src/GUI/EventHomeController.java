package GUI;

import Entities.Attachement;
import Entities.Event;
import Entities.Person;
import Entities.Place;
import Services.AttachementService;
import Services.EventService;
import Services.PersonService;
import Services.PlaceService;
import Tools.Session;
import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Achref Bouthouri
 */
public class EventHomeController implements Initializable {

    @FXML
    private Label ConnectedUsr;
    private List<Event> events;
   
    @FXML
    private ScrollPane Card;
   
//    @FXML
//    Image logo = new Image(getClass().getResourceAsStream("logo.png"));
//    ImageView Logo = new ImageView(logo);
    Image user = new Image(getClass().getResourceAsStream("user.png"));
    @FXML
    ImageView ConnectedAvtr = new ImageView(user);
    Image hott = new Image(getClass().getResourceAsStream("hot.png"));
    ImageView hot = new ImageView(hott);
    Image bro = new Image(getClass().getResourceAsStream("browse.png"));
    ImageView browse = new ImageView(bro);
    Image so = new Image(getClass().getResourceAsStream("sort.png"));
    ImageView sort = new ImageView(so);
    int column, row;
    /**
     * Initializes the controller class.
     */
    EventService ps = new EventService();
    PersonService ps1 = new PersonService();
    
    @FXML
    private TextField SearchBar;
    @FXML
    private GridPane EventContainer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ConnectedUsr.setText(Session.getUser().getFullName());
        Person p1 = ps1.findById((Session.getUser().getId()));
        AttachementService as = new AttachementService();
        Attachement a = as.findById(p1.getAvatar());
        //System.out.println((Session.getUser().getAvatar()));
        File file = new File(a.getPath());
        Image image = new Image(file.toURI().toString());
        ConnectedAvtr.setImage(image);
       // String balance = Float.toString(Session.getUser().getBalance());
        //ch_balance.setText(balance);
        events = new ArrayList<>(ps.afficherEvent());
        column = 0;
        row = 1;
        try {
            for (Event p : events) {
              
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("EventCard.fxml"));
                VBox cardBox = loader.load();
                EventCardController eventHomeController = loader.getController();
                eventHomeController.SetData1(p);
                if (column == 5) {
                    column = 0;
                    ++row;
                }
                EventContainer.add(cardBox, column++, row);
                GridPane.setMargin(cardBox, new Insets(10));
          
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
            EventContainer.getScene().setRoot(root);
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
            EventContainer.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Addevent(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEvent.fxml"));
        try {
            Parent root = loader.load();
            AddEventController ac = loader.getController();
            EventContainer.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void reservations(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reservations.fxml"));
        try {
            Parent root = loader.load();
            ReservationController rc = loader.getController();
            EventContainer.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private List<Event> searchList(String searchWords) {
        List<Event> searchedplaces = new ArrayList<>();
//       List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(""));
        events.stream().filter((p) -> (p.getDescription().toLowerCase().equals(searchWords.toLowerCase()))).forEachOrdered((p) -> {
            searchedplaces.add(p);
        });
        return searchedplaces;
    }

    @FXML
    private void search(MouseEvent event) {
        EventContainer.getChildren().clear();
        try {
            for (Event p : searchList(SearchBar.getText())) {
                //  p.setImg("Djem.jpg");
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("EventCard.fxml"));
                VBox cardBox = loader.load();
                EventCardController cardHomeController = loader.getController();
                cardHomeController.SetData1(p);
                if (column == 5) {
                    column = 0;
                    ++row;
                }
                EventContainer.add(cardBox, column++, row);
                GridPane.setMargin(cardBox, new Insets(10));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    @FXML
    void GoMaps(ActionEvent event) {
   FXMLLoader loader = new FXMLLoader(getClass().getResource("Map.fxml"));
        try {
            Parent root = loader.load();
            MapController mc = loader.getController();
           EventContainer.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @FXML
    private void goHome(MouseEvent event) {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        try {
            Parent root = loader.load();
            HomeController mc = loader.getController();
           EventContainer.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    
}
