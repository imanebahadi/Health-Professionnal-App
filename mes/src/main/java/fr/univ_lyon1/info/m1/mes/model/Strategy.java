package fr.univ_lyon1.info.m1.mes.model;
import fr.univ_lyon1.info.m1.mes.Controllers.*;
import java.util.List;
public abstract class Strategy {
    protected String label;

    public Strategy(String  label) {
        this.label = label;
    }

    public abstract Patient findPatient(MES mes, String text);
}