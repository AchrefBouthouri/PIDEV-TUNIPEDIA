/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Attachement;
import Entities.Category;
import Entities.Person;
import Services.AttachementService;
import Services.CategoryService;
import Services.PersonService;
import Services.PlaceService;
import Tools.Session;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Achref Bouthouri
 */
public class CategoriesController implements Initializable {

    @FXML
    private Label ConnectedUsr;
    private List<Category> categories;
    @FXML
    private GridPane PlaceContainer;
    @FXML
    private ScrollPane Card;
    @FXML
    private ComboBox<?> Trier;
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
    PlaceService ps = new PlaceService();
    PersonService ps1 = new PersonService();
    CategoryService cs = new CategoryService();
    @FXML
    private TextField SearchBar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList list = FXCollections.observableArrayList("Top Places", "A-Z", "Z-A");
        Trier.setItems(list);
        ConnectedUsr.setText(Session.getUser().getFullName());
        Person p1 = ps1.findById((Session.getUser().getId()));
        AttachementService as = new AttachementService();
        Attachement a = as.findById(p1.getAvatar());
        //System.out.println((Session.getUser().getAvatar()));
        File file = new File(a.getPath());
        Image image = new Image(file.toURI().toString());
        ConnectedAvtr.setImage(image);
        //String balance = Float.toString(Session.getUser().getBalance());
        //ch_balance.setText(balance);
        categories = new ArrayList<>(cs.afficherCategory());
        column = 0;
        row = 1;
        try {
            for (Category c : categories) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("CardCategorie.fxml"));
                AnchorPane cardBox = loader.load();
                CardCategorieController cardCategorieController = loader.getController();
                cardCategorieController.SetData(c);
                if (column == 5) {
                    column = 0;
                    ++row;
                }
                PlaceContainer.add(cardBox, column++, row);
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

    private List<Category> searchList(String searchWords) {
        List<Category> searchedcategories = new ArrayList<>();
        if (searchWords == null || searchWords.isEmpty()) {
            return cs.afficherCategory();
        } else {
            categories.stream().filter((p) -> (p.getName().toLowerCase().contains(searchWords.toLowerCase()))).forEachOrdered((p) -> {
                searchedcategories.add(p);
            });
            return searchedcategories;
        }
    }

    @FXML
    private void search(KeyEvent event) {
        PlaceContainer.getChildren().clear();
        row = 1;
        column = 0;
        try {
            for (Category c : searchList(SearchBar.getText())) {
                //  p.setImg("Djem.jpg");
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("CardCategorie.fxml"));
                AnchorPane cardBox = loader.load();
                CardCategorieController cardCategoryController = loader.getController();
                cardCategoryController.SetData(c);
                if (column == 5) {
                    column = 0;
                    ++row;
                }
                PlaceContainer.add(cardBox, column++, row);
                GridPane.setMargin(cardBox, new Insets(10));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    void GoMaps(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Map.fxml"));
        try {
            Parent root = loader.load();
            MapController mc = loader.getController();
            PlaceContainer.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Trier(ActionEvent event) {
        String s = Trier.getSelectionModel().getSelectedItem().toString();
        switch (s) {
            case "A-Z":
                PlaceContainer.getChildren().clear();
                row = 1;
                column = 0;
                categories = new ArrayList<>(cs.afficherCategoryByAZ());
                try {
                    for (Category c : categories) {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("CardCategorie.fxml"));
                        AnchorPane cardBox = loader.load();
                        CardCategorieController cardCategorieController = loader.getController();
                        cardCategorieController.SetData(c);
                        if (column == 5) {
                            column = 0;
                            ++row;
                        }
                        PlaceContainer.add(cardBox, column++, row);
                        GridPane.setMargin(cardBox, new Insets(10));

                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                break;
            case "Z-A":
                PlaceContainer.getChildren().clear();
                row = 1;
                column = 0;
                categories = new ArrayList<>(cs.afficherCategoryByZA());
                try {
                    for (Category c : categories) {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("CardCategorie.fxml"));
                        AnchorPane cardBox = loader.load();
                        CardCategorieController cardCategorieController = loader.getController();
                        cardCategorieController.SetData(c);
                        if (column == 5) {
                            column = 0;
                            ++row;
                        }
                        PlaceContainer.add(cardBox, column++, row);
                        GridPane.setMargin(cardBox, new Insets(10));

                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                break;
        }
    }

    @FXML
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
    @FXML
    private void goProfile(MouseEvent event) {
        PersonService ps = new PersonService();
       if (ps.checkRole(ConnectedUsr.getText()).equals("Admin")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
        try {
            Parent root = loader.load();
            AdminDashboardController adc = loader.getController();
           PlaceContainer.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } }
       else {
                          FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
        try {
            Parent root = loader.load();
            ProfileController pc = loader.getController();
           PlaceContainer.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
       }
    }

}
