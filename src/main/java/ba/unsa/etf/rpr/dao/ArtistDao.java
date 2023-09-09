package ba.unsa.etf.rpr.dao;


import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.exceptions.GalleryException;

import java.util.List;

/**
 * Dao interface for Artist domain bean
 *
 * @author Lejla Heleg
 */
public interface ArtistDao extends Dao<Artist> {
    public List<Artist> fetchArtists() throws GalleryException;

    public Artist getByName(String name) throws GalleryException;
}
