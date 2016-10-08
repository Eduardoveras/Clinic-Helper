package com.clinichelper.Repository;

import com.clinichelper.Entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by eva_c on 10/8/2016.
 */
public interface EquipmentRepository extends JpaRepository<Equipment, String> {
    Equipment findByJascId (String jascId);

    @Query("select E from Equipment E where E.equipmentName = :equipmentName")
    List<Equipment> findEquipmentByequipmentName(@Param("equipmentName") String equipmentName);
}
