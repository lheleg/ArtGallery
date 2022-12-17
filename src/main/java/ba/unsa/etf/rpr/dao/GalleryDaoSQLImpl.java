package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Gallery;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
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
        return null;
    }

    @Override
    public Gallery add(Gallery item) {
        return null;
    }

    @Override
    public Gallery update(Gallery item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Gallery> getAll() {
        return null;
    }
}
