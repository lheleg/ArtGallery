package ba.unsa.etf.rpr.dao;
import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Gallery;
import ba.unsa.etf.rpr.domain.Painting;
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
public class PaintingDaoSQLImpl extends AbstractDao<Painting> implements PaintingDao {

    /**
     * Constructs a new PaintingDaoSQLImpl instance.
     * Initializes the DAO with the "Paintings" table name.
     */
    public PaintingDaoSQLImpl() {
        super("Paintings");
    }

    /**
     * Converts a ResultSet row into a Painting object.
     *
     * @param rs the ResultSet containing the row data.
     * @return a Painting object created from the ResultSet data.
     * @throws GalleryException if a GalleryException occurs during the conversion.
     */
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

    /**
     * Converts a Painting object into a map of column names to values.
     *
     * @param ob the Painting object to convert.
     * @return a map representing the Painting object.
     */
    @Override
    public Map<String, Object> object2row(Painting ob) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", ob.getId());
        row.put("available", ob.getAvailable());
        row.put("title", ob.getTitle());
        row.put("artistId", ob.getArtist().getId());
        row.put("galleryId", ob.getGallery().getId());
        row.put("image", ob.getImage());
        return row;
    }

    /**
     * Retrieves a list of paintings associated with a specific Artist.
     *
     * @param artist the Artist object to retrieve paintings for.
     * @return a list of Painting objects associated with the specified Artist.
     * @throws GalleryException if a GalleryException occurs during the operation.
     */
    @Override
    public List<Painting> getByArtist(Artist artist) throws GalleryException{
        return executeQuery("SELECT * FROM Paintings WHERE artistId = ?", new Object[]{artist.getId()});
    }

    /**
     * Retrieves a list of paintings associated with a specific Gallery.
     *
     * @param gallery the Gallery object to retrieve paintings for.
     * @return a list of Painting objects associated with the specified Gallery.
     * @throws GalleryException if a GalleryException occurs during the operation.
     */
    @Override
    public List<Painting> getByGallery(Gallery gallery) throws GalleryException{
        return executeQuery("SELECT * FROM Paintings WHERE galleryId = ?", new Object[]{gallery.getId()});
    }

    /**
     * Retrieves a list of available paintings associated with a specific Gallery.
     *
     * @param gallery the Gallery object to retrieve available paintings for.
     * @return a list of available Painting objects associated with the specified Gallery.
     * @throws GalleryException if a GalleryException occurs during the operation.
     */
    @Override
    public List<Painting> getByGalleryAndAvailability(Gallery gallery) throws GalleryException {
        return executeQuery("SELECT * FROM Paintings WHERE galleryId=? AND available=1", new Object[]{gallery.getId()});
    }

    /**
     * Retrieves a painting by its title.
     *
     * @param paintingName the title of the painting to retrieve.
     * @return a Painting object with the specified title or null if not found.
     * @throws GalleryException if a GalleryException occurs during the operation.
     */
    @Override
    public Painting getByName(String paintingName) throws GalleryException {
        return executeQueryUnique("SELECT * FROM Paintings WHERE title = ?", new Object[]{paintingName});
    }

    /**
     * Fetches a list of all paintings from the database.
     *
     * @return a list of Painting objects representing all paintings in the database.
     * @throws GalleryException if a GalleryException occurs during the operation.
     */
    @Override
    public List<Painting> fetchPaintings() throws GalleryException{
        List<Painting> paintings = new ArrayList<>();
        try{
            ResultSet resultSet = getConnection().createStatement().executeQuery("SELECT * FROM Paintings");
            while (resultSet.next()) {
                Painting gal = row2object(resultSet);
                paintings.add(gal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (GalleryException e) {
            e.printStackTrace();
        }
        //return the list of paintings
        return paintings;
    }
}
