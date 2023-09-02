package ba.unsa.etf.rpr.exceptions;

/**
 * The type Artist exception.
 */
public class ArtistException extends Exception {

    /**
     * Instantiates a new Artist exception
     *
     * @param message the message
     * @param reason the reason
     */
    public ArtistException(String message, Exception reason){
        super(message, reason);
    }

    /**
     * Instantiates a new Artist exception
     *
     * @param message the message
     */
    public ArtistException(String message){
        super(message);
    }
}
