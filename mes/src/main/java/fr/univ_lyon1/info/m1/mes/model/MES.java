package fr.univ_lyon1.info.m1.mes.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MES {


    private static final List<HealthProfessional> HEALTHPROFESSIONALS = new ArrayList<>(); 
    //static bon ?

    private static final  Map<String, Patient> REGISTRY = new HashMap<>();

    public Patient getPatient(final String ssID) {
        
        return REGISTRY.get(ssID);
    }

    /*public Patient createPatient(final String name, final String ssID) {
        final Patient p = new Patient(name, ssID);
        registry.put(ssID, p);
        return p;
    }*/

    public Map<String, Patient> getRegistry() {
        return REGISTRY;
    }

    public void addHealthProfessional(final HealthProfessional hp) {
        HEALTHPROFESSIONALS.add(hp);
    }

    public List<Patient> getPatients() {
        return new ArrayList<>(REGISTRY.values());
    }

    public List<HealthProfessional> getHealthProfessional() {
        return HEALTHPROFESSIONALS;
    }

    /*public void createExampleConfiguration() {       
       

        final Patient a = createPatient("Alice Foo", "299010212345678");
        final Patient b = createPatient("Bob Bar", "199010212345678");
        createPatient("Charles Boz", "102020212345678");
        final HealthProfessional w = new HealthProfessional("Dr. Who", this);
        final HealthProfessional s = new Dentist("Dr. Strange", this);
        new Homeopath("Dr. Hahnemann", this);
        new NonSpecialise("Dr. David", this);
        new Ophtalmologue("Dr Smith", this);
        a.addPrescription(w, "One apple a day");
        a.addPrescription(w, "Sport twice a week");
        b.addPrescription(w, "Whatever placebo, you're not sick");
        b.addPrescription(s, "Snake oil");

    }*/


}
