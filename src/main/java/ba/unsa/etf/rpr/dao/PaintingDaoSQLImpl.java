package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Artist;
import ba.unsa.etf.rpr.domain.Gallery;
import ba.unsa.etf.rpr.domain.Painting;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PaintingDaoSQLImpl implements PaintingDao{

    private Connection connection;

    public PaintingDaoSQLImpl() {
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
    public Painting getById(int id) {
        String query = "SELECT * FROM Paintings WHERE id = ?";
        String query1 = "SELECT * FROM Artists WHERE id = rs.getInt(\"artistID\"";
        String query2 = "SELECT * FROM Galleries WHERE id = rs.getInt(\"galleryID\"";

        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            PreparedStatement stmt1 = this.connection.prepareStatement(query1);
            PreparedStatement stmt2 = this.connection.prepareStatement(query2);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            ResultSet rs1 = stmt1.executeQuery();
            ResultSet rs2 = stmt2.executeQuery();

            if (rs.next()){ // result set is iterator.
                Artist artist = new Artist();
                Gallery gallery = new Gallery();
                Painting painting = new Painting();
                painting.setId(rs.getInt("id"));
                painting.setAvailable(rs.getBoolean("available"));
                painting.setTitle(rs.getString("title"));

                artist.setId(rs1.getInt("artistID"));
                artist.setFirstName(rs1.getString("firstName"));
                artist.setLastName(rs1.getString("lastName"));
                artist.setStyle(rs1.getString("style"));

                painting.setArtist(artist);

                gallery.setId(rs2.getInt("galleryId"));
                gallery.setName(rs2.getString("name"));

                painting.setGallery(gallery);
                rs.close();
                return painting;
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Painting add(Painting item) {
        String insert = "INSERT INTO Paintings(available,title,artistId,galleryId) VALUES(?, ?, ?, ?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setBoolean(1, item.getAvailable());
            stmt.setString(2, item.getTitle());
            stmt.setInt(3, item.getArtist().getId());
            stmt.setInt(4, item.getGallery().getId());

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
    public Painting update(Painting item) {
        String insert = "UPDATE Paintings SET available = ?, title = ?, artistId = ?, galleryId = ? WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getAvailable());
            stmt.setObject(2, item.getTitle());
            stmt.setObject(3, item.getArtist());
            stmt.setObject(4, item.getGallery());
            stmt.setObject(5, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        String insert = "DELETE FROM Paintings WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Painting> getAll() {
        String query = "SELECT * FROM Paintings";
        String query1 = "SELECT * FROM Artists";
        String query2 = "SELECT * FROM Galleries";

        List<Painting> paintings = new ArrayList<Painting>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            PreparedStatement stmt1 = this.connection.prepareStatement(query1);
            PreparedStatement stmt2 = this.connection.prepareStatement(query2);
            ResultSet rs = stmt.executeQuery();
            ResultSet rs1 = stmt1.executeQuery();
            ResultSet rs2 = stmt2.executeQuery();
            while (rs.next()){ // result set is iterator.
                Painting painting = new Painting();
                Artist artist = new Artist();
                Gallery gallery = new Gallery();

                painting.setId(rs.getInt("id"));
                painting.setAvailable(rs.getBoolean("available"));
                painting.setTitle(rs.getString("title"));

                artist.setId(rs.getInt("artistID"));
                artist.setFirstName(rs1.getString("firstName"));
                artist.setLastName(rs1.getString("lastName"));
                artist.setStyle(rs1.getString("style"));

                painting.setArtist(artist);

                gallery.setId(rs.getInt("galleryId"));
                gallery.setName(rs2.getString("name"));

                painting.setGallery(gallery);
                paintings.add(painting);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return paintings;
    }
}
