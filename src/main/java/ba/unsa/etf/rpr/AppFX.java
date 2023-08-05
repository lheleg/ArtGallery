package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.controllers.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;


public class AppFX extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        scene.setFill(Color.TRANSPARENT);
        stage.setTitle("Art Gallery");
        stage.setScene(scene);

        HomeController homeController = fxmlLoader.getController();
        homeController.setPrimaryStage(stage);

        stage.getIcons().add(new Image("/images/ikonica.png"));
      //  stage.initStyle(StageStyle.TRANSPARENT); // hides upper window bar
        stage.setResizable(false);
        stage.show();
       // Stage secondStage
    }

    public static void main(String[] args) {
        launch();
    }
}
