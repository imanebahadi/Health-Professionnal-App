package fr.univ_lyon1.info.m1.mes;


import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Patient.PatientBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class BuilderTest {

    @Test 
    void init() {
        MES mes = new MES();

        final HealthProfessional hp = HealthProfessional.builder()
        .name("test")
        .mes(mes).build();
        assertEquals("test", hp.getName());

        final Patient p = Patient.builder()
        .name("alice")
        .ssID("1234").build();
        assertEquals("alice", p.getName());
        assertEquals("1234", p.getSSID());
    }
}

