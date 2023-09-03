package ba.unsa.etf.rpr.domain;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DetailStage extends Stage {
    private static DetailStage instance;

    private DetailStage() {
        this.initStyle(StageStyle.TRANSPARENT);
        this.getIcons().add(new Image("images/ikonica.png"));
    }

    public static DetailStage getInstance() {
        if (instance == null) {
            instance = new DetailStage();
        }
        return instance;
    }
}
