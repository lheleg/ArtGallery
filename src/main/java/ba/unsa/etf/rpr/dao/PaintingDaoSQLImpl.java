package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Painting;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
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
        return null;
    }

    @Override
    public Painting add(Painting item) {
        return null;
    }

    @Override
    public Painting update(Painting item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Painting> getAll() {
        return null;
    }
}
