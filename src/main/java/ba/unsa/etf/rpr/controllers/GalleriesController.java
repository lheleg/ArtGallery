package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.ArtistManager;
import ba.unsa.etf.rpr.business.GalleryManager;
import ba.unsa.etf.rpr.business.PaintingManager;
import ba.unsa.etf.rpr.business.WishManager;
import ba.unsa.etf.rpr.domain.*;
import ba.unsa.etf.rpr.exceptions.GalleryException;
import ba.unsa.etf.rpr.util.SecondaryStage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
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
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * The type PicturePerfect page controller.
 */
public class GalleriesController {
    @FXML
    private ScrollPane scroller;

    @FXML
    private GridPane pane = new GridPane();

    @FXML
    private Button galleriesButton;
    @FXML
    private Button artistsButton;

    @FXML
    private Button myGalleryButton;

    private User user;
    private Stage primaryStage;


    private final GalleryManager g = new GalleryManager();
    private final WishManager w = new WishManager();

    private List<Wish> myGallery = new ArrayList<>();

    private List<Painting> myGalleryPaintings = new ArrayList<>();

    private final ArtistManager a = new ArtistManager();

    private final PaintingManager p = new PaintingManager();

    private SecondaryStage secondaryStage;

    public GalleriesController() throws GalleryException {
    }

    /**
     * Initialize the GalleryDivs method.
     * Fetch galleries and display them in a grid.
     *
     * @throws GalleryException If there is an issue fetching galleries.
     */
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
            ImageView imageView = new ImageView(new Image("/images/galleries/"+gal.getId()+".jpg"));
            imageView.setFitWidth(270);
            imageView.setFitHeight(170);
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
            stackPane.setCursor(Cursor.HAND);
            pane.add(stackPane, column, row);
            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }
        scroller.setPadding(new Insets(0));
        scroller.setContent(pane);
    }

    /**
     * Initialize the ArtistDivs method.
     * Fetch artists and display them in a grid.
     *
     * @throws GalleryException If there is an issue fetching artists.
     */
    public void ArtistDivs() throws GalleryException {
        List<Artist> artists = a.fetchArtists();
        int row = 0;
        int column = 0;
        pane.setHgap(100);
        pane.setVgap(15);
        pane.setPadding(new Insets(7,7,0,45));
        pane.setAlignment(Pos.CENTER);

        for (Artist artist : artists) {
            ImageView imageView = new ImageView(new Image("/images/artists/"+artist.getId()+".jpg"));
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);
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

            stackPane.setCursor(Cursor.HAND);
            pane.add(stackPane, column, row);
            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }
        scroller.setPadding(new Insets(0));
        scroller.setContent(pane);
    }

    /**
     * Initialize the ShowPaintings method.
     * Show paintings based on the selected gallery or artist.
     *
     * @param id      The ID of the gallery or artist.
     * @param forWhat Indicates whether the paintings are for a gallery, artist, or private gallery.
     * @throws GalleryException If there is an issue fetching paintings.
     */
    public void ShowPaintings(Integer id, String forWhat) throws GalleryException {
        int row = 0;
        int column = 0;
        pane.setHgap(100);
        pane.setVgap(30);
        pane.setPadding(new Insets(7,7,0,45));
        pane.setAlignment(Pos.CENTER);

        List<Painting> paintings;

        if (forWhat == "gallery"){
            paintings = p.getByGallery(g.getById(id));
        }else if (forWhat == "artist") {
            paintings = p.getByArtist(a.getById(id));
        }else paintings = myGalleryPaintings;

        for (Painting painting : paintings) {
            ImageView imageView = new ImageView(new Image("/images/paintings/"+painting.getId()+".jpg"));
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

            VBox vbox = new VBox(stackPane, paiTitle);;
            VBox pom;

            if(forWhat != "gallery" && forWhat != "artist"){
                ImageView remove = new ImageView("/images/cancel.png");
                remove.setFitHeight(25);
                remove.setFitWidth(25);
                remove.setCursor(Cursor.HAND);;

                remove.setOnMouseClicked(event -> {
                    Wish wish = myGallery.get(paintings.indexOf(painting));
                    wish.setUnsavedDate(new Date());
                    try {
                        w.update(wish);
                    } catch (GalleryException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        myGallery = w.getUserSavedPaintings(this.user);
                        myGalleryPaintings = new ArrayList<>();
                        for(Wish wi : myGallery){
                            myGalleryPaintings.add(wi.getPainting());
                        }
                        pane.getChildren().clear();
                        ShowPaintings(null, "private gallery");
                    } catch (GalleryException e) {
                        throw new RuntimeException(e);
                    }
                });

                pom = new VBox(vbox, remove);
                pom.setSpacing(5);
            }else {
                pom = new VBox(vbox);
            }
            pom.setAlignment(Pos.CENTER);
            vbox.setAlignment(Pos.CENTER);

            imageView.setOnMouseClicked(event -> {
                try {
                    ShowPaintingDetails(painting);
                } catch (GalleryException e) {
                    throw new RuntimeException(e);
                }
            });
            imageView.setCursor(Cursor.HAND);
            pane.add(pom, column, row);
            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }
        if(myGallery.isEmpty() && paintings.isEmpty()) {
            Text mess = new Text("O o p s ... your Gallery is empty!\nBe free to explore our artists and galleries to create your own collection!");
            mess.setFont(Font.font(null, FontWeight.MEDIUM, 20));
            mess.setTextAlignment(TextAlignment.CENTER);
            VBox vbox = new VBox(mess);
            vbox.setAlignment(Pos.CENTER);
            scroller.setPadding(new Insets(120));
            scroller.setContent(vbox);
        }else {
            scroller.setPadding(new Insets(0));
            scroller.setContent(pane);
        }
    }

    /**
     * Initialize the ShowPaintingDetails method.
     * Show details of a selected painting.
     *
     * @param painting The painting to display details for.
     * @throws GalleryException If there is an issue displaying painting details.
     */
    public void ShowPaintingDetails(Painting painting) throws GalleryException{
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/paintingDetails.fxml"));

            PaintingDetailsController controller = new PaintingDetailsController();
            controller.setPainting(painting);
            controller.setUser(this.user);
            controller.setG(this);

            fxmlLoader.setController(controller);
            Parent root = fxmlLoader.load();
            SecondaryStage detailsStage = SecondaryStage.getInstance();
            secondaryStage = detailsStage;

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            detailsStage.setScene(scene);
            detailsStage.setResizable(false);
            detailsStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle the showInfo event.
     * Load and display additional information.
     *
     * @param event The mouse event triggering the action.
     * @throws IOException If there is an issue loading information.
     */
    @FXML
    private void showInfo(MouseEvent event) throws IOException {
        // Load the FXML file for new stage
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/info.fxml"));
        Parent root = loader.load();
        // Create a new stage and set its scene
        SecondaryStage stage = SecondaryStage.getInstance();
        secondaryStage = stage;
        stage.setTitle("Info");

        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));

        stage.setResizable(false);
        // Show the new stage
        stage.show();
    }

    /**
     * Initialize the controller.
     * Set initial content and event handlers.
     *
     * @throws GalleryException If there is an issue initializing the controller.
     */
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

        myGalleryButton.setOnAction(event -> {
            try {
                myGallery = w.getUserSavedPaintings(this.user);
            } catch (GalleryException e) {
                throw new RuntimeException(e);
            }
            myGalleryPaintings = new ArrayList<>();
            for(Wish wish : myGallery){
                myGalleryPaintings.add(wish.getPainting());
            }
            // Clear existing content in pane
            pane.getChildren().clear();
            try {
                ShowPaintings(null,"private gallery");
            } catch (GalleryException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Handle the closeAction event.
     * Close the secondary stage and primary stage.
     *
     * @param event The mouse event triggering the action.
     */
    @FXML
    private void closeAction(MouseEvent event) {
        if(secondaryStage != null) secondaryStage.close();
        primaryStage.close();
    }

    /**
     * Handle the logOut event.
     * Log out and return to the home page.
     *
     * @param event The mouse event triggering the action.
     * @throws IOException If there is an issue logging out.
     */
    @FXML
    private void logOut(MouseEvent event) throws IOException {
        if(secondaryStage != null) secondaryStage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
        Parent root = loader.load();

        HomeController controller = loader.getController();

        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));

        stage.setTitle("Details");

        controller.setPrimaryStage(stage);
        stage.getIcons().add(new Image("/images/ikonica.png"));
        stage.initStyle(StageStyle.TRANSPARENT); // hides upper window bar
        stage.setResizable(false);
        stage.show();
        primaryStage.close();
    }

    /**
     * Set the current user.
     *
     * @param user The user to set.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Set the primary stage.
     *
     * @param primaryStage The primary stage to set.
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Get the user's private gallery.
     *
     * @return The user's private gallery as a list of paintings.
     */
    public List<Wish> getMyGallery() {
        return myGallery;
    }
}
