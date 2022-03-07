/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Wassym
 */
//public class FXMain extends Application {
//
//    @Override
//    public void start(Stage primaryStage) {
//       
//        try {
//            Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
//            Scene scene = new Scene(root);
//          //  scene.getStylesheets().add(getClass().getResource("Signup.css").toExternalForm());
//          //  primaryStage.setTitle("Ajout d'un personne");
//     
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        launch(args);
//    }
//}

public class FXMain extends Application {
    private double xOffset = 0 ;
    private double yOffset = 0 ;

    @Override
    public void start(Stage primaryStage) {
       
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
          //  scene.getStylesheets().add(getClass().getResource("Signup.css").toExternalForm());
          //  primaryStage.setTitle("Ajout d'un personne");
          primaryStage.initStyle(StageStyle.DECORATED.UNDECORATED);
         root.setOnMousePressed((MouseEvent event) -> {
             xOffset = event.getSceneX();
             yOffset = event.getSceneY();
            });
         
            root.setOnMouseDragged((MouseEvent event) -> {
                primaryStage.setX(event.getScreenX() - 1024);
                primaryStage.setY(event.getScreenY() - 860);
            });
            
            
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
