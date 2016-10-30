/**
 * Created by Djidjelly Siclait on 10/2/2016.
 */
package com.clinichelper.Repository;

import com.clinichelper.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository  extends JpaRepository<User, String>{

    User findByUserId(String userId);

    @Query("select u from User u where u.clinic.clinicId = :clinic")
    List<User> findByClinicId(@Param("clinic") String clinicId);

    @Query("select u from User u where u.firstName = :first and u.lastName = :last")
    List<User> findByFullName(@Param("first") String firstName, @Param("last") String lastName);

    @Query("select u from User u where u.email = :email and u.clinic.clinicId = :clinic")
    User findUserAccountWithUsernameAndClinicID(@Param("email") String email, @Param("clinic") String clinicId);


    @Query("select u from User u where u.email = :email and u.password = :password and u.clinic.clinicId = :clinic")
    User findUserAccountWithUsernameAndClinicIdAndPassword(@Param("email") String email, @Param("clinic") String clinicId, @Param("password") String password);

}
