package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Gallery;
import ba.unsa.etf.rpr.domain.Painting;
import ba.unsa.etf.rpr.exceptions.GalleryException;

import java.util.List;

public class PaintingManager {
    /**
     * Deletes paining
     *
     * @param paintingId the painting id
     * @throws GalleryException the gallery exception
     */
    public void delete(int paintingId) throws GalleryException {
        try {
            DaoFactory.paintingDao().delete(paintingId);
        } catch (GalleryException e) {
            if (e.getMessage().contains("FOREIGN KEY")) {
                throw new GalleryException("NO");
            }
            throw e;
        }

    }
    /**
     * Adds painting
     *
     * @param pai the Painting
     * @return the painting
     * @throws GalleryException the gallery exception
     */
    public Painting add(Painting pai) throws GalleryException {
        return DaoFactory.paintingDao().add(pai);
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     * @throws GalleryException the gallery exception
     */
    public Painting getById(int id) throws GalleryException {
        return DaoFactory.paintingDao().getById(id);
    }

    /**
     * Get paintings by Gallery
     *
     * @param gallery the gallery
     * @return the list of paintings
     */
    public List<Painting> getByGallery(Gallery gallery) throws GalleryException {
        return DaoFactory.paintingDao().getByGallery(gallery);
    }

    /**
     * Get paintings by Artist
     *
     * @param artist the gallery
     * @return the list of paintings
     */
    public List<Painting> getByArtist(Artist artist) throws GalleryException {
        return DaoFactory.paintingDao().getByGallery(artist);
    }
}
