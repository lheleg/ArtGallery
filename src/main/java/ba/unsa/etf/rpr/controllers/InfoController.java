package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class InfoController {

    /**
     * Initialize the controller.
     */
    @FXML
    void initialize() {
    }

    /**
     * Handle the closeAction event.
     *
     * @param event The mouse event triggering the action.
     */
    @FXML
    private void closeAction(MouseEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
