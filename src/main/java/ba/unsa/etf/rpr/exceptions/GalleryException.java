package ba.unsa.etf.rpr.exceptions;

public class GalleryException extends Exception{

    public GalleryException(String message, Exception reason){
        super(message, reason);
    }

    public GalleryException(String message){
        super(message);
    }
}
