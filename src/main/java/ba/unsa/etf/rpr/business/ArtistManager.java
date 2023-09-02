package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.exceptions.GalleryException;

import java.util.List;

/**
 * The type Artist manager
 */
public class ArtistManager {

    /**
     * Deletes artist
     *
     * @param artistId the artist id
     * @throws GalleryException the artist exception
     */
    public void delete(int artistId) throws GalleryException {
        try {
            DaoFactory.artistDao().delete(artistId);
        } catch (GalleryException e) {
            if (e.getMessage().contains("FOREIGN KEY")) {
                throw new GalleryException("NO");
            }
            throw e;
        }

    }

    /**
     * Adds artist
     *
     * @param artist the Artist
     * @return the artist
     * @throws GalleryException the gallery exception
     */
    public Artist add(Artist artist) throws GalleryException {
        return DaoFactory.artistDao().add(artist);
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     * @throws GalleryException the gallery exception
     */
    public Artist getById(int id) throws GalleryException {
        return DaoFactory.artistDao().getById(id);
    }

    /**
     * Fetch artists
     *
     * @return the list of all artists
     */
    public List<Artist> fetchArtists() throws GalleryException {
        return DaoFactory.artistDao().fetchArtists();
    }
}
