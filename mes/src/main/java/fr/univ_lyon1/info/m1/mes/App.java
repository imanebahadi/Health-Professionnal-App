package fr.univ_lyon1.info.m1.mes;

import fr.univ_lyon1.info.m1.mes.Controllers.Controller;
import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Init;
import fr.univ_lyon1.info.m1.mes.view.JfxView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class for the application (structure imposed by JavaFX).
 */
public class App extends Application {

    /**
     * With javafx, start() is called when the application is launched.
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final MES model = new MES();
        final Controller controller = new Controller(model);
        new Init();

        new JfxView(controller, stage, 600, 600);
        new JfxView(controller, new Stage(), 600, 600);
    }


    /**
     * A main method in case the user launches the application using
     * App as the main class.
     */
    public static void main(final String[] args) {
        Application.launch(args);
    }
}
