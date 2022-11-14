package fr.univ_lyon1.info.m1.mes;

import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Patient.PatientBuilder;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class PatientTest {
    private static Patient patient;
    private static MES mes;
    private static HealthProfessional hp;

    @BeforeAll
    static void setUp() {
        //GIVEN
    mes = new MES();
    hp = new HealthProfessional("Smith",mes);
    
    PatientBuilder pb = new PatientBuilder("alice","1234");
    patient = pb.build();
    }

    @Test
    void addPrescriptionTest() {
        //WHEN
        patient.addPrescription(hp,"Paracétamol");
        List <Prescription> prescriptions = patient.getPrescriptions();
        // THEN
        assertThat(prescriptions, hasItem(
                hasProperty("content", equalTo("Paracétamol"))));


    }

    @Test
    void removePrescriptionTest() {
        //WHEN
        patient.addPrescription(hp,"Doliprane");
        List <Prescription> pres = patient.getPrescriptions();
        patient.removePrescription(pres.get(pres.size()-1));
        List <Prescription> prescriptions = patient.getPrescriptions();
        // THEN
        assertThat(prescriptions, not(hasItem(
                hasProperty("content", equalTo("Doliprane")))));


    }
}