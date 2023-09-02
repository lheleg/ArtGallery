package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ArtistManager;
import ba.unsa.etf.rpr.business.GalleryManager;
import ba.unsa.etf.rpr.business.PaintingManager;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Gallery;
import ba.unsa.etf.rpr.domain.Painting;
import ba.unsa.etf.rpr.exceptions.GalleryException;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    public GridPane pane = new GridPane();
    public Button galleriesButton;
    public Button artistsButton;
    private final GalleryManager g = new GalleryManager();

    private final ArtistManager a = new ArtistManager();

    private final PaintingManager p = new PaintingManager();
    public void GalleryDivs() throws GalleryException {
        List<Gallery> galleries = g.fetchGalleries();
        int row = 0;
        int column = 0;
        pane.setHgap(24);
        pane.setVgap(15);
        pane.setPadding(new Insets(7));
        pane.setAlignment(Pos.CENTER);
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
            galName.setTextFill(Color.BEIGE);
            galName.setFont(Font.font(null, FontWeight.BOLD, 18));
            galName.setAlignment(Pos.CENTER);

            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(imageView, rect, galName);
            stackPane.setAlignment(Pos.CENTER);

         //   stackPane.setOnMouseClicked(event -> ShowPaintings(gal.getId(), gallery));
            pane.add(stackPane, column, row);
            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }
        scroller.setContent(pane);
    }

    public void ArtistDivs() throws GalleryException {
        List<Artist> artists = a.fetchArtists();
        int row = 0;
        int column = 0;
        pane.setHgap(100);
        pane.setVgap(15);
        pane.setPadding(new Insets(7,7,0,45));
        pane.setAlignment(Pos.CENTER);

        for (Artist artist : artists) {
            ImageView imageView = new ImageView(new Image(artist.getImage()));
            imageView.setFitWidth(200);
            imageView.setFitHeight(250);
            Rectangle rect = new Rectangle(imageView.getFitWidth()+5, imageView.getFitHeight()+5,
                    new LinearGradient(0, 1.4, 0, 0, true, javafx.scene.paint.CycleMethod.NO_CYCLE,
                            new Stop(0, Color.BLACK), new Stop(1, Color.TRANSPARENT)));
            rect.setArcHeight(10);
            rect.setArcWidth(10);

            Label galName = new Label(artist.getFirstName() + " " + artist.getLastName());
            galName.setTextFill(Color.LIGHTBLUE);
            galName.setFont(Font.font(null, FontWeight.BOLD, 18));
            galName.setAlignment(Pos.CENTER);

            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(imageView, rect, galName);
            stackPane.setAlignment(Pos.CENTER);

            pane.add(stackPane, column, row);
            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }
        scroller.setContent(pane);
    }

    public void ShowPaintings(int id, String forWhat) throws GalleryException {
        int row = 0;
        int column = 0;
        pane.setHgap(100);
        pane.setVgap(15);
        pane.setPadding(new Insets(7,7,0,45));
        pane.setAlignment(Pos.CENTER);

        List<Painting> paintings;

        if (forWhat == "gallery"){
            paintings = p.getByGallery(g.getById(id));
        }else {
            paintings = p.getByArtist(a.getById(id));
        }

        for (Painting painting : paintings) {
            ImageView imageView = new ImageView(new Image(painting.getImage()));
            imageView.setFitWidth(190);
            imageView.setFitHeight(240);
            Rectangle rect = new Rectangle(imageView.getFitWidth()+5, imageView.getFitHeight()+5,
                    new LinearGradient(0, 1.4, 0, 0, true, javafx.scene.paint.CycleMethod.NO_CYCLE,
                            new Stop(0, Color.BLACK), new Stop(1, Color.TRANSPARENT)));
            rect.setArcHeight(10);
            rect.setArcWidth(10);

            Label galName = new Label(painting.getTitle());
            galName.setTextFill(Color.INDIANRED);
            galName.setFont(Font.font(null, FontWeight.BOLD, 18));
            galName.setAlignment(Pos.CENTER);

            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(imageView, rect, galName);
            stackPane.setAlignment(Pos.CENTER);

            pane.add(stackPane, column, row);
            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }
        scroller.setContent(pane);
    }

    @FXML
    public void initialize() throws GalleryException {
        ArtistDivs();

        galleriesButton.setOnAction(event -> {
            // Clear existing content in pane
            pane.getChildren().clear();
            try {
                GalleryDivs();
            } catch (GalleryException e) {
                throw new RuntimeException(e);
            }
        });

        artistsButton.setOnAction(event -> {
            // Clear existing content in pane
            pane.getChildren().clear();
            try {
                ArtistDivs();
            } catch (GalleryException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    private void closeAction(MouseEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
