package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Painting;
import ba.unsa.etf.rpr.exceptions.GalleryException;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class PaintingDetailsController {

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
    private void closeAction(MouseEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void initialize() throws GalleryException {

    }
}
