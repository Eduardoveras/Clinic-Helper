/**
 * Created by Djidjelly Siclait on 9/25/2016.
 */
package Service;

import Entity.Staff;

public class StaffORM extends GenericORMService<Staff>{
    private static StaffORM instance;

    private StaffORM(){
        super(Staff.class);
    }

    public static StaffORM getInstance(){
        if(instance == null)
            instance = new StaffORM();

        return instance;
    }
}
