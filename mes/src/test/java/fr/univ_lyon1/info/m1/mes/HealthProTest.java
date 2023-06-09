package fr.univ_lyon1.info.m1.mes;

import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Patient.PatientBuilder;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HealthProTest {

    MES model = new MES();

    @Test

    public void HealthProfessionalName() {
        // Given
        HealthProfessional hp = new HealthProfessional("Dr. Smith", model);

        // When
        String name = hp.getName();

        // Then
        assertThat(name, is("Dr. Smith"));
    }

    @Test
    /**
     * Test addPrescription, and demonstrate advanced Hamcrest assertions.
     */
    public void GetPrescriptionTest() {
        // Given
        HealthProfessional hp = new HealthProfessional("Dr. Smith", model);
        PatientBuilder pb = new PatientBuilder("Alice","20123456789012");
        Patient p = pb.build();
        p.addPrescription(hp, "Do some sport");

        // When
        List<Prescription> prescriptions = hp.getPrescriptions("20123456789012");

        // Then
        assertThat(prescriptions, hasItem(
            hasProperty("content", equalTo("Do some sport"))));
    }

    @Test
    /**
     * Not-so-relevant test, mostly another example of advanced assertion. More
     * relevant things to test: play with several Patients, check that a
     * prescription made for one patient doesn't apply to the other, etc.
     */
    public void GetNotPrescriptionTest() {
        // Given
        HealthProfessional hp = new HealthProfessional("Dr. Smith", model);
        PatientBuilder pb = new PatientBuilder("Alice","20123456789012");
        Patient p = pb.build();
        p.addPrescription(hp, "Eat fruits");

        // When
        List<Prescription> prescriptions = hp.getPrescriptions("20123456789012");

        // Then
        assertThat(prescriptions, not(
            hasItem(
                hasProperty("content", equalTo("Do some sport")))));
    }

}
