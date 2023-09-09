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

    /**
     * Constructs a new UserDaoSQLImpl instance.
     * Initializes the DAO with the "Users" table name.
     */
    public UserDaoSQLImpl() {
        super("Users");
    }

    /**
     * Converts a ResultSet row into a User object.
     *
     * @param rs the ResultSet containing the row data.
     * @return a User object created from the ResultSet data.
     * @throws GalleryException if a GalleryException occurs during the conversion.
     */
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

    /**
     * Converts a User object into a map of column names to values.
     *
     * @param ob the User object to convert.
     * @return a map representing the User object.
     */
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
    /**
     * Retrieves the total number of users in the Users table.
     *
     * @return the total number of users.
     * @throws SQLException if a SQL exception occurs during the operation.
     */
    public int totalUsers() throws SQLException{
        int total = 0;
        String query = "SELECT COUNT(username) AS total FROM USERS";
        try (PreparedStatement st = AbstractDao.getConnection().prepareStatement(query)) {
            ResultSet result = st.executeQuery();
            if (result.next()) total = result.getInt("total");
        }
        return total;
    }

    /**
     * Finds a user by their username.
     *
     * @param username the username of the user to find.
     * @return a User object with the specified username or null if not found.
     */
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

    /**
     * Validates if a username is unique in the system.
     *
     * @param username the username to validate.
     * @return true if the username is unique; false otherwise.
     */
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
