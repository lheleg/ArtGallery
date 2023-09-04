package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Painting;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.GalleryException;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;

/**
 * The type Painting detail controller
 */
public class PaintingDetailsController {
    @FXML
    public Button webButton;

    @FXML
    public Button saveButton;
    @FXML
    public ScrollPane messPane;
    @FXML
    public ImageView cancelImageView;
    private Painting painting;

    private User user;

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
        if (!painting.getAvailable()) {
            availability = "not " + availability;
            webButton.setDisable(true);
        };
        Text mess = new Text("Dear " + user.getFirstName() + ",\n" + painting.getTitle() + " by " + painting.getArtist().getFirstName() + " " + painting.getArtist().getLastName() + "\nis " + availability + " for sale!");
        Text greet = new Text("- team  pp");

        mess.setFont(Font.font(null, FontWeight.MEDIUM, 18));

        greet.setFont(Font.font(null, FontWeight.MEDIUM, 18));

        VBox vbox = new VBox(mess, greet);
        vbox.setAlignment(Pos.CENTER);

        messPane.setContent(vbox);

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

        saveButton.setOnAction(actionEvent -> {

        });
    }

    public void setUser(User user) {
        this.user = user;
    }
}
