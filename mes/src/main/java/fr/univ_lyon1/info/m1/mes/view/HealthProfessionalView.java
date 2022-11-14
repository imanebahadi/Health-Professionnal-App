package fr.univ_lyon1.info.m1.mes.view;


import java.util.List;


import fr.univ_lyon1.info.m1.mes.Controllers.Controller;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.NameStrategy;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.PrefixStrategy;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import fr.univ_lyon1.info.m1.mes.model.PrescriptionObserver;
import fr.univ_lyon1.info.m1.mes.model.SSIDStrategy;
import fr.univ_lyon1.info.m1.mes.model.Strategy;
import fr.univ_lyon1.info.m1.mes.utils.EasyAlert;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ComboBox;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class HealthProfessionalView implements PrescriptionObserver {
    private final VBox pane = new VBox();
    private final HealthProfessional healthProfessional;
    private String selectedPatientSSID;
    private final VBox prescriptions = new VBox();

    private Patient patient = null;

    private final Controller controller;
                                    //modele
    public HealthProfessionalView(final HealthProfessional hp, final Controller controller) {
        this.controller = controller;

        pane.setStyle("-fx-border-color: gray;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-padding: 5;\n"
                + "-fx-border-width: 1;\n");

        this.healthProfessional = hp;
        final Label l = new Label(hp.getName());
        final HBox strategy = new HBox();
        final Label s = new Label("Strategy: ");
        final ComboBox cb;
        cb = new ComboBox();
        ObservableList<Strategy> listStrategy =
                FXCollections.observableArrayList(new SSIDStrategy("By SSID"),
                        new NameStrategy("By Name"), new PrefixStrategy("By Prefix"));
        cb.setItems(listStrategy);
        cb.getSelectionModel().select(0);
        strategy.getChildren().addAll(s, cb);
        pane.getChildren().addAll(l, strategy);
        final HBox search = new HBox();
        final TextField t = new TextField();
        final Button b = new Button("Search");
        search.getChildren().addAll(t, b);
        pane.getChildren().addAll(search, prescriptions);
        final Label err = new Label("Patient not found !!!");
        String errorMessage = String.format("-fx-text-fill: RED;");
        err.setStyle(errorMessage);
        pane.getChildren().add(err);
        err.setVisible(false);
        final EventHandler<ActionEvent> ssHandler = event -> {
            final String text = t.getText().trim();
            if (text.equals("")) {
                return; // Do nothing
            }
            Patient lastPatient = patient;
            patient = controller.findPatient(((Strategy) cb.getValue()), text);
            try {
                selectedPatientSSID = patient.getSSID();
                err.setVisible(false);
                controller.registerViewObserver(HealthProfessionalView.this, patient);
                if (lastPatient != null) {
                    controller.unregisterViewObserver(HealthProfessionalView.this, lastPatient);
                }
                showPrescriptions();
            } catch (NullPointerException e) {
                System.err.println("Patient '" + text + "' not found !!!");
                err.setVisible(true);
            }
            t.setText("");
            t.requestFocus();
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
        final EventHandler<ActionEvent> prescriptionHandler = event -> {
            final String text = tp.getText().trim();
            if (text.equals("")) {
                return; // Do nothing
            }
            tp.setText("");
            tp.requestFocus();
            parent.prescribe(text);
        };

        List<String> predefPrescr = controller.getPredefMedicines(hp);
        if (predefPrescr != null) {
        for (
                final String p : predefPrescr) {
            final Button predefPrescrB = new Button(p);
            predefPrescrB.setOnAction(event -> parent.prescribe(p));
            pane.getChildren().add(predefPrescrB);
        }
        }
        
        tp.setOnAction(prescriptionHandler);
        bp.setOnAction(prescriptionHandler);
    }

    void prescribe(final String prescription) {
        if (selectedPatientSSID == null) {
            return;
        }
        if (!controller.addPrescription(healthProfessional, selectedPatientSSID, prescription)) {
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

        List<Prescription> listPrescriptions =
                controller.getPatientPrescriptionsByHP(patient, healthProfessional);
        for (final Prescription pr : listPrescriptions) {
            final HBox pView = new HBox();
            final Label content = new Label(
                    "- " + pr.getContent());
            final Button removeBtn = new Button("x");
            removeBtn.setOnAction(event -> {
                controller.removePrescription(patient, pr);
                pView.getChildren().remove(content);
                pView.getChildren().remove(removeBtn);
            });
            pView.getChildren().addAll(content, removeBtn);
            prescriptions.getChildren().add(pView);
        }
    }

    private void refreshHPPrescriptions() {
        if (selectedPatientSSID != null) {
            showPrescriptions();
        }
    }

    public Pane asPane() {
        return pane;
    }

    @Override
    public void update() {
        refreshHPPrescriptions();
    }

}
