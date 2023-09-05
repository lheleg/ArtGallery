package ba.unsa.etf.rpr.util;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SecondaryStage extends Stage {
    private static SecondaryStage instance;

    private SecondaryStage() {
        this.initStyle(StageStyle.TRANSPARENT);
        this.getIcons().add(new Image("images/ikonica.png"));
    }

    public static SecondaryStage getInstance() {
        if (instance == null) {
            instance = new SecondaryStage();
        }
        return instance;
    }
}
