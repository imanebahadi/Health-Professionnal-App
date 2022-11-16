package fr.univ_lyon1.info.m1.mes;

import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.*;

class MESTest {

    private static Patient patient;
    private static MES mes;
    private static HealthProfessional hp;

    @BeforeAll
    static void setUp() {
        //GIVEN
        mes = new MES();
        hp = new HealthProfessional("Smith", mes);
        patient = mes.createPatient("alice",
                "1234"); // on creer un patient qui servira pour tout les tests
    }

    @Test
    void getPatientTest() {
        assertThat(mes.getPatients(), hasItem(patient));
    }

    @Test
    void createPatient() {
    }

    @Test
    void addHealthProfessional() {
    }

    @Test
    void getPatients() {
    }

    @Test
    void getHealthProfessional() {
    }
}