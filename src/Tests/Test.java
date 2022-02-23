/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Entities.Offer;
import Entities.Person;
import Entities.Place;
import Enum.Gender;
import Enum.Type;
import Services.OfferService;
import Services.PersonService;
import Services.PlaceService;
import Tools.ConnexionDB;
import Tools.Md5;
import java.sql.Date;

/**
 *
 * @author Achref Bouthouri
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Connexion Base de Donnees
        ConnexionDB c = ConnexionDB.getInstance();

        // Services
        PersonService ps = new PersonService();
        PlaceService pls = new PlaceService();
        OfferService os = new OfferService();
        //CategoryService CS = new CategoryService();
        //OfferService OC = new OfferService();
        

        // Instanciation des objets
        //Person p1 = new Administrateur(0, "Wassim Omrani", "wassim.omrani@esprit.tn", "123", 12, false, new Date(0), Gender.Male, "Tunisia");
        //Person p2 = new Owner(0, "Achref Bouthouri", "achref.bouthouri@esprit.tn", "123", 0, true, new Date(1), Gender.Male, "Tunisia", true);
        //Person p3 = new Client(0, "Houssem Gadhgadhi", "houssem.gadhgadhi@esprit.tn", "123", 12, false, new Date(0), Gender.Male, "Tunisia");
        //Person p4 = new Client(0, "Youssef Ben Slama", "youssef.benslama@esprit.tn", "123", 12, false, new Date(0), Gender.Male, "Tunisia");
        //Person p5 = new Client(0, "Mohamed Amine Teyeb", "amine.tayeb@esprit.tn", "123", 12, false, new Date(0), Gender.Male, "Tunisia");
        //Person p6 = new Client(0, "Mohamed Amine Chouchene", "mohamedamine.chouchene@esprit.tn", "123", 12, false, new Date(0), Gender.Male, "Tunisia");

        Place pl1 = new Place(0, "Hammamet", "XXX", "XXX", "XXX", "4150", "14N15E", "12S13W", 2, 0, 0, true, 0, Type.Private, 0);
        
        //Attachement a1 = new Attachement(0, "ville.png", "src/ville.png");
        //Category c1 = new Category(0,"Ville",a1.getId());
        Offer o = new Offer (2,new Date(122,1,1),new Date(122,1,1),500,8);
        
        // os.ajouterOffer(o);
        //  os.SupprimerOffer(o);
       // os.UpdateDateDebut(date_debut, o);
        os.UpdateMontant(900,3);
        //os.Updatedatefin(date_fin, o);
       // os.afficherOffer()
        //AS.ajouterAttachement(a1);
        //CLS.AjouterClient(PER1, a1);
        //System.out.println(ps.afficherPerson());
        //pls.AjouterPlace(pl1, p1);

        

    }

}
