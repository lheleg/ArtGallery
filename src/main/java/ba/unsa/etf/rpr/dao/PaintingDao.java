package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Gallery;
import ba.unsa.etf.rpr.domain.Painting;
import ba.unsa.etf.rpr.exceptions.GalleryException;

import java.util.List;

/**
 * Dao interface for Painting domain bean
 *
 * @author Lejla Heleg
 */
public interface PaintingDao extends Dao<Painting>{
    public List<Painting> getByArtist(Artist artist) throws GalleryException;
    public List<Painting> getByGallery(Gallery galleryId) throws GalleryException;

    public List<Painting> fetchPaintings() throws GalleryException;

    public List<Painting> getByGalleryAndAvailability(Gallery gallery) throws GalleryException;

    public Painting getByName(String paiName) throws GalleryException;
}
