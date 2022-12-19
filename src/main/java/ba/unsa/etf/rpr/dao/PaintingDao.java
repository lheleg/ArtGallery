package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Gallery;
import ba.unsa.etf.rpr.domain.Painting;

import java.util.List;

/**
 * Dao interface for Painting domain bean
 *
 * @author Lejla Heleg
 */
public interface PaintingDao extends Dao<Painting>{
    public List<Painting> getByArtistId(int artistId);
    public List<Painting> getByGalleryId(int galleryId);

    public List<Painting> getByGalleryIdAndAvailability(int galleryId);

}
