package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Gallery;
import ba.unsa.etf.rpr.domain.Painting;
import ba.unsa.etf.rpr.exceptions.GalleryException;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL Implementation of DAO
 *
 * @author Lejla Heleg
 */
public class PaintingDaoSQLImpl extends AbstractDao<Painting> implements PaintingDao {

    public PaintingDaoSQLImpl() {
        super("Paintings");
    }

    @Override
    public Painting row2object(ResultSet rs) throws GalleryException {
        try {
            Painting pai = new Painting();
            pai.setId(rs.getInt("id"));
            pai.setAvailable(rs.getBoolean("available"));
            pai.setTitle(rs.getString("title"));
            pai.setArtist(DaoFactory.artistDao().getById(rs.getInt("artistId")));
            pai.setGallery(DaoFactory.galleryDao().getById(rs.getInt("galleryId")));
            pai.setImage(rs.getString("image"));
            return pai;
        } catch (Exception e) {
            throw new GalleryException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Painting ob) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", ob.getId());
        row.put("available", ob.getAvailable());
        row.put("title", ob.getTitle());
        row.put("artistId", ob.getArtist());
        row.put("galleryId", ob.getGallery());
        row.put("image", ob.getImage());
        return row;
    }

    @Override
    public List<Painting> getByArtist(Artist artist) throws GalleryException{
        return executeQuery("SELECT * FROM Paintings WHERE artistId = ?", new Object[]{artist.getId()});
    }

    @Override
    public List<Painting> getByGallery(Gallery gallery) throws GalleryException{
        return executeQuery("SELECT * FROM Paintings WHERE galleryId = ?", new Object[]{gallery.getId()});
    }

    @Override
    public List<Painting> getByGalleryAndAvailability(Gallery gallery) throws GalleryException {
        return executeQuery("SELECT * FROM Paintings WHERE galleryId=? AND available=1", new Object[]{gallery.getId()});
    }
}
