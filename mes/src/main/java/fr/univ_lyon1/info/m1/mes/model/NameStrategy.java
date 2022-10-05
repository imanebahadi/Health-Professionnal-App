package fr.univ_lyon1.info.m1.mes.model;
import fr.univ_lyon1.info.m1.mes.Controllers.*;
import java.util.List;
public class NameStrategy extends Strategy {

    public NameStrategy(String label) {
        super(label);
    }

    @Override
    public String toString() {
        return label;
    }

    @Override
    public Patient findPatient(MES mes, String text) {
        return mes.getPatients().stream().filter(p -> p.getName().equals(text)).findFirst().orElse(null);
    }
}