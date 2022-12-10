package fr.univ_lyon1.info.m1.mes.model;

import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class HealthProfessional {
    
    private final String name;
    private final MES mes;

    private final  Map<String, String> proposedMedicinesIndications = new HashMap<>();

    public static HealthProfessionalBuilder<?> builder() {
        return new HealthProfessionalBuilder<>();
    }

    public HealthProfessional(final String name, final MES mes) {
        this.name = name;
        this.mes = mes;
        addProposedMedicine("Paracetamol", "Pain-Killer");
        mes.addHealthProfessional(this);
    }

    public Map<String, String> getMedicineIndication() {
        return proposedMedicinesIndications;
    }

    protected void addProposedMedicine(final String predefMedicine, final String indication) {
        this.proposedMedicinesIndications.put(predefMedicine, indication); 
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

    public static class HealthProfessionalBuilder<T extends HealthProfessionalBuilder<T>> {
        private String name;
        private MES mes;

        public HealthProfessionalBuilder() { }

        public HealthProfessionalBuilder(final String name, final MES mes) {
            this.name = name;
            this.mes = mes;
        }

        public T name(final String name) {
            this.name = name;
            return (T) this;
        }

        public T mes(final MES mes) {
            this.mes = mes;
            return (T) this;
        }
            
        public String getName() {
            return name;
        }

        public MES getMes() {
            return mes;
        }

        public HealthProfessional build() {
            return new HealthProfessional(name, mes);
        }
    }
}

