/**
 * Created by Djidjelly Siclait on 10/23/2016.
 */
package com.clinichelper.Repository;

import com.clinichelper.Entity.Chore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChoreRepository extends JpaRepository<Chore, String>{

    Chore findByChoreId(String choreId);

    @Query("select c from Chore c where c.clinic.clinicId = :clinic")
    List<Chore> findByClinicId(@Param("clinic") String clinicId);
/*
    @Query("select c from Chore c where c.type = :choreType and c.clinic.clinicId = :clinic")
    List<Chore> findByType(@Param("choreType")Task type, @Param("clinic") String clinicId);*/
}
