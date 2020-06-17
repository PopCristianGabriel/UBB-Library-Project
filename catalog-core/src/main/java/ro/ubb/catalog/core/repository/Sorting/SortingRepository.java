package ro.ubb.catalog.core.repository.Sorting;

import ro.ubb.catalog.core.model.*;
import ro.ubb.catalog.core.model.Entity;
import ro.ubb.catalog.core.repository.Repository;

import java.io.Serializable;
import java.sql.SQLException;

public interface SortingRepository<ID extends Serializable,T extends Entity<ID>> extends Repository<ID,T> {

    Iterable<T> findAll(Sort sort);
    Iterable<T> findAll();
    public void add_statistic(String key,Integer value);
    public void add_statistic(String key);
    public String get_most();
    void commit();
    public void writeToDataBase() throws SQLException;
}
