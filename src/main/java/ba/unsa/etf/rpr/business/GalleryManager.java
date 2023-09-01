package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Gallery;
import ba.unsa.etf.rpr.exceptions.GalleryException;

import java.util.List;

/**
 * The type Gallery manager
 */
public class GalleryManager {
    /**
     * Deletes gallery
     *
     * @param galleryId the gallery id
     * @throws GalleryException the gallery exception
     */
    public void delete(int galleryId) throws GalleryException {
        try {
            DaoFactory.galleryDao().delete(galleryId);
        } catch (GalleryException e) {
            if (e.getMessage().contains("FOREIGN KEY")) {
                throw new GalleryException("NO");
            }
            throw e;
        }

    }
    /**
     * Adds gallery
     *
     * @param gall the Gallery
     * @return the gallery
     * @throws GalleryException the gallery exception
     */
    public Gallery add(Gallery gall) throws GalleryException {
        return DaoFactory.galleryDao().add(gall);
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     * @throws GalleryException the gallery exception
     */
    public Gallery getById(int id) throws GalleryException {
        return DaoFactory.galleryDao().getById(id);
    }

    /**
     * Fetch public galleries
     *
     * @return the list of galleries
     */
    public List<Gallery> fetchGalleries() throws GalleryException {
        return DaoFactory.galleryDao().fetchGalleries();
    }
}
