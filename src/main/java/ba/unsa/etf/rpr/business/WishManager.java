package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.domain.Wish;
import ba.unsa.etf.rpr.exceptions.GalleryException;

import java.util.List;

/**
 * The type Wish manager
 */
public class WishManager {
    /**
     * Deletes wish
     *
     * @param wishId the wish id
     * @throws GalleryException the gallery exception
     */
    public void delete(int wishId) throws GalleryException {
        try {
            DaoFactory.wishDao().delete(wishId);
        } catch (GalleryException e) {
            if (e.getMessage().contains("FOREIGN KEY")) {
                throw new GalleryException("NO");
            }
            throw e;
        }

    }

    /**
     * Adds wish
     *
     * @param wish the wish
     * @return the wish
     * @throws GalleryException the gallery exception
     */
    public Wish add(Wish wish) throws GalleryException {
        return DaoFactory.wishDao().add(wish);
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     * @throws GalleryException the gallery exception
     */
    public Wish getById(int id) throws GalleryException {
        return DaoFactory.wishDao().getById(id);
    }

    /**
     * Retrieves a list of wishes representing paintings saved by the specified user.
     * @param user The user whose saved paintings are to be retrieved.
     * @return A List of Wishes representing the paintings saved by the specified user.
     * @throws GalleryException If an error occurs while executing the query.
     */
    public List<Wish> getUserSavedPaintings(User user) throws GalleryException {
        return DaoFactory.wishDao().getUserSavedPaintings(user);
    }
}
