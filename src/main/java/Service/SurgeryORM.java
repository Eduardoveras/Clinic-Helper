/**
 * Created by Djidjelly Siclait on 9/25/2016.
 */
package Service;

import Entity.Surgery;

public class SurgeryORM extends GenericORMService<Surgery>{
    private static SurgeryORM instance;

    private SurgeryORM(){
        super(Surgery.class);
    }

    public static SurgeryORM getInstance(){
        if(instance == null)
            instance = new SurgeryORM();

        return instance;
    }
}
