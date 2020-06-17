package ro.ubb.catalog.core.repository;

import ro.ubb.catalog.core.model.Entity;
import ro.ubb.catalog.core.model.ValidatorException;


import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Interface for generic CRUD operations on a repository for a specific type.
 *
 * @author radu.
 *
 */
public interface Repository<ID,T extends Entity<ID>> {
    /**
     * Find the entity with the given {@code id}.
     *
     * @param id
     *            must be not null.
     * @return an {@code Optional} encapsulating the entity with the given id.
     * @throws IllegalArgumentException
     *             if the given id is null.
     */
    Optional<T> findOne(ID id) throws ValidatorException;
    Entity get_entity_by_id(ID id);
    /**
     *
     * @return all entities.
     */
    void add_statistic(String key,Integer value);

    String get_most();

    Iterable<T> findAll();

    /**
     * Saves the given entity.
     *
     * @param entity
     *            must not be null.
     * @return an {@code Optional} - null if the entity was saved otherwise (e.g. id already exists) returns the entity.
     * @throws IllegalArgumentException
     *             if the given entity is null.
     * @throws ValidatorException
     *             if the entity is not valid.
     */
    Optional<T> save(T entity) throws ValidatorException, IOException, ParserConfigurationException;

    /**
     * Removes the entity with the given id.
     *
     * @param id
     *            must not be null.
     * @return an {@code Optional} - null if there is no entity with the given id, otherwise the removed entity.
     * @throws IllegalArgumentException
     *             if the given id is null.
     */
    Optional<T> delete(ID id) throws IOException, ParserConfigurationException;

    /**
     * Updates the given entity.
     *
     * @param entity
     *            must not be null.
     * @return an {@code Optional} - null if the entity was updated otherwise (e.g. id does not exist) returns the
     *         entity.
     * @throws IllegalArgumentException
     *             if the given entity is null.
     * @throws ValidatorException
     *             if the entity is not valid.
     */
    Optional<T> update(T entity) throws ValidatorException, IOException, ParserConfigurationException;

    void add_statistic(String title);


    public void truncateTables() throws SQLException;
    public void writeToDataBase() throws SQLException;
    public void comit();

    void flush();
}
