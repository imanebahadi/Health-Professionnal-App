package fr.univ_lyon1.info.m1.mes;

import fr.univ_lyon1.info.m1.mes.Controllers.Controller;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import fr.univ_lyon1.info.m1.mes.model.SSIDStrategy;
import fr.univ_lyon1.info.m1.mes.model.Strategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ControllerTest {

    private MES mes;
    private Controller controller;

    @BeforeEach
    void setUp() {
        mes = new MES();
        controller = new Controller(mes);
    }

    @AfterEach
    void tearDown() {
        //permet de liberer les ressources
        mes = null;
        controller = null;
    }

    @Test
    void getListPatients() {
        Patient patient1 = new Patient.PatientBuilder("alice", "1234").build();
        Patient patient2 = new Patient.PatientBuilder("Bob", "1678").build();
        assertThat(controller.getListPatients(),
                containsInAnyOrder(Arrays.asList(patient1, patient2).toArray()));
    }

    @Test
    void addPatient() {
        assertThat(controller.addPatient("marine", "1456"), is(notNullValue()));
    }

    @Test
    void getListHP() {
        HealthProfessional hp1 =
                new HealthProfessional.HealthProfessionalBuilder("dr Smith", mes).build();
        HealthProfessional hp2 =
                new HealthProfessional.HealthProfessionalBuilder("dr Who", mes).build();
        assertThat(controller.getListHP(), hasItems(hp1, hp2));
    }

    @Test
    void findPatient() {
        Strategy strategy = new SSIDStrategy("By SSID");
        new Patient.PatientBuilder("alice", "1234").build();
        assertThat(controller.findPatient(strategy, "1234"), is(notNullValue()));

    }

    @Test
    void getPatientPrescriptionsByHP() {
        HealthProfessional hp =
                new HealthProfessional.HealthProfessionalBuilder("dr S", mes).build();
        Patient patient = new Patient.PatientBuilder("alice", "1234").build();
        patient.addPrescription(hp, "Paracetamol");
        patient.addPrescription(hp, "Doliprane");
        assertThat(controller.getPatientPrescriptionsByHP(patient, hp).size(), is(2));

    }

    @Test
    void addPrescription() {
        HealthProfessional hp =
                new HealthProfessional.HealthProfessionalBuilder("dr M", mes).build();
        new Patient.PatientBuilder("alice", "1234").build();
        assertThat(controller.addPrescription(hp, "1234", "Paracétamol"), is(true));
    }

    @Test
    void getIndications() {
        HealthProfessional hp =
                new HealthProfessional.HealthProfessionalBuilder("dr P", mes).build();
        assertThat(controller.getIndications(hp), is(notNullValue()));
    }

    @Test
    void getPatientPrescriptions() {
        Patient patient = new Patient.PatientBuilder("alice", "1234").build();
        HealthProfessional hp =
                new HealthProfessional.HealthProfessionalBuilder("dr L", mes).build();
        patient.addPrescription(hp, "Doliprane");
        patient.addPrescription(hp, "Paracetamol");
        assertThat(controller.getPatientPrescriptions(patient).size(), is(2));
    }

    @Test
    void removePrescription() {
        Patient patient = new Patient.PatientBuilder("alice", "1234").build();
        HealthProfessional hp =
                new HealthProfessional.HealthProfessionalBuilder("dr K", mes).build();
        patient.addPrescription(hp, "Doliprane");
        patient.addPrescription(hp, "Paracetamol");
        List<Prescription> prescriptions = controller.getPatientPrescriptions(patient);
        controller.removePrescription(patient, prescriptions.get(prescriptions.size() - 1));
        assertThat(controller.getPatientPrescriptions(patient), not(hasItem(
                hasProperty("content", equalTo("Paracetamol")))));
    }
}