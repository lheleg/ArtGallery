package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.GalleryException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * MySQL Implementation of DAO
 *
 * @author Lejla Heleg
 */
public class UserDaoSQLImpl extends AbstractDao<User> implements UserDao {

    public UserDaoSQLImpl() {
        super("Users");
    }

    @Override
    public User row2object(ResultSet rs) throws GalleryException {
        try {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            return user;
        } catch (SQLException e) {
            throw new GalleryException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(User ob) {
        return null;
    }
}