package fr.univ_lyon1.info.m1.mes.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MES {

    private static final List<HealthProfessional> HEALTHPROFESSIONALS = new ArrayList<>();
    private static final  Map<String, Patient> REGISTRY = new HashMap<>();

    public Patient getPatient(final String ssID) { return REGISTRY.get(ssID); }
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

}
