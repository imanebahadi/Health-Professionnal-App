package fr.univ_lyon1.info.m1.mes.model;
import fr.univ_lyon1.info.m1.mes.Controllers.*;
import java.util.List;
public class PrefixStrategy extends Strategy {

    public PrefixStrategy(String label) {
        super(label);
    }

    @Override
    public String toString() {
        return label;
    }

    @Override
    public Patient findPatient(MES mes, String text) {
        return mes.getPatients().stream().filter(p -> p.getName().contains(text)).findFirst().orElse(null);
    }
}