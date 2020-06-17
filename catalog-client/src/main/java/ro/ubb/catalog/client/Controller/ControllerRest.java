package ro.ubb.catalog.client.Controller;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.client.RestTemplate;
import ro.ubb.catalog.core.model.*;
import ro.ubb.catalog.core.repository.Sorting.Sort;

import ro.ubb.catalog.web.converter.*;
import ro.ubb.catalog.web.dto.*;



import java.util.ArrayList;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
public class ControllerRest {
    private RestTemplate restTemplate;
    private String urlBooks;
    private String urlClients;

    @Autowired
    private BookConverter bookConverter;
    @Autowired
    private ClientConverter clientConverter;

    public ControllerRest(RestTemplate restTemplate, String urlbooks, String urlclients) {
        this.restTemplate = restTemplate;
                this.urlBooks = urlbooks;
                this.urlClients = urlclients;
    }


    public void Controller_buy_book(Long idClient, Long idBook){
        Book book = this.restTemplate.postForObject(
                urlBooks+"/getById",
                idBook,
                Book.class
        );
        Client client = this.restTemplate.postForObject(
                urlClients+"/getById",
                idClient,
                Client.class
        );
        if(book == null || client == null){
            return;
        }
        else{
            BuyBookBody request = new BuyBookBody(idClient,book);
            this.restTemplate.postForObject(this.urlClients+"/addBookToClient",
                    request,
                    void.class);
            this.restTemplate.postForObject(this.urlBooks+"/deleteBook",book.getid(),Book.class);
            this.restTemplate.postForObject(this.urlBooks+"/addStatistics1Param",
                    book.getTitle(),
                    Void.class
                    );
            this.restTemplate.postForObject(this.urlClients+"/addStatistics2Param",
                    new ClientStatisticsModel(client.getFullName(),1),
                    Void.class
                    );
        }

    }

    public void Controller_save_book(Book book){

        this.restTemplate.postForObject(this.urlBooks+"/saveBook",
                this.bookConverter.convertModelToDto(book),
                Book.class);
    }

    public void Controller_write_to_database(){
        this.restTemplate.postForObject(
                this.urlBooks+"/truncate",
                null,
                Void.class
        );
        this.restTemplate.postForObject(
                this.urlBooks+"/commit",
                null,
                Void.class
        );
        this.restTemplate.postForObject(this.urlBooks+"/flush",
                null,
                null);
        this.restTemplate.postForObject(
                this.urlBooks+"/writeToDataBase",
                null,
                Void.class
        );
        this.restTemplate.postForObject(
                this.urlClients+"/writeToDataBase",
                null,
                Void.class
        );
    }


    public void Controller_save_client(Client client){
        this.restTemplate.postForObject(this.urlClients+"/saveClient",
                this.clientConverter.convertModelToDto(client),
                Client.class);
    }

    public boolean Controller_find_one_client(Long id){
        return this.restTemplate.postForObject(
                this.urlClients+"/findOne",
                id,
                boolean.class
        );
    }

    public boolean Controller_find_one_book(Long id){
        return this.restTemplate.postForObject(
                this.urlBooks+"/findOne",
                id,
                boolean.class
        );
    }

    public ArrayList<Book> Controller_get_books(){
        BooksDto books =  this.restTemplate.getForObject(
                this.urlBooks+"/get",
                BooksDto.class
        );
        ArrayList<Book> realBooks = books.getBooks().stream().map(e->this.bookConverter.convertDtoToModel(e)).collect(Collectors
                .toCollection(ArrayList::new));
        return realBooks;
    }

    public ArrayList<Client> Controller_get_clients(){
        ArrayList<Client> clients = this.restTemplate.getForObject(
                this.urlClients+"/get",
                ClientsDto.class
        ).getClients().stream().map(e->this.clientConverter.convertDtoToModel(e)).collect(Collectors
                .toCollection(ArrayList::new));
        return clients;
    }


    public void Controller_delete_book(Long id){
        this.restTemplate.postForObject(
                this.urlBooks+"/deleteBook",
                id,
                Book.class
        );
    }
    public void Controller_delete_client(Long id){
        Client client = this.restTemplate.postForObject(this.urlClients+"/deleteClient",
                id,
                Client.class
                );

        for(Book book : client.get_books_bought()){
            this.restTemplate.postForObject(this.urlBooks+"/saveBook",
                    this.bookConverter.convertModelToDto(book),
                    Book.class
                    );
        }

        this.restTemplate.postForObject(
                this.urlClients+"/deleteClient",
                id,
                Book.class
        );
    }

        public void Controller_update_book(Book book){
            this.restTemplate.postForObject(this.urlBooks+"/updateBook",
                    this.bookConverter.convertModelToDto(book),
                    Book.class
                    );
        }

        public void Controller_update_client(Client client){
        this.restTemplate.postForObject(this.urlClients+"/updateClient",
                this.clientConverter.convertModelToDto(client),
                Client.class);
        };

    public ArrayList<Book> Controller_get_books_sorted(Sort sort){
        return this.restTemplate.postForObject(this.urlBooks+"/sorted",
                sort,
                ListObject.class

        ).getEntities();

    }

    public ArrayList<Client> Controller_get_clients_filtered(String name){
        return (ArrayList<Client>) this.Controller_get_clients().stream().filter(c->c.getFullName().contains(name)).collect(Collectors.toList());
    }

    public Long get_nr_of_books() {

        ArrayList<Book> books = (ArrayList<Book>)this.Controller_get_books();
        ArrayList<Client> clients = (ArrayList<Client>) this.Controller_get_clients();
        Long max = new Long(1);
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
        ArrayList<Client> clients = (ArrayList<Client>) this.Controller_get_clients();
        Long max = new Long(1);
        for(Client c : clients){
            if(c.getid() > max){
                max = c.getid();
            }
        }
        return max+1;
    }
    public void set_rented_books_in_statistics() {
        for(Client c : this.Controller_get_clients()){
            for (Book b : c.get_books_bought()){
                this.restTemplate.postForObject(
                        this.urlBooks+"/addStatistics1Param",
                        b.getTitle(),
                        Void.class
                );
            }
        }
    }

    public void set_most_active_clients_in_statistics() {
        for(Client c: this.Controller_get_clients()){
            this.restTemplate.postForObject(
                    this.urlClients+"/addStatistics2Param",
                    new ClientStatisticsModel(c.getFullName(),c.get_books_bought().size()),
                    Void.class
            );

        }
    }

    public String Controller_get_most_active_client(){
        return this.restTemplate.postForObject(
                this.urlClients+"/getMost",
                null,
                String.class
        );
    }

    public String Controller_get_most_purchased_book(){
        return this.restTemplate.postForObject(
            this.urlBooks+"/getMost",
            null,
            String.class);
    }

    public ArrayList<Client> Controller_get_clients_sorted(Sort sort) {
        ClientsDto clients =  this.restTemplate.postForObject(this.urlClients+"/sortedClients",
                sort,
                ClientsDto.class
                );
        return clients.getClients().stream().map(e->this.clientConverter.convertDtoToModel(e)).collect(Collectors
                .toCollection(ArrayList::new));

    }

    public void write_to_data_base() {
        this.restTemplate.postForObject(this.urlClients+"/writeToDataBase",null,Void.class);
        this.restTemplate.postForObject(this.urlBooks+"/writeToDataBase",null,Void.class);
    }
}
