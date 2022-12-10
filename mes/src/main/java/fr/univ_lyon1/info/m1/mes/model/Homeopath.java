package fr.univ_lyon1.info.m1.mes.model;


public class Homeopath extends HealthProfessional {
    public Homeopath(final String name, final MES mes) {
        super(name, mes);
    }

    public static HomeopathBuilder builder() {
        return new HomeopathBuilder();
    }

    public static class HomeopathBuilder extends HealthProfessionalBuilder<HomeopathBuilder> {

        public HomeopathBuilder() { }

        @Override
        public Homeopath build() {
            return new Homeopath(getName(), getMes());
        }
    }
}
