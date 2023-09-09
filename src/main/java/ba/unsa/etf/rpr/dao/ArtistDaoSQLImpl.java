package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.exceptions.GalleryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL Implementation of DAO
 *
 * @author Lejla Heleg
 */
public class ArtistDaoSQLImpl extends AbstractDao<Artist> implements ArtistDao{

    /**
     * Constructs a new ArtistDaoSQLImpl instance.
     * Initializes the DAO with the "Artists" table name.
     */
    public ArtistDaoSQLImpl() {
        super("Artists");
    }

    /**
     * Converts a ResultSet row into an Artist object.
     *
     * @param rs the ResultSet containing the row data.
     * @return an Artist object created from the ResultSet data.
     * @throws GalleryException if a GalleryException occurs during the conversion.
     */
    @Override
    public Artist row2object(ResultSet rs) throws GalleryException {
        try {
            Artist art = new Artist();
            art.setId(rs.getInt("id"));
            art.setFirstName(rs.getString("firstName"));
            art.setLastName(rs.getString("lastName"));
            art.setStyle(rs.getString("style"));
            art.setImage(rs.getString("image"));
            return art;
        } catch (SQLException e) {
            throw new GalleryException(e.getMessage(), e);
        }
    }

    /**
     * Converts an Artist object into a map of column names to values.
     *
     * @param ob the Artist object to convert.
     * @return a map representing the Artist object.
     */
    @Override
    public Map<String, Object> object2row(Artist ob) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", ob.getId());
        row.put("firstName", ob.getFirstName());
        row.put("lastName", ob.getLastName());
        row.put("style", ob.getStyle());
        row.put("image", ob.getImage());
        return row;
    }

    /**
     * Fetches a list of all artists from the database.
     *
     * @return a list of Artist objects representing all artists in the database.
     * @throws GalleryException if a GalleryException occurs during the operation.
     */
    @Override
    public List<Artist> fetchArtists() throws GalleryException {
        List<Artist> artists = new ArrayList<>();
        try{
            ResultSet resultSet = getConnection().createStatement().executeQuery("SELECT * FROM Artists");
            while (resultSet.next()) {
                Artist artist = row2object(resultSet);
                artists.add(artist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (GalleryException e) {
            e.printStackTrace();
        }
        //return the list of artists
        return artists;
    }

    /**
     * Retrieves an artist by their first name.
     *
     * @param name the first name of the artist to retrieve.
     * @return an Artist object with the specified first name or null if not found.
     * @throws GalleryException if a GalleryException occurs during the operation.
     */
    @Override
    public Artist getByName(String name) throws GalleryException {
        return executeQueryUnique("SELECT * FROM Artists WHERE firstName = ?", new Object[]{name});
    }
}
