package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class InfoController {

    @FXML
    void initialize() {
    }
    @FXML
    private void closeAction(MouseEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
