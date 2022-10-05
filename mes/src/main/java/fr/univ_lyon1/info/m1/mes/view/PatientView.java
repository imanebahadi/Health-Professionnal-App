package fr.univ_lyon1.info.m1.mes.view;

import fr.univ_lyon1.info.m1.mes.Controllers.Controller;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import fr.univ_lyon1.info.m1.mes.model.PrescriptionObserver;
import fr.univ_lyon1.info.m1.mes.utils.EasyClipboard;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;

public class PatientView implements PrescriptionObserver {
    private final Pane pane = new VBox();
    private final Patient patient;
    private Pane prescriptionPane = new VBox();
    private final Controller controller;

    public PatientView(final Patient patient, final Controller controller) {
        this.patient = patient;
        this.controller = controller;
        controller.registerViewObserver(this, patient);

        pane.setStyle("-fx-border-color: gray;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-padding: 5;\n"
                + "-fx-border-width: 1;\n");
        final Label l = new Label(patient.getName());
        final Button bSSID = new Button("📋");
        bSSID.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                EasyClipboard.copy(patient.getSSID());
            }
        });
        final HBox nameBox = new HBox();
        nameBox.getChildren().addAll(l, bSSID);
        pane.getChildren().addAll(nameBox, prescriptionPane);
        showPrescriptions();
    }

    void showPrescriptions() {
        prescriptionPane.getChildren().clear();
        prescriptionPane.getChildren().add(new Label("Prescriptions:\n"));
        List<Prescription> listPrescription = controller.getPatientPrescriptions(patient);
        for (final Prescription pr : patient.getPrescriptions()) {
            prescriptionPane.getChildren().add(new Label("- From "
                    + pr.getHealthProfessional().getName()
                    + ": " + pr.getContent()));
        }
    }

    public Pane asPane() {
        return pane;
    }

    private void refreshPatientPrescriptions() {
        showPrescriptions();
    }

    @Override
    public void update() {
        refreshPatientPrescriptions();
    }

}
