/**
 * Created by Djidjelly Siclait on 10/8/2016.
 */
package com.clinichelper.Repository;

import com.clinichelper.Entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, String>{

    Contact findByContactId(String contactId);

    @Query("select c from Contact c where c.email = :email and c.clinic.clinicId = :clinic")
    Contact findByStaffEmail(@Param("email") String email, @Param("clinic") String clinicId);

    @Query("select c from Contact c where c.clinic.clinicId = :clinic")
    List<Contact> findByClinicId(@Param("clinic") String clinicId);

    @Query("select c from Contact c where c.firstName = :firstname and c.lastName = :lastname and c.clinic.clinicId = :clinic")
    List<Contact> findByFirstNameAndLastName(@Param("firstname") String firstName, @Param("lastname") String lastName, @Param("clinic") String clinicId);

    @Query("select c from Contact c where c.clinic.clinicId = :clinic and c.birthDate between :starting and :ending")
    List<Contact> findByStaffBirthDateRange(@Param("starting")Date start, @Param("ending") Date end, @Param("clinic") String clinicId);
}
