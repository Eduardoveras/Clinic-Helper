/**
 * Created by Djidjelly Siclait on 11/1/2016.
 */
package com.clinichelper.Repository;

import com.clinichelper.Entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, String> {
    Medication findByMedicationId(String medicationId);

    @Query("select m from Medication m where m.clinic.clinicId = :clinic")
    List<Medication> findByClinic(@Param("clinic") String clinicId);

    @Query("select m from Medication m where m.medicationName = :name and m.clinic.clinicId = :clinic")
    List<Medication> findByMedicationName(@Param("name") String name, @Param("clinic") String clinicId);

    @Query("select m from Medication m where m.supplier = :supplier and m.clinic.clinicId = :clinic")
    List<Medication> findBySupplier(@Param("supplier") String supplier, @Param("clinic") String clinicId);
}
