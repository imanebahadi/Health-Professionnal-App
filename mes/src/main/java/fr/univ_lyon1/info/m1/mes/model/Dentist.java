package fr.univ_lyon1.info.m1.mes.model;

public class Dentist extends HealthProfessional {

    public Dentist(final String name, final MES mes) {
        super(name, mes);
    }

    public static DentistBuilder builder() {
        return new DentistBuilder();
    }


public static class DentistBuilder extends HealthProfessionalBuilder<DentistBuilder> {

        public DentistBuilder() { }

        @Override
        public Dentist build() {
            return new Dentist(getName(), getMes());
        }
    }
}
