package ro.ubb.catalog.core.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import ro.ubb.catalog.core.model.Entity;
import ro.ubb.catalog.core.repository.Sorting.Sort;
import ro.ubb.catalog.core.repository.Sorting.SortingRepository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.sql.DataSource;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.lang.IllegalArgumentException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
@NoArgsConstructor
@AllArgsConstructor
@Repository
public class DBrepository<ID extends Serializable,T extends Entity<ID>> extends JdbcDaoSupport implements SortingRepository<ID,T>, Serializable{
    protected ArrayList<T> entities;
    Class<T> type;
    protected HashMap<String,Integer> Statistics = new HashMap();


    @Autowired
    private EntityManager entityManager;

    @Autowired
    public void setDs(DataSource dataSource) {
        setDataSource(dataSource);
    }


    public DBrepository(Class<T> type,EntityManager entityManager ) throws SQLException, ParserConfigurationException, IOException, ValidatorException {
        entities=new ArrayList<>();
        this.type=type;
        this.entityManager = entityManager;
        this.readFromDataBase();
    }

    @Override
    public void add_statistic(String key,Integer value){
        if(this.Statistics.containsKey(key)){
            this.Statistics.put(key,this.Statistics.get(key)+value);
        }
        else{
            this.Statistics.put(key,value);
        }
    }
    @Override
    public void add_statistic(String key){
        if(this.Statistics.containsKey(key)){
            this.Statistics.put(key,this.Statistics.get(key)+1);
        }
        else{
            this.Statistics.put(key,1);
        }
    }
    public String get_most(){
        String key = null;
        int most = 0;

        Set<String> keys = this.Statistics.keySet();
        for (String k : keys){
            if(this.Statistics.get(k) > most){
                most = this.Statistics.get(k);
                key = k;
            }
        }
        return key;
    }

    @Transactional
    public void readFromDataBase() throws SQLException, ParserConfigurationException, ValidatorException, IOException {
        if(this.type.equals(Book.class)){

            Query query = this.entityManager.createQuery("SELECT a FROM Book a");
            ArrayList<Book> books = (ArrayList<Book>) query.getResultList();
            for(Book book : books){

                    this.save((T)book);

            }

        }

        if(this.type.equals(Client.class)){

            Query query = this.entityManager.createQuery("SELECT a FROM Client a ");
            ArrayList<Client> clients = (ArrayList<Client>) query.getResultList();
            for(Client client : clients){
                if(client.booksBought == null){
                    client.booksBought = new ArrayList<>();
                }
                this.save((T)client);
            }
        }



    }

    @Override
    public void commit(){
        this.entityManager.getTransaction().commit();
    }



    @Override
    @Transactional
    public void truncateTables() throws SQLException {




        boolean d = this.entityManager.isOpen();


            String hql2 = String.format("delete Client where id > 0");
            Query query2 = this.entityManager.createQuery(hql2);
            int ok2 = query2.executeUpdate();




            String hql = String.format("delete Book where id > 0");
            Query query = this.entityManager.createQuery(hql);
            int ok = query.executeUpdate();









    }

    private static Object deepCopy(Object object) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
            outputStrm.writeObject(object);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
            return objInputStream.readObject();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    @Transactional
    public void writeToDataBase() throws SQLException {


     boolean d = this.entityManager.getTransaction().isActive();
     this.entityManager.getTransaction().begin();
        for (Entity<ID> entity : this.entities){
            if(this.type.equals(Book.class)) {
                Book b = (Book)entity;

                this.entityManager.persist(b);
            }
            else{
                this.entityManager.persist((Client)entity);
            }
        }
    this.entityManager.getTransaction().commit();



    }

    @Override
    public void comit() {
        this.entityManager.getTransaction().commit();
    }

    @Override
    public void flush() {
        this.entityManager.clear();
    }


    @Override
    public Iterable<T> findAll(Sort sort) {
        this.entities.sort(sort);
        return this.entities;

    }

    @Override
    public Optional<T> findOne(ID id) throws ValidatorException {
        if(id == null){
            throw new ValidatorException("invalid id");
        }
        Optional<T> b1=null;


        try {
            b1 = Optional.ofNullable(this.entities.stream().filter(b -> b.getId() == id).collect(toSingleton()));
        }catch(IllegalStateException e){

        }
       if(b1==null){
            return null;
        }
        return b1;

        //TODO throw ex
    }

    @Override
    public Entity get_entity_by_id(ID id) {
        for(Entity e:this.entities){
            if(e.getId().equals(id))
                return e;

        }
        return null;
    }




    public static <T> Collector<T, ?, T> toSingleton() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() != 1) {
                        throw new IllegalStateException();
                    }
                    return list.get(0);
                }
        );
    }

    @Override
    public Iterable<T> findAll() {
        return this.entities;
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException, IOException, ParserConfigurationException {
        if(entity == null){
            throw new IllegalArgumentException("null argument");
        }


        boolean found=this.entities.stream().anyMatch(entity1->entity1.equals(entity));

        if(found == false) {
            this.entities.add(entity);
            return Optional.empty();
        }
        else{
            Optional<T> E = Optional.ofNullable(entity);
            return E;
        }
    }

    @Override
    public Optional<T> delete(ID id) throws IOException, ParserConfigurationException {
        if(id == null){
            throw new IllegalArgumentException("null id");
        }

        for(int i = 0 ; i < this.entities.size(); i++){
            if(this.entities.get(i).is_the_same_id(id)){
                this.entities.remove(i);
                break;
            }
        }
        return Optional.empty();

        /*Optional<T> entityAux = null;
        try {
             entityAux = Optional.ofNullable(this.entities.stream().filter(entity1 -> entity1.is_the_same_id(id)).collect(toSingleton()));
        }catch(IllegalStateException e){
            Optional<T> nush = null;
            return nush;
        }
        this.entities= (ArrayList<T>) this.entities.stream().filter(entity1->entity1.getId()!=id).collect(Collectors.toList());
        return entityAux;*/
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException, IOException, ParserConfigurationException {
        if(entity == null){
            throw new IllegalArgumentException("null entity");
        }

        /*this.entities.stream().filter(entity1->entity1.equals(entity)).forEach(e->
        {
            e.setEntity(entity);

        }
        );*/
        this.entities.stream().filter(entity1->entity1.equals(entity)).forEach(e->e.setEntity(entity));
        Optional<T> Entity = Optional.ofNullable(entity);
        return Entity;
    }


    public int getSize(){
        return this.entities.size();
    }


}
