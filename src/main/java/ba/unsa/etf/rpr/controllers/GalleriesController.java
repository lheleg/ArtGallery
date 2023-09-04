package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ArtistManager;
import ba.unsa.etf.rpr.business.GalleryManager;
import ba.unsa.etf.rpr.business.PaintingManager;
import ba.unsa.etf.rpr.domain.*;
import ba.unsa.etf.rpr.exceptions.GalleryException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.io.IOException;
import java.util.List;

/**
 * The type PicturePerfect page controller
 */
public class GalleriesController {
    public ScrollPane scroller;
    public GridPane pane = new GridPane();
    public Button galleriesButton;
    public Button artistsButton;

    private User user;
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

            stackPane.setOnMouseClicked(event -> {
                try {
                    // Clear existing content in pane
                    pane.getChildren().clear();
                    ShowPaintings(gal.getId(), "gallery");
                } catch (GalleryException e) {
                    throw new RuntimeException(e);
                }
            });
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

            stackPane.setOnMouseClicked(event -> {
                try {
                    // Clear existing content in pane
                    pane.getChildren().clear();
                    ShowPaintings(artist.getId(), "artist");
                } catch (GalleryException e) {
                    throw new RuntimeException(e);
                }
            });

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
        pane.setVgap(30);
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
            imageView.setFitWidth(150);
            imageView.setFitHeight(200);
            Rectangle rect = new Rectangle(imageView.getFitWidth()+55, imageView.getFitHeight()+55);
            rect.setFill(Color.web("#5d4037"));

            Rectangle rect1 = new Rectangle(imageView.getFitWidth()+35, imageView.getFitHeight()+35);
            rect1.setFill(Color.WHITE);

            Label paiTitle = new Label(painting.getTitle());
            paiTitle.setTextFill(Color.BLACK);
            paiTitle.setFont(Font.font(12));
            paiTitle.setAlignment(Pos.CENTER);

            StackPane stackPane = new StackPane(rect, rect1, imageView);
            stackPane.setAlignment(Pos.CENTER);

            VBox vbox = new VBox(stackPane, paiTitle);
            vbox.setAlignment(Pos.CENTER);

            vbox.setOnMouseClicked(event -> {
                try {
                    ShowPaintingDetails(painting);
                } catch (GalleryException e) {
                    throw new RuntimeException(e);
                }
            });
            pane.add(vbox, column, row);
            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }
        scroller.setContent(pane);
    }

    public void ShowPaintingDetails(Painting painting) throws GalleryException{
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/paintingDetails.fxml"));

            PaintingDetailsController controller = new PaintingDetailsController();
            controller.setPainting(painting);
            controller.setUser(this.user);

            fxmlLoader.setController(controller);
            Parent root = fxmlLoader.load();
            DetailStage detailsStage = DetailStage.getInstance();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            detailsStage.setScene(scene);
            detailsStage.setResizable(false);
            detailsStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() throws GalleryException {
        GalleryDivs();

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
