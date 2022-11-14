package fr.univ_lyon1.info.m1.mes.model;

import java.util.ArrayList;
import java.util.List;

public class HealthProfessional {
    
    private final String name;
    private final MES mes;

    private final List<String> proposedMedicines;

    public static HealthProfessionalBuilder<?> builder() {
        return new HealthProfessionalBuilder<>();
    }

    public HealthProfessional(final String name, final MES mes) {
        this.name = name;
        this.mes = mes;
        this.proposedMedicines = new ArrayList<>();
        addProposedMedicine("Paracetamol");
        mes.addHealthProfessional(this);
    }

    public List<String> getProposedMedicines() {
        return proposedMedicines;
    }

    protected void addProposedMedicine(final String medicine) {
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

    public static class HealthProfessionalBuilder<T extends HealthProfessionalBuilder<T>> {
        private String name;
        private MES mes;

        public HealthProfessionalBuilder() { }

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

