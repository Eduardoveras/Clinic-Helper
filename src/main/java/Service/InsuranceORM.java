/**
 * Created by Djidjelly Siclait on 10/2/2016.
 */
package Service;

import Entity.Insurance;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class InsuranceORM extends GenericORMService<Insurance>{
    private static InsuranceORM instance;

    private InsuranceORM(){
        super(Insurance.class);
    }

    public static InsuranceORM getInstance(){
        if(instance == null)
            instance = new InsuranceORM();

        return instance;
    }

    public static Insurance findRegisteredPatientsInsuranceInformation(String patientJascId){
        EntityManager em = GetEntityManager();

        TypedQuery<Insurance> query = em.createQuery("select i from Insurance as i where i.owner.jascId = '" + patientJascId + "'", Insurance.class);

        return query.getSingleResult();
    }
}
