package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.dao.AbstractDao;
import ba.unsa.etf.rpr.domain.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileReader;
import java.sql.Connection;

public class RegisterController {

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
        registerButton.setOnAction(actionEvent -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();

            boolean textFieldsFilled = true;

            if (username.isEmpty()) {
                wrongUsernameLabel.setText("This field cannot be empty.");
                textFieldsFilled = false;
            }

            if (password.isEmpty()) {
                wrongPasswordLabel.setText("This field cannot be empty");
                textFieldsFilled = false;
            }

            if (firstName.isEmpty()) {
                wrongFirstNameLabel.setText("This field cannot be empty");
                textFieldsFilled = false;
            }

            if (lastName.isEmpty()) {
                wrongLastNameLabel.setText("This field cannot be empty");
                textFieldsFilled = false;
            }

            if (textFieldsFilled) {

                User user = new User();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setUsername(username);
                user.setPassword(password);

                try {
                    Connection connection = AbstractDao.getConnection();

                    UserManager u = new UserManager();
                    User newUser = u.add(user);

                    // Check if the user object was returned by the add User method from UserDaoSQLImpl.java
                    if (newUser != null) {
                        errorLabel.setText("User registered successfully. Log in to proceed.");
                    } else {
                        errorLabel.setText("Error registering user. Please try again.");
                    }


                    connection.close();

                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
        });

        firstNameField.textProperty().addListener((observable, oldValue, newValue) -> clearErrorMessage(wrongFirstNameLabel));
        lastNameField.textProperty().addListener((observable, oldValue, newValue) -> clearErrorMessage(wrongLastNameLabel));
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> clearErrorMessage(wrongPasswordLabel));
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> clearErrorMessage(wrongUsernameLabel));
    }

    @FXML
    private void registerButtonPerformed() {

    }
    private void clearErrorMessage(Label label) {
        label.setText("");
    }
}
