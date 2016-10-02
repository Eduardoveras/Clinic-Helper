/**
 * Created by Eduardo veras on 25-Sep-16.
 */
package Service;


import Entity.Patient;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class PatientORM extends GenericORMService<Patient>{
    private static PatientORM instance;

    private PatientORM(){
        super(Patient.class);
    }

    public static PatientORM getInstance(){
        if(instance == null)
            instance = new PatientORM();

        return instance;
    }

    // SPecialized Functions
    public static Patient findPatientByIdentificationCard(String identificationNumber){
        EntityManager em = GetEntityManager();

        TypedQuery<Patient> query = em.createQuery("select p from Patient as p where p.patientId = '" + identificationNumber + "'", Patient.class);

        List<Patient> patients = query.getResultList();

        return patients.get(0);
    }

    public static Patient findRegisteredPatientByFLTEFields(String firstName, String lastName, String telephone, String email){
        EntityManager em = GetEntityManager();

        TypedQuery<Patient>  query = em.createQuery("select p from Patient as p where p.patientName='" + firstName
                + "' and p.patientLastName = '" + lastName
                + "' and p.patientTelephoneNumber = '" + telephone
                + "' and p.patientEmail = '" + email + "'", Patient.class);

        return query.getSingleResult();
    }

    public static List<Patient> generateListOfPatientsThatMatchRegEx(String regex){
        String[] expresions = regex.split(" ");
        EntityManager em;
        List<Patient> candidates = new ArrayList<Patient>();

        for (String exp:
             expresions) {
            em = GetEntityManager();
            // Searching by first name
            TypedQuery<Patient> queryFirstName = em.createQuery("select p from Patient as p where p.patientName = '" + exp + "'", Patient.class);
            // Searching by last name
            TypedQuery<Patient> queryLastName = em.createQuery("select p from Patient as p where p.patientLastName = '" + exp + "'", Patient.class);
            candidates = stitchIntoLargerList(candidates, queryFirstName.getResultList(), queryLastName.getResultList());
        }

        return (candidates.size() != 0) ? candidates : null;
    }

    // Auxiliary Functions
    private static List<Patient> stitchIntoLargerList(List<Patient> head, List<Patient> middle, List<Patient> tail){
        // Removing duplicates already found inside of head
        middle = removeDuplicatesFromSubmittedLists(middle, head);
        tail = removeDuplicatesFromSubmittedLists(tail, head);

        // Removing duplicates found in both the middle and the tail
        middle = removeDuplicatesFromSubmittedLists(middle, tail);

        // Adding any new patients left in the middle list
        if(middle.size() != 0)
            for (int i = 0; i < middle.size(); i++)
                head.add(middle.get(i));

        // Adding any new patients left in the tail list
        if(tail.size() != 0)
            for (int i = 0; i < tail.size(); i++)
                head.add(tail.get(i));

        return head;
    }

    private static List<Patient> removeDuplicatesFromSubmittedLists(List<Patient> firstList, List<Patient> secondList){
        // Creating a copy of the first list to filter through
        List<Patient> newFirstList = firstList;
        int index = 0;

        for (Patient f:
             firstList) {
            for (Patient s:
                 secondList) {
                if (f.getPatientId().equals(s.getPatientId())){
                    // removing all instances of duplicates in the first list
                    newFirstList.remove(index);
                    break;
                }
            }
            index++;
        }

        return newFirstList;
    }
}
