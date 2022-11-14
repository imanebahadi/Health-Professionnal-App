package fr.univ_lyon1.info.m1.mes;

import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.NameStrategy;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Patient.PatientBuilder;
import fr.univ_lyon1.info.m1.mes.model.PrefixStrategy;
import fr.univ_lyon1.info.m1.mes.model.SSIDStrategy;
import fr.univ_lyon1.info.m1.mes.model.Strategy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


class StrategyTest {
    private static MES mes;
    private Strategy strategy;

    @BeforeAll
    static void setUp(){
        mes = new MES();
        /*  mes.createPatient("alice","1876");
        mes.createPatient("pierre", "1790");
        mes.createPatient("tom", "1098");*/

        PatientBuilder pb = new PatientBuilder("alice","1876");
        pb.build();
        PatientBuilder pb2 = new PatientBuilder("pierre","1790");
        pb2.build();
        PatientBuilder pb3 = new PatientBuilder("tom","1098");
        pb3.build();
 
    }

    @Test
    void findPatientBySSID() {
        //WHEN
        strategy = new SSIDStrategy("By SSID");
        Patient patient = strategy.findPatient(mes,"1098");

        // THEN
        assertThat( patient.getSSID(),is("1098"));
    }
    @Test
    void findPatientByName() {
        //WHEN
        strategy = new NameStrategy("By Name");
        Patient patient = strategy.findPatient(mes,"alice");
        // THEN
        assertThat( patient.getName(),is("alice"));
    }
    @Test
    void findPatientByPrefix() {
        //WHEN
        strategy = new PrefixStrategy("By prefix");
        Patient patient = strategy.findPatient(mes,"al");
        // THEN
        assertThat( patient.getName(),startsWith("al"));
    }
}