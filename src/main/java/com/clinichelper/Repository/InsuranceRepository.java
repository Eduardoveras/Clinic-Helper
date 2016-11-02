/**
 * Created by Djidjelly Siclait on 10/2/2016.
 */
package com.clinichelper.Repository;

import com.clinichelper.Entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InsuranceRepository extends JpaRepository<Insurance, String>{

    Insurance findByInsuranceId(String insuranceId);

    @Query("select i from Insurance i where i.insuranceSerialCode = :code")
    List<Insurance> findByInsuranceSerialCode(@Param("code") String insuranceSerialCode);

    @Query("select i from Insurance i where i.owner.patientId = :owner")
    List<Insurance> findByOwnerId(@Param("owner") String patientId);
}
