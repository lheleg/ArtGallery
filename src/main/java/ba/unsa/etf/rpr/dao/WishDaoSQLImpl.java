package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.domain.Wish;
import ba.unsa.etf.rpr.exceptions.GalleryException;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Dao interface for Wish domain bean
 *
 * @author Lejla Heleg
 */
public class WishDaoSQLImpl extends AbstractDao<Wish> implements WishDao {

    /**
     * Constructs a new WishDaoSQLImpl instance.
     * Initializes the DAO with the "Wishes" table name.
     */
    public WishDaoSQLImpl() {
        super("Wishes");
    }

    /**
     * Converts a ResultSet row into a Wish object.
     *
     * @param rs the ResultSet containing the row data.
     * @return a Wish object created from the ResultSet data.
     * @throws GalleryException if a GalleryException occurs during the conversion.
     */
    @Override
    public Wish row2object(ResultSet rs) throws GalleryException {
        try {
            Wish wish = new Wish();
            wish.setId(rs.getInt("id"));
            wish.setPainting(DaoFactory.paintingDao().getById(rs.getInt("paintingId")));
            wish.setUser(DaoFactory.userDao().getById(rs.getInt("userId")));
            wish.setSavedDate(rs.getDate("savedDate"));
            wish.setUnsavedDate(rs.getDate("unsavedDate"));
            return wish;
        } catch (Exception e) {
            throw new GalleryException(e.getMessage(), e);
        }
    }

    /**
     * Converts a Wish object into a map of column names to values.
     *
     * @param ob the Wish object to convert.
     * @return a map representing the Wish object.
     */
    @Override
    public Map<String, Object> object2row(Wish ob) {
        Map<String, Object> item = new TreeMap<>();
        item.put("id", ob.getId());
        item.put("paintingId", ob.getPainting().getId());
        item.put("userId", ob.getUser().getId());
        item.put("savedDate", ob.getSavedDate());
        item.put("unsavedDate", ob.getUnsavedDate());

        return item;
    }

    /**
     * Retrieves a list of wishes representing paintings saved by the specified user.
     * @param user The user whose saved paintings are to be retrieved.
     * @return A List of Wishes representing the paintings saved by the specified user.
     * @throws GalleryException If an error occurs while executing the query.
     */
    @Override
    public List<Wish> getUserSavedPaintings(User user) throws GalleryException {
        return executeQuery("SELECT * FROM Wishes WHERE userId = ? and unsavedDate = null",new Object[]{user.getId()});
    }
}
