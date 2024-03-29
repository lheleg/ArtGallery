package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.GalleryException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

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
    public Button loginButton;

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
     * The Cancel image view
     */
    @FXML
    public ImageView cancelImageView;

    private Stage primaryStage;

    /**
     * Initialize the LoginController.
     *
     * @throws GalleryException If there is an issue initializing the controller.
     */
    public void initialize() throws GalleryException  {
        loginButton.setOnAction(actionEvent -> {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            boolean textFieldsFilled;

            if (!username.isEmpty()) {
                wrongUsernameLabel.setText("");
            } else wrongUsernameLabel.setText("This field cannot be empty.");

            if (!password.isEmpty()) {
                wrongPasswordLabel.setText("");
                textFieldsFilled = true;
            } else {
                wrongPasswordLabel.setText("This field cannot be empty");
                textFieldsFilled = false;
            }

            User user = new User();

            if (textFieldsFilled && user != null) {

                UserManager u = new UserManager();
                user = u.findUserByUsername(username);

                    // Check the input of password
                try {
                    if (Objects.equals(user.getPassword(), u.hashPassword(password))) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/galleries.fxml"));
                            Parent root = loader.load();

                            GalleriesController controller = loader.getController();
                            controller.setUser(user);

                            Stage stage = new Stage();
                            stage.initStyle(StageStyle.TRANSPARENT);
                            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));

                            controller.setPrimaryStage(stage);
                            stage.getIcons().add(new Image("/images/ikonica.png"));
                            stage.initStyle(StageStyle.TRANSPARENT); // hides upper window bar
                            stage.setResizable(false);
                            stage.show();
                            this.primaryStage.close();
                            ((Stage) usernameField.getScene().getWindow()).close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else {
                        // Display an error message
                        errorLabel.setText("Invalid username or password.");
                    }
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        cancelImageView.setOnMouseClicked( mouseEvent -> {
            Stage stage = (Stage) cancelImageView.getScene().getWindow();
            stage.close();
        });
    }

    /**
     * Set the primary stage.
     *
     * @param primaryStage The primary stage to set.
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}