/**
 * Created by Djidjelly Siclait on 10/2/2016.
 */
package com.clinichelper.Repository;

import com.clinichelper.Entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InsuranceRepository extends JpaRepository<Insurance, String>{

    Insurance findByJascId(String jascId);
/*
    @Query("select i from Insurance i where i.owner.jascID = :owner")
    Insurance findRegisteredPatientsInsuranceInformation(@Param("owner") String patientJascId);
    */
}
