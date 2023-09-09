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

    /**
     * Constructs a new GalleryDaoSQLImpl instance.
     * Initializes the DAO with the "Galleries" table name.
     */
    public GalleryDaoSQLImpl() {
        super("Galleries");
    }

    /**
     * Converts a ResultSet row into a Gallery object.
     *
     * @param rs the ResultSet containing the row data.
     * @return a Gallery object created from the ResultSet data.
     * @throws GalleryException if a GalleryException occurs during the conversion.
     */
    @Override
    public Gallery row2object(ResultSet rs) throws GalleryException {
        try {
            Gallery gal = new Gallery();
            gal.setId(rs.getInt("id"));
            gal.setName(rs.getString("name"));
            gal.setUrl(rs.getString("url"));
            gal.setImage(rs.getString("image"));
            return gal;
        } catch (SQLException e) {
            throw new GalleryException(e.getMessage(), e);
        }
    }

    /**
     * Converts a Gallery object into a map of column names to values.
     *
     * @param ob the Gallery object to convert.
     * @return a map representing the Gallery object.
     */
    @Override
    public Map<String, Object> object2row(Gallery ob) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", ob.getId());
        row.put("name", ob.getName());
        row.put("url", ob.getUrl());
        row.put("image", ob.getImage());
        return row;
    }

    /**
     * Fetches a list of all galleries from the database.
     *
     * @return a list of Gallery objects representing all galleries in the database.
     */
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

    /**
     * Retrieves a gallery by its name.
     *
     * @param name the name of the gallery to retrieve.
     * @return a Gallery object with the specified name or null if not found.
     * @throws GalleryException if a GalleryException occurs during the operation.
     */
    @Override
    public Gallery getByName(String name) throws GalleryException {
        return executeQueryUnique("SELECT * FROM Galleries WHERE name = ?", new Object[]{name});
    }

}
