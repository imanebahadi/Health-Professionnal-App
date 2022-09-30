package fr.univ_lyon1.info.m1.mes.model;


public class Homeopath extends HealthProfessional {
    public Homeopath(final String name, final MES mes) {
        super(name, mes);
        addProposedMedicine("Natrum Muriaticum 30CH");
        addProposedMedicine("Sucre 200K");
    }
}
