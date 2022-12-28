package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Gallery;
import ba.unsa.etf.rpr.exceptions.GalleryException;

import java.sql.*;
import java.util.Map;

public class ArtistDaoSQLImpl extends AbstractDao<Artist> implements ArtistDao{

    public ArtistDaoSQLImpl() {
        super("Artists");
    }

    @Override
    public Artist row2object(ResultSet rs) throws GalleryException {
        try {
            Artist art = new Artist();
            art.setId(rs.getInt("id"));
            art.setFirstName(rs.getString("firstName"));
            art.setLastName(rs.getString("lastName"));
            art.setStyle(rs.getString("style"));
            return art;
        } catch (SQLException e) {
            throw new GalleryException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Artist ob) {
        return null;
    }
}
