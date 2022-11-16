package fr.univ_lyon1.info.m1.mes.model;

public class Init {

    public Init() {

        MES mes = new MES();

        final HealthProfessional hp = HealthProfessional.builder()
        .name("Dr. Who")
        .mes(mes).build();

        final Dentist hp2 = Dentist.builder()
        .name("Dr. Strange")
        .mes(mes).build();
        hp2.addProposedMedicine("Don't eat for one hour", "if anesthesia");

        final Homeopath hm = Homeopath.builder()
        .name("Dr. Hahnemann")
        .mes(mes).build();
        hm.addProposedMedicine("Natrum Muriaticum 30CH", "allergies");
        hm.addProposedMedicine("Sucre 200K", "si grippe");

        final NonSpecialise ns = NonSpecialise.builder()
        .name("Dr. David")
        .mes(mes).build();

        final Ophtalmologue o = Ophtalmologue.builder()
        .name("Dr Smith")
        .mes(mes).build();
        o.addProposedMedicine("Lunette de repos", "avant de dormir");
        o.addProposedMedicine("Boire de l'eau", "1,5L par jour minimum");

        final Patient patient1 = Patient.builder()
        .name("Alice Foo")
        .ssID("299010212345678").build();
        patient1.addPrescription(hp, "One apple a day");
        patient1.addPrescription(hp, "Sport twice a week");

        final Patient patient2 = Patient.builder()
        .name("Bob Bar")
        .ssID("199010212345678").build();
        patient2.addPrescription(hp, "Whatever placebo, you're not sick");
        patient2.addPrescription(hp2, "Snake oil");

        final Patient patient3 = Patient.builder()
        .name("Charles Boz")
        .ssID("102020212345678").build();

      


    }
}
