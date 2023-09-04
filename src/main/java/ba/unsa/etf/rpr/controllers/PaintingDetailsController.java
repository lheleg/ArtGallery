package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Painting;
import ba.unsa.etf.rpr.exceptions.GalleryException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.*;

/**
 * The type Painting detail controller
 */
public class PaintingDetailsController {
    @FXML
    public Button webButton;
    @FXML
    public ScrollPane messPane;
    @FXML
    public ImageView cancelImageView;
    private Painting painting;

    /**
     * Gets painting
     *
     * @return the painting
     */
    public Painting getPaining() {
        return painting;
    }

    /**
     * Sets painting
     *
     * @param painting the painting
     */
    public void setPainting(Painting painting) {
        this.painting = painting;
    }


    @FXML
    public void initialize() throws GalleryException {
        String availability = "available";
        String greeting = ". . . y a y . . .";
        if (!painting.getAvailable()) {
            availability = "not " + availability;
            greeting = ". . . o o p s . . .";
        };
        Text mess = new Text(greeting + "\n" + painting.getTitle() + " by " + painting.getArtist().getFirstName() + " " + painting.getArtist().getLastName() + "\nis " + availability + " for sale!");

        mess.setFont(Font.font(null, FontWeight.MEDIUM, 18));
        mess.setTextAlignment(TextAlignment.CENTER);

        messPane.setContent(mess);

        cancelImageView.setOnMouseClicked( mouseEvent -> {
            Stage stage = (Stage) cancelImageView.getScene().getWindow();
            stage.close();
        });

        webButton.setOnAction(actionEvent -> {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    // Open the default web browser to the specified URL
                    desktop.browse(new java.net.URI(painting.getGallery().getUrl()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
}
