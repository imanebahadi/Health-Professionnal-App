package fr.univ_lyon1.info.m1.mes.model;
public class SSIDStrategy extends Strategy {

    public SSIDStrategy(final String label) {
        super(label);
    }

    @Override
    public String toString() {
        return getLabel();
    }

    @Override
    public Patient findPatient(final MES mes, final String text) {
        return mes.getPatient(text);
    }
}
