import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * ClockApp extends Application from JavaFX.
 * It is responsible for starting the application, setting up the UI, and displaying the main JavaFX window.
 */
public class ClockApp extends Application {

    /**
     * start is the main method of ClockApp.
     * @param stage The stage on which the application is set
     * @throws Exception if there is an issue loading the FXML file
     */
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ClockApp.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}
