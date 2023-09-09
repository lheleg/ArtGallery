package ba.unsa.etf.rpr.util;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * A custom secondary stage for JavaFX applications with specific styling and properties.
 * This class is implemented as a singleton, ensuring that only one instance exists throughout the application.
 */
public class SecondaryStage extends Stage {
    private static SecondaryStage instance;

    /**
     * Private constructor to create a new instance of SecondaryStage.
     * Configures the stage style and sets the application icon.
     */
    private SecondaryStage() {
        this.initStyle(StageStyle.TRANSPARENT);
        this.getIcons().add(new Image("images/ikonica.png"));
    }

    /**
     * Get the singleton instance of SecondaryStage.
     * If an instance does not exist, a new one is created. If it exists, it is closed.
     * The stage is centered on the screen.
     *
     * @return the Singleton instance of SecondaryStage.
     */
    public static SecondaryStage getInstance() {
        if (instance == null) {
            instance = new SecondaryStage();
        }else instance.close();
        instance.centerOnScreen();
        return instance;
    }
}
