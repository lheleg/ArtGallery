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

        stageGall.getIcons().add(new Image("/images/ikonica.png"));
        stageGall.initStyle(StageStyle.TRANSPARENT); // hides upper window bar
        stageGall.setResizable(false);
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
        Stage stageArt = new Stage();
        stageArt.setTitle("Our artists");
        stageArt.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));

        stageArt.getIcons().add(new Image("/images/ikonica.png"));
        //  stageArt.initStyle(StageStyle.TRANSPARENT); // hides upper window bar
        stageArt.setResizable(false);
        // Show the new stage
        stageArt.show();
    }

    @FXML
    private void closeAction(MouseEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    void initialize() {

    }



}
