/**
 * Created by Djidjelly Siclait on 9/25/2016.
 */
package Tools;

import Entity.User;
import Service.UserORM;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;
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

    // User Related Functions
    public boolean isUsernameTaken(String username){
        User user = UserORM.getInstance().Find(username);

        return (user != null ) ? true : false;
    }

    public boolean createNewUser(String username, String firstName, String lastName, String email, String password, String role){
        try{
            UserORM.getInstance().Create(new User(username, firstName, lastName, email, password, role));
            System.out.println("Created new user");
            return true;
        } catch (EntityNotFoundException exp){
            System.out.println("\n\nEntity ERROR! --> " + exp.getMessage() + "\n");
            return false;
        } catch (TransactionRequiredException exp) {
            System.out.println("\n\nTransaction ERROR! --> " + exp.getMessage() + "\n");
            return false;
        } catch (PersistenceException exp){
            System.out.println("\n\nPersistence ERROR! --> " + exp.getMessage() + "\n");
            return false;
        } catch (Exception exp){
            System.out.println("\n\nGeneral ERROR! --> " + exp.getMessage() + "\n");
            return false;
        }
    }

    public boolean doesUserAccountExist(String username, String password){
        User account = UserORM.findUserAccountWithUsernameAndPassword(username, password);

        return (account != null) ? true : false;
    }

    public void deleteUserAccount(String username){
        UserORM.getInstance().Delete(UserORM.getInstance().Find(username));
    }
}
