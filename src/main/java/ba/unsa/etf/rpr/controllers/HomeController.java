package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;


public class HomeController {

    @FXML
    private Label clickableGall;

    @FXML
    private Label clickableArt;

    public GridPane galleryPane = new GridPane();
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @FXML
    private void showGalleries(MouseEvent event) throws IOException {
        this.primaryStage.close();

        // Load the FXML file for new stage
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/galleries.fxml"));
        Parent root = loader.load();

        // Create a new stage and set its scene
        Stage stageGall = new Stage();
        stageGall.setTitle("Our galleries");
        stageGall.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));

        // Show the new stage
        stageGall.show();
    }

    @FXML
    private void showArtists(MouseEvent event) throws IOException {
        this.primaryStage.close();

        // Load the FXML file for new stage
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/artists.fxml"));
        Parent root = loader.load();

        // Create a new stage and set its scene
        Stage stageGall = new Stage();
        stageGall.setTitle("Our artists");
        stageGall.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));

        // Show the new stage
        stageGall.show();
    }

    @FXML
    void initialize() {

    }
}
