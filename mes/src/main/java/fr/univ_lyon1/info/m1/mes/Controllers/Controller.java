package fr.univ_lyon1.info.m1.mes.Controllers;

import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Message;
import fr.univ_lyon1.info.m1.mes.model.Patient.PatientBuilder;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import fr.univ_lyon1.info.m1.mes.model.PrescriptionObserver;
import fr.univ_lyon1.info.m1.mes.model.Strategy;
import fr.univ_lyon1.info.m1.mes.view.JfxView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Controller {
    private final MES mes;
    private List<JfxView> jfxViews;
    private PrescriptionObserver observer;
    private Patient patient;

    public Controller(final MES mes) {
        this.mes = mes;
        this.jfxViews = new ArrayList<>();
    }

    public void registerViewObserver(final PrescriptionObserver observer, final Patient patient) {
        this.observer = observer;
        this.patient = patient;
        patient.register(observer);
    }

    public void unregisterViewObserver(final PrescriptionObserver observer, final Patient patient) {
        patient.unregister(observer);
    }

    public void attachToJfxView(final JfxView jfxView) {
        this.jfxViews.add(jfxView);
    }

    public List<Patient> getListPatients() {
        return mes.getPatients();
    }

    public Patient addPatient(final String name, final String ssID) {
        PatientBuilder pb = new PatientBuilder(name, ssID);
        Patient patient = pb.build();

        for (JfxView jfxView : jfxViews) {
            jfxView.addPatientView(patient);
        }
        return patient;
    }

    public List<HealthProfessional> getListHP() {
        return mes.getHealthProfessional();
    }

    public Patient findPatient(final Strategy strategy, final String text) {
        return strategy.findPatient(mes, text);
    }

    public List<Prescription> getPatientPrescriptionsByHP(final Patient p,
                                                          final HealthProfessional hp) {
        return p.getPrescriptions(hp);
    }

    public boolean addPrescription(final HealthProfessional healthProfessional,
                                   final String patientSSID, final String prescription) {
        Patient patient = healthProfessional.getPatient(patientSSID);
        if (patient == null) {
            return false;
        }
        patient.addPrescription(healthProfessional, prescription);
        return true;
    }

    public Map<String, String> getIndications(final HealthProfessional healthProfessional) {
        return healthProfessional.getMedicineIndication();
    }

    public List<Prescription> getPatientPrescriptions(final Patient p) {
        return p.getPrescriptions();
    }

    public void removePrescription(final Patient patient, final Prescription prescription) {
        patient.removePrescription(prescription);
    }

    public Message addMessage(final String message) {
        Message msg = new Message(message);
        msg.addMessage(message);
        return msg;
    }

    public String getMessageFromPatient(final Message msg) {
        return msg.messageFromPatient();
    }

}
