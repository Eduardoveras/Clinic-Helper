package com.clinichelper.Repository;

import com.clinichelper.Entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by eva_c on 10/8/2016.
 */
public interface EquipmentRepository extends JpaRepository<Equipment, String> {
    Equipment findBy (String jascId);

    //@Query()
}
