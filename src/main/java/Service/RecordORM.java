/**
 * Created by Djidjelly Siclait on 9/25/2016.
 */
package Service;

import Entity.Record;

public class RecordORM extends GenericORMService<Record>{
    private static RecordORM instance;

    private RecordORM(){
        super(Record.class);
    }

    public static RecordORM getInstance(){
        if(instance == null)
            instance = new RecordORM();

        return instance;
    }
}
