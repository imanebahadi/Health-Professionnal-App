package fr.univ_lyon1.info.m1.mes.model;

public class NameStrategy extends Strategy {
    public NameStrategy(final String label) {
        super(label);
    }

    @Override
    public String toString() {
        return getLabel();
    }

    @Override
    public Patient findPatient(final MES mes, final String text) {
        return mes.getPatients().stream().filter(p -> p.getName().equals(text)).findFirst()
                .orElse(null);
    }
}
