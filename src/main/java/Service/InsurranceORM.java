/**
 * Created by Djidjelly Siclait on 10/2/2016.
 */
package Service;

import Entity.Insurrance;

public class InsurranceORM extends GenericORMService<Insurrance>{
    private static InsurranceORM instance;

    private InsurranceORM(){
        super(Insurrance.class);
    }

    public static InsurranceORM getInstance(){
        if(instance == null)
            instance = new InsurranceORM();

        return instance;
    }
}
