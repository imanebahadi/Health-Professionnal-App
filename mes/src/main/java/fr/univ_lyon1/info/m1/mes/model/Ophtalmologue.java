package fr.univ_lyon1.info.m1.mes.model;

public class Ophtalmologue extends HealthProfessional {
    public Ophtalmologue(String name, MES mes) {
        super(name, mes);
        addProposedMedicine("Lunette de repos");
        addProposedMedicine("Boire de l'eau");
    }
}

