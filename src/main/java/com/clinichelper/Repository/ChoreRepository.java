/**
 * Created by Djidjelly Siclait on 10/23/2016.
 */
package com.clinichelper.Repository;

import com.clinichelper.Entity.Chore;
import com.clinichelper.Tools.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChoreRepository extends JpaRepository<Chore, String>{

    Chore findByJascId(String jascId);

    @Query("select c from Chore c where c.type = :choreType")
    List<Chore> findByType(@Param("choreType")Task type);
}
