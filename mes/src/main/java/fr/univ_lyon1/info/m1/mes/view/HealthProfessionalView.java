package fr.univ_lyon1.info.m1.mes.view;


import java.util.ArrayList;
import java.util.List;

import fr.univ_lyon1.info.m1.mes.Controllers.Controller;
import fr.univ_lyon1.info.m1.mes.model.*;
import fr.univ_lyon1.info.m1.mes.utils.EasyAlert;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HealthProfessionalView implements PrescriptionObserver {
    private final VBox pane = new VBox();
    private HealthProfessional healthProfessional;
    private String selectedPatientSSID;
    private final VBox prescriptions = new VBox();

    private Patient patient=null;

    private final Controller controller;

    public HealthProfessionalView(final HealthProfessional hp, Controller controller) {
        this.controller=controller;

        pane.setStyle("-fx-border-color: gray;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-padding: 5;\n"
                + "-fx-border-width: 1;\n");
        this.healthProfessional = hp;
        final Label l = new Label(hp.getName());
        pane.getChildren().add(l);
        final HBox search = new HBox();
        final TextField t = new TextField();
        final Button b = new Button("Search");
        search.getChildren().addAll(t, b);
        pane.getChildren().addAll(search, prescriptions);
        final EventHandler<ActionEvent> ssHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                final String text = t.getText().trim();
                if (text.equals("")) {
                    return; // Do nothing
                }
                selectedPatientSSID = text;
                Patient lastPatient=patient;
                patient = controller.findPatient(healthProfessional,selectedPatientSSID);
                if (patient!=null){
                    controller.registerViewObserver(HealthProfessionalView.this,patient);
                    if (lastPatient!=null)
                        controller.unregisterViewObserver(HealthProfessionalView.this,lastPatient);
                    showPrescriptions();
                }
                t.setText("");
                t.requestFocus();
            }
        };
        b.setOnAction(ssHandler);
        t.setOnAction(ssHandler);

        pane.getChildren().add(new Label("Prescribe"));
        final HBox addPrescription = new HBox();
        final TextField tp = new TextField();
        final Button bp = new Button("Add");
        addPrescription.getChildren().addAll(tp, bp);
        pane.getChildren().add(addPrescription);
        final HealthProfessionalView parent = this;
        final EventHandler<ActionEvent> prescriptionHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                final String text = tp.getText().trim();
                if (text.equals("")) {
                    return; // Do nothing
                }
                tp.setText("");
                tp.requestFocus();
                parent.prescribe(text);
            }
        };

        List<String> predefPrescr = controller.getPredefMedicines(hp);
        for (final String p : predefPrescr) {
            final Button predefPrescrB = new Button(p);
            predefPrescrB.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    parent.prescribe(p);
                }
            });
            pane.getChildren().add(predefPrescrB);
        }
        tp.setOnAction(prescriptionHandler);
        bp.setOnAction(prescriptionHandler);
    }
    
    void prescribe(final String prescription) {
        if (selectedPatientSSID==null)
            return;
            if (!controller.addPrescription(healthProfessional,selectedPatientSSID,prescription))
        {
            EasyAlert.alert("Please select a patient first");
            return;
        }
        showPrescriptions();
    }

    void showPrescriptions() {
        prescriptions.getChildren().clear();

        if (patient == null) {
            prescriptions.getChildren().add(new Label(
                "Use search above to see prescriptions"));
            return;
        }
        prescriptions.getChildren().add(new Label(
            "Prescriptions for " + patient.getName()));

        List<Prescription> listPrescriptions = controller.getPatientPrescriptionsByHP(patient,healthProfessional);
        for (final Prescription pr : listPrescriptions) {
            final HBox pView = new HBox();
            final Label content = new Label(
                    "- " + pr.getContent());
            final Button removeBtn = new Button("x");
            removeBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    controller.removePrescription(patient,pr);
                    pView.getChildren().remove(content);
                    pView.getChildren().remove(removeBtn);
                }
                
            });
            pView.getChildren().addAll(content, removeBtn);
            prescriptions.getChildren().add(pView);
        }
    }

    private void refreshHPPrescriptions(){
        if (selectedPatientSSID!=null) showPrescriptions();
    }

    public Pane asPane() {
        return pane;
    }

    @Override
    public void update() {
        refreshHPPrescriptions();
    }

}
