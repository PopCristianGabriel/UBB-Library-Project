package ro.ubb.catalog.client.Controller;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.repository.NoRepositoryBean;
import ro.ubb.catalog.core.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.ubb.catalog.core.repository.DBrepository;
import ro.ubb.catalog.core.repository.Repository;

import javax.persistence.EntityManager;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.IllegalArgumentException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.stream.Collectors;
import ro.ubb.catalog.core.repository.Sorting.*;

@AllArgsConstructor
@NoRepositoryBean
public class Controller implements IController {

    private Repository<Long,Book> bookRepository;

    private Repository<Long, Client> clientRepository;

    private  static final Logger log= LoggerFactory.getLogger(Controller.class);



    public Controller(DBrepository<Long, Book> books, DBrepository<Long, Client> clients) {
        this.bookRepository=books;
        this.clientRepository=clients;

        Handler handlerObj = new ConsoleHandler();
        handlerObj.setLevel(Level.ALL);

    }
    public Controller() throws SQLException, ValidatorException, ParserConfigurationException, IOException {
        try {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        "ro.ubb.catalog.core.config"
                );

            EntityManager EntityManager = (EntityManager) context.getBean("entityManager");
            EntityManager em = EntityManager;
            this.bookRepository = new DBrepository<Long, Book>(Book.class, em);
            this.clientRepository = new DBrepository<Long, Client>(Client.class, em);
        }catch (SQLException e){
            System.out.println("bazinga");
        }
        catch (ValidatorException e){
            System.out.println("bazinga");
        }catch (ParserConfigurationException e){
            System.out.println("bazinga");
        }catch (IOException e){
            System.out.println("bazinga");
        }

    }


    public boolean Controller_buy_book(Long bookId, Long clientId) throws ValidatorException {
        log.info("The method buy book was called ");
        if(bookId < 0){
            log.info("The method threw an error ");
            throw new ValidatorException("invalid id");
        }
        if(clientId < 0){
            log.info("The methd threw an error ");
            throw new ValidatorException("invalid id");
        }
        List<Book> bookList = this.Controller_get_books();
        List<Client> clientList = this.Controller_get_clients();
        Book bookToBuy = null;
        Client client = null;
        int bookIndex = 0;
        int clientIndex = 0;
        for(Book b : bookList) {
            if (b.getId() == bookId) {
                bookToBuy = b;
                bookIndex = bookList.indexOf(b);
                break;
            }
        }
        for(Client c: clientList) {
            if (c.getId() == clientId) {
                client = c;
                clientIndex = clientList.indexOf(c);
                break;
            }
        }
        if(client == null || bookToBuy == null){
            log.info("The method threw an error ");
            throw new IllegalArgumentException("invalid ids");
        }

        clientList.get(clientIndex).add_money_spent(bookList.get(bookIndex).getPrice());
        clientList.get(clientIndex).add_book(bookList.get(bookIndex));
        this.clientRepository.add_statistic( clientList.get(clientIndex).getFullName(),1);
        this.bookRepository.add_statistic(bookList.get(bookIndex).getTitle());
        log.info("The book has been bought");
        return true;


    }


    public boolean Controller_find_one(Long id, String where) throws ValidatorException {
        log.info("The method find one was called ");
        if(where == "book") {
            try{
                this.bookRepository.findOne(id);
                log.info("The method ended normally ");
                return true;
            } catch (Exception | ValidatorException e) {
                log.info("The method threw an exception ");
                throw e;

            }
        }

        else if(where == "client"){
            try{
                this.clientRepository.findOne(id);
                log.info("The method ended normally ");
                return true;
            } catch (Exception | ValidatorException e) {
                log.info("The method threw an exception ");
                throw e;
            }
        }
        return true;
    }

    public List<Book> Controller_get_books(){
        boolean q = log.isDebugEnabled();
        boolean w = log.isInfoEnabled();
       log.info("Method get books was called");


        List<Book> bookList = (List<Book>) this.bookRepository.findAll();
        log.info("the method ended normally, the books are ={}",bookList);
        return (List<Book>) this.bookRepository.findAll();
    }

    public List<Client> Controller_get_clients(){
        log.info("Method get clients was called");
        List<Client> list = (List<Client>) this.clientRepository.findAll();
        log.info("The method ended normally,the clients are ={}",list );
        return (List<Client>) this.clientRepository.findAll();
    }

    public ArrayList<Book> ControllerGetBooksSorted(Sort sort){
        log.info("Method books sort was called");
        ArrayList<Book> booksSorted = (ArrayList<Book>) ((DBrepository<Long, Book>) this.bookRepository).findAll(sort);
        log.info("The the method ended normally, the books sorted are={}",booksSorted);

        return booksSorted;
    }

    public ArrayList<Client> ControllerGetClientsSorted(Sort sort){
        log.info("Method clients sort was called");
        ArrayList<Client> clientsSorted = (ArrayList<Client>) ((DBrepository<Long, Client>) this.clientRepository).findAll(sort);
        log.info("The method ended normally , the clients are ={}",clientsSorted);
        return clientsSorted;

    }

    public boolean Controller_save_book(Book book) throws ValidatorException, FileNotFoundException, ParserConfigurationException{
        try{
            log.info("Method save book was called ");
            this.bookRepository.save(book);
        } catch (ValidatorException | FileNotFoundException | ParserConfigurationException  e) {
            log.info("The method threw an error ");
            throw e;

        } catch (IOException e) {
            log.info("The method threw an error ");
            e.printStackTrace();
        }
        log.info("The book ={} was saved ",book);
        return true;
    }

    public Client Controller_save_client(Client client) throws ValidatorException, FileNotFoundException, ParserConfigurationException{
        try{
            log.info("The method save client was called ");
            this.clientRepository.save(client);
        } catch (ValidatorException | FileNotFoundException | ParserConfigurationException e) {
            log.info("The method threw an error ");
            throw e;

        } catch (IOException e) {
            log.info("The method threw an error ");
            e.printStackTrace();
        }
        log.info("The client ={} was saved",client);
        return client;
    }

    public boolean Controller_delete_book(Long id) throws IOException, ParserConfigurationException {
        try{
            log.info("The method delete book was called ");
            this.bookRepository.delete(id);
        } catch (Exception e) {
            log.info("The method threw an error ");
            throw e;
        }
        log.info("The book with id={} has been deleted",id);
        return true;
    }



    public boolean Controller_delete_client(Long id) throws FileNotFoundException, ParserConfigurationException {
        try{
            log.info("The method delete client was called ");
            Client client = (Client) this.clientRepository.get_entity_by_id(id);
            if(client != null) {
                ArrayList<Book> books = client.get_books_bought();
                for (Book b : books){
                    this.bookRepository.save(b);
                }
            }
            this.clientRepository.delete(id);

        } catch (IllegalArgumentException | FileNotFoundException | ParserConfigurationException  e) {
            log.info("The method threw an error ");
            throw e;
        } catch (IOException e) {
            log.info("The method threw an error ");
            e.printStackTrace();
        } catch (ValidatorException e) {
            log.info("The method threw an error ");
            e.printStackTrace();
        }
        log.info("The client with de id={} was deleted",id);
        return true;
    }


    public boolean Controller_update_book(Book book) throws ValidatorException, IOException, ParserConfigurationException {
        try{
            log.info("The method update book was called ");
            this.bookRepository.update(book);
        } catch (Exception | ValidatorException e) {
            log.info("The method threw an error ");
            throw e;
        }
        log.info("The book ={} was updated",book.getid());
        return true;
    }

    public Client Controller_update_client(Client client) throws ValidatorException, IOException, ParserConfigurationException {
        try{
            log.info("The method update client was called ");
            this.clientRepository.update(client);
        } catch (Exception | ValidatorException e) {
            log.info("The method threw an error ");
            throw e;
        }
        log.info("The client ={} was updated",client.getid());
        return client;
    }

    public ArrayList<Client> Controller_filter_by_name(String name){
        log.info("The method  filtre by name was called");
        ArrayList<Client> clientListFiltered = new ArrayList<Client>();

        /*for(Client c : clientList){
            if(c.getFullName().contains(name)){
                clientListFiltered.add(c);
            }
        }*/
        clientListFiltered= (ArrayList<Client>) this.Controller_get_clients().stream().filter(c->c.getFullName().contains(name)).collect(Collectors.toList());
        log.info("The method ended normally, the students are ={}",clientListFiltered);
        return clientListFiltered;



    }

    public Long get_nr_of_books() {

        ArrayList<Book> books = (ArrayList<Book>)this.bookRepository.findAll();
        ArrayList<Client> clients = (ArrayList<Client>) this.clientRepository.findAll();
        Long max = null;
        for (Book b : books){
            if (b.getId() > max){
                max = b.getId();
            }
        }
        for(Client c : clients){
            ArrayList<Book> clientsBooks = c.get_books_bought();
            for(Book b : clientsBooks){
                if(b.getId() > max){
                    max = b.getId();
                }
            }
        }
        return max+1;
    }

    public Long get_nr_of_clients(){
        ArrayList<Client> clients = (ArrayList<Client>) this.clientRepository.findAll();
        Long max = null;
        for(Client c : clients){
            if(c.getid() > max){
                max = c.getid();
            }
        }
        return max+1;
    }

    public void set_rented_books_in_statistics() {
        for(Client c : this.clientRepository.findAll()){
            for (Book b : c.get_books_bought()){
                this.bookRepository.add_statistic(b.getTitle());
            }
        }
    }

    public void set_most_active_clients_in_statistics() {
        for(Client c: this.clientRepository.findAll()){
            this.clientRepository.add_statistic(c.getFullName(),c.get_books_bought().size());
        }
    }

    public String get_most_active_client() {
        return this.clientRepository.get_most();
    }

    public String get_most_rented_book(){
        return this.bookRepository.get_most();
    }



    public void write_to_data_base() throws SQLException {
        this.bookRepository.truncateTables();
        this.bookRepository.comit();
        this.bookRepository.flush();
        this.bookRepository.writeToDataBase();
        this.clientRepository.writeToDataBase();
     //   this.bookRepository.comit();
    }


}
