package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Gallery;
import ba.unsa.etf.rpr.exceptions.GalleryException;

import java.util.List;

/**
 * Dao interface for Gallery domain bean
 *
 * @author Lejla Heleg
 */
public interface GalleryDao extends Dao<Gallery>{

    public Gallery getGalleryByUserId(int userId) throws GalleryException;
    public List<Gallery> fetchGalleries() throws GalleryException;
}
