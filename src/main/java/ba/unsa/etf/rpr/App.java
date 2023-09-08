package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Gallery;
import ba.unsa.etf.rpr.domain.Painting;

import org.apache.commons.cli.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * The type App.
 */
public class App 
{
    private static final Option addGallery = new Option("ag", "add-gallery", false, "Adding new gallery to db");
    private static final Option addArtist = new Option("aa", "add-artist", false, "Adding new artist to db");
    private static final Option addPainting = new Option("ap", "add-painting", false, "Adding new painting to db");
    private static final Option deleteGallery = new Option("dg", "delete-gallery", false, "Deleting a gallery from db");
    private static final Option deleteArtist = new Option("da", "delete-artist", false, "Deleting a artist from db");
    private static final Option deletePainting = new Option("dp", "delete-painting", false, "Deleting a painting db");
    private static final Option updateGallery = new Option("ug", "update-gallery", false, "Updating a gallery in db");
    private static final Option updateArtist = new Option("ua", "update-artist", false, "Updating a artist in db");
    private static final Option updatePainting = new Option("up", "update-painting", false, "Updating a painting in db");
    private static final Option getGalleries = new Option("getG", "get-galleries", false, "Printing all galleries from db");
    private static final Option getArtists = new Option("getA", "get-artists", false, "Printing all artists from db");
    private static final Option getPaintings = new Option("getP", "get-paintings", false, "Printing all paintings from db");

    private static final Option galleryName  = new Option("", "gallery-name", false, "");
    private static final Option galleryUrl  = new Option("", "gallery-url", false, "");
    private static final Option galleryImage  = new Option("", "gallery-image", false, "");
    private static final Option artistFirstName  = new Option("", "artist-name", false, "");
    private static final Option artistLastName  = new Option("", "artist-surname", false, "");
    private static final Option artistStyle  = new Option("", "artist-style", false, "");
    private static final Option artistImage  = new Option("", "artist-image", false, "");
    private static final Option paintingAvailable  = new Option("", "painting-available", false, "");
    private static final Option paintingTitle  = new Option("", "painting-title", false, "");
    private static final Option paintingArtistId  = new Option("", "painting-artist", false, "");
    private static final Option paintingGalleryId  = new Option("", "painting-gallery", false, "");
    private static final Option paintingImage  = new Option("", "painting-image", false, "");


    /**
     * Print options.
     *
     * @param options the options
     */
    public static String formatOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        String header = "java -jar hotel-booking.jar [option] 'something else if needed'";
        helpFormatter.printUsage(printWriter, 200, header);
        helpFormatter.printOptions(printWriter, 200, options, 2, 7);
        printWriter.close();
        return stringWriter.toString();
    }


    /**
     * Add options.
     *
     * @return the options
     */
    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addGallery);
        options.addOption(addArtist);
        options.addOption(addPainting);
        options.addOption(deleteGallery);
        options.addOption(deleteArtist);
        options.addOption(deletePainting);
        options.addOption(updateGallery);
        options.addOption(updateArtist);
        options.addOption(deletePainting);
        options.addOption(updatePainting);
        options.addOption(getArtists);
        options.addOption(getPaintings);
        options.addOption(galleryName);
        options.addOption(galleryImage);
        options.addOption(galleryUrl);
        options.addOption(artistFirstName);
        options.addOption(artistLastName);
        options.addOption(artistStyle);
        options.addOption(artistImage);
        options.addOption(paintingAvailable);
        options.addOption(paintingTitle);
        options.addOption(paintingArtistId);
        options.addOption(paintingGalleryId);
        options.addOption(paintingImage);
        return options;
    }
    public static void main( String[] args ) {

    }
}
