package fr.univ_lyon1.info.m1.mes.model;

import java.util.ArrayList;
import java.util.List;

public class HealthProfessional {
    private final String name;
    private final MES mes;

    private List<String> proposedMedicines;

    public HealthProfessional(final String name, final MES mes) {
        this.name = name;
        this.mes = mes;
        this.proposedMedicines=new ArrayList<>();
        this.proposedMedicines.add("Paracetamol");
        mes.addHealthProfessional(this);
    }

    public List<String> getProposedMedicines() {
        return proposedMedicines;
    }

    protected void addProposedMedicine(String medicine){
        this.proposedMedicines.add(medicine);
    }

    public String getName() {
        return name;
    }

    public Patient getPatient(final String ssID) {

        return mes.getPatient(ssID);
    }

    public List<Prescription> getPrescriptions(final String ssID) {
        return mes.getPatient(ssID).getPrescriptions(this);
    }
}

