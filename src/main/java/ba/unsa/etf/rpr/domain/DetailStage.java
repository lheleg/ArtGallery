package ba.unsa.etf.rpr.domain;

import javafx.stage.Stage;

public class DetailStage extends Stage {
    private static DetailStage instance;

    private DetailStage() { }

    public static DetailStage getInstance() {
        if (instance == null) {
            instance = new DetailStage();
        }
        return instance;
    }
}
