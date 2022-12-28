package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Gallery;
import ba.unsa.etf.rpr.exceptions.GalleryException;

import java.sql.*;
import java.util.Map;

public class GalleryDaoSQLImpl extends AbstractDao<Gallery> implements GalleryDao{

    public GalleryDaoSQLImpl() {
        super("Galleries");
    }

    @Override
    public Gallery row2object(ResultSet rs) throws GalleryException {
        return null;
    }

    @Override
    public Map<String, Object> object2row(Gallery ob) {
        return null;
    }
}
