package ro.ubb.catalog.client.Controller;



import ro.ubb.catalog.core.model.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IController {
    /**
     * @param bookId
     * @param clientId
     * @return true if book was bought, false otherwise
     * @throws ValidatorException
     */
    public boolean Controller_buy_book(Long bookId, Long clientId) throws ValidatorException;

    /**
     *
     * @param id
     * @param where
     * @return true if id is found and false otherwhise
     * @throws ValidatorException
     */
    public boolean Controller_find_one(Long id, String where) throws ValidatorException;


    /**
     *
     * @return list of books
     */
    public List<Book> Controller_get_books();


    /**
     *
     * @return list of clients
     */
    public List<Client> Controller_get_clients();


    /**
     *
     * @param book
     * @return true if book was added, false otherwise
     * @throws ValidatorException
     */
    public boolean Controller_save_book(Book book) throws ValidatorException, FileNotFoundException, ParserConfigurationException;


    /**
     *
     * @param client
     * @return true if client was saved, false otherwise
     * @throws ValidatorException
     */
    public Client Controller_save_client(Client client) throws ValidatorException, FileNotFoundException, ParserConfigurationException;

    /**
     *
     * @param id
     * @return true if the book was deleted, false otherwise
     */
    public boolean Controller_delete_book(Long id) throws IOException, ParserConfigurationException;



    /**
     *
     * @param id
     * @return true if the client was deleted, false otheriwse
     */
    public boolean Controller_delete_client(Long id) throws FileNotFoundException, ParserConfigurationException;


    /**
     *
     * @param book
     * @return true if book was updated, false otherwise
     * @throws ValidatorException
     */
    public boolean Controller_update_book(Book book) throws ValidatorException, IOException, ParserConfigurationException;

    /**
     *
     * @param client
     * @return true if client was updated, false otherwise
     * @throws ValidatorException
     */
    public Client Controller_update_client(Client client) throws ValidatorException, IOException, ParserConfigurationException;


    /**
     *
     * @param name
     * @return a list of clients filtered by the parameter
     */
    public ArrayList<Client> Controller_filter_by_name(String name);

}
