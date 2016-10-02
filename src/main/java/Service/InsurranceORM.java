/**
 * Created by Djidjelly Siclait on 10/2/2016.
 */
package Service;

import Entity.Insurance;

public class InsurranceORM extends GenericORMService<Insurance>{
    private static InsurranceORM instance;

    private InsurranceORM(){
        super(Insurance.class);
    }

    public static InsurranceORM getInstance(){
        if(instance == null)
            instance = new InsurranceORM();

        return instance;
    }
}
