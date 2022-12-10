package fr.univ_lyon1.info.m1.mes.model;
public abstract class Strategy {
    private final String label;

    public String getLabel() {
        return label;
    }

    public Strategy(final String label) {
        this.label = label;
    }

    public abstract Patient findPatient(MES mes, String text);
}
