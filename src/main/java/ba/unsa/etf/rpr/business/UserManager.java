package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.GalleryException;

public class UserManager {

    /**
     * Deletes user
     *
     * @param userId the user id
     * @throws GalleryException the gallery exception
     */
    public void delete(int userId) throws GalleryException {
        try {
            DaoFactory.userDao().delete(userId);
        } catch (GalleryException e) {
            if (e.getMessage().contains("FOREIGN KEY")) {
                throw new GalleryException("NO");
            }
            throw e;
        }

    }

    /**
     * Adds user
     *
     * @param user the user
     * @return the user
     * @throws GalleryException the gallery exception
     */
    public User add(User user) throws GalleryException {
        return DaoFactory.userDao().add(user);
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     * @throws GalleryException the gallery exception
     */
    public User getById(int id) throws GalleryException {
        return DaoFactory.userDao().getById(id);
    }

    public User findUserByUsername(String username) {
        return null;
    }
}
