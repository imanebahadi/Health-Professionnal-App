package fr.univ_lyon1.info.m1.mes.model;

public class NonSpecialise extends HealthProfessional {
    public NonSpecialise(final String name, final MES mes) {
        super(name, mes);
    }

    public static NonSpecialiseBuilder builder() {
        return new NonSpecialiseBuilder();
    }

    public static class NonSpecialiseBuilder extends 
                        HealthProfessionalBuilder<NonSpecialiseBuilder> {
            public NonSpecialiseBuilder() { }
            @Override
            public NonSpecialise build() {
                return new NonSpecialise(getName(), getMes());
            }
        }
}
