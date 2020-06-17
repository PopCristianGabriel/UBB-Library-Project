package ro.ubb.catalog.core.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import ro.ubb.catalog.core.model.LinkEntity;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.ArrayList;
@NoArgsConstructor
@AllArgsConstructor
@Repository
public class LinkEntityRepository extends JdbcDaoSupport implements ILinkEntityRepository, Serializable {
    ArrayList<LinkEntity> entities;
    @Autowired
    private EntityManager entityManager;

    @Autowired
    public void setDs(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public LinkEntityRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    public void add_entity(LinkEntity entity) {
        this.entities.add(entity);
    }

    @Override
    public ArrayList<LinkEntity> get_entities() {
        return this.entities;
    }

    @Override
    public void remove_entity(LinkEntity entity) {
        for(LinkEntity ent : this.entities){
            if(ent.getBook().equals(entity.getBook()) && ent.getClient().equals(entity.getClient())){
                this.entities.remove(ent);
            }
        }
    }


}
