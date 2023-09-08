package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.util.SecondaryStage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;


/**
 * The type Home page controller
 */
public class HomeController {

    @FXML
    public GridPane root;

    @FXML
    private ImageView closeImageView;

    @FXML
    private ImageView infoImageView;


    private Stage primaryStage;

    private SecondaryStage secondaryStage;

    /**
     * Handle the event to show the registration form.
     *
     * @param event The mouse event triggering the action.
     * @throws IOException If there is an issue loading the registration form.
     */
    @FXML
    private void showRegister(MouseEvent event) throws IOException {

        // Load the FXML file for new stage
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/register.fxml"));

        RegisterController controller = new RegisterController();
      //  controller.setPrimaryStage(this.primaryStage);

        loader.setController(controller);
        Parent root = loader.load();
        // Create a new stage and set its scene
        SecondaryStage stage = SecondaryStage.getInstance();
        secondaryStage = stage;
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));

        stage.setX(primaryStage.getX() + 150);
        stage.setY(primaryStage.getY() + 100);
        stage.setResizable(false);
        // Show the new stage
        stage.show();
    }

    /**
     * Handle the event to show the login form.
     *
     * @param event The mouse event triggering the action.
     * @throws IOException If there is an issue loading the login form.
     */
    @FXML
    private void showLogin(MouseEvent event) throws IOException {

        // Load the FXML file for new stage
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));

        LogInController controller = new LogInController();
        controller.setPrimaryStage(this.primaryStage);

        loader.setController(controller);
        Parent root = loader.load();
        // Create a new stage and set its scene
        SecondaryStage stage = SecondaryStage.getInstance();
        secondaryStage = stage;

        stage.setX(primaryStage.getX() + 570);
        stage.setY(primaryStage.getY() + 135);
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));

        stage.setResizable(false);
        // Show the new stage
        stage.show();
    }

    /**
     * Handle the closeAction event.
     * Close the secondary stage and primary stage.
     *
     * @param event The mouse event triggering the action.
     */
    @FXML
    private void closeAction(MouseEvent event) {
        if(secondaryStage != null) secondaryStage.close();
        primaryStage.close();
    }

    /**
     * Handle the event to show information about App.
     *
     * @param event The mouse event triggering the action.
     * @throws IOException If there is an issue when loading.
     */
    @FXML
    private void showInfo(MouseEvent event) throws IOException {
        // Load the FXML file for new stage
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/info.fxml"));
        Parent root = loader.load();
        // Create a new stage and set its scene
        SecondaryStage stage = SecondaryStage.getInstance();
        secondaryStage = stage;
        stage.setTitle("Info");

        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));

        stage.setResizable(false);
        // Show the new stage
        stage.show();
    }

    /**
     * Initialize the controller.
     */
    @FXML
    void initialize() {

    }

    /**
     * Set the primary stage.
     *
     * @param primaryStage The primary stage to set.
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
