
package Tools;
//
///**

import Entities.Person;
import java.util.HashMap;
import java.util.Map;
import Services.PersonService;





// *
// * @author USER
// */
public class Session {
     private static final PersonService fs = new PersonService();
    
    private static Session instance = null;
    private  static Person user = null;


  
    
    private Session(Person userConnected) {
        super();
        Session.user = userConnected;
    }
    
    public final static Session getInstance(Person userConnected) {

        if (Session.instance == null) {
            Session.instance = new Session(userConnected);

        }
        System.out.println(Session.instance);
        return Session.instance;
    }
    
    public final static Session getFirstInstance(Person userConnected) {

        if (Session.instance == null) {

            Session.instance = new Session(userConnected);
         //   System.out.println(userConnected.getId());
      
        }
        return Session.instance;
    }

    public static PersonService getFs() {
        return fs;
    }

    public static Session getInstance() {
        return instance;
    }

    public static Person getUser() {
        return user;
    }

    public Session() {
    }


    public static void setUser(Person user) {
        Session.user = user;
    }


   
 
    

}