package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Gallery;
import ba.unsa.etf.rpr.exceptions.GalleryException;

import java.sql.*;
import java.util.*;

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
            gal.setUrl(rs.getString("url"));
            gal.setUser(DaoFactory.userDao().getById(rs.getInt("userId")));
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
        row.put("url", ob.getUrl());
        row.put("userId", ob.getUser());
        return row;
    }

    @Override
    public List<Gallery> fetchGalleries() {
        List<Gallery> galleries = new ArrayList<>();
        try{
            ResultSet resultSet = getConnection().createStatement().executeQuery("SELECT * FROM Galleries");
            while (resultSet.next()) {
                Gallery gal = row2object(resultSet);
                galleries.add(gal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (GalleryException e) {
            e.printStackTrace();
        }
        //return the list of galleries
        return galleries;
    }
}
