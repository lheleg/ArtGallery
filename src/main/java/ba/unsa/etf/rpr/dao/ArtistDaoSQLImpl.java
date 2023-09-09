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
            art.setImage(rs.getString("image"));
            return art;
        } catch (SQLException e) {
            throw new GalleryException(e.getMessage(), e);
        }
    }

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

    @Override
    public Artist getByName(String name) throws GalleryException {
        return executeQueryUnique("SELECT * FROM Artists WHERE firstName = ?", new Object[]{name});
    }
}
