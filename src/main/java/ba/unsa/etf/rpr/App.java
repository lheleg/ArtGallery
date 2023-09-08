package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.ArtistManager;
import ba.unsa.etf.rpr.business.GalleryManager;
import ba.unsa.etf.rpr.business.PaintingManager;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Gallery;
import ba.unsa.etf.rpr.domain.Painting;

import ba.unsa.etf.rpr.exceptions.GalleryException;
import org.apache.commons.cli.*;

import java.io.PrintWriter;

/**
 * CLI implementation in following class
 * @author Lejla Heleg
 */
public class App {
    /**
     * Defining final variables to describe all code having options
     */
    private static final Option addGallery = new Option("ag", "add-gallery", false, "Adding new gallery to db");
    private static final Option addArtist = new Option("aa", "add-artist", false, "Adding new artist to db");
    private static final Option addPainting = new Option("ap", "add-painting", false, "Adding new painting to db");

    private static final Option getGalleries = new Option("getG", "get-galleries", false, "Printing all galleries from db");
    private static final Option getArtists = new Option("getA", "get-artists", false, "Printing all artists from db");
    private static final Option getPaintings = new Option("getP", "get-paintings", false, "Printing all paintings from db");

    private static final Option galleryName = new Option("", "gallery-name", false, "");
    private static final Option galleryUrl = new Option("", "gallery-url", false, "");
    private static final Option galleryImage = new Option("", "gallery-image", false, "");
    private static final Option artistFirstName = new Option("", "artist-name", false, "");
    private static final Option artistLastName = new Option("", "artist-surname", false, "");
    private static final Option artistStyle = new Option("", "artist-style", false, "");
    private static final Option artistImage = new Option("", "artist-image", false, "");
    private static final Option paintingAvailable = new Option("", "painting-available", false, "");
    private static final Option paintingTitle = new Option("", "painting-title", false, "");
    private static final Option paintingArtistId = new Option("", "painting-artist", false, "");
    private static final Option paintingGalleryId = new Option("", "painting-gallery", false, "");
    private static final Option paintingImage = new Option("", "painting-image", false, "");


    /**
     * Print options.
     *
     * @param options the options
     */
    public static void formatOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar ArtGallery.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
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

    public static void main(String[] args) {
        CommandLineParser commandLineParser = new DefaultParser();
        Options options = addOptions();
        GalleryManager g = new GalleryManager();
        ArtistManager a = new ArtistManager();
        PaintingManager p = new PaintingManager();
        try {
            CommandLine commandLine = commandLineParser.parse(options, args);
            if (commandLine.hasOption("ag")) {
                if (!commandLine.hasOption("gallery-name") || !commandLine.hasOption("gallery-url") || !commandLine.hasOption("gallery-image")) {
                    System.out.println("Adding a gallery requires: gallery-name, gallery-url and gallery-image");
                    System.exit(1);
                }
                String galleryName = commandLine.getArgList().get(0);
                String galleryUrl = commandLine.getArgList().get(1);
                String galleryImage = commandLine.getArgList().get(2);
                Gallery gall = new Gallery(galleryName, galleryUrl, galleryImage);
                // add the new gallery
                g.add(gall);
                System.out.println(gall.getName() + " added successfully.");
            } else if (commandLine.hasOption("aa")) {
                if (!commandLine.hasOption("artist-name") || !commandLine.hasOption("artist-surname")
                        || !commandLine.hasOption("artist-style") || !commandLine.hasOption("artist-image")) {
                    System.out.println("Adding an artist requires: artist-name, artist-surname, artist-style and artist-image");
                    System.exit(1);
                }
                String artistFirstName = commandLine.getArgList().get(0);
                String artistLastName = commandLine.getArgList().get(1);
                String artistStyle = commandLine.getArgList().get(2);
                String artistImage = commandLine.getArgList().get(3);
                Artist artist = new Artist(artistFirstName, artistLastName, artistStyle, artistImage);
                // add new artist
                a.add(artist);

                System.out.println("Artist " + artist.getFirstName() + " " + artist.getLastName() + " added successfully.");
            } else if (commandLine.hasOption("ap")) {
                if (!commandLine.hasOption("painting-available") || !commandLine.hasOption("painting-title")
                        || !commandLine.hasOption("painting-artist") || !commandLine.hasOption("painting-gallery") || !commandLine.hasOption("painting-image")) {
                    System.out.println("Adding a painting requires: painting-available, painting-title, painting-artist, painting-gallery and painting-image");
                    System.exit(1);
                }
                Boolean paintingAvailable = Boolean.getBoolean(commandLine.getArgList().get(0));
                String paintingTitle = commandLine.getArgList().get(1);
                int paintingArtistId = Integer.parseInt(commandLine.getArgList().get(2));
                int paintingGalleryId = Integer.parseInt(commandLine.getArgList().get(3));
                String paintingImage = commandLine.getArgList().get(4);
                Artist artist = a.getById(paintingArtistId);
                Gallery gallery = g.getById(paintingGalleryId);
                Painting pai = new Painting(paintingAvailable, paintingTitle, artist, gallery, paintingImage);
                // add new painting
                p.add(pai);

                System.out.println("Painting " + pai.getTitle() + " added successfully.");
            }
        } catch (ParseException e) {
            System.out.println("Error while parsing the command line arguments: " + e.getMessage());
            formatOptions(options);
        } catch (GalleryException e) {
            throw new RuntimeException(e);
        }
    }
}
