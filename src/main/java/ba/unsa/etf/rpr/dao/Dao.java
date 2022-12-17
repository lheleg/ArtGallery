package ba.unsa.etf.rpr.dao;

import java.util.List;

/**
 * Root interface for all DAO classes
 *
 * @author Lejla Heleg
 */
public interface Dao<T> {
    /**
     * get entity from database base on ID
     * @param id primary key od entity
     * @return entity from database
     */
    T getById(int id);

    /**
     * Saves entity into database
     * @param item bean for saving to database
     * @return saved item with id field populated
     */
    T add(T item);

    /**
     * Fully updates entity in database based on id match.
     * @param item bean to be updated
     * @return updated version of bean
     */
    T update(T item);

    /**
     * Hard delete of item from database with given id
     * @param id primary key of entity
     */
    void delete(int id);

    /**
     * Lists all entities from database
     * @return list of entities from database
     */
    List<T> getAll();
}
