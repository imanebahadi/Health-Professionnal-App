package fr.univ_lyon1.info.m1.mes.view;

import fr.univ_lyon1.info.m1.mes.Controllers.Controller;
import fr.univ_lyon1.info.m1.mes.model.Message;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

public class MessageView {

    private VBox root = new VBox();
    private Scene scene;
    int i = 0;
    private final Button add = new Button("Send");
    final Label messageL = new Label("Message: ");
    final TextField messageT = new TextField();


    private Message message;
    private Controller controller;

    public MessageView(Message message, Controller controller) {
        this.message = message;
        this.controller = controller;
        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Messagerie");
        newWindow.show();
        start(newWindow);
        initChatBox();
    }

    public void start(Stage stage) {

        initChatBox();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        scrollPane.setContent(root);

        root.getChildren().addAll(new HBox(messageL, messageT), add);
        root.setPadding(new Insets(40, 40, 40, 40));

        scene = new Scene(scrollPane, 400, 400);
        stage.setScene(scene);
        stage.show();

    }
    private void initChatBox() {

        add.disableProperty()
                .bind(messageT.textProperty().isEmpty());
        add.setOnAction(evt -> {
            if (i == 0) {
                Message msg = controller.addMessage(messageT.getText());

                String n = controller.getMessageFromPatient(msg);
                final Label msgT = new Label(n);
                msgT.setStyle("-fx-border-color: pink;\n"
                        + "-fx-border-insets: 5;\n"
                        + "-fx-padding: 5;\n"
                        + "-fx-border-width: 1;\n");
                HBox boxSender = new HBox(msgT);
                boxSender.setAlignment(Pos.CENTER_RIGHT);
                root.getChildren().addAll(boxSender);
                i++;
            } else if (i == 1) {
                Message msg = controller.addMessage(messageT.getText());
                String n = controller.getMessageFromPatient(msg);
                final Label msgT = new Label(n);
                msgT.setStyle("-fx-border-color: blue;\n"
                        + "-fx-border-insets: 5;\n"
                        + "-fx-padding: 5;\n"
                        + "-fx-border-width: 1;\n");

                root.getChildren().addAll(msgT);
                i = 0;
            }
        });

    }
}
