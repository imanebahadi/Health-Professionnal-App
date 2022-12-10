package fr.univ_lyon1.info.m1.mes.view;

import fr.univ_lyon1.info.m1.mes.Controllers.Controller;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Message;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

import java.util.ArrayList;
import java.util.List;

public class JfxView {
    private Pane patients = new VBox();

    private Pane patientForm = new VBox();

    private Pane healthPro = new VBox();

    private List<PatientView> listPatientView;
    private List<HealthProfessionalView> listHPView;

    private Controller controller;

    /**
     * Create the main view of the application.
     */
    public JfxView(final Controller controller, final Stage stage,
                   final int width, final int height) {
        this.controller = controller;
        controller.attachToJfxView(this);
        listPatientView = new ArrayList<>();
        listHPView = new ArrayList<>();
        // Name of window
        stage.setTitle("Mon Espace Santé");

        final HBox root = new HBox(10);

        createPatientsWidget();
        root.getChildren().add(patients);

        createHPWidget();
        root.getChildren().add(healthPro);

        HBox.setHgrow(patients, Priority.SOMETIMES);
        HBox.setHgrow(healthPro, Priority.ALWAYS);

        // Create a ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        scrollPane.setContent(root);

        // Everything's ready: add it to the scene and display it
        final Scene scene = new Scene(scrollPane, width, height);
        stage.setScene(scene);
        stage.show();
    }

    private Pane createHPWidget() {
        for (HealthProfessional p : controller.getListHP()) {
            HealthProfessionalView hpv = new HealthProfessionalView(p, controller);
            listHPView.add(hpv);
            healthPro.getChildren().add(hpv.asPane());
        }
        return healthPro;
    }

    private void createPatientsWidget() {
        patients.getChildren().clear();
        for (Patient p : controller.getListPatients()) {
            final PatientView patientView = new PatientView(p, controller);
            listPatientView.add(patientView);
            patients.getChildren().add(patientView.asPane());
        }
        final Label nameL = new Label("Name: ");
        final TextField nameT = new TextField();
        final TextField messageT = new TextField();
        final Label ssIDL = new Label("ssID: ");
        final TextField ssIDT = new TextField();
        final Button newP = new Button("New");
        
        final Button message = new Button("Message");
        // disable New button if nameT or ssIDL is null
        newP.disableProperty()
                .bind(nameT.textProperty().isEmpty().or(ssIDT.textProperty().isEmpty()));
        patients.getChildren().addAll(
                new HBox(nameL, nameT),
                new HBox(ssIDL, ssIDT),
                newP, message);

        newP.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * @param event
             */
            @Override
            public void handle(final ActionEvent event) {
                final Patient pat = controller.addPatient(nameT.getText(), ssIDT.getText());
                final PatientView patientView =
                        new PatientView(pat, controller); //crée un panel pr un patient
                listPatientView.add(patientView);
            }
        });

        message.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                Message msg = controller.addMessage(messageT.getText());
                new MessageView(msg,controller);
            }
        });

    }


    public void addPatientView(final Patient patient) {
        final PatientView patientView = new PatientView(patient, controller);
        listPatientView.add(patientView);
        patients.getChildren().add(patientView.asPane());
    }

    public void addButton() {
        final Button Message = new Button("Message");
    }
}
