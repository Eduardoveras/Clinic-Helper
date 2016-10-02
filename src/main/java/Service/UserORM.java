/**
 * Created by Djidjelly Siclait on 9/25/2016.
 */
package Service;

import Entity.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

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

    public static User findUserAccountWithUsernameAndPassword(String username, String password){
        EntityManager em = GetEntityManager();

        TypedQuery<User> query = em.createQuery("select u from User as u where u.username ='" +
                username + "' and u.password = '" +
                password + "'", User.class);

        List<User> users = query.getResultList();

        if (users.size() == 0)
            return null;
        else
            return users.get(0);
    }
}
