package fr.univ_lyon1.info.m1.mes.Controllers;

import fr.univ_lyon1.info.m1.mes.model.*;
import fr.univ_lyon1.info.m1.mes.view.JfxView;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private final MES mes;
    private List<JfxView> jfxViews;

    public Controller(MES mes) {
        this.mes = mes;
        this.jfxViews=new ArrayList<>();
    }

    public void registerViewObserver(PrescriptionObserver observer, Patient patient){
        patient.register(observer);
    }

    public void unregisterViewObserver(PrescriptionObserver observer, Patient patient){
        patient.unregister(observer);
    }
    public void attachToJfxView(JfxView jfxView) {
        this.jfxViews.add(jfxView);
    }

    public List<Patient> getListPatients() {
        return mes.getPatients();
    }

    public Patient addPatient(String name, String ssID) {
        Patient patient= this.mes.createPatient(name, ssID);
        for(JfxView jfxView: jfxViews){
            jfxView.addPatientView(patient);
        }
        return patient;
    }

    public List<HealthProfessional> getListHP() {
        return mes.getHealthProfessional();
    }

    public Patient findPatient(HealthProfessional healthProfessional, String selectedPatientSSID) {
        return healthProfessional.getPatient(selectedPatientSSID);
    }

    public List<Prescription> getPatientPrescriptionsByHP(Patient p, HealthProfessional healthProfessional){
        return p.getPrescriptions(healthProfessional);
    }

    public boolean addPrescription(HealthProfessional healthProfessional,String patientSSID,String prescription){
        Patient patient= healthProfessional.getPatient(patientSSID);
        if (patient==null) return false;
        patient.addPrescription(healthProfessional, prescription);
            return true;
    }

    public List<String> getPredefMedicines(HealthProfessional healthProfessional){
        return healthProfessional.getProposedMedicines();
    }

    public List<Prescription> getPatientPrescriptions(Patient p){
        return p.getPrescriptions();
    }

    public void removePrescription(Patient patient,Prescription prescription) {
        patient.removePrescription(prescription);

    }


}
