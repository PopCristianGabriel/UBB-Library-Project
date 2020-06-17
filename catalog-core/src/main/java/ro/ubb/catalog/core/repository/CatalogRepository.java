package ro.ubb.catalog.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ro.ubb.catalog.core.model.BaseEntity;
import ro.ubb.catalog.core.model.Entity;

import java.io.Serializable;

/**
 * Created by radu.
 */
@NoRepositoryBean
public interface CatalogRepository<T extends Entity<ID>, ID extends Serializable>
        extends JpaRepository<T, ID> {
}
