package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Gallery;
import ba.unsa.etf.rpr.exceptions.GalleryException;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL Implementation of DAO
 *
 * @author Lejla Heleg
 */
public class GalleryDaoSQLImpl extends AbstractDao<Gallery> implements GalleryDao{

    public GalleryDaoSQLImpl() {
        super("Galleries");
    }

    @Override
    public Gallery row2object(ResultSet rs) throws GalleryException {
        try {
            Gallery gal = new Gallery();
            gal.setId(rs.getInt("id"));
            gal.setName(rs.getString("name"));
            return gal;
        } catch (SQLException e) {
            throw new GalleryException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Gallery ob) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", ob.getId());
        row.put("name", ob.getName());
        return row;
    }
}
