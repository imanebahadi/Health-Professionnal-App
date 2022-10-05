package fr.univ_lyon1.info.m1.mes.model;


public class PrefixStrategy extends Strategy {

    public PrefixStrategy(final String label) {
        super(label);
    }

    @Override
    public String toString() {
        return getLabel();
    }

    @Override
    public Patient findPatient(final MES mes, final String text) {
        return mes.getPatients().stream().filter(p -> p.getName().contains(text)).findFirst()
                .orElse(null);
    }
}
