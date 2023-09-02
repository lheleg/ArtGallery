package ba.unsa.etf.rpr.exceptions;

/**
 * The type Gallery exception.
 */
public class GalleryException extends Exception{

    /**
     * Instantiates a new Gallery exception
     *
     * @param message the message
     * @param reason the reason
     */
    public GalleryException(String message, Exception reason){
        super(message, reason);
    }

    /**
     * Instantiates a new Gallery exception
     *
     * @param message the message
     */
    public GalleryException(String message){
        super(message);
    }
}
