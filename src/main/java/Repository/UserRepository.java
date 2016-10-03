/**
 * Created by Djidjelly Siclait on 10/2/2016.
 */
package Repository;

import Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository  extends JpaRepository<User, String>{

    User findByUsername(String username);

    @Query("select u from User u where u.username = :username and u.password = :pasword")
    User findUserAccountWithUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
