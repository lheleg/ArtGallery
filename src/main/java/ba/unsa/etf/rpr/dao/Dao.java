package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.GalleryException;

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
    T getById(int id) throws GalleryException;

    /**
     * Saves entity into database
     * @param item bean for saving to database
     * @return saved item with id field populated
     */
    T add(T item) throws GalleryException;

    /**
     * Fully updates entity in database based on id match.
     * @param item bean to be updated
     * @return updated version of bean
     */
    T update(T item) throws GalleryException;

    /**
     * Hard delete of item from database with given id
     * @param id primary key of entity
     */
    void delete(int id) throws GalleryException;

    /**
     * Lists all entities from database
     * @return list of entities from database
     */
    List<T> getAll() throws GalleryException;
}
