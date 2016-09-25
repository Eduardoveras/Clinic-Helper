/**
 * Created by Djidjelly Siclait on 9/25/2016.
 */
package Tools;

import Entity.User;
import Service.UserORM;

import java.util.List;

public class DatabaseManager {

    // Declaring Singleton
    private DatabaseManager(){

    }

    public void bootDatabaseServer(){
        //Setting up default configurations
        List<User> users = UserORM.getInstance().FindAll();

        if (users.size() == 0){
            System.out.println("Creating True Admin ...");
            //Creating True Admin for Application
            UserORM.getInstance().Create(new User("admin", "JASC", "ITTT", "djidjellypas92@gmail.com", "admin", "admin"));
            System.out.println("True Admin created...");
        }
    }
}
