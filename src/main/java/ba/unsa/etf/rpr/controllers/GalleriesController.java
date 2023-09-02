package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.GalleryManager;
import ba.unsa.etf.rpr.domain.Gallery;
import ba.unsa.etf.rpr.exceptions.GalleryException;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

public class GalleriesController {
    public ScrollPane scroller;
    public GridPane galPane = new GridPane();
    public Button galleriesButton;
    private final GalleryManager g = new GalleryManager();
    public void GalleryDivs() throws GalleryException {
        List<Gallery> galleries = g.fetchGalleries();
        int row = 0;
        int column = 0;
        galPane.setHgap(24);
        galPane.setVgap(15);
        galPane.setPadding(new Insets(7));
        galPane.setAlignment(Pos.CENTER);
       // System.out.println(Arrays.toString(galleries.toArray()));
        for (Gallery gal : galleries) {
            ImageView imageView = new ImageView(new Image(gal.getImage()));
            imageView.setFitWidth(275);
            imageView.setFitHeight(160);
            Rectangle rect = new Rectangle(imageView.getFitWidth()+5, imageView.getFitHeight()+5,
                    new LinearGradient(0, 1.4, 0, 0, true, javafx.scene.paint.CycleMethod.NO_CYCLE,
                            new Stop(0, Color.BLACK), new Stop(1, Color.TRANSPARENT)));
            rect.setArcHeight(10);
            rect.setArcWidth(10);

            Label galName = new Label(gal.getName());
            galName.setTextFill(Color.WHITE);
            galName.setFont(Font.font(null, FontWeight.BOLD, 18));
            galName.setAlignment(Pos.CENTER);

            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(imageView, rect, galName);
            stackPane.setAlignment(Pos.CENTER);


            galPane.add(stackPane, column, row);
            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }
        scroller.setContent(galPane);
    }

    @FXML
    public void initialize() throws GalleryException {
        GalleryDivs();

        galleriesButton.setOnAction(event -> {
            // Clear existing content in galPane
            galPane.getChildren().clear();
            try {
                GalleryDivs();
            } catch (GalleryException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
