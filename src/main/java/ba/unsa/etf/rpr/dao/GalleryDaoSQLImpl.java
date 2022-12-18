package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Gallery;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GalleryDaoSQLImpl implements GalleryDao{

    private Connection connection;

    public GalleryDaoSQLImpl() {
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
    public Gallery getById(int id) {
        String query = "SELECT * FROM Galleries WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                Gallery gallery = new Gallery();
                gallery.setId(rs.getInt("id"));
                gallery.setName(rs.getString("name"));
                rs.close();
                return gallery;
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Gallery add(Gallery item) {

        String insert = "INSERT INTO Galleries(name) VALUES(?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, item.getName());
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
    public Gallery update(Gallery item) {
        String insert = "UPDATE Galleries SET name = ? WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, item.getName());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        String insert = "DELETE FROM Galleries WHERE id = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Gallery> getAll() {

        String query = "SELECT * FROM Galleries";
        List<Gallery> galleries = new ArrayList<Gallery>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                Gallery gallery = new Gallery();
                gallery.setId(rs.getInt("id"));
                gallery.setName(rs.getString("name"));
                galleries.add(gallery);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return galleries;
    }
}
