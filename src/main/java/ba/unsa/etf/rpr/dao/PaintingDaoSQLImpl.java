package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Painting;
import ba.unsa.etf.rpr.exceptions.GalleryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
            pai.setGallery(DaoFactory.galleryDao().getById(rs.getInt("paintingId")));
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
        return row;
    }

    @Override
    public List<Painting> getByArtistId(int artistId) {
        List<Painting> paintings = new ArrayList<>();
        String query = "SELECT * FROM Paintings WHERE artistId = ?";

        return paintings;
    }

    @Override
    public List<Painting> getByGalleryId(int galleryId) {
        List<Painting> paintings = new ArrayList<>();
        String query = "SELECT * FROM Paintings WHERE galleryId = ?";

        return paintings;
    }

    @Override
    public List<Painting> getByGalleryIdAndAvailability(int galleryId) {
        List<Painting> paintings = new ArrayList<>();
        String query = "SELECT * FROM Paintings WHERE galleryId=? AND available=1";

        return paintings;
    }
}
