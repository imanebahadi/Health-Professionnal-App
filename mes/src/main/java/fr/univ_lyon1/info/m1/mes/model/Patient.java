package fr.univ_lyon1.info.m1.mes.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Patient implements PrescriptionObservable {
    private final List<PrescriptionObserver> prescriptionObserverList = new ArrayList<>();
    private final List<Prescription> prescriptions = new ArrayList<>();
    private final String name;
    private final String ssID;

    public Patient(final String name, final String ssID) {
        this.name = name;
        this.ssID = ssID;
    }

    public List<Prescription> getPrescriptions(final HealthProfessional hp) {
        return prescriptions.stream()
                .filter(p -> p.getHealthProfessional() == hp)
                .collect(Collectors.toList());
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public static PatientBuilder builder() {
        return new PatientBuilder();
    }

    public void addPrescription(final HealthProfessional hp, final String content) {
        prescriptions.add(new Prescription(hp, content));
        notifyObservers();
    }

    public void removePrescription(final Prescription p) {
        prescriptions.remove(p);
        notifyObservers();
    }

    public String getName() {
        return name;
    }

    public String getSSID() {
        return ssID;
    }

    @Override
    public void register(final PrescriptionObserver observer) {
        prescriptionObserverList.add(observer);
    }

    @Override
    public void unregister(final PrescriptionObserver observer) {
        prescriptionObserverList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (PrescriptionObserver observer : prescriptionObserverList) {
            observer.update();
        }
    }

    public static class PatientBuilder {
        private String name;
        private String ssID;
        private MES mes = new MES();

        public PatientBuilder(final String name, final String ssID) {
            this.name = name;
            this.ssID = ssID;
        }

        public PatientBuilder() { }
        
        public PatientBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public PatientBuilder ssID(final String ssID) {
            this.ssID = ssID;
            return this;
        }

        public String getName() {
                return name;
        }

        public String getssID() {
                return ssID;
        }

        public Patient build() {
            Patient p = new Patient(name, ssID); 
            mes.getRegistry().put(ssID, p);           
            return p;
        }
    }
}
