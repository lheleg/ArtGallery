package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Painting;
import ba.unsa.etf.rpr.exceptions.GalleryException;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class PaintingDetailsController {
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
    public void initialize() throws GalleryException{
        String availability = "available";
        if (!(getPaining().getAvailable())) availability = "not " + availability;
        Text mess = new Text("Hello,\n" + painting.getTitle() + " by " + painting.getArtist().getFirstName() + " " + painting.getArtist().getLastName() + "\nis " + availability + " for sale.");
        messPane.setContent(mess);

        cancelImageView.setOnMouseClicked( mouseEvent -> {
            Stage stage = (Stage) cancelImageView.getScene().getWindow();
            stage.close();
        });
    }
}
