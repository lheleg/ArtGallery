package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * The type Log in page controller
 */
public class LogInController {
    /**
     * The Username field
     */
    public TextField usernameField;
    /**
     * The Password field
     */
    public PasswordField passwordField;
    /**
     * The Login button
     */
    public Button logInButton;
    /**
     * The Wrong username in
     */
    @FXML
    public Label wrongUsername;
    /**
     * The Wrong password in
     */
    @FXML
    public Label wrongPassword;


    @FXML
    public void initialize() {
    }

}