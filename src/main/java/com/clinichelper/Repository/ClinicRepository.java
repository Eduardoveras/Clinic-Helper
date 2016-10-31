/**
 * Created by Djidjelly Siclait on 10/30/2016.
 */
package com.clinichelper.Repository;

import com.clinichelper.Entity.Clinic;
import com.clinichelper.Tools.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClinicRepository extends JpaRepository<Clinic, String>{
    Clinic findByClinicId(String clinicId);

    @Query("select c from Clinic c where c.clinicName = :name")
    List<Clinic> findByClinicName(@Param("name") String clinicName);

    @Query("select c from Clinic c where c.accountType = :account")
    List<Clinic> findByAccountType(@Param("account")AccountType accountType);
}
