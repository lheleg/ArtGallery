package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    @FXML
    private ImageView menuImageView;

    private Stage primaryStage;

    @FXML
    private void showRegister(MouseEvent event) throws IOException {

        // Load the FXML file for new stage
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/register.fxml"));
        Parent root = loader.load();

        // Create a new stage and set its scene
        Stage stage = new Stage();
        stage.setTitle("Register");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));

        stage.getIcons().add(new Image("/images/ikonica.png"));
        stage.initStyle(StageStyle.TRANSPARENT); // hides upper window bar
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
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));

        stage.getIcons().add(new Image("/images/ikonica.png"));
        stage.initStyle(StageStyle.TRANSPARENT); // hides upper window bar
        stage.setResizable(false);
        // Show the new stage
        stage.show();
    }

    @FXML
    private void closeAction(MouseEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    void initialize() {

    }


    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
