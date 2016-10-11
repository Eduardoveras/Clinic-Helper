/**
 * Created by Djidjelly Siclait on 10/2/2016.
 */
package com.clinichelper.Repository;

import com.clinichelper.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository  extends JpaRepository<User, String>{

    User findByUsername(String username);

    @Query("select u from User u where u.staff.jascId = :jascId")
    User findByStaffJascId(@Param("jascId") String staffJascId);

/*
    @Query("select u from User u where u.username = :username and u.password = :password")
    User findUserAccountWithUsernameAndPassword(@Param("username") String username, @Param("password") String password);
*/
}
