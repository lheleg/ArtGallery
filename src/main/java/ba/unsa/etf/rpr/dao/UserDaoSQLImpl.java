package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.GalleryException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

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
        Map<String, Object> row = new TreeMap<>();
        row.put("id", ob.getId());
        row.put("username", ob.getUsername());
        row.put("password", ob.getPassword());
        row.put("firstName", ob.getFirstName());
        row.put("lastName", ob.getLastName());
        return row;
    }
    public int totalUsers() throws SQLException{
        int total = 0;
        String query = "SELECT COUNT(username) AS total FROM USERS";
        try (PreparedStatement st = AbstractDao.getConnection().prepareStatement(query)) {
            ResultSet result = st.executeQuery();
            if (result.next()) total = result.getInt("total");
        }
        return total;
    }

    public User findUserByUsername(String username){
        try {
            ResultSet rs = getConnection().createStatement().executeQuery("SELECT id FROM Users WHERE username = '" + username + "'");
            if(rs.next()){
                return getById(rs.getInt(1));
            }
        } catch (SQLException | GalleryException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Boolean validateUsername(String username)  {
        try {
            ResultSet rs = getConnection().createStatement().executeQuery("SELECT id FROM Users WHERE username = '" + username + "'");
            if(rs.next()){
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
