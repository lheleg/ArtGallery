package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ArtistDaoSQLImpl implements ArtistDao{

    private Connection connection;

    public ArtistDaoSQLImpl() {
        try {
            FileReader fr = new FileReader("src/main/resources/db.properties");
            Properties p = new Properties();
            p.load(fr);
            String url = p.getProperty("url");
            String user = p.getProperty("user");
            String password = p.getProperty("password");
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Artist getById(int id) {
        String query = "SELECT * FROM Artists WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Artist artist = new Artist();
                artist.setId(rs.getInt("id"));
                artist.setFirstName(rs.getString("firstName"));
                artist.setLastName(rs.getString("lastName"));
                artist.setStyle(rs.getString("style"));
                rs.close();
                return artist;
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Artist add(Artist item) {
        String insert = "INSERT INTO Artists(firstName,lastName,style) VALUES(?, ?, ?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getFirstName());
            stmt.setString(2, item.getLastName());
            stmt.setString(3, item.getStyle());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setId(rs.getInt(1)); //set id to return it back
            return item;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Artist update(Artist item) {
        String insert = "UPDATE Artists SET firstName = ?, lastName = ?, style = ? WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getFirstName());
            stmt.setObject(2, item.getLastName());
            stmt.setObject(3, item.getStyle());
            stmt.setObject(4, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        String insert = "DELETE FROM Artists WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Artist> getAll() {
        String query = "SELECT * FROM Artists";
        List<Artist> artists = new ArrayList<Artist>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Artist artist = new Artist();
                artist.setId(rs.getInt("id"));
                artist.setFirstName(rs.getString("firstName"));
                artist.setLastName(rs.getString("lastName"));
                artist.setStyle(rs.getString("style"));
                artists.add(artist);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return artists;
    }
}
