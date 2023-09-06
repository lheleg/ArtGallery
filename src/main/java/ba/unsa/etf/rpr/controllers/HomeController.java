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
        stage.setTitle("Register");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));

        stage.setX(primaryStage.getX() + 150);
        stage.setY(primaryStage.getY() + 100);
        stage.setResizable(false);
        // Show the new stage
        stage.show();
    }

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
        stage.setTitle("Login");

        stage.setX(primaryStage.getX() + 570);
        stage.setY(primaryStage.getY() + 135);
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));

        stage.setResizable(false);
        // Show the new stage
        stage.show();
    }

    @FXML
    private void closeAction(MouseEvent event) {
        secondaryStage.close();
        primaryStage.close();
    }

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
    @FXML
    void initialize() {

    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
