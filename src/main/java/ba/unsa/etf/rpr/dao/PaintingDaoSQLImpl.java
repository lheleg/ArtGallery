package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Painting;
import ba.unsa.etf.rpr.exceptions.GalleryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PaintingDaoSQLImpl extends AbstractDao<Painting> implements PaintingDao {


    public PaintingDaoSQLImpl() {
        super("Paintings");
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

    @Override
    public Painting row2object(ResultSet rs) throws GalleryException {
        return null;
    }

    @Override
    public Map<String, Object> object2row(Painting ob) {
        return null;
    }
}
