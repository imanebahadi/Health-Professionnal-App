package fr.univ_lyon1.info.m1.mes.model;

public class Ophtalmologue extends HealthProfessional {
    public Ophtalmologue(final String name, final MES mes) {
        super(name, mes);
    }

    public static OphtalmologueBuilder builder() {
        return new OphtalmologueBuilder();
    }
    public static class OphtalmologueBuilder extends
                        HealthProfessionalBuilder<OphtalmologueBuilder> {
        public OphtalmologueBuilder() { }

        @Override
        public Ophtalmologue build() {
            return new Ophtalmologue(getName(), getMes());
        }
    }
}


