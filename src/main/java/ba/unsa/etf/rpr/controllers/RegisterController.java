package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UserManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class RegisterController {
    private final UserManager u = new UserManager();

    /**
     * The First name field
     */
    public TextField firstNameField;

    /**
     * The Last name field
     */
    public TextField lastNameField;

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
    public Button registerButton;

    /**
     * The Error message Label
     */
    public Label errorLabel;

    /**
     * The Wrong username label,
     * for error message
     */
    @FXML
    public Label wrongUsernameLabel;

    /**
     * The Wrong password label,
     * for error message
     */
    @FXML
    public Label wrongPasswordLabel;

    /**
     * The Wrong First name label,
     * for error message
     */
    @FXML
    public Label wrongFirstNameLabel;

    /**
     * The Wrong Last name label,
     * for error message
     */
    @FXML
    public Label wrongLastNameLabel;

    /**
     * The Cancel image view
     */
    @FXML
    public ImageView cancelImageView;

    private Stage primaryStage;
    public void initialize() {
        firstNameField.textProperty().addListener((observable, oldValue, newValue) -> clearErrorMessage(wrongFirstNameLabel));
        lastNameField.textProperty().addListener((observable, oldValue, newValue) -> clearErrorMessage(wrongLastNameLabel));
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> clearErrorMessage(wrongPasswordLabel));
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> clearErrorMessage(wrongUsernameLabel));
    }

    private void clearErrorMessage(Label label) {
        label.setText("");
    }
}
