package ro.ubb.catalog.core.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Entity<ID> implements Serializable {
    @Id
    private ID id;



    public boolean is_the_same_id(ID id){


        return this.id.equals(id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }



    public void setEntity(Entity<ID> e){
        this.id=e.getId();
    }


    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }
}
