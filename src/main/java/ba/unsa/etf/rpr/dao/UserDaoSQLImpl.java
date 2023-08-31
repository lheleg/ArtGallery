package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.GalleryException;

import java.sql.ResultSet;
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
        return null;
    }

    @Override
    public Map<String, Object> object2row(User ob) {
        return null;
    }
}
