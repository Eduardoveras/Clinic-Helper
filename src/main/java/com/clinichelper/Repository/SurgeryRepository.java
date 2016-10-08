package com.clinichelper.Repository;

import com.clinichelper.Entity.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by eva_c on 10/8/2016.
 */
public interface SurgeryRepository extends JpaRepository<Surgery, String> {
    Surgery findByJascId(String jascId);

    @Query("select s from Surgery s where s.surgeryName = :surgeryName " )
    List<Surgery> findSurgeryBysurgeryName(@Param("surgeryName") String surgeryName);

    @Query("select s from Surgery s where s.surgeryDate = :surgeryDate " )
    List<Surgery> findSurgeryBysurgeryDate( @Param("surgeryDate") Date surgeryDate);

    @Query("select s from Surgery s where s.surgeryRoom = :surgeryRoom " )
    List<Surgery> findSurgeryBysurgeryRoom( @Param("surgeryRoom") String surgeryRoom );

    @Query("select s from Surgery s where s.surgeryDate = :surgeryDate and s.surgeryRoom = :surgeryRoom " )
    List<Surgery> findSurgeryBysurgeryDateAndsurgeryRoom ( @Param("surgeryDate") Date surgeryDate, @Param("surgeryRoom") String surgeryRoom );

}
