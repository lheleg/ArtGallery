package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.GalleryException;

import java.sql.*;
import java.util.*;

/**
 * Abstract class that implements core DAO CRUD methods
 *
 * @author Lejla Heleg
 */
public abstract class AbstractDao<T extends Idable> implements Dao<T>{

    private static Connection connection = null;
    private String tableName;
    public AbstractDao(String tableName) {
        try{
            System.out.println("kreirano");
            this.tableName = tableName;
            if(AbstractDao.connection==null){
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("db.properties").openStream());
            String url = p.getProperty("url");
            String user = p.getProperty("user");
            String password = p.getProperty("password");
            this.connection = DriverManager.getConnection(url, user, password);}
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static Connection getConnection(){
        return AbstractDao.connection;
    }

    public void setConnection(Connection connection){
        AbstractDao.connection = connection;
    }

    /**
     * Method for mapping ResultSet into Object
     * @param rs - result set from database
     * @return a Bean object for specific table
     * @throws GalleryException in case of error with db
     */
    public abstract T row2object(ResultSet rs) throws GalleryException;

    /**
     * Method for mapping Object into Map
     * @param ob - a bean object for specific table
     * @return key, value sorted map of object
     */
    public abstract Map<String, Object> object2row(T ob);

    public T getById(int id) throws GalleryException {
        return executeQueryUnique("SELECT * FROM "+this.tableName+" WHERE id = ?", new Object[]{id});
    }

    public List<T> getAll() throws GalleryException {
        return executeQuery("SELECT * FROM "+tableName, null);
    }

    public void delete(int id) throws GalleryException {
        String sql = "DELETE FROM "+tableName+" WHERE id = ?";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new GalleryException(e.getMessage(), e);
        }
    }

    public T add(T item) throws GalleryException{
        Map<String, Object> row = object2row(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(tableName);
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(")");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            // bind params. IMPORTANT treeMap is used to keep columns sorted so params are bind correctly
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setId(rs.getInt(1)); //set id to return it back */

            return item;
        }catch (SQLException e){
            throw new GalleryException(e.getMessage(), e);
        }
    }

    public T update(T item) throws GalleryException{
        Map<String, Object> row = object2row(item);
        String updateColumns = prepareUpdateParts(row);
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ")
                .append(tableName)
                .append(" SET ")
                .append(updateColumns)
                .append(" WHERE id = ?");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString());
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter++;
            }
            stmt.setObject(counter, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            throw new GalleryException(e.getMessage(), e);
        }
    }

    /**
     * Utility method for executing any kind of query
     * @param query - SQL query
     * @param params - params for query
     * @return List of objects from database
     * @throws GalleryException in case of error with db
     */
    public List<T> executeQuery(String query, Object[] params) throws GalleryException{
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            if (params != null){
                for(int i = 1; i <= params.length; i++){
                    stmt.setObject(i, params[i-1]);
                }
            }
            ResultSet rs = stmt.executeQuery();
            ArrayList<T> resultList = new ArrayList<>();
            while (rs.next()) {
                resultList.add(row2object(rs));
            }
            return resultList;
        } catch (SQLException e) {
            throw new GalleryException(e.getMessage(), e);
        }
    }

    /**
     * Utility for query execution that always return single record
     * @param query - query that returns single record
     * @param params - list of params for sql query
     * @return Object
     * @throws GalleryException in case when object is not found
     */
    public T executeQueryUnique(String query, Object[] params) throws GalleryException{
        List<T> result = executeQuery(query, params);
        if (result != null && result.size() == 1){
            return result.get(0);
        }else{
            throw new GalleryException("Object not found");
        }
    }

    /**
     * Accepts KV storage of column names and return CSV of columns and question marks for insert statement
     * Example: (id, name, date) ?,?,?
     */
    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue; //skip insertion of id due autoincrement
            columns.append(entry.getKey());
            questions.append("?");
            if (row.size() != counter) {
                columns.append(",");
                questions.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<>(columns.toString(), questions.toString());
    }

    /**
     * Prepare columns for update statement id=?, name=?, ...
     * @param row - row to be converted intro string
     * @return String for update statement
     */
    private String prepareUpdateParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue; //skip update of id due where clause
            columns.append(entry.getKey()).append("= ?");
            if (row.size() != counter) {
                columns.append(",");
            }
        }
        return columns.toString();
    }
}