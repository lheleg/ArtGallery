package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;


public class HomeController {

    @FXML
    private Label clickableGall;

    @FXML
    private void showGalleries(MouseEvent event) throws IOException {
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
    void initialize() {

    }
}
