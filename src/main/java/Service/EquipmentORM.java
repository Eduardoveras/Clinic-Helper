/**
 * Created by Djidjelly Siclait on 9/25/2016.
 */
package Service;

import Entity.Equipment;

public class EquipmentORM extends GenericORMService<Equipment>{
    private static EquipmentORM instance;

    private EquipmentORM(){
        super(Equipment.class);
    }

    public static EquipmentORM getInstance(){
        if(instance == null)
            instance = new EquipmentORM();

        return instance;
    }
}
