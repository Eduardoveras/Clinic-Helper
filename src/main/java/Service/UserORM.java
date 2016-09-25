/**
 * Created by Djidjelly Siclait on 9/25/2016.
 */
package Service;

import Entity.User;

public class UserORM extends GenericORMService<User>{
    private static UserORM instance;

    private UserORM(){
        super(User.class);
    }

    public static UserORM getInstance(){
        if(instance == null)
            instance = new UserORM();

        return instance;
    }
}
